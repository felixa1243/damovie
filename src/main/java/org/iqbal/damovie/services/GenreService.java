package org.iqbal.damovie.services;

import org.iqbal.damovie.models.Genre;
import org.iqbal.damovie.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return genreRepository.findByGenreName(name).orElse(null);
    }
}
