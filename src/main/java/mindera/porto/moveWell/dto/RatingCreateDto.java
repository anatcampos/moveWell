package mindera.porto.moveWell.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class RatingCreateDto {

    @Min(value = 1, message = "Invalid rating.")
    @Max(value = 5, message = "Invalid rating.")
    private Integer ratingStar;

    private String username;
    private String password;

    //@NotBlank(message = "Required field.")
    private Long videoId;


    public RatingCreateDto(Integer ratingStar) {
        this.ratingStar = ratingStar;
    }

    public Integer getRatingStar() {
        return ratingStar;
    }

    public void setRatingStar(Integer ratingStar) {
        this.ratingStar = ratingStar;
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

    public Long getVideoId() {
        return videoId;
    }

    public void setVideoId(Long videoId) {
        this.videoId = videoId;
    }

}
