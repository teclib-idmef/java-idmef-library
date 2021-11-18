package org.idmef;

import java.util.Map;

/**
 * IDMEF Vector object.
 */
public class Vector extends IDMEFObject {

    /**
     * Constructs an empty Vector.
     */
    public Vector() {
    }

    /**
     * Construct a Vector from a map.
     *
     * @param map the map
     */
    public Vector(Map<String, Object> map) {
        super(map);
    }
}
