package org.idmef;

import java.util.Map;

/**
 * IDMEF Target object.
 */
public class Target extends IDMEFObject {

    /**
     * Constructs an empty Target.
     */
    public Target() {
    }

    /**
     * Construct a Target from a map.
     *
     * @param map the map
     */
    public Target(Map<String, Object> map) {
        super(map);
    }
}
