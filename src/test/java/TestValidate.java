import org.idmef.IDMEFObject;
import org.idmef.IDMEFValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class TestValidate {

    private static void validate(IDMEFObject idmefObject) {
        IDMEFValidator validator = new IDMEFValidator();

        try {
            validator.validate(idmefObject);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    void testValidateMessage1() {
        validate(Util.message1());
    }

}
