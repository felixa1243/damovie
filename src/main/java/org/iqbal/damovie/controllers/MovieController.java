package org.iqbal.damovie.controllers;

import org.iqbal.damovie.models.Genre;
import org.iqbal.damovie.models.Movie;
import org.iqbal.damovie.models.requests.MovieRequest;
import org.iqbal.damovie.models.responses.SuccessResponse;
import org.iqbal.damovie.services.IGenreService;
import org.iqbal.damovie.services.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final IMovieService movieService;
    private final IGenreService genreService;

    @Autowired
    public MovieController(IMovieService movieService, IGenreService genreService) {
        this.movieService = movieService;
        this.genreService = genreService;
    }

    @GetMapping
    ResponseEntity<SuccessResponse<Set<Movie>>> getAllMovie() {
        return ResponseEntity.ok(new SuccessResponse<>("200", "success get all data", movieService.getAll()));
    }

    @PostMapping
    ResponseEntity<SuccessResponse<Movie>> saveMovie(@Valid @RequestBody MovieRequest movieRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>("201", "success data", movieService.save(movieRequest)));
    }

    @GetMapping("q")
    ResponseEntity<SuccessResponse<List<Movie>>> getByTitle(@Param(value = "title") String title) {
        return ResponseEntity.ok(new SuccessResponse<>("200", "success get all data", movieService.getByTitle(title)));
    }

    @PutMapping("{id}")
    ResponseEntity<SuccessResponse<Movie>> updateMovie(@PathVariable("id") String id, @Valid @RequestBody MovieRequest movieRequest) {
        return ResponseEntity.ok(new SuccessResponse<>("201", "success update data", movieService.update(id, movieRequest)));
    }

    @DeleteMapping("{id}")
    ResponseEntity<SuccessResponse<String>> deleteMovie(@PathVariable String id) {
        return ResponseEntity.ok(new SuccessResponse<>("200", "delete data success", movieService.delete(id)));
    }

    @GetMapping("{id}")
    ResponseEntity<SuccessResponse<Movie>> getById(@PathVariable String id) {
        return ResponseEntity.ok(new SuccessResponse<>("200", "success get data", movieService.getById(id)));
    }

    @GetMapping("/genres")
    ResponseEntity<SuccessResponse<List<Genre>>> getAllGenre() {
        return ResponseEntity.ok(new SuccessResponse<>("200", "success get all data", genreService.getAll()));
    }
}
