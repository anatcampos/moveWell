package mindera.porto.moveWell.dto;

public class videoCreateDto {

    private String url;

    private Double averageRating;

    public videoCreateDto(String url, String averageRating) {
        this.url = url;
        this.averageRating = averageRating;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(String averageRating) {
        this.averageRating = averageRating;
    }
}
