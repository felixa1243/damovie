package org.iqbal.damovie.repositories;

import org.iqbal.damovie.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre, String> {
    Optional<Genre> findByGenreName(String genreName);
}
