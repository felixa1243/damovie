package org.iqbal.damovie.services;

import org.iqbal.damovie.models.Genre;
import org.iqbal.damovie.models.Movie;
import org.iqbal.damovie.models.requests.MovieRequest;
import org.iqbal.damovie.repositories.MovieRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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
    public Movie getByTitle(String title) {
        Optional<Movie> result = this.movieRepository.getAllByTitle(title);
        return result.orElseThrow();
    }

    @Override
    @Transactional
    public Movie save(MovieRequest movieRequest) {
        Movie movie = modelMapper.map(movieRequest, Movie.class);
        //check if all genre is exists
        movieRequest.getGenresName().forEach(s -> {
            //if not exists then save a new genre to database
            Genre genre = genreService.getByName(s);
            if (genre == null) {
                genreService.save(new Genre(s));
            }
            //add genre into the movie
            movie.getMovieGenres().add(genre);
        });
        //save movie
        return movieRepository.save(movie);
    }

    @Override
    public Movie update(Long id, Movie movie) throws EntityNotFoundException {
        return movieRepository.findById(id).orElseThrow();
    }

    @Override
    public String delete(Long id) throws EntityNotFoundException {
        return "Movie with id " + id + " was successfully deleted!";
    }
}
