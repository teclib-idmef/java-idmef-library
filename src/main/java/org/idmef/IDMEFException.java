package org.idmef;

import java.io.IOException;

/**
 * IDMEF exception class.
 *
 * IDMEF exceptions are thrown when validating, serializing an object or deserializing bytes.
 *
 */
public class IDMEFException extends IOException {
    /**
     * Construct a IDMEFException
     *
     * @param message the message contained in the exception
     */
    public IDMEFException(String message) {
        super(message);
    }
}
