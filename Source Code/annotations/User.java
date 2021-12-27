package annotations;

import Demo.ClassValidator;
import Demo.UserValidator;
import validators.AbstractValidator;
import validators.BaseValidator;
import validators.builtin.MinValidator;
import validators.builtin.NotNullValidator;
import validators.builtin.Rule;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@ValidatedBy(validatorClass = UserValidator.class)
public @interface User {
}
