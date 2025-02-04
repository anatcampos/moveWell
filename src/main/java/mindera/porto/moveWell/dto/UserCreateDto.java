package mindera.porto.moveWell.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserCreateDto {

    @NotBlank(message = "Required field.")
    private String name;

    @NotBlank(message = "Required field.")
    private String username;

    @Min(value = 0, message = "Invalid age.")
    @Max(value = 120, message = "Invalid age.")
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
