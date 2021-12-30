package validators;


import util.ChainValidatorLinker;
import validators.builders.DateValidatorBuilder;

import validators.builders.CustomValidatorBuilder;

import validators.builders.NumericValidatorBuilder;
import validators.builders.StringValidatorBuilder;
import validators.builders.ValidatorBuilderFactory;
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

    public final NumericValidatorBuilder<Integer> AddIntegerRuleFor(Function<T, Integer> getIntegerFunction) {
        NumericValidatorBuilder<Integer> integerValidatorBuilder
                =  ValidatorBuilderFactory.getNumericBuilderOf(Integer.class);
        chainValidatorLinker.add(new Rule<>(integerValidatorBuilder, getIntegerFunction));
        return integerValidatorBuilder;
    }


    public final DateValidatorBuilder AddDateRuleFor(Function<T, Date> getDateFunction){
        DateValidatorBuilder dateValidatorBuilder = (DateValidatorBuilder) ValidatorBuilderFactory.getDateValidatorBuilder();
        chainValidatorLinker.add(new Rule<T,Date>(dateValidatorBuilder,getDateFunction));
        return dateValidatorBuilder;
    }

    public final <S> CustomValidatorBuilder<S> AddCustomRuleFor(Function<T, S> getFunction) {
        CustomValidatorBuilder<S> customValidatorBuilder = ValidatorBuilderFactory.getCustomValidatorBuilder();
        chainValidatorLinker.add(new Rule<>(customValidatorBuilder, getFunction));
        return customValidatorBuilder;
    }


}
