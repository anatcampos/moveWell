package mindera.porto.moveWell.repository;

import mindera.porto.moveWell.entity.Rating;
import mindera.porto.moveWell.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
}
