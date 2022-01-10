package Test;

import org.junit.Assert;
import org.junit.Test;
import validators.builtin.NotEmptyValidator;
import validators.builtin.NotNullValidator;

public class NotNull_NotEmpty_Test {
    @Test
    public void notNullTest() {
        NotNullValidator test = new NotNullValidator();
        Assert.assertTrue(test.isValid("Thai"));
        Assert.assertFalse(test.isValid(null));
    }

    @Test
    public void notEmptyTest() {
        NotEmptyValidator test = new NotEmptyValidator();
        Assert.assertTrue(test.isValid("Thai"));
        Assert.assertFalse(test.isValid(""));
    }
}
