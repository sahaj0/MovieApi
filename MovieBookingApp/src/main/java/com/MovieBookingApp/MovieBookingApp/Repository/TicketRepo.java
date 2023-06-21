package com.MovieBookingApp.MovieBookingApp.Repository;

import com.MovieBookingApp.MovieBookingApp.Model.Ticket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;


@Repository
@Transactional
public interface TicketRepo extends JpaRepository<Ticket,Long> {


    @Query(value = "select * from ticket where transcation_id=?1",nativeQuery = true)
    public Ticket getTicket(long id);

    @Query(value="select t from Ticket t where t.movie_id_fk= :movie_id")
    public Ticket getBookedStatus(long movie_id);

}
