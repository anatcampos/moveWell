package mindera.porto.moveWell.repository;

import mindera.porto.moveWell.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{
}
