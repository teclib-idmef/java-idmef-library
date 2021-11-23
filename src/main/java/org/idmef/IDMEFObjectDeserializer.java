package org.idmef;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class IDMEFObjectDeserializer extends StdDeserializer<IDMEFObject> {

    public IDMEFObjectDeserializer() {
        this(null);
    }

    public IDMEFObjectDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public IDMEFObject deserialize(JsonParser jp, DeserializationContext context) throws IOException, JsonProcessingException {
        JsonNode jsonNode = jp.getCodec().readTree(jp);

        if (!jsonNode.isObject()) {
            throw new IDMEFException("Invalid JsonNode type: " + jsonNode.getClass().getName());
        }

        return new IDMEFObject(jsonNode);
    }
}
