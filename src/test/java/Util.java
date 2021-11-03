import org.idmef.Analyzer;
import org.idmef.Message;

public class Util {

    static Message message1() {
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

        return msg;
    }

    static String string1() {
        return "{\n" +
                "\"Version\":\"2.0.3\",\n" +
                "\"CreateTime\":\"2021-10-28T11:32:19.359177\",\n" +
                "\"ID\":\"afe884a3-d658-4a15-bd08-34abba526aca\",\n" +
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
