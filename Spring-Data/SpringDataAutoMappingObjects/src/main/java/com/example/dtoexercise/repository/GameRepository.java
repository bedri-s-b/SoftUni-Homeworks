package com.example.dtoexercise.repository;

import com.example.dtoexercise.model.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Repository
@Transactional
public interface GameRepository extends JpaRepository<Game, Long> {

    @Modifying
    @Query(value = "UPDATE Game g SET g.title = :title WHERE g.id = :gameId")
    void editTitle(@Param(value = "gameId") long gameId
            , @Param(value = "title") String title);

    @Modifying
    @Query(value = "UPDATE Game g SET g.price = :newPrice WHERE g.id = :gameId")
    void editPrice(@Param(value = "gameId") long gameId,
                   @Param(value = "newPrice") BigDecimal newPrice);

    @Modifying
    @Query(value = "UPDATE Game g SET g.size = :size WHERE g.id = :gameId")
    void editSize(@Param(value = "gameId") long gameId,
                  @Param(value = "size") double size);

    @Modifying
    @Query(value = "UPDATE Game g SET g.trailer = :trailer WHERE g.id = :gameId")
    void editTrailer(@Param(value = "gameId") long gameId,
                     @Param(value = "trailer") String trailer);

    @Modifying
    @Query(value = "UPDATE Game g SET g.imageThumbnail = :thumbnail WHERE g.id = :gameId")
    void editThumbnailURL(@Param(value = "gameId") long gameId,
                          @Param(value = "thumbnail") String thumbnail);

 }
