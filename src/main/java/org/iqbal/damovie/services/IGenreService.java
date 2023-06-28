package org.iqbal.damovie.services;

import org.iqbal.damovie.models.Genre;

import java.util.List;

public interface IGenreService {
    Genre save(Genre genre);
    Genre getByName(String name);
    List<Genre> getAll();
}
