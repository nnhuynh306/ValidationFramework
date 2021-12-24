package validators.annotation.parser;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public interface FieldValueParser<T, S> {
     S parseValue(Field field, T t, Class<S> clazz) throws Exception;

      static <T> Method getGetter(T t, Field field) {
          String name = field.getName();
          String methodName = "get" + name.substring(0, 1).toUpperCase() + name.substring(1);

          try {
               return t.getClass().getDeclaredMethod(methodName);
          } catch (NoSuchMethodException | SecurityException ex) {
               return null;
          }
     }
}
