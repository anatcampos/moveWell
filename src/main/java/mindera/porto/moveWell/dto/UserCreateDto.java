package mindera.porto.moveWell.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import mindera.porto.moveWell.entity.Role;

public class UserCreateDto {

    @NotBlank(message = "Required field.")
    private String name;

    @NotBlank(message = "Required field.")
    private String username;

    @NotBlank(message = "Required field.")
    private String password;

    @Min(value = 0, message = "Invalid age.")
    @Max(value = 120, message = "Invalid age.")
    private Integer age;

    private Role role;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
