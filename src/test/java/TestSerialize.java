import org.idmef.Message;
import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;

import static org.junit.jupiter.api.Assertions.fail;

public class TestSerialize {

    @Test
    void testSerializeMessage1() {
        Message m = Util.message1();

        try {
            byte[] b = m.serialize();

            FileOutputStream os = new FileOutputStream("out.json");
            os.write(b);
            os.close();
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}
