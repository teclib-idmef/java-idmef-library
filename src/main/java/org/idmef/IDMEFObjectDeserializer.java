/*
 * Copyright (C) 2022 Teclib'
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.idmef;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

/**
 * Class for IDMEFObject deserialization.
 *
 * Class inherits from com.fasterxml.jackson.databind.deser.std.StdDeserializer and overrides deserialize method.
 *
 * @see com.fasterxml.jackson.databind.deser.std.StdDeserializer
 *
 */
public class IDMEFObjectDeserializer extends StdDeserializer<IDMEFObject> {

    /**
     * Construct a IDMEFObjectDeserializer instance
     */
    public IDMEFObjectDeserializer() {
        this(null);
    }

    /**
     * Construct a IDMEFObjectDeserializer instance
     *
     * @param vc the class to which to deserialize
     */
    public IDMEFObjectDeserializer(Class<?> vc) {
        super(vc);
    }

    /**
     * Deserialize JSON content to a IDMEFObject
     *
     * @param jp      Parsed used for reading JSON content
     * @param context Context that can be used to access information about
     *                this deserialization activity.
     * @return deserialized IDMEFObject
     * @throws IOException if an exception occurred during JSON content reading
     * @throws JsonProcessingException if an exception occurred when processing (parsing, generating) JSON content that are not pure I/O problems.
     */
    @Override
    public IDMEFObject deserialize(JsonParser jp, DeserializationContext context) throws IOException, JsonProcessingException {
        JsonNode jsonNode = jp.getCodec().readTree(jp);

        if (!jsonNode.isObject()) {
            throw new IDMEFException("Invalid JsonNode type: " + jsonNode.getClass().getName());
        }

        return new IDMEFObject(jsonNode);
    }
}
