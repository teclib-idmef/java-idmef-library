package org.idmef;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;

import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

public class IDMEFValidator {
    private static final String SCHEMA_RESOURCE_PATH = "/IDMEFv2.schema";

    /**
     * Constructs a Validator.
     */
    public IDMEFValidator() {
    }

    private boolean validateJsonNode(JsonNode jsonNode) throws IDMEFException {
        JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);
        InputStream is = IDMEFValidator.class.getResourceAsStream(SCHEMA_RESOURCE_PATH);
        if (is == null)
            throw new IDMEFException("cannot load schema");

        JsonSchema schema = factory.getSchema(is);

        Set<ValidationMessage> errors = schema.validate(jsonNode);

        return errors.size() == 0;
    }

    /**
     * Validate the object content w.r.t. current IDMEF JSON schema.
     *
     */
    public boolean validate(IDMEFObject idmefObject) throws IDMEFException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.valueToTree(idmefObject);

        return validateJsonNode(jsonNode);
    }

    /**
     * Validate the bytes w.r.t. current IDMEF JSON schema.
     *
     */
    public boolean validate(byte[] json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(json);

        return validateJsonNode(jsonNode);
    }
}
