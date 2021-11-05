package org.idmef;

import java.util.Map;

/**
 * IDMEF Analyzer object.
 *
 * This implementation provides property setting/getting.
 *
 * Current implementation does not check property keys in put method. Property keys and values are checked
 * when calling validate() method.
 */
public class Analyzer extends IDMEFObject {

    /**
     * Constructs an empty Analyzer.
     */
    public Analyzer() {
    }

    /**
     * Construct an Analyzer from a map.
     *
     * @param map the map
     */
    public Analyzer(Map<String, Object> map) {
        super(map);
    }
}
