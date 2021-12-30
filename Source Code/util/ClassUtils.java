package util;

public class ClassUtils {
    public static Object parse(String value, Class<?> clazz) throws ClassCastException, NumberFormatException {
        if (clazz == Integer.class) {
            return Integer.parseInt(value);
        } else if (clazz == Short.class) {
            return Short.parseShort(value);
        } else if (clazz == Long.class) {
            return Long.parseLong(value);
        } else if (clazz == Double.class) {
            return Double.parseDouble(value);
        } else if (clazz == Float.class) {
            return Float.parseFloat(value);
        } else if (clazz == Byte.class) {
            return Byte.parseByte(value);
        } else if (clazz == Boolean.class) {
            return Boolean.parseBoolean(value);
        }
        throw new ClassCastException("Can't parse " + String.class.getName() + " to " + clazz.getName() );
    }
}

