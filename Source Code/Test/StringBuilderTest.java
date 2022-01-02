package Test;

import org.junit.Test;
import validators.builders.StringChainValidatorBuilder;


public class StringBuilderTest {
    @Test
    public void testAnnotation() throws NoSuchFieldException {
        StringChainValidatorBuilder test = new StringChainValidatorBuilder();
        test =  test.minLength(2,true);
    }
}
