package Demo;

import annotations.User;
import annotations.ValidatedBy;

public class CustomUser {

    @User
    TestUser testUser;

    public void setTestUser(TestUser testUser) {
        this.testUser = testUser;
    }

    public TestUser getTestUser() {
        return testUser;
    }
}
