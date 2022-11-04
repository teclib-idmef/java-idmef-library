# Java IDMEF library

![GitHub top language](https://img.shields.io/github/languages/top/teclib-idmef/java-idmef-library) 
![GitHub](https://img.shields.io/github/license/teclib-idmef/java-idmef-library) 
![GitHub tag (latest by date)](https://img.shields.io/github/v/tag/teclib-idmef/java-idmef-library) 
![GitHub release (latest by date)](https://img.shields.io/github/v/release/teclib-idmef/java-idmef-library)
![GitHub issues](https://img.shields.io/github/issues/teclib-idmef/java-idmef-library)

A Java library for parsing, handling, and generating JSON IDMEFv2 messages. It can be used to represent Incident Detection Message Exchange Format (IDMEFv2) messages in memory, validate them and serialize/unserialize them for exchange with other systems.

IDMEFv2 messages can be transported using the [`java-idmef-transport-library`](https://github.com/teclib-idmef/java-idmef-transport-library).

This code is currently in an experimental status and is regularly kept in sync with the development status of the IDMEFv2 format, as part of the [SECurity Exchange Format project](https://www.secef.net/).

The latest revision of the IDMEFv2 format specification can be found there: https://github.com/IDMEFv2/IDMEFv2-Specification

You can find more information about the previous version (v1) of the Intrusion Detection Message Exchange Format in [RFC 4765](https://tools.ietf.org/html/rfc4765).

## Compiling the library

The following prerequisites must be installed on your system to install and use this library:

* Java: version 11 or above
* Jackson (aka JSON for Java): https://github.com/FasterXML/jackson

To compile the library:

``` shell
./gradlew build
``` 

This will build a JAR archive located in `./build/libs`.

## Using the library

### javadoc


### Message creation

A new message can be created by instantiating the `idmefv2.Message` class. This object can then be used like a regular Python dictionary:

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

You can validate an IDMEFv2 message using its `validate()` method. A `ValidationException` is raised if the message is invalid.

``` java
    try:
        msg.validate()
    except jsonschema.exceptions.ValidationError as e:
        print("Validation failure: %s" % (e, ))
    else:
        print("The message is valid")
```


### Message serialization/unserialization

Before the message can be sent to a remote system, it must be serialized.

To serialize a message, use the ``serialize()`` method, e.g.

``` java
class Foo {
}
```

The argument given to the ``serialize()`` method specifies the expected MIME content type for the resulting payload.

For the time being, only the ``application/json`` content type is supported, which results in a JSON-encoded message.

Likewise, when a message is received from a foreign system, it must be unserialized before it can be used. This is achieved using the ``unserialize()`` class method.

``` java
    result = msg.serialize('application/json')
```

## Contributions

All contributions must be licensed under the Apache-2.0 license. See the LICENSE file inside this repository for more information.

