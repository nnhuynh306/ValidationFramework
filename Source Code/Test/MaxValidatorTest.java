package Test;

import org.junit.Assert;
import org.junit.Test;
import util.comparator.Comparator;
import util.comparator.IntegerComparator;
import validators.builtin.MaxValidator;

public class MaxValidatorTest {
    @Test
    public void maxIntValidatorTest(){
        Comparator<Integer,Integer> comparator = new IntegerComparator();
        MaxValidator maxValidator = new MaxValidator(comparator,5,true);
        Assert.assertTrue(maxValidator.isValid(3));
    }
}
