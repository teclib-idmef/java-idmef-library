package org.idmef;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

public class Utils {

    private static final String BASE = '/' + Utils.class.getPackage().getName().replace(".", "/");

    public static JsonNode loadResource(String path) throws IOException {
        InputStream inputStream = Utils.class.getClassLoader().getResourceAsStream(BASE + '/' + path);

        ObjectMapper mapper = new ObjectMapper();

        return mapper.readTree(inputStream);
    }

    public static JsonNode loadString(String content) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        return mapper.readTree(content);
    }

    public static void main(String[] args) {
        try {
            System.out.println(Utils.loadString("{\"foo\":123}"));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }
}
