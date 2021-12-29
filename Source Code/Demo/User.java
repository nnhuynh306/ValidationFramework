package Demo;

import Demo.ClassValidator;
import Demo.UserValidator;
import annotations.ValidatedBy;
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
@ValidatedBy(validatorClass = ClassValidator.class)
public @interface User {
}
