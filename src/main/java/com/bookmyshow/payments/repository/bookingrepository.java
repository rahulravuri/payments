package com.bookmyshow.payments.repository;

import com.bookmyshow.payments.Models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface  bookingrepository  extends JpaRepository<Booking, Integer> {

    @Query(value = "SELECT s FROM Booking s   WHERE s.bookingID = :id")
    Booking findById(@Param("id") int id);

}
