package org.iqbal.damovie.services;

import org.iqbal.damovie.models.Movie;
import org.iqbal.damovie.models.requests.MovieRequest;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public interface IMovieService {
    List<Movie> getByTitle(String title);

    List<Movie> getAll();

    Movie save(MovieRequest movieRequest);

    Movie update(String id, Movie movie) throws EntityNotFoundException;

    String delete(String id) throws EntityNotFoundException;
}
