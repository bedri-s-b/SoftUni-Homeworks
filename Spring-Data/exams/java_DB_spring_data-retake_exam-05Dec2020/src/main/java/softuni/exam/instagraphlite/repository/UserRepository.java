package softuni.exam.instagraphlite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.instagraphlite.models.entity.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    boolean existsByUserName(String userName);

    User findByUserName(String username);

    @Query("SELECT DISTINCT u FROM User u JOIN FETCH u.posts WHERE SIZE(u.posts) > 0 ORDER BY SIZE(u.posts) DESC ")
    List<User> findAllByPostCount();
}
