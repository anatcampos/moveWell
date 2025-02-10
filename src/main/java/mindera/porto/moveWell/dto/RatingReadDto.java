package mindera.porto.moveWell.dto;

public class RatingReadDto {

    private Long ratingId;

    private Integer ratingStar;
    private Integer totalRatings;
    private Double averageRating;


    public RatingReadDto(Long ratingId, Integer ratingStar, Integer totalRatings, Double averageRating) {
        this.ratingId = ratingId;
        this.ratingStar = ratingStar;
        this.totalRatings= totalRatings;
        this.averageRating= averageRating;
    }

    public RatingReadDto() {

    }

    public Long getRatingId() {
        return ratingId;
    }

    public void setRatingId(Long ratingId) {
        this.ratingId = ratingId;
    }

    public Integer getRatingStar() {
        return ratingStar;
    }

    public void setRatingStar(Integer ratingStar) {
        this.ratingStar = ratingStar;
    }

    public Integer getTotalRatings() {
        return totalRatings;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    public void setTotalRatings(Integer totalRatings) {
        this.totalRatings = totalRatings;
    }
}
