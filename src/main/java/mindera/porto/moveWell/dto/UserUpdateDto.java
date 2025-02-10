package mindera.porto.moveWell.dto;

import jakarta.validation.constraints.NotBlank;

public class UserUpdateDto {

    @NotBlank(message = "Required field.")
    private String username;

    @NotBlank(message = "Required field.")
    private String password;

    @NotBlank(message = "Required field.")
    private String newUsername;

    @NotBlank(message = "Required field.")
    private String newPassword;

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

    public String getNewUsername() {
        return newUsername;
    }

    public void setNewUsername(String newUsername) {
        this.newUsername = newUsername;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
