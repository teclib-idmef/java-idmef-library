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
