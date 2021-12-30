package validators.builtin;

import validators.BaseValidator;
import validators.Validator;
import validators.builders.ValidatorBuilder;
import validators.result.ValidationResult;
import validators.result.ValidationResults;

import java.util.function.Function;

/**
 * A Validator (Rule) for type T but just for validate 1 of its field (Type S)
 *
 * @param <T> type of Object
 * @param <S> type of Object's field to be validated
 */
public class Rule<T, S> extends BaseValidator<T> {
    private final ValidatorBuilder<S> builder;
    private final Function<T, S> getSValueFunction;
    private Validator<S> validator;
    private String name;

    public Rule(ValidatorBuilder<S> builder, Function<T, S> getSValueFunction) {
        this.builder = builder;
        this.getSValueFunction = getSValueFunction;
    }

    public boolean validate(T t,ValidationResults returnResults) {
        boolean result;
        if (hasNext()) {
            result = getValidator().validate(getSValueFunction.apply(t), returnResults) && nextValidator.validate(t, returnResults);
        } else {
            result = getValidator().validate(getSValueFunction.apply(t), returnResults);
        }

        if (returnResults != null) {
            returnResults.add(createResult(result));
        }

        return result;
    }

    public Validator<S> getValidator() {
        if (validator == null) return validator = builder.build();
        return validator;
    }

    private ValidationResult createResult(boolean result) {
        return new ValidationResult(result,
                getName(),
                result?"": "Rule for " + getName() + " has failed");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
