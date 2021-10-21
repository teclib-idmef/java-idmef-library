package org.idmef;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

public class Utils {

    private static final String BASE = '/' + Utils.class.getPackage().getName().replace(".", "/");

    public static JsonNode loadResource(String path) throws IOException {
        InputStream inputStream = Utils.class.getClassLoader().getResourceAsStream(path);

        if (inputStream == null)
            throw new IOException("resource " + path + " not found");

        ObjectMapper mapper = new ObjectMapper( );

        return mapper.readTree(inputStream);
    }

    public static JsonNode loadString(String content) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        return mapper.readTree(content);
    }
}
