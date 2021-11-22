import org.idmef.Message;
import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;

import static org.junit.jupiter.api.Assertions.fail;

public class TestSerialize {

    private static void serialize(Message msg, String outFileName) {
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
