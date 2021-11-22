import org.idmef.IDMEFObject;
import org.idmef.Message;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TestDeserialize {

    private static IDMEFObject deserialize(String json) {
        IDMEFObject msg = null;

        try {
            msg = Message.deserialize(json.getBytes());
        } catch (Exception e) {
            fail(e.getMessage());
        }

        return msg;
    }

    @Test
    void testDeserializeMessage1() {
        IDMEFObject msg = deserialize(Util.string1());

        System.err.println("Message: " + msg.getProperties().getClass().getName());
        System.err.println("Analyzer: " + msg.get("Analyzer").getClass().getName());

        assertEquals(deserialize(Util.string1()), Util.message1());
    }

    @Test
    void testDeserializeMessage2() {
        IDMEFObject msg = deserialize(Util.string2());

        System.err.println("Message: " + msg.getProperties().getClass().getName());
        System.err.println("Sensor: " + msg.get("Sensor").getClass().getName());

        assertEquals(msg, Util.message2());
    }
}
