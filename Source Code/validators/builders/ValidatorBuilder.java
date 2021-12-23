package validators.builders;

import validators.Validator;

public interface ValidatorBuilder<T> {
    Validator<T> build();
}
