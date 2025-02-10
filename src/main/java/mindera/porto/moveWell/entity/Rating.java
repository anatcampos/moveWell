package mindera.porto.moveWell.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ratings")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer ratingStar; // de 1 a 5 estrelas de avaliação

    @ManyToOne
    private Video video;

    @ManyToOne
    private User user;

    public Rating() {
    }

    public Rating(Long id, Integer ratingStar, Video video, User user) {
        this.id = id;
        this.ratingStar = ratingStar;
        this.video = video;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRatingStar() {
        return ratingStar;
    }

    public void setRatingStar(Integer ratingStar) {
        this.ratingStar = ratingStar;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

//    @Override
//    public String toString() {
//        return "Rating{" +
//                "id=" + id +
//                ", ratingStar=" + ratingStar +
//                ", video=" + video +
//                ", user=" + user +
//                '}';
//    }
}
