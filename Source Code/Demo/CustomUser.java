package Demo;

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
