package org.idmef;

import java.util.Map;

/**
 * IDMEF Observable object.
 */
public class Observable extends IDMEFObject {

    /**
     * Constructs an empty Observable.
     */
    public Observable() {
    }

    /**
     * Construct an Observable from a map.
     *
     * @param map the map
     */
    public Observable(Map<String, Object> map) {
        super(map);
    }
}
