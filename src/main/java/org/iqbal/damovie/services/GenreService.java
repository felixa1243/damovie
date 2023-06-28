package org.iqbal.damovie.services;

import org.iqbal.damovie.models.Genre;
import org.iqbal.damovie.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreService implements IGenreService {
    private final GenreRepository genreRepository;
    @Autowired
    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public Genre save(Genre genre) {
        return genreRepository.save(genre);
    }

    @Override
    public Genre getByName(String name) {
        Optional<Genre> result = genreRepository.findByGenreName(name);
        return result.orElse(null);
    }

    @Override
    public List<Genre> getAll() {
        return genreRepository.findAll();
    }
}
