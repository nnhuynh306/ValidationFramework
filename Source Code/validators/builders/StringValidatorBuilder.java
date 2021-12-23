package validators.builders;

import util.comparator.StringIntComparator;
import validators.builtin.MinValidator;


public class StringValidatorBuilder extends BaseValidatorBuilder<String> {

    public StringValidatorBuilder minLength(int value) {
        addValidatorToChain(new MinValidator<String, Integer>(true, new StringIntComparator(), value));
        return this;
    }
}
