# Java IDMEF library

![GitHub top language](https://img.shields.io/github/languages/top/teclib-idmef/java-idmef-library) 
![GitHub](https://img.shields.io/github/license/teclib-idmef/java-idmef-library) 
![GitHub tag (latest by date)](https://img.shields.io/github/v/tag/teclib-idmef/java-idmef-library) 
![GitHub release (latest by date)](https://img.shields.io/github/v/release/teclib-idmef/java-idmef-library)
![GitHub issues](https://img.shields.io/github/issues/teclib-idmef/java-idmef-library)
[![](https://jitpack.io/v/teclib-idmef/java-idmef-library.svg)](https://jitpack.io/#teclib-idmef/java-idmef-library)

A Java library for parsing, handling, and generating JSON IDMEFv2 messages. It can be used to represent Incident Detection Message Exchange Format (IDMEFv2) messages in memory, validate them and serialize/unserialize them for exchange with other systems.

IDMEFv2 messages can be transported using the [`java-idmef-transport-library`](https://github.com/teclib-idmef/java-idmef-transport-library).

This code is currently in an experimental status and is regularly kept in sync with the development status of the IDMEFv2 format, as part of the [SECurity Exchange Format project](https://www.secef.net/).

The latest revision of the IDMEFv2 format specification can be found there: https://github.com/IDMEFv2/IDMEFv2-Specification

You can find more information about the previous version (v1) of the Intrusion Detection Message Exchange Format in [RFC 4765](https://tools.ietf.org/html/rfc4765).

## Compiling the library

The following prerequisites must be installed on your system to install and use this library:

* Java: version 11 or above

The library has the following third-party dependencies:

* Jackson (aka JSON for Java): https://github.com/FasterXML/jackson
* Networknt Java JSON Schema Validator: https://github.com/networknt/json-schema-validator

**Note**: building using gradle automaticaly pulls the needed dependencies.

To compile the library:

``` shell
./gradlew build
``` 

This will build a JAR archive located in `./build/libs`.

## Using the library

### Add the library to your project dependencies

The library is published on https://jitpack.io

Using the library is therefore very simple:

**Step 1**. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:

```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

**Step 2**. Add the dependency

```
	dependencies {
	        implementation 'com.github.teclib-idmef:java-idmef-library:V1.0.2'
	}
```

### Message creation

A new message can be created by instantiating the `org.idmef.IDMEFObject` class. Once created, message fields can be set using the `put()` method.

``` java
import org.idmef.IDMEFObject;

class Test {
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

    public static void main(String[] args)
    {
        IDMEFObject msg1 = message1();

    	System.out.println(msg1.get("ID"));
    }
}
```

### Message validation

You can validate an IDMEFv2 message using `validate()` method of class `IDMEFValidator`. A `IDMEFException` is raised if the message is invalid.

``` java
import org.idmef.IDMEFException;
import org.idmef.IDMEFObject;
import org.idmef.IDMEFValidator;

/* see above to generate IDMEF message */

IDMEFObject msg1 = message1();

IDMEFValidator validator = new IDMEFValidator();

try {
validator.validate(msg1);
} catch (IDMEFException e) {
    System.err.println(e.getMessage());
}

System.out.println("Message is valid");
```

### Message serialization

Before the message can be sent to a remote system, it must be serialized using the `serialize()` method.

``` java
import org.idmef.IDMEFObject;

/* see above to generate IDMEF message */

IDMEFObject msg1 = message1();

byte[] b = null;

try {
    b = msg1.serialize();
} catch (Exception e) {
    System.err.println(e.getMessage());
}

System.out.println("Message is serialized:" + new String(b));
```

### Message deserialization

Likewise, when a message is received in its serialized form, it must be first deserialized using the `deserialize()` class method.

``` java
import org.idmef.IDMEFObject;

class Test4 {
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

    public static void main(String[] args)
    {
    	String json = string1();

        IDMEFObject msg = null;

        try {
            msg = IDMEFObject.deserialize(json.getBytes());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    	System.out.println("Message is deserialized and its ID is " + msg.get("ID"));
    }
}
```

## Contributions

All contributions must be licensed under the Apache-2.0 license. See the LICENSE file inside this repository for more information.

