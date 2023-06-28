package org.iqbal.damovie.controllers;

import org.iqbal.damovie.models.Movie;
import org.iqbal.damovie.models.requests.MovieRequest;
import org.iqbal.damovie.models.responses.SuccessResponse;
import org.iqbal.damovie.services.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final IMovieService movieService;

    @Autowired
    public MovieController(IMovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping
    ResponseEntity<SuccessResponse<Movie>> saveMovie(@RequestBody MovieRequest movieRequest) {
        return ResponseEntity.ok(new SuccessResponse<>("200", "OK", movieService.save(movieRequest)));
    }

    @GetMapping
    ResponseEntity<SuccessResponse<Movie>> getByTitle(@Param(value = "title") String title) {
        return ResponseEntity.ok(new SuccessResponse<>("200", "OK", movieService.getByTitle(title)));
    }
}
