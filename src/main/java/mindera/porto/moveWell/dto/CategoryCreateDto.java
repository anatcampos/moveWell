package mindera.porto.moveWell.dto;

import jakarta.validation.constraints.Pattern;

public class CategoryCreateDto {

    //@Pattern(regexp = "(?i)joelho|ombro|tornozelo|lombar|coxa", message = "Invalid area.")
    private String area;

    private String username;
    private String password;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
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
}
