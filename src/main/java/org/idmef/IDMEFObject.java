package org.idmef;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

/*
    TODO:
    - transform a JsonNode to a Map
    - for embedded objects, lookup object class using property name (Analyzer -> org.idmef.Analyzer)
    - for array of objects, use the array property name to lookup object class
 */


/**
 * IDMEF base object.
 *
 * This implementation provides property setting/getting.
 *
 * Current implementation does not check property keys in put method. Property keys and values are checked
 * when calling validate() method.
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

    @JsonAnyGetter
    public Map<String, Object> getProperties() {
        return properties;
    }

    public Object get(String key) {
        return properties.get(key);
    }

    /**
     * Set a property of the Message. If value is an array, transform it to a List.
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
     * <b>Note: The method first validates the Message.</b>
     *
     * @return the JSON bytes
     * @throws IDMEFException if the Message is not valid
     */
    public byte[] serialize() throws /* IDMEFException,*/ IOException {
        //validate();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        return objectMapper.writeValueAsBytes(this);
    }

    /**
     * Deserialize JSON bytes to a Message
     *
     * @param json the JSON bytes
     * @return a Message object with content filled from JSON
     */
    public static IDMEFObject deserialize(byte[] json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(json, IDMEFObject.class);
    }

}
