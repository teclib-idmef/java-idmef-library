package org.idmef;

import java.util.Map;

/**
 * IDMEF Attachment object.
 */
public class Attachment extends IDMEFObject {

    /**
     * Constructs an empty Attachment.
     */
    public Attachment() {
    }

    /**
     * Construct an Attachment from a map.
     *
     * @param map the map
     */
    public Attachment(Map<String, Object> map) {
        super(map);
    }
}
