package Demo;

import validators.annotation.AnnotationValidator;
import validators.annotation.parser.AnnotatedClassParserImpl;
import validators.annotation.parser.FieldValueParserImpl;
import validators.BaseValidator;
import validators.annotation.AnnotatedFieldValidator;
import validators.result.ValidationResults;

public class Main {
    public static void main(String[] args) {
        UserValidator test = new UserValidator();
        TestUser user = new TestUser();
        user.setName("1234");
        ValidationResults results = new ValidationResults();
        System.out.println(test.validate(user, results));
        System.out.println(new AnnotationValidator<TestUser>(new AnnotatedClassParserImpl<>()).validate(user, results));
    }
}
