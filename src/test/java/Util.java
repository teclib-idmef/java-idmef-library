import org.idmef.IDMEFObject;

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

    static IDMEFObject message1() {
        IDMEFObject msg = new IDMEFObject();
        msg.put("Version", "2.0.3");
        msg.put("ID", "09db946e-673e-49af-b4b2-a8cd9da58de6");
        msg.put("CreateTime", "2021-11-22T14:42:51.881033Z");

        IDMEFObject analyzer = new IDMEFObject();
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
                "\"CreateTime\":\"2021-11-22T14:42:51.881033Z\",\n" +
                "\"ID\":\"09db946e-673e-49af-b4b2-a8cd9da58de6\",\n" +
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

    static IDMEFObject message2() {
        IDMEFObject msg = new IDMEFObject();
        msg.put("Version", "2.0.3");
        msg.put("ID", "fae2ece1-ea54-461a-bedb-953495d6d39c");
        msg.put("CreateTime", "2021-11-23T09:48:58.416374Z");

        IDMEFObject analyzer = new IDMEFObject();
        analyzer.put("IP", "127.0.0.1");
        analyzer.put("Name", "foobar");
        analyzer.put("Model", "generic");
        analyzer.put("Category", new String[]{"LOG"});
        analyzer.put("Data", new String[]{"Log"});
        analyzer.put("Method", new String[]{"Monitor"});

        msg.put("Analyzer", analyzer);

        IDMEFObject sensor1 = new IDMEFObject();
        sensor1.put("IP", "192.168.1.1");
        sensor1.put("Name", "TheSensor");
        sensor1.put("Model", "TheSensorModel");

        IDMEFObject sensor2 = new IDMEFObject();
        sensor2.put("IP", "192.168.1.2");
        sensor2.put("Name", "TheSensor2");
        sensor2.put("Model", "TheSensor2Model");

        msg.put("Sensor", new IDMEFObject[]{sensor1, sensor2});

        return msg;
    }

    static String string2() {
        return "{\"Version\": \"2.0.3\",\n" +
                "\"CreateTime\":\"2021-11-23T09:48:58.416374Z\",\n" +
                "\"ID\":\"fae2ece1-ea54-461a-bedb-953495d6d39c\",\n" +
                " \"Analyzer\": {\"IP\": \"127.0.0.1\",\n" +
                "\t      \"Name\": \"foobar\",\n" +
                "\t      \"Model\": \"generic\",\n" +
                "\t      \"Category\": [\"LOG\"],\n" +
                "\t      \"Data\": [\"Log\"],\n" +
                "\t      \"Method\": [\"Monitor\"]},\n" +
                " \"Sensor\": [{\"IP\": \"192.168.1.1\",\n" +
                "\t     \"Name\": \"TheSensor\",\n" +
                "\t     \"Model\": \"TheSensorModel\"},\n" +
                "\t    {\"IP\": \"192.168.1.2\",\n" +
                "\t     \"Name\": \"TheSensor2\",\n" +
                "\t     \"Model\": \"TheSensor2Model\"}]}\n";
    }
}
