package mindera.porto.moveWell.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="views")
public class Views {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateOfView;

    @ManyToOne
    private User userViewer;

    @ManyToOne
    private Video videoViewed;

    public Views() {
    }

    public LocalDateTime getDateOfView() {
        return dateOfView;
    }

    public void setDateOfView(LocalDateTime dateOfView) {
        this.dateOfView = dateOfView;
    }

    public User getUserViewer() {
        return userViewer;
    }

    public void setUserViewer(User userViewer) {
        this.userViewer = userViewer;
    }

    public Video getVideoViewed() {
        return videoViewed;
    }

    public void setVideoViewed(Video videoViewed) {
        this.videoViewed = videoViewed;
    }
}
