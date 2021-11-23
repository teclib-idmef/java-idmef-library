import org.idmef.IDMEFException;
import org.idmef.IDMEFObject;
import org.idmef.IDMEFValidator;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class TestValidate {

    private static boolean validate(IDMEFObject idmefObject) {
        IDMEFValidator validator = new IDMEFValidator();

        try {
            return validator.validate(idmefObject);
        } catch (IDMEFException e) {
            fail(e.getMessage());
        }

        return false;
    }

    private static boolean validate(byte[] json) {
        IDMEFValidator validator = new IDMEFValidator();

        try {
            return validator.validate(json);
        } catch (IOException e) {
            fail(e.getMessage());
        }

        return false;
    }

    @Test
    void testValidateMessage1() {
        assertTrue(validate(Util.message1()));
    }

    @Test
    void testValidateString1() {
        assertTrue(validate(Util.string1().getBytes(StandardCharsets.UTF_8)));
    }

    @Test
    void testValidateMessage2() {
        assertTrue(validate(Util.message2()));
    }

    @Test
    void testValidateString2() {
        assertTrue(validate(Util.string2().getBytes(StandardCharsets.UTF_8)));
    }
}
