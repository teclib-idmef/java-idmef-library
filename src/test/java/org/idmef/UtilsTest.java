package org.idmef;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    @Test
    void loadResource() {
    }

    @Test()
    void loadString1() {
        assertThrows(JsonProcessingException.class,
                () -> Utils.loadString("{\"foo\":123"));
    }

    @Test()
    void loadString2() {
        try {
            assertEquals(123, Utils.loadString("{\"foo\":123}").get("foo").asInt());
        } catch (JsonProcessingException e) {
            fail(e.getMessage());
        }
    }

}