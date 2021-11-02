package org.idmef;

import java.util.List;
import java.util.Map;

class BaseObject {
    private Map<String, Object> content;

    /**
     * Set a property of the Message.
     *
     * @param key the property key
     * @param value the property value
     */
    public void put(String key, Object value) {
        Object v = value;
        if (value.getClass().isArray())
            v = List.of(value);

        content.put(key, v);
    }

    /**
     * Get a property of the Message
     *
     * @param k the property key
     * @return the property value
     */
    public Object get(String k) {
        return content.get(k);
    }

    Map<String, Object> getContent() {
        return content;
    }
}
