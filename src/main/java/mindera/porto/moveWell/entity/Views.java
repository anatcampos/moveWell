package mindera.porto.moveWell.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="views")
public class Views {

    private LocalDateTime dateOfView;

    @ManyToOne
    private User userViewer;

    @ManyToOne
    private Video videoViewed;

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
