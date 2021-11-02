import org.idmef.IDMEFException;
import org.idmef.Message;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.fail;

public class TestValidate {

    @Test
    void testValidateMessage1() {
        Message msg = new Message();
        msg.put("Version", "2.0.3");
        msg.put("ID", "afe884a3-d658-4a15-bd08-34abba526aca");
        msg.put("CreateTime", "2021-10-28T11:32:19.359177");
        Map<String, Object> analyzer = new HashMap<>();
        analyzer.put("IP", "127.0.0.1");
        analyzer.put("Name", "foobar");
        analyzer.put("Model", "generic");
        // more compact version
        analyzer.put("Category", List.of("LOG"));
        analyzer.put("Data", List.of("Log"));
        analyzer.put("Method", List.of("Monitor"));
        msg.put("Analyzer", analyzer);

        try {
            msg.validate();
        } catch (IDMEFException e) {
            fail(e.getMessage());
        }
    }
}
