package org.iqbal.damovie.services;

import org.iqbal.damovie.models.Genre;
import org.iqbal.damovie.models.Movie;
import org.iqbal.damovie.models.requests.MovieRequest;
import org.iqbal.damovie.repositories.MovieRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MovieService implements IMovieService {
    private final MovieRepository movieRepository;
    private final IGenreService genreService;
    private final ModelMapper modelMapper;

    @Autowired
    public MovieService(MovieRepository movieRepository, IGenreService genreService, ModelMapper modelMapper) {
        this.movieRepository = movieRepository;
        this.genreService = genreService;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Movie> getAll() {
        return movieRepository.findAll();
    }

    @Override
    public List<Movie> getByTitle(String title) {
        return movieRepository.findAllByTitle(title);
    }

    @Override
    public Movie save(MovieRequest movieRequest) {
        Movie movie = modelMapper.map(movieRequest, Movie.class);
        System.out.println(movieRequest.getGenresName());
        // check if all genres exist
        Set<Genre> genres = movieRequest.getGenresName().stream().map(genreName -> {
            Genre genre = genreService.getByName(genreName);
            if (genre == null) {
                genre = new Genre(genreName);
                genreService.save(genre);
            }
            return genre;
        }).collect(Collectors.toSet());
        movie.setMovieGenres(genres);
        return movieRepository.save(movie);
    }


    @Override
    public Movie update(String id, Movie movie) throws EntityNotFoundException {
        Movie existingMovie = movieRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Movie with id " + id + " not found"));
        movieRepository.save(existingMovie);
        Movie updatedMovie = modelMapper.map(existingMovie, Movie.class);
        existingMovie.setActive(false);
        updatedMovie.setTitle(movie.getTitle());
        updatedMovie.setDirector(movie.getDirector());
        updatedMovie.setSummary(movie.getSummary());
        updatedMovie.setActive(true);
        updatedMovie.setMovieGenres(movie.getMovieGenres());
        // Save the updated movie
        return movieRepository.save(updatedMovie);
    }

    @Override
    public String delete(String id) throws EntityNotFoundException {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Movie with id " + id + " not found"));
        movieRepository.deleteById(id);
        return "Movie with id " + id + " was successfully deleted!";
    }

}
