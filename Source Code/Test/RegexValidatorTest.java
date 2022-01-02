package Test;

import org.junit.Assert;
import org.junit.Test;
import validators.builtin.RegexValidator;

public class RegexValidatorTest {
    @Test
    public void testValid(){
        RegexValidator regexValidator = new RegexValidator(true,"[1-9]");
        Assert.assertTrue(regexValidator.isValid("2"));
    }
}
