package com.MovieBookingApp.MovieBookingApp.Service;

import com.MovieBookingApp.MovieBookingApp.Execption.MovieNullException;
import com.MovieBookingApp.MovieBookingApp.Model.Movie;
import com.MovieBookingApp.MovieBookingApp.Model.Ticket;
import com.MovieBookingApp.MovieBookingApp.Repository.MovieRepo;
import com.MovieBookingApp.MovieBookingApp.Repository.TicketRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements  MovieService{
    @Autowired
    MovieRepo mrepo;

    @Autowired
    TicketRepo tRepo;


    @Override
    public List<Movie> getAllMovie() {
        List<Movie> movie=mrepo.findAll();
        if(movie !=null && movie.size() >0)
        {
            return movie;
        }
        return null;
    }

    @Override
    public Movie getMovieById(long movieId) throws MovieNullException {

        Optional<Movie> movie = mrepo.findById(movieId);
        if(movie.isPresent()) {
            return movie.get();

        }
        throw new MovieNullException("movie is not present");

    }

    @Override
    public boolean update(Movie movie) {
        Ticket tt=tRepo.getBookedStatus(movie.getMovie_Id());
       System.out.println(tt+" dekhoooo");

      tt.setSeats_available(movie.getSeats_available());
      tt.setTheater_name(movie.getTheater_name());
      tt.setMovie_name(movie.getMovie_name());

      tRepo.save(tt);
      mrepo.save(movie);
      //  mrepo.UpdateIn(movie.getTicketStatus(),movie.getMovie_Id());
        return true;
    }

    @Override
    public boolean updateMovieStatus(String ticketStatus, Long movie_id) {
        mrepo.UpdateIn(ticketStatus,movie_id);
        return true;
    }


    @Override
    public Movie addMovie(Movie movie) {

//        List<Ticket> tk=new ArrayList<>();
//       movie.setTicketList(new ArrayList<>());
        System.out.println("inside from repo");
        return mrepo.save(movie);
    }

    @Override
    public boolean deleteMovie(Long movieId) {
       Optional<Movie> obj= mrepo.findById(movieId);
       if(obj.isPresent()){
           System.out.println(obj+"from db");
           mrepo.deleteById(movieId);
           return true;
       }
        return false;

    }

    @Override
    public Movie getMovieByName(String movie_name) {
        Optional<Movie> movie = mrepo.findByMovieName(movie_name);
        if(movie.isPresent()) {
            return mrepo.getMovieByName(movie_name);

        }

        return null;
    }

    @Override
    public boolean updateByMovieName(Movie movie) {
       Movie name= mrepo.getMovieByName(movie.getMovie_name());
       String status=movie.getTicketStatus();
       System.out.println(status+" ssss");
        if(name!=null)
        {
         //   name.setBookedTickets(movie.getBookedTickets());
        //    book1.setBookPrice(book.getBookPrice());
      //      bookRepo.saveAndFlush(book1);
       //     return true;
       //     movie.setMovie_Id(movie.getMovie_Id());
           // name.setSeats_available(movie.getSeats_available());
            mrepo.save(name);
            return true;
        }
        return false;
    }

}
