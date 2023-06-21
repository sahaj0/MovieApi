package com.MovieBookingApp.MovieBookingApp.Service;

import com.MovieBookingApp.MovieBookingApp.Model.Movie;
import com.MovieBookingApp.MovieBookingApp.Model.Ticket;
import com.MovieBookingApp.MovieBookingApp.Repository.MovieRepo;
import com.MovieBookingApp.MovieBookingApp.Repository.TicketRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TicketServiceImpl implements TicketService{
    @Autowired
    MovieRepo movieRepo;

    @Autowired
    TicketRepo ticketRepo;

    @Autowired
    MovieService movieService;

    @Override
    public List<Ticket> getTickets() {
        return ticketRepo.findAll();
    }



    @Override
    public boolean deleteTicket(long id) {
       Optional<?> del=Optional.ofNullable(id);

       Ticket tk= ticketRepo.getTicket(id);
        String movie=tk.getMovie_name();
        Movie mov = movieService.getMovieByName(movie);
        if(mov!=null){
            movieRepo.updateFromTicket(tk.getNumberOfTickets()+mov.getSeats_available(),movie);
        }
       if(del.isPresent()){
           ticketRepo.deleteById(id);
           return true;
       }
        return false;
    }

    @Override
    public ResponseEntity<?> bookMovie(String movie_name, Ticket ticket) {

      // Optional <Movie> movie = Optional.ofNullable(movieService.getMovieByName(movie_name));
       Optional <Movie> movie = Optional.ofNullable(movieService.getMovieByName(movie_name));
         if(movie.isPresent()){
             if(ticket.getNumberOfTickets()==0){
                 return new ResponseEntity<>("Please Book atleast one movie ",HttpStatus.BAD_REQUEST);
             }
             ticket.setBooking_date(new Date());
             ticket.setMovie_id_fk(movie.get().getMovie_Id());
             ticket.setMovie_name(movie.get().getMovie_name());
             ticket.setTheater_name(movie.get().getTheater_name());
             ticket.setNumberOfTickets(ticket.getNumberOfTickets());
             ticket.setTranscation_Id(ticket.getTranscation_Id());

             System.out.println(movie.get().getSeats_available() +" from movie");

             int availableTickets = movie.get().getSeats_available();


             if(availableTickets<=0) return new ResponseEntity<>("Sorry seats are not available",HttpStatus.BAD_REQUEST);
             else if (availableTickets<ticket.getNumberOfTickets())return new ResponseEntity<>("Only "+availableTickets +" seats are available !!",HttpStatus.BAD_REQUEST);


             System.out.println(availableTickets+" ----");
             System.out.println(ticket.getSeats_available()+" get from ticket");

             ticket.setSeats_available(movie.get().getSeats_available()- ticket.getNumberOfTickets());

             System.out.println(ticket.getSeats_available()+" ticket get"); //0

             movie.get().setSeats_available(ticket.getSeats_available()); //0

              //0
             System.out.println(availableTickets +" movie");

           int totalTicketsBuy = ticket.getNumberOfTickets(); //5
            // mov.setSeats_available();

             String status=movie.get().getTicketStatus();
             System.out.println(availableTickets+" movie vs ticket"+ " "+ totalTicketsBuy  );

             if(availableTickets>=totalTicketsBuy){
                  if(status.equals("Available")){
                      movieRepo.save(movie.get());
                      ticketRepo.save(ticket);
                      return new ResponseEntity<>( ticket, HttpStatus.OK);
                  }

                  else return new ResponseEntity<>(ticket.getMovie_name()+" Not Available at this moment",HttpStatus.NOT_FOUND);
             }
             return new ResponseEntity<>("all tickets sold out: ", HttpStatus.NO_CONTENT);

         }

        return new ResponseEntity<>("something went wrong: ", HttpStatus.NO_CONTENT);

    }

    }

