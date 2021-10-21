package org.idmef;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    @Test
    void loadResource1() {
        try {
            assertEquals(123, Utils.loadResource("/test/example1.json").get("foo").asInt());
        } catch (JsonProcessingException e) {
            fail(e.getMessage());
        } catch (IOException e) {
            fail(e.getMessage());
        }
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