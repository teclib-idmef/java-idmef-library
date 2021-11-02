package org.idmef;

import java.util.HashMap;
import java.util.Map;

public class Message {

    private Map<String, Object> content;

    public Message() {
        content = new HashMap<>();
    }

    public void put(String k, Object v) {

    }

    public Object get(String k) {
        return null;
    }

    public void validate() throws ValidationException {
        throw new ValidationException();
    }

    public byte[] serialize() {
        return null;
    }

    public static Message unserialize(byte[] b) {
        return null;
    }
}
