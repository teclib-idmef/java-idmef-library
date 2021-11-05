package org.idmef;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.jimblackler.jsonschemafriend.*;

import java.io.IOException;
import java.net.URL;
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
public class Message extends IDMEFObject {
    private static final String SCHEMA_RESOURCE_PATH = "/IDMEFv2.schema";

    /**
     * Constructs an empty Message.
     */
    public Message() {
    }

    /**
     * Construct a Message from a map.
     *
     * @param map the map
     */
    public Message(Map<String, Object> map) {
        super(map);
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
            validator.validate(schema, this);
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
    public byte[] serialize() throws IDMEFException, IOException {
        validate();

        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.writeValueAsBytes(this);
    }

    /**
     * Deserialize JSON bytes to a Message
     *
     * @param json the JSON bytes
     * @return a Message object with content filled from JSON
     */
    public static Message unserialize(byte[] json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Object> map = objectMapper.readValue(json, new TypeReference<Map<String,Object>>(){});

        return new Message(map);
    }
}
