import org.idmef.Message;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class TestValidate {

    @Test
    void testValidateMessage1() {
        Message m = Util.message1();

        try {
            m.validate();
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

}
