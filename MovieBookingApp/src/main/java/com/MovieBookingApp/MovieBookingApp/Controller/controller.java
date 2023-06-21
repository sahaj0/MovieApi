package com.MovieBookingApp.MovieBookingApp.Controller;


import com.MovieBookingApp.MovieBookingApp.Execption.MovieNullException;
import com.MovieBookingApp.MovieBookingApp.Execption.SampleException;
//import com.MovieBookingApp.MovieBookingApp.Kafka.DataPublisherServiceImpl;
import com.MovieBookingApp.MovieBookingApp.Model.Movie;
import com.MovieBookingApp.MovieBookingApp.Repository.MovieRepo;
import com.MovieBookingApp.MovieBookingApp.Service.AuthService;
import com.MovieBookingApp.MovieBookingApp.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class controller {

    @GetMapping("/get")
    public String get(){
        return "heyyy";
    }

    @Autowired
    MovieService movieService;

    @Autowired
    AuthService authService;

//    @Autowired
//    DataPublisherServiceImpl producer;


    @PostMapping("/addMovie")
    public ResponseEntity<?> AddMovie(@RequestHeader("Authorization") String token ,@RequestBody Movie movie ) {

    authService.validateToken(token);
    System.out.println( authService.validateToken(token)+"  token");
    System.out.println(movie+"from front end");
       if(authService.validateToken(token).containsValue("admin")){
           if (movieService.addMovie(movie) != null) {

               System.out.println("inside");
//               producer.setTemp(" Movies is added Successfully");
               return new ResponseEntity<>( HttpStatus.OK);
           }
           return new ResponseEntity<>("Something went wrong", HttpStatus.NOT_FOUND);
       }
        return new ResponseEntity<>("Not Authorized",HttpStatus.FORBIDDEN);

    }


    @GetMapping("/getMovie")
    public ResponseEntity<?> getMovie(){

        List<Movie> Movielist = movieService.getAllMovie();

        if(Movielist!=null)
        {
//            producer.setTemp("All Movies Fetch Successfully");
            return new ResponseEntity<List<Movie>>(Movielist, HttpStatus.OK);
           // throw new SampleException("sample Exception");
        }

        return new ResponseEntity<String>("list is empty", HttpStatus.NOT_FOUND);
    }


    @GetMapping("/getMovieByName/{movie_name}")
    public ResponseEntity<?> getMovieByName(@PathVariable("movie_name") @RequestBody String name) throws MovieNullException {
     //Movie mov=mrepo.getMovieByName(name);
     Movie mov =movieService.getMovieByName(name);
     if(mov==null){
         return new ResponseEntity<>("Not found",HttpStatus.BAD_REQUEST);
     }

        if(mov.getSeats_available()<=20 ){
            movieService.getMovieByName(name);
//            producer.setTemp("Movies Fetch Successfully");
            return new ResponseEntity<>(mov ,HttpStatus.ACCEPTED);
        }
         return new ResponseEntity<>(mov ,HttpStatus.OK);
    }


    @DeleteMapping("/delete/{MovieId}")
    public ResponseEntity<?> deleteMovieById(@RequestHeader("Authorization") String token,@PathVariable("MovieId") Long MovieId)
    {
        System.out.println(token+"from front end");
       Map<String,String> info= authService.validateToken(token);
       if(info.containsValue("admin")){
           if(movieService.deleteMovie(MovieId)){

//               producer.setTemp("Movies is deleted Successfully");

               return new ResponseEntity<>(HttpStatus.OK);
           }
          return new ResponseEntity<>("Movie isn't present in database",HttpStatus.BAD_REQUEST);
       }
        return new ResponseEntity<String>("Movie could not be deleted", HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @PutMapping("/updateTicketStatus")
    public ResponseEntity<?> update(@RequestHeader("Authorization") String token,@RequestBody Movie movie){
       // String m=movie.getTicketStatus();

        Map<String,String> info= authService.validateToken(token);
        System.out.println(movie.getMovie_Id()+" hhh");
        Optional<?> op=Optional.ofNullable(movie.getMovie_Id());
        System.out.println(movie.getTicketStatus()+" hello");

       if(info.containsValue("admin")){
           if(op.isPresent() ){
               movieService.updateMovieStatus(movie.getTicketStatus(),movie.getMovie_Id());
             //  mrepo.UpdateIn(movie.getTicketStatus(),movie.getMovie_Id());
//               producer.setTemp("Status is updated Successfully");
               return  new ResponseEntity<>(HttpStatus.OK);
           }
           return new ResponseEntity<String>("Movie could not be Updated", HttpStatus.INTERNAL_SERVER_ERROR);
       }
        return new ResponseEntity<>("Not Authorized",HttpStatus.FORBIDDEN);
    }




    @GetMapping("/kafka/{msg}")
    public ResponseEntity<String> kafka(@PathVariable("msg") String msg){

        System.out.println(msg+" from kafka method");
//        producer.setTemp("I'm from kafka method");
        return new ResponseEntity<>("done",HttpStatus.ACCEPTED);

    }

}
