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

import org.idmef.IDMEFObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TestDeserialize {

    private static void deserializeAndCheck(String json, IDMEFObject expected) {
        IDMEFObject msg = null;

        try {
            msg = IDMEFObject.deserialize(json.getBytes());
        } catch (Exception e) {
            fail(e.getMessage());
        }

        String[] propertiesToCheck = {"ID", "CreateTime", "Version"};

        for (String property: propertiesToCheck) {
            assertEquals(msg.get(property), expected.get(property));
        }
    }

    @Test
    void testDeserializeMessage1() {
        deserializeAndCheck(Util.string1(), Util.message1());
    }

    @Test
    void testDeserializeMessage2() {
        deserializeAndCheck(Util.string2(), Util.message2());
    }
}
