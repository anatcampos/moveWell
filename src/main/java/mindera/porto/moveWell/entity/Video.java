package mindera.porto.moveWell.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "videos")
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String url;

    private Integer numberOfViews;

    private Double averageRating;

    private Integer numberOfRatings;

    @ManyToOne
    private User userCreator;

    @ManyToOne
    private Category category;

    @OneToMany(mappedBy = "video")
    private List<Rating> ratings;

    @OneToMany(mappedBy = "videoViewed")
    private List<Views> views;

    public Video() {
    }

    public Video(Long id, String url, Integer numberOfViews, Double averageRating, Integer numberOfRatings) {
        this.id = id;
        this.url = url;
        this.numberOfViews = numberOfViews;
        this.averageRating = averageRating;
        this.numberOfRatings = numberOfRatings;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumberOfRatings() {
        return numberOfRatings;
    }

    public void setNumberOfRatings(Integer numberOfRatings) {
        this.numberOfRatings = numberOfRatings;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    public Integer getNumberOfViews() {
        return numberOfViews;
    }

    public void setNumberOfViews(Integer numberOfViews) {
        this.numberOfViews = numberOfViews;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public User getUserCreator() {
        return userCreator;
    }

    public void setUserCreator(User userCreator) {
        this.userCreator = userCreator;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
