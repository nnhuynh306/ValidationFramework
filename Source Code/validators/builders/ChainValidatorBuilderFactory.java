package validators.builders;


import util.comparator.ComparatorFactory;


@SuppressWarnings("unchecked")
public class ChainValidatorBuilderFactory {

    public static <T> NumericChainValidatorBuilder<T> getNumericChainValidatorBuilderOf(Class<T> clazz) {
        if (clazz == Integer.class || clazz == int.class) {
            return new NumericChainValidatorBuilder<T>(ComparatorFactory.getComparator(Integer.class), clazz);
        } else if (clazz == Long.class || clazz == long.class) {
            return new NumericChainValidatorBuilder<T>(ComparatorFactory.getComparator(Long.class), clazz);
        } else if (clazz == Short.class || clazz == short.class) {
            return new NumericChainValidatorBuilder<T>(ComparatorFactory.getComparator(Short.class), clazz);
        } else if (clazz == Float.class || clazz == float.class) {
            return new NumericChainValidatorBuilder<T>(ComparatorFactory.getComparator(Float.class), clazz);
        } else if (clazz == Double.class || clazz == double.class) {
            return new NumericChainValidatorBuilder<T>(ComparatorFactory.getComparator(Double.class), clazz);
        } else if (clazz == Byte.class || clazz == byte.class) {
            return new NumericChainValidatorBuilder<T>(ComparatorFactory.getComparator(Byte.class), clazz);
        } else {
            return null;
        }
    }

    public static StringChainValidatorBuilder getStringChainValidatorBuilder() {
        return new StringChainValidatorBuilder();
    }

    public static DateChainValidatorBuilder getDateChainValidatorBuilder() {
        return new DateChainValidatorBuilder();
    }

    public static <T> CustomChainValidatorBuilder<T> getCustomChainValidatorBuilder() {
        return new CustomChainValidatorBuilder<T>();
    }

    public static BooleanChainValidatorBuilder getBooleanChainValidatorBuilder() {
        return new BooleanChainValidatorBuilder();
    }

    public static <T> ChainValidatorBuilder<T> getChainValidatorBuilderFor(Class<T> tClass) {
        if (tClass == null) {
            return getCustomChainValidatorBuilder();
        }
        if (tClass == String.class) {
            return (ChainValidatorBuilder<T>) getStringChainValidatorBuilder();
        }

        ChainValidatorBuilder<T> chainValidatorBuilder = getNumericChainValidatorBuilderOf(tClass);

        if (chainValidatorBuilder == null) {
            return getCustomChainValidatorBuilder();
        }
        return chainValidatorBuilder;
    }
}
