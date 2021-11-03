import org.idmef.Message;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class TestSerialize {

    @Test
    void testSerializeMessage1() {
        Message m = Util.message1();

        try {
            byte[] b = m.serialize();
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}
