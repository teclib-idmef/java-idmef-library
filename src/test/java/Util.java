import org.idmef.Analyzer;
import org.idmef.Message;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Util {

    static String now() {
        return ZonedDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.ISO_INSTANT);
    }

    static String uuid() {
        return UUID.randomUUID().toString();
    }

    static Message message1() {
        Message msg = new Message();
        msg.put("Version", "2.0.3");
        msg.put("ID", uuid());
        msg.put("CreateTime", now());
        Analyzer analyzer = new Analyzer();
        analyzer.put("IP", "127.0.0.1");
        analyzer.put("Name", "foobar");
        analyzer.put("Model", "generic");
        analyzer.put("Category", new String[]{"LOG"});
        analyzer.put("Data", new String[]{"Log"});
        analyzer.put("Method", new String[]{"Monitor"});
        msg.put("Analyzer", analyzer);

        return msg;
    }

    static String string1() {
        return "{\n" +
                "\"Version\":\"2.0.3\",\n" +
                "\"CreateTime\":\"" + now() + "\",\n" +
                "\"ID\":\"" + uuid() + "\",\n" +
                "\"Analyzer\":{\n" +
                    "\"Category\":[\"LOG\"],\n" +
                    "\"IP\":\"127.0.0.1\",\n" +
                    "\"Model\":\"generic\",\n" +
                    "\"Data\":[\"Log\"],\n" +
                    "\"Method\":[\"Monitor\"],\n" +
                    "\"Name\":\"foobar\"\n" +
                    "}\n" +
                "}\n";
    }
}
