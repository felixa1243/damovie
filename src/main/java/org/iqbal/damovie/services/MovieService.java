package org.iqbal.damovie.services;

import org.iqbal.damovie.models.Genre;
import org.iqbal.damovie.models.Movie;
import org.iqbal.damovie.models.requests.MovieRequest;
import org.iqbal.damovie.repositories.MovieRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
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
    public Set<Movie> getAll() {
        return movieRepository.findAllMovie();
    }

    @Override
    public List<Movie> getByTitle(String title) {
        return movieRepository.findAllByTitle(title);
    }
    @Transactional
    @Override
    public Movie save(MovieRequest movieRequest) {
        Movie movie = modelMapper.map(movieRequest, Movie.class);
        List<Movie> searchResult = getByTitle(movie.getTitle());
        if (searchResult.size() > 0) {
            throw new DataIntegrityViolationException("Data is exists");
        }
        Set<Genre> genres = saveNotExistsGenres(movieRequest.getGenresName());
        movie.setGenres(genres);
        return movieRepository.save(movie);
    }

    @Override
    public Movie getById(String id) {
        return movieRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Movie with id "+id+" is not found!"));
    }

    @Override
    public Movie update(String id, MovieRequest movieRequest) throws EntityNotFoundException {
        Movie existingMovie = getById(id);
        movieRepository.save(existingMovie);
        Movie updatedMovie = modelMapper.map(existingMovie, Movie.class);
        existingMovie.setActive(false);
        updatedMovie.setTitle(movieRequest.getTitle());
        updatedMovie.setDirector(movieRequest.getDirector());
        updatedMovie.setSummary(movieRequest.getSummary());
        updatedMovie.setActive(true);
        Set<Genre> genres = saveNotExistsGenres(movieRequest.getGenresName());
        updatedMovie.setGenres(genres);
        // Save the updated movie
        return movieRepository.save(updatedMovie);
    }

    @Override
    public String delete(String id) throws EntityNotFoundException {
        getById(id);
        movieRepository.deleteById(id);
        return "Movie with id " + id + " was successfully deleted!";
    }

    private Set<Genre> saveNotExistsGenres(Set<String> stringGenres) {
        return stringGenres
                .stream()
                .map(genreName -> {
                    Genre genre = genreService.getByName(genreName);
                    if (genre == null) {
                        genre = new Genre(genreName);
                        genreService.save(genre);
                    }
                    return genre;
                })
                .collect(Collectors.toSet());
    }

}
