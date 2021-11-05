package org.idmef;

import com.fasterxml.jackson.annotation.JsonAnyGetter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class IDMEFObject {

    protected Map<String, Object> content;

    IDMEFObject() {
        content = new HashMap<>();
    }

    @JsonAnyGetter
    public Map<String, Object> getProperties() {
        return content;
    }

    /**
     * Set a property of the Message. If value is an array, transform it to a List.
     *
     * @param key the property key
     * @param value the property value
     * @return the value that was set
     */
    public Object put(String key, Object value) {
        Object adaptedValue = value;

        if (value.getClass().isArray()) {
            List<Object> l = new ArrayList<>();

            for(int i = 0; i < Array.getLength(value); i++)
                l.add(Array.get(value, i));

            adaptedValue = l;
        }

        content.put(key, adaptedValue);

        return adaptedValue;
    }
}
