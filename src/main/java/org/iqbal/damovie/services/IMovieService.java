package org.iqbal.damovie.services;

import org.iqbal.damovie.models.Movie;
import org.iqbal.damovie.models.requests.MovieRequest;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Set;

public interface IMovieService {
    List<Movie> getByTitle(String title);

    Set<Movie> getAll();
    Movie getById(String id);

    Movie save(MovieRequest movieRequest);

    Movie update(String id, MovieRequest movie) throws EntityNotFoundException;

    String delete(String id) throws EntityNotFoundException;
}
