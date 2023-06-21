package com.MovieBookingApp.MovieBookingApp.Service;

import com.MovieBookingApp.MovieBookingApp.Model.Movie;
import com.MovieBookingApp.MovieBookingApp.Model.Ticket;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TicketService {

     List<Ticket> getTickets() ;


    //    public List<Ticket> getTicketTable(int b);


    boolean deleteTicket(long id);

    ResponseEntity<?> bookMovie(String movie_name, Ticket ticket);

    //  public ResponseEntity<?> bookMovie(String movieName, Ticket ticket);
//    public boolean deleteReader(int bid);
}
