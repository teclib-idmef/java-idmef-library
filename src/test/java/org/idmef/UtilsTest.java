package org.idmef;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class UtilsTest {

    @Test
    void loadResource() {
    }

    @Test()
    void loadString() {
        assertThrows(JsonProcessingException.class,
                () -> Utils.loadString("{\"foo\":123"));
    }
}