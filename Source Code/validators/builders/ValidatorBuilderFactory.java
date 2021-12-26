package validators.builders;

import util.comparator.*;
import validators.Validator;
import validators.annotation.AnnotatedFieldValidator;
import validators.annotation.AnnotationValidator;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

@SuppressWarnings("unchecked")
public class ValidatorBuilderFactory {

    public static <T> NumericValidatorBuilder<T> getNumericBuilderOf(Class<T> clazz) {
        if (clazz == Integer.class || clazz == int.class) {
            return new NumericValidatorBuilder<T>(ComparatorFactory.getComparator(Integer.class), clazz);
        } else if (clazz == Long.class || clazz == long.class) {
            return new NumericValidatorBuilder<T>(ComparatorFactory.getComparator(Long.class), clazz);
        } else if (clazz == Short.class || clazz == short.class) {
            return new NumericValidatorBuilder<T>(ComparatorFactory.getComparator(Short.class), clazz);
        } else if (clazz ==  Float.class || clazz == float.class) {
            return new NumericValidatorBuilder<T>(ComparatorFactory.getComparator(Float.class), clazz);
        } else if (clazz == Double.class || clazz == double.class) {
            return new NumericValidatorBuilder<T>(ComparatorFactory.getComparator(Double.class), clazz);
        }
        else {
            return  null;
        }
    }

    public static StringValidatorBuilder getStringValidatorBuilder() {
        return new StringValidatorBuilder();
    }

    public static <T> CustomValidatorBuilder<T> getCustomValidatorBuilder() {
        return new CustomValidatorBuilder<T>();
    }

}
