package org.iqbal.damovie.services;

import org.iqbal.damovie.models.Genre;

import java.util.List;

public interface IGenreService {
    Genre save(Genre genre);
    List<Genre> getAll();
    Genre getByName(String name);
}
