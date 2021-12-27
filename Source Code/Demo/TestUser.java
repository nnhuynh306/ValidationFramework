package Demo;

import annotations.Min;

public class TestUser {

    @Min(value = "1")
    private String name = "12";

    @Min(value = "0")
    public Integer age = 1;

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
