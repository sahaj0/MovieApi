package com.MovieBookingApp.MovieBookingApp.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long movie_Id;
    private String movie_name;

    private String movie_genre;
    private String movie_language;
    private String theater_name;
    private int seats_available;

    private int totalTickets;

    private String img_url;

    private String ticketStatus = "NA";

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="movie_id_fk",referencedColumnName="movie_id")
    private List<Ticket> ticketList=new ArrayList<>();

//    public Movie(Long movie_Id, String movie_name, String movie_genre, String movie_language, String theater_name, int seats_available) {
//        this.movie_Id = movie_Id;
//        this.movie_name = movie_name;
//        this.movie_genre = movie_genre;
//        this.movie_language = movie_language;
//        this.theater_name = theater_name;
//        this.seats_available = seats_available;
//    }
//
//    public Long getMovie_Id() {
//        return movie_Id;
//    }
//
//    public void setMovie_Id(Long movie_Id) {
//        this.movie_Id = movie_Id;
//    }
//
//    public String getMovie_name() {
//        return movie_name;
//    }
//
//    public void setMovie_name(String movie_name) {
//        this.movie_name = movie_name;
//    }
//
//    public String getMovie_genre() {
//        return movie_genre;
//    }
//
//    public void setMovie_genre(String movie_genre) {
//        this.movie_genre = movie_genre;
//    }
//
//    public String getMovie_language() {
//        return movie_language;
//    }
//
//    public void setMovie_language(String movie_language) {
//        this.movie_language = movie_language;
//    }
//
//    public String getTheater_name() {
//        return theater_name;
//    }
//
//    public void setTheater_name(String theater_name) {
//        this.theater_name = theater_name;
//    }
//
//    public int getSeats_available() {
//        return seats_available;
//    }
//
//    public void setSeats_available(int seats_available) {
//        this.seats_available = seats_available;
//    }
//
//    public Movie(){
//
//    }

//  @OneToMany(targetEntity= Ticket.class)

//    @OneToMany(fetch = FetchType.LAZY)
//    @JoinColumn(name = "movie_id_fk")
//    private List<Ticket> TicketList;

}