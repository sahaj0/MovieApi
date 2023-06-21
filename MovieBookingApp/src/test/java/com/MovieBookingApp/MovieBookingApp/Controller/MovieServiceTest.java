package com.MovieBookingApp.MovieBookingApp.Controller;

import com.MovieBookingApp.MovieBookingApp.Model.Movie;
import com.MovieBookingApp.MovieBookingApp.Service.MovieService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class MovieServiceTest {

    @Mock
    MovieService movieService;

    @Test
    public void getMovie(){
        String url="/getMovie";

        Movie Movielist = new Movie();

        Movielist.setMovie_Id(1l);
        Movielist.setMovie_name("Dhoom");
        Movielist.setMovie_genre("Action");
        Movielist.setMovie_language("Hindi");
        Movielist.setTheater_name("Max");
        Movielist.setSeats_available(100);
        Movielist.setTotalTickets(100);
        Movielist.setImg_url("https://upload.wikimedia.org/wikipedia/en/f/f2/Fast_X_poster.jpg");
        Movielist.setTicketStatus("Available");

        List<Movie> list=new ArrayList<>();
        list.add(Movielist);

        List ls=new ArrayList<>();


        when(movieService.getAllMovie()).thenReturn(list);
        List<Movie> MList = movieService.getAllMovie();
        assertEquals(list, MList);

    }
}
