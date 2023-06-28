package org.iqbal.damovie.repositories;

import org.iqbal.damovie.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    Optional<Movie> getAllByTitle(String title);
}
