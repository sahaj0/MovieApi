package com.MovieBookingApp.MovieBookingApp.Model;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Optional;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Transient
    private Long Transcation_Id;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "movie_id")

    private String movie_name;

    private String theater_name;
    private Long movie_id_fk;

    private int seatNumber;

    private int numberOfTickets;

    private int seats_available;

    private Date booking_date;

    public long getMovieId() {
        return movie_id_fk;
    }

    public void setMovieId(long movieId) {
        this.movie_id_fk = movieId;
    }





}