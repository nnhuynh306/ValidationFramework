package Demo;

import annotations.Min;

public class TestUser {
    @Min(value = "5")
    private String name = "12";

    @Min(value = "5")
    public Integer age = 13;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }
}
