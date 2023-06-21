package com.MovieBookingApp.MovieBookingApp.Service;

import com.MovieBookingApp.MovieBookingApp.Execption.MovieNullException;
import com.MovieBookingApp.MovieBookingApp.Model.Movie;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MovieService {

    public List<Movie> getAllMovie();
    public Movie addMovie(Movie movie) ;


    boolean deleteMovie(Long movieId);

  //  Movie getMovieByName(Movie movieName);

   Movie getMovieByName(String movieName);

   boolean updateByMovieName(Movie movie_name);

    Movie getMovieById(long movieId) throws MovieNullException;

    boolean update(Movie movie);

    boolean updateMovieStatus(String ticketStatus, Long movie_id);
}
