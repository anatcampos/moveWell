package mindera.porto.moveWell.dto;

import jakarta.validation.constraints.NotBlank;
import mindera.porto.moveWell.entity.Category;

public class VideoCreateDto {

    @NotBlank(message = "Required field.")
    private String url;
    private String username;
    private String password;

    @NotBlank(message = "Required field.")
    private Long categoryId;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
