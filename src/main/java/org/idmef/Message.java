package org.idmef;

import net.jimblackler.jsonschemafriend.*;

import java.net.URL;
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
    private static final String SCHEMA_RESOURCE_PATH = "/IDMEFv2.schema";

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
     * @throws IDMEFException if the Message is not valid.
     */
    public void validate() throws IDMEFException {
        URL r = Message.class.getResource(SCHEMA_RESOURCE_PATH);
        if (r == null)
            throw new IDMEFException("Cannot locate schema resource");

        Schema schema;

        SchemaStore schemaStore = new SchemaStore();
        try {
            schema = schemaStore.loadSchema(r);
        } catch (GenerationException e) {
            throw new IDMEFException("error loading schema:" + e.getMessage());
        }

        Validator validator = new Validator();

        try {
            validator.validate(schema, content);
        } catch (ValidationException e) {
            throw new IDMEFException("error validating:" + e.getMessage());
        }
    }

    /**
     * Serialize a Message to JSON bytes.
     *
     * <b>Note: The method first validates the Message.</b>
     *
     * @return the JSON bytes
     * @throws IDMEFException if the Message is not valid
     */
    public byte[] serialize() throws IDMEFException {
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
