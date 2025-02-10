package mindera.porto.moveWell.dto;

import mindera.porto.moveWell.entity.User;

public class VideoDeleteDto {

    private Long id;
    private String username;
    private String password;
    private User userCreator;

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public User getUserCreator() {
        return userCreator;
    }

    public void setUserCreator(User userCreator) {
        this.userCreator = userCreator;
    }
}
