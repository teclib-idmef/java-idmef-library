package org.idmef;

import java.util.HashMap;
import java.util.Map;

/**
 * IDMEF Message implementation.
 *
 * This implementation provides message property setting/getting, validation w.r.t. the IDMEF JSON schema
 * and serialization/deserialization to JSON.
 *
 * Current implementation does not check property keys in put method. Property keys and values are checked
 * when calling validate() method.
 */
public class Message {

    private Map<String, Object> content;

    /**
     * Constructs an empty Message.
     */
    public Message() {
        content = new HashMap<>();
    }

    /**
     * Set a property of the Message.
     *
     * @param k the property key
     * @param v the property value
     */
    public void put(String k, Object v) {
        content.put(k, v);
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

    /**
     * Validate the Message content w.r.t. current IDMEF JSON schema.
     *
     * @throws ValidationException if the Message is not valid.
     */
    public void validate() throws ValidationException {
        throw new ValidationException();
    }

    /**
     * Serialize a Message to JSON bytes.
     *
     * <b>Note: The method first validates the Message.</b>
     *
     * @return the JSON bytes
     * @throws ValidationException if the Message is not valid
     */
    public byte[] serialize() throws ValidationException {
        validate();

        return null;
    }

    /**
     * Deserialize JSON bytes to a Message
     *
     * @param b the JSON bytes
     * @return a Message object with content filled from JSON
     */
    public static Message unserialize(byte[] b) {
        return null;
    }
}
