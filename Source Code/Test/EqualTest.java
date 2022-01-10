package Test;

import annotations.AssertTrue;
import org.junit.Assert;
import org.junit.Test;
import util.comparator.Comparator;
import util.comparator.StringComparator;
import validators.builtin.EqualValidator;

public class EqualTest {
    @Test
    public void equalTest(){
        Comparator<String,String> comparator = new StringComparator();
        EqualValidator<String,String> test = new EqualValidator<>(comparator,"Thai");
        Assert.assertTrue(test.isValid("Thai"));
    }
}
