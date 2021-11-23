package org.idmef;

import net.jimblackler.jsonschemafriend.*;

import java.net.URL;

public class IDMEFValidator {
    private static final String SCHEMA_RESOURCE_PATH = "/IDMEFv2.schema";

    /**
     * Constructs an empty Message.
     */
    public IDMEFValidator() { }

    /**
     * Validate the Message content w.r.t. current IDMEF JSON schema.
     *
     * @throws IDMEFException if the Message is not valid.
     */
    public void validate(IDMEFObject idmefObject) throws IDMEFException {
        URL r = IDMEFValidator.class.getResource(SCHEMA_RESOURCE_PATH);
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
            validator.validate(schema, idmefObject);
        } catch (ValidationException e) {
            throw new IDMEFException("error validating:" + e.getMessage());
        }
    }

}
