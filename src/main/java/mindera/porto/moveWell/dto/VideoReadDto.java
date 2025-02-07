package mindera.porto.moveWell.dto;

public class VideoReadDto {

    private Long id;

    private String url;

    private Integer numberOfViews;

    private Double averageRating;

    private Integer numberOfRatings;

    public VideoReadDto(Long id, String url, Integer numberOfViews, Double averageRating, Integer numberOfRatings) {
        this.id = id;
        this.url = url;
        this.numberOfViews = numberOfViews;
        this.averageRating = averageRating;
        this.numberOfRatings = numberOfRatings;
    }

    public VideoReadDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getNumberOfViews() {
        return numberOfViews;
    }

    public void setNumberOfViews(Integer numberOfViews) {
        this.numberOfViews = numberOfViews;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    public Integer getNumberOfRatings() {
        return numberOfRatings;
    }

    public void setNumberOfRatings(Integer numberOfRatings) {
        this.numberOfRatings = numberOfRatings;
    }
}
