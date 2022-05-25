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

import java.io.FileOutputStream;

import static org.junit.jupiter.api.Assertions.fail;

public class TestSerialize {

    private static void serialize(IDMEFObject msg, String outFileName) {
        try {
            byte[] b = msg.serialize();

            FileOutputStream os = new FileOutputStream(outFileName);
            os.write(b);
            os.close();
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    void testSerializeMessage1() {
        serialize(Util.message1(), "out1.json");
    }

    @Test
    void testSerializeMessage2() {
        serialize(Util.message2(), "out2.json");
    }
}
