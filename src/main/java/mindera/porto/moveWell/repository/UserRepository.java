package mindera.porto.moveWell.repository;

import mindera.porto.moveWell.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    @Query("SELECT u FROM User u WHERE u.username = ?1 AND u.password = ?2")
    Optional<User> findUserByUsernameAndPassword(String username, String password);

    @Query("SELECT u FROM User u WHERE u.username = ?1")
    Optional<User> findUserByUsername(String username);


}
