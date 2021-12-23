package validators;

import validators.result.ValidationResults;

public interface Validator<T> {
    boolean validate(T t, ValidationResults returnResults);
    void setNext(Validator<T> validator);
    boolean hasNext();
}
