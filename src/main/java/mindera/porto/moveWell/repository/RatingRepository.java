package mindera.porto.moveWell.repository;

import mindera.porto.moveWell.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository <Rating, Long> {

    @Query("SELECT AVG(r.ratingStar) FROM Rating r WHERE r.video.id = :videoId")
    Double getAverageRatingByVideo(@Param("videoId") Long videoId);

    @Query("SELECT AVG(r.ratingStar) FROM Rating r WHERE r.user.id = :userId")
    Double getAverageRatingByUser(@Param("userId") Long userId);
}
