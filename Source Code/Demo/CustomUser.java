package Demo;

import annotations.Nested;

public class CustomUser {

    @Nested
    TestUser testUser;

    public void setTestUser(TestUser testUser) {
        this.testUser = testUser;
    }

    public TestUser getTestUser() {
        return testUser;
    }
}
