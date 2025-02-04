package mindera.porto.moveWell.repository;

import mindera.porto.moveWell.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
