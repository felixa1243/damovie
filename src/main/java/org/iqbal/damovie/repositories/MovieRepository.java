package org.iqbal.damovie.repositories;

import org.iqbal.damovie.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface MovieRepository extends JpaRepository<Movie, String> {
    @Query(value = "SELECT m FROM Movie m WHERE LOWER(m.title) LIKE LOWER(CONCAT('%', :title, '%')) AND m.isActive = true ")
    List<Movie> findAllByTitle(@Param("title") String title);

    @Query(value = "SELECT * FROM movies m JOIN movie_genres mg ON m.movie_id = mg.movie_id WHERE m.is_active = true", nativeQuery = true)
    Set<Movie> findAllMovie();

    @Modifying
    @Query(value = "UPDATE Movie SET isActive = false WHERE id = :id")
    void deleteById(@Param("id") String id);
}
