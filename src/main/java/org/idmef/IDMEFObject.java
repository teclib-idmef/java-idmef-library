package org.idmef;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

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
    public IDMEFObject(IDMEFObject parent, String propertyName)
    {
        properties = new LinkedHashMap<>();

        if (parent != null)
            parent.put(propertyName, this);
    }

    /**
     * Construct an empty IDMEFObject.
     */
    public IDMEFObject()
    {
        this(null, "");
    }

    /**
     * Construct an IDMEFObject from a Map.
     *
     * @param map the Map
     */
    IDMEFObject(Map<String, Object> map) {
        this();

        properties.putAll(map);
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

    private void putFields(JsonNode node) throws IDMEFException {
        Iterator<Map.Entry<String, JsonNode>> fields = node.fields();

        while (fields.hasNext()) {
            Map.Entry<String, JsonNode> field = fields.next();
            String key = field.getKey();
            JsonNode value = field.getValue();

            put(key, convertField(value));
        }
    }

    IDMEFObject(JsonNode node) throws IDMEFException {
        this();

        putFields(node);
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
        return properties.equals(obj);
    }
}
