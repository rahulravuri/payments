package com.bookmyshow.payments.Services;

import com.bookmyshow.payments.repository.bookingrepository;
import com.bookmyshow.payments.Models.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    @Autowired
    bookingrepository bookingrepository;

    public Booking getbookingbyid(int id){
        Booking b=bookingrepository.findById(id);
        System.out.println(id);

        return b;

    }
}
