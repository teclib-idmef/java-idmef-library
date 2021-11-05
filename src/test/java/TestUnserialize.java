import org.idmef.Message;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class TestUnserialize {

    @Test
    void testUnserializeMessage1() {
        String s = Util.string1();

        try {
            Message m = Message.unserialize(s.getBytes());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}
