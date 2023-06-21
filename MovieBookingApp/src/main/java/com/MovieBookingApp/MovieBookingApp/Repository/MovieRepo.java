package com.MovieBookingApp.MovieBookingApp.Repository;

import com.MovieBookingApp.MovieBookingApp.Model.Movie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;


@Repository
@Transactional
public interface MovieRepo extends JpaRepository<Movie,Long> {

    @Query(value = "select * from movie where movie_name=?1",nativeQuery = true)
    public Movie getMovieByName(String movie_name);

    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE  Movie m SET m.ticketStatus =?1 where m.movie_Id=?2")
    public void UpdateIn( String ticketStatus,Long movie_Id );





  //  @Query(value ="select m from Movie m where m.movie_name =:e" )
  //  Optional<Movie> findByMovieName(@Param("e")  String movie_name);

    @Query(value ="select * from movie where movie_name =?1" ,nativeQuery = true)
    Optional<Movie> findByMovieName( String movie_name);



    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE Movie m SET m.seats_available=?1 where m.movie_name=?2")
   public void updateFromTicket(int seats_available,String movie);
}
