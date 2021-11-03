import org.idmef.Analyzer;
import org.idmef.IDMEFException;
import org.idmef.Message;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class TestValidate {

    @Test
    void testValidateMessage1() {
        Message msg = new Message();
        msg.put("Version", "2.0.3");
        msg.put("ID", "afe884a3-d658-4a15-bd08-34abba526aca");
        msg.put("CreateTime", "2021-10-28T11:32:19.359177");
        Analyzer analyzer = new Analyzer();
        analyzer.put("IP", "127.0.0.1");
        analyzer.put("Name", "foobar");
        analyzer.put("Model", "generic");
        analyzer.put("Category", new String[]{"LOG"});
        analyzer.put("Data", new String[]{"Log"});
        analyzer.put("Method", new String[]{"Monitor"});
        msg.put("Analyzer", analyzer);

        try {
            msg.validate();
        } catch (IDMEFException e) {
            fail(e.getMessage());
        }
    }
}
