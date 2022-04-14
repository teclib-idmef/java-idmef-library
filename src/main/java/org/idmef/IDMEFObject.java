package org.idmef;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

/**
 * IDMEF base object.
 *
 * <p>This implementation provides:</p>
 *
 * <ul>
 *     <li>property getting</li>
 *     <li>property setting: no check is done on property key, check is done by IDMEFValidator</li>
 *     <li>serialization of a IDMEFObject instance to JSON bytes</li>
 *     <li>deserialization if JSON bytes to a IDMEFObject instance</li>
 * </ul>.
 *
 */
@JsonDeserialize(using = IDMEFObjectDeserializer.class)
public class IDMEFObject {

    private LinkedHashMap<String, Object> properties;

    /**
     * Construct an empty IDMEFObject.
     */
    public IDMEFObject()
    {
        properties = new LinkedHashMap<>();
    }

    private static Object convertField(JsonNode value) throws IDMEFException {
        if (value.isInt())
            return value.asInt();
        else if (value.isTextual())
            return value.textValue();
        else if (value.isObject())
            return new IDMEFObject(value);
        else if (value.isArray()) {
            List<Object> l = new ArrayList<>();

            for(int i = 0; i < value.size(); i++)
                l.add(convertField(value.get(i)));

            return l;
        } else
            throw new IDMEFException("Unhandled node type: " + value.getClass().getName());
    }

    IDMEFObject(JsonNode node) throws IDMEFException {
        this();

        Iterator<Map.Entry<String, JsonNode>> fields = node.fields();

        while (fields.hasNext()) {
            Map.Entry<String, JsonNode> field = fields.next();

            put(field.getKey(), convertField(field.getValue()));
        }
    }

    /**
     * Get all properties of the object.
     *
     * @return a Map containing IDMEFObject properties
     */
    @JsonAnyGetter
    public Map<String, Object> getProperties() {
        return properties;
    }

    /**
     * Get a property by its key.
     *
     * @param key the property key
     * @return the value associated to the key or null
     */
    public Object get(String key) {
        return properties.get(key);
    }

    /**
     * Set a property of the Message. If value is an array, transform it to a List before setting the property
     *
     * @param key the property key
     * @param value the property value
     * @return the real value that was set
     */
    public Object put(String key, Object value) {
        Object adaptedValue = value;

        if (value.getClass().isArray()) {
            List<Object> l = new ArrayList<>();

            for(int i = 0; i < Array.getLength(value); i++)
                l.add(Array.get(value, i));

            adaptedValue = l;
        }

        properties.put(key, adaptedValue);

        return adaptedValue;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param obj the reference object with which to compare.
     * @return if this object is the same as the obj argument; false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (! (obj instanceof IDMEFObject))
            return false;

        IDMEFObject idmefObject = (IDMEFObject) obj;

        return properties.equals(idmefObject.properties);
    }

    /**
     * Serialize a Message to JSON bytes.
     *
     * @return the JSON bytes
     * @throws IDMEFException if an exception occured during serialization
     */
    public byte[] serialize() throws /* IDMEFException,*/ IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        return objectMapper.writeValueAsBytes(this);
    }

    /**
     * Deserialize JSON bytes to a IDMEFObject
     *
     * @param json the JSON bytes
     * @return a IDMEFObject object with content filled from JSON
     */
    public static IDMEFObject deserialize(byte[] json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(json, IDMEFObject.class);
    }

}
