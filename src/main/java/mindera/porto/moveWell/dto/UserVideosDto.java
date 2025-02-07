package mindera.porto.moveWell.dto;

import mindera.porto.moveWell.entity.Video;

import java.util.List;

public class UserVideosDto {

    private Long id;
    private String username;
    private List<Video> videos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }
}
