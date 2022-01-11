package validators;

import validators.builders.ChainValidatorBuilder;
import validators.result.ValidationResults;

import java.util.function.Function;

/**
 * A Validator (Rule) for type T but just for validate 1 of its field (Type S)
 *
 * @param <T> type of Object
 * @param <S> type of Object's field to be validated
 */
public class Rule<T, S> extends BaseValidator<T> {
    private final ChainValidatorBuilder<S> builder;
    private final Function<T, S> getSValueFunction;
    private Validator<S> validator;

    public Rule(ChainValidatorBuilder<S> builder, Function<T, S> getSValueFunction) {
        this.builder = builder;
        this.getSValueFunction = getSValueFunction;
    }

    public boolean validate(T t, ValidationResults returnResults) {
       try {
           boolean result;
           if (hasNext()) {
               result = getValidator().validate(getSValueFunction.apply(t), returnResults) && nextValidator.validate(t, returnResults);
           } else {
               result = getValidator().validate(getSValueFunction.apply(t), returnResults);
           }

           addResult(result, returnResults);

           return result;
       } catch (Exception e) {

           boolean result;

           if (hasNext()) {
               result = nextValidator.validate(t, returnResults);
           } else {
               result = true;
           }

           addResult(result, returnResults);
           e.printStackTrace();
           return false;
       }
    }

    public Validator<S> getValidator() {
        if (validator == null) return validator = builder.build();
        return validator;
    }
}
