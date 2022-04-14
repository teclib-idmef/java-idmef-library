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

/**
 * IDMEF validator class.
 *
 * This class can validate a IDMEF object or an array of bytes containing JSON data.
 *
 * Validation is done against version 2 revision 0.3 of the IDMEF schema.
 *
 */
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
     * @param idmefObject a IDMEF object instance
     *
     * @return true is json contains a valid IDMEF object according to schema, false if not
     *
     * @throws IDMEFException if an exception occured during validation (schema cannot be loaded)
     */
    public boolean validate(IDMEFObject idmefObject) throws IDMEFException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.valueToTree(idmefObject);

        return validateJsonNode(jsonNode);
    }

    /**
     * Validate the bytes w.r.t. current IDMEF JSON schema.
     *
     * @param json a byte array containing the JSON data
     *
     * @return true is json contains a valid IDMEF object according to schema, false if not
     *
     * @throws IDMEFException if an exception occured during validation (schema cannot be loaded)
     */
    public boolean validate(byte[] json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(json);

        return validateJsonNode(jsonNode);
    }
}
