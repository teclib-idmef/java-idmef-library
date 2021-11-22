import org.idmef.Message;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class TestDeserialize {

    private static Message deserialize(String json) {
        Message msg = null;

        try {
            msg = Message.deserialize(json.getBytes());
        } catch (Exception e) {
            fail(e.getMessage());
        }

        return msg;
    }

    @Test
    void testDeserializeMessage1() {
        deserialize(Util.string1());
    }

    @Test
    void testDeserializeMessage2() {
        deserialize(Util.string2());
    }
}
