package org.iqbal.damovie.repositories;

import org.iqbal.damovie.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, String> {
    @Query(nativeQuery = true, value = "SELECT * FROM movies WHERE LOWER(title) LIKE LOWER(CONCAT('%', :title, '%')) AND is_active = true")
    List<Movie> findAllByTitle(@Param("title") String title);
    @Query(value = "SELECT m FROM Movie m JOIN m.movieGenres mg WHERE m.isActive = true")
    List<Movie> findAll();

    @Modifying
    @Query(value = "UPDATE Movie SET isActive = false WHERE id = :id")
    void deleteById(@Param("id") String id);

}
