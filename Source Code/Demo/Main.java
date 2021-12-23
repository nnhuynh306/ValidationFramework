package Demo;

import validators.result.ValidationResults;

public class Main {
    public static void main(String[] args) {
        UserValidator test = new UserValidator();
        TestUser user = new TestUser();
        user.setName("halo");
        ValidationResults results = new ValidationResults();
        System.out.println(test.validate(user, results));
    }
}
