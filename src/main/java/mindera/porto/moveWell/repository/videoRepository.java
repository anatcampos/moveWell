package mindera.porto.moveWell.repository;

import mindera.porto.moveWell.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface videoRepository extends JpaRepository <Video, Long> {
}
