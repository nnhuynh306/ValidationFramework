package Test;

import org.junit.Assert;
import org.junit.Test;
import util.comparator.*;
import validators.builtin.MinValidator;

public class MinValidatorTest {
    @Test
    public void MinIntValidatorTest() {
        Comparator<Integer, Integer> comparator = new IntegerComparator();
        MinValidator MinValidator = new MinValidator(comparator, 5, true);
        Assert.assertFalse(MinValidator.isValid(3));
        Assert.assertTrue(MinValidator.isValid(7));
    }

    @Test
    public void MinDoubleValidatorTest() {
        Comparator<Double, Double> comparator = new DoubleComparator();
        MinValidator MinValidator = new MinValidator(comparator, 5.5, true);
        Assert.assertTrue(MinValidator.isValid(5.5));
        Assert.assertFalse(MinValidator.isValid(5.3));
    }

    @Test
    public void MinLongValidatorTest() {
        Comparator<Long, Long> comparator = new LongComparator();
        MinValidator MinValidator = new MinValidator(comparator, Long.parseLong("102400000"), false);
        Assert.assertTrue(MinValidator.isValid(Long.parseLong("102500000")));
        Assert.assertFalse(MinValidator.isValid(Long.parseLong("102400000")));
    }
}
