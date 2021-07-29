package softuni.exam.instagraphlite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import softuni.exam.instagraphlite.models.entity.Picture;

import java.util.List;

@Repository
public interface PictureRepository extends JpaRepository<Picture,Long> {

    boolean existsByPath(String path);

    Picture findByPath(String path);

    @Query(value = "SELECT p FROM Picture p WHERE p.size > :size ORDER BY p.size")
    List<Picture> findAllBySizeBigger(@Param("size") double size);
}


