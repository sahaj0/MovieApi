package com.MovieBookingApp.MovieBookingApp.Controller;
import com.MovieBookingApp.MovieBookingApp.Execption.MovieNullException;
import com.MovieBookingApp.MovieBookingApp.Model.Movie;
import com.MovieBookingApp.MovieBookingApp.Model.Ticket;
import com.MovieBookingApp.MovieBookingApp.Repository.TicketRepo;
import com.MovieBookingApp.MovieBookingApp.Service.AuthService;
import com.MovieBookingApp.MovieBookingApp.Service.MovieService;
import com.MovieBookingApp.MovieBookingApp.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("")
@CrossOrigin(origins = "*")
public class TicketController {

    @Autowired
    MovieService movieService;

    @Autowired
    TicketService ticketService;

    @Autowired
    TicketRepo tkrepo;

    @Autowired
    AuthService authService;



    @PostMapping("/MovieBook/{movie_name}")
    public ResponseEntity<?> BookMovie(@PathVariable("movie_name") String movie_name, @RequestBody Ticket ticket) throws MovieNullException {
      //  Movie movie=movieService.getMovieByName(movie_name);
        System.out.println(movie_name+" "+ticket.getNumberOfTickets()+ "fromm front end");
        System.out.println("from end");
        return ticketService.bookMovie(movie_name,ticket);
    }



     @GetMapping("/getAllTickets")
    public ResponseEntity<?> getTickets(){
         List<Ticket> tickets=ticketService.getTickets();
         return new ResponseEntity<>(tickets,HttpStatus.OK);
     }

     @DeleteMapping("/deleteTicket/{id}")
     public ResponseEntity<?> delete(@RequestHeader("Authorization") String token ,@PathVariable("id") long id) throws MovieNullException {
         {
             Map<String,String> info= authService.validateToken(token);
             if(info.containsValue("admin") && ticketService.deleteTicket(id))
             {
                 return new ResponseEntity<>(HttpStatus.OK);
             }
             return new ResponseEntity<String>("something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
         }
     }

}
