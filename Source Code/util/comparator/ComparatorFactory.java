package util.comparator;

public class ComparatorFactory {

    public static Comparator getComparator(Class<?> clazz1, Class<?> clazz2) {
        if (clazz1 == clazz2) {
            return getComparator(clazz1);
        }

        if (clazz1 == String.class && (clazz2 == Integer.class || clazz2 == int.class)) {
            return new StringIntComparator();
        }

        return null;
    }

    public static Comparator getComparator(Class<?> clazz) {
        if (clazz == Integer.class || clazz == int.class) {
            return new IntegerComparator();
        } else if (clazz == Long.class || clazz == long.class) {
            return new LongComparator();
        } else if (clazz == Short.class || clazz == short.class) {
            return new ShortComparator();
        } else if (clazz ==  Float.class || clazz == float.class) {
            return new FloatComparator();
        } else if (clazz == Double.class || clazz == double.class) {
            return new DoubleComparator();
        }
        else {
            return  null;
        }
    }
}
