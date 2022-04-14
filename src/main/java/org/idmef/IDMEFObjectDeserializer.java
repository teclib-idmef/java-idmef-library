package org.idmef;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

/**
 * Class for IDMEFObject deserialization.
 *
 * Class inherits from com.fasterxml.jackson.databind.deser.std.StdDeserializer and overrides deserialize method.
 *
 * @see com.fasterxml.jackson.databind.deser.std.StdDeserializer
 *
 */
public class IDMEFObjectDeserializer extends StdDeserializer<IDMEFObject> {

    /**
     * Construct a IDMEFObjectDeserializer instance
     */
    public IDMEFObjectDeserializer() {
        this(null);
    }

    /**
     * Construct a IDMEFObjectDeserializer instance
     *
     * @param vc the class to which to deserialize
     */
    public IDMEFObjectDeserializer(Class<?> vc) {
        super(vc);
    }

    /**
     * Deserialize JSON content to a IDMEFObject
     *
     * @param jp      Parsed used for reading JSON content
     * @param context Context that can be used to access information about
     *                this deserialization activity.
     * @return deserialized IDMEFObject
     * @throws IOException if an exception occurred during JSON content reading
     * @throws JsonProcessingException if an exception occurred when processing (parsing, generating) JSON content that are not pure I/O problems.
     */
    @Override
    public IDMEFObject deserialize(JsonParser jp, DeserializationContext context) throws IOException, JsonProcessingException {
        JsonNode jsonNode = jp.getCodec().readTree(jp);

        if (!jsonNode.isObject()) {
            throw new IDMEFException("Invalid JsonNode type: " + jsonNode.getClass().getName());
        }

        return new IDMEFObject(jsonNode);
    }
}
