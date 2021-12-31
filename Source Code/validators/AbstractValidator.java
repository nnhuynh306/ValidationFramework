package validators;


import util.ChainValidatorLinker;
import validators.builders.*;

import validators.result.ValidationResult;
import validators.result.ValidationResults;


import java.util.Date;


import java.util.function.Function;

public abstract class AbstractValidator<T> extends BaseValidator<T>  {

    protected ChainValidatorLinker<T> chainValidatorLinker = new ChainValidatorLinker<>();

    public AbstractValidator() {
    }

    @Override
    public boolean validate(T t, ValidationResults returnResults) {
        Validator<T> firstValidator = chainValidatorLinker.getFirstValidator();

        boolean result;
        if (firstValidator != null) {
            result = chainValidatorLinker.getFirstValidator().validate(t, returnResults);
        }
        else {
            result = true;
        }

        addResult(result, returnResults);

        return result;
    }


    public final StringValidatorBuilder AddStringRuleFor(Function<T, String> getStringFunction) {
        StringValidatorBuilder stringValidatorBuilder = ValidatorBuilderFactory.getStringValidatorBuilder();
        chainValidatorLinker.add(new Rule<>(stringValidatorBuilder, getStringFunction));
        return stringValidatorBuilder;
    }

    public final DateValidatorBuilder AddDateRuleFor(Function<T, Date> getDateFunction){
        DateValidatorBuilder dateValidatorBuilder = ValidatorBuilderFactory.getDateValidatorBuilder();
        chainValidatorLinker.add(new Rule<>(dateValidatorBuilder,getDateFunction));
        return dateValidatorBuilder;
    }

    public final <S> CustomValidatorBuilder<S> AddCustomRuleFor(Function<T, S> getFunction) {
        CustomValidatorBuilder<S> customValidatorBuilder = ValidatorBuilderFactory.getCustomValidatorBuilder();
        chainValidatorLinker.add(new Rule<>(customValidatorBuilder, getFunction));
        return customValidatorBuilder;
    }

    public final<S> NumericValidatorBuilder<S> AddNumericRuleFor(Function<T, S> getFunction, Class<S> sClass) {
        NumericValidatorBuilder<S> numericValidatorBuilder =  ValidatorBuilderFactory.getNumericBuilderOf(sClass);
        chainValidatorLinker.add(new Rule<>(numericValidatorBuilder, getFunction));
        return numericValidatorBuilder;
    }

    public final BooleanValidatorBuilder AddBooleanRuleFor(Function<T, Boolean> getFunction) {
        BooleanValidatorBuilder booleanValidatorBuilder =  ValidatorBuilderFactory.getBooleanValidatorBuilder();
        chainValidatorLinker.add(new Rule<>(booleanValidatorBuilder, getFunction));
        return booleanValidatorBuilder;
    }
}
