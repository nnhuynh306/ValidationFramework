package Test;

import org.junit.Assert;
import org.junit.Test;
import util.comparator.*;
import validators.builtin.MaxValidator;

public class MaxValidatorTest {
    @Test
    public void maxIntValidatorTest() {
        Comparator<Integer, Integer> comparator = new IntegerComparator();
        MaxValidator maxValidator = new MaxValidator(comparator, 5, true);
        Assert.assertTrue(maxValidator.isValid(3));
        Assert.assertFalse(maxValidator.isValid(7));
    }

    @Test
    public void maxDoubleValidatorTest() {
        Comparator<Double, Double> comparator = new DoubleComparator();
        MaxValidator maxValidator = new MaxValidator(comparator, 5.5, true);
        Assert.assertTrue(maxValidator.isValid(5.5));
        Assert.assertFalse(maxValidator.isValid(6.3));
    }

    @Test
    public void maxLongValidatorTest() {
        Comparator<Long, Long> comparator = new LongComparator();
        MaxValidator maxValidator = new MaxValidator(comparator, Long.parseLong("102400000"), false);
        Assert.assertTrue(maxValidator.isValid(Long.parseLong("102300000")));
        Assert.assertFalse(maxValidator.isValid(Long.parseLong("102400000")));
    }
}
