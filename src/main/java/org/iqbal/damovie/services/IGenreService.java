package org.iqbal.damovie.services;

import org.iqbal.damovie.models.Genre;

public interface IGenreService {
    Genre save(Genre genre);
    Genre getByName(String name);
}
