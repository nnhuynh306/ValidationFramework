package validators.builders;

import util.comparator.*;

@SuppressWarnings("unchecked")
public class ValidatorBuilderFactory {

    public static <T> NumericValidatorBuilder<T> getNumericBuilderOf(Class<?> clazz) {
        if (clazz == Integer.class || clazz == int.class) {
            return new NumericValidatorBuilder<T>(ComparatorFactory.getComparator(Integer.class));
        } else if (clazz == Long.class || clazz == long.class) {
            return new NumericValidatorBuilder<T>(ComparatorFactory.getComparator(Long.class));
        } else if (clazz == Short.class || clazz == short.class) {
            return new NumericValidatorBuilder<T>(ComparatorFactory.getComparator(Short.class));
        } else if (clazz ==  Float.class || clazz == float.class) {
            return new NumericValidatorBuilder<T>(ComparatorFactory.getComparator(Float.class));
        } else if (clazz == Double.class || clazz == double.class) {
            return new NumericValidatorBuilder<T>(ComparatorFactory.getComparator(Double.class));
        }
        else {
            return  null;
        }
    }

    public static StringValidatorBuilder getStringValidatorBuilder() {
        return new StringValidatorBuilder();
    }

    enum NumericType {
        INTEGER,
        FLOAT,
        DOUBLE,
        SHORT,
        LONG
    }
}
