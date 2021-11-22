package org.idmef;

import com.fasterxml.jackson.annotation.JsonAnyGetter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class IDMEFObject {

    private Map<String, Object> properties;

    /**
     * Construct an empty IDMEFObject.
     */
    public IDMEFObject(IDMEFObject parent, String propertyName)
    {
        properties = new HashMap<>();

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
        this.properties = map;
    }

    @JsonAnyGetter
    public Map<String, Object> getProperties() {
        return properties;
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
