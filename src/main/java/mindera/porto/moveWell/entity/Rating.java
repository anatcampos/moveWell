package mindera.porto.moveWell.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "ratings")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer ratingStar; //de 1 a 5 estrelas de avaliação

    @ManyToOne
    private Video video;

    @ManyToOne
    private User user;

    public Rating() {
    }

    public Rating(Long id, Integer ratingStar) {
        this.id = id;
        this.ratingStar = ratingStar;
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
}
