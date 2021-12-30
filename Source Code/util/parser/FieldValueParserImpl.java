package util.parser;

import java.lang.reflect.Field;
import java.util.Objects;

@SuppressWarnings("unchecked")
public class FieldValueParserImpl<T, S> implements FieldValueParser<T, S> {
    @Override
    public S parseValue(Field field,T t, Class<S> clazz) throws Exception {
        try {
            return clazz.cast(field.get(t));
        } catch (IllegalAccessException e) {
            return clazz.cast(Objects.requireNonNull(FieldValueParser.getGetter(t, field)).invoke(t));
        }
    }
}
