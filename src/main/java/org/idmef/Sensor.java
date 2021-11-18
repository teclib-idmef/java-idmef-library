package org.idmef;

import java.util.Map;

/**
 * IDMEF Sensor object.
 */
public class Sensor extends IDMEFObject {

    /**
     * Constructs an empty Sensor.
     */
    public Sensor() {
    }

    /**
     * Construct a Sensor from a map.
     *
     * @param map the map
     */
    public Sensor(Map<String, Object> map) {
        super(map);
    }
}
