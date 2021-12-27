package validators.builders;

import util.comparator.StringIntComparator;
import validators.builtin.MinValidator;
import validators.builtin.RegexValidator;


public class StringValidatorBuilder extends BaseValidatorBuilder<String> {

    public StringValidatorBuilder minLength(int value) {
        addValidatorToChain(new MinValidator<String, Integer>(true, new StringIntComparator(), value));
        return this;
    }

    public StringValidatorBuilder notEmpty(int size) {
        return this;
    }

    public StringValidatorBuilder maxLength(int size) {
        return this;
    }

    public StringValidatorBuilder notNull() {
        return this;
    }

    public StringValidatorBuilder regex(String regexStr) {
        addValidatorToChain(new RegexValidator<String>(true, regexStr));
        return this;
    }
}
