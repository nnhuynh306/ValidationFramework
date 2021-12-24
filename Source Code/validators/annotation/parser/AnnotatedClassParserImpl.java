package validators.annotation.parser;

import util.ChainValidatorLinker;
import validators.Validator;
import validators.annotation.AnnotatedFieldValidator;
import validators.annotation.processor.AnnotationProcessorImpl;
import validators.builders.StringValidatorBuilder;
import validators.builders.ValidatorBuilderFactory;

import java.lang.reflect.Field;
import java.util.Objects;

public class AnnotatedClassParserImpl<T> implements AnnotatedClassParser<T> {

    private AnnotationProcessorImpl annotationProcessor = new AnnotationProcessorImpl();

    public <S> AnnotatedFieldValidator<T, S> parse(Field field, Class<S> sClass) {
        try {
            return new AnnotatedFieldValidator<>(field, Objects.requireNonNull(getValidatorFromAnnotationFor(field, sClass)), new FieldValueParserImpl<>(), sClass);
        } catch (NullPointerException e) {
            return null;
        }
    }

    @Override
    public ChainValidatorLinker<T> parse(T t) {
        ChainValidatorLinker<T> chainValidatorLinker = new ChainValidatorLinker<>();

        for(Field field: t.getClass().getDeclaredFields()) {
            chainValidatorLinker.add(parse(field, field.getType()));
        }

        return chainValidatorLinker;
    }

    @SuppressWarnings("unchecked")
    private <S> Validator<S> getValidatorFromAnnotationFor(Field field, Class<S> sClass) {
        if (sClass == String.class) {
            return (Validator<S>) getStringValidatorFromAnnotationFor(field);
        }
        return null;
    }

    private Validator<String> getStringValidatorFromAnnotationFor(Field field) {
        StringValidatorBuilder stringValidatorBuilder = ValidatorBuilderFactory.getStringValidatorBuilder();
        annotationProcessor.process(field, stringValidatorBuilder);
        return stringValidatorBuilder.build();
    }
}
