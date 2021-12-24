package validators.annotation.parser;

import util.ChainValidatorLinker;
import validators.annotation.AnnotatedFieldValidator;

import java.lang.reflect.Field;

public interface AnnotatedClassParser<T> {
    /**
     *
     * @param field field with type E
     * @param <S>
     * @return  A validator for annotated field
     */
     <S> AnnotatedFieldValidator<T, S> parse(Field field, Class<S> clazz);
     ChainValidatorLinker<T> parse(T t);

    public static boolean isNumericType(Class<?> clazz) {
        return clazz == Integer.class || clazz == int.class
                || clazz == Short.class || clazz == short.class
                || clazz == Long.class || clazz == long.class
                || clazz == Float.class || clazz == float.class
                || clazz == Double.class || clazz == double.class;

    }
}
