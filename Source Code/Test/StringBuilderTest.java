package Test;

import validators.builders.StringChainValidatorBuilder;
import org.junit.Test;

public class StringBuilderTest {
    @Test
    public void testAnnotation() throws NoSuchFieldException {
        StringChainValidatorBuilder test = new StringChainValidatorBuilder();
        test =  test.minLength(2,true);
    }
}
