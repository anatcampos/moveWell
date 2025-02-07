package mindera.porto.moveWell.repository;

import mindera.porto.moveWell.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
public interface VideoRepository extends JpaRepository <Video, Long> {

    @Query("SELECT v FROM Video v ORDER BY v.id DESC LIMIT 3")
    List<Video> findLast20Videos();

    List<Video> findByCategoryId (Long categoryId);
}
