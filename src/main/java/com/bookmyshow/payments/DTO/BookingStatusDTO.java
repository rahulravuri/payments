package com.bookmyshow.payments.DTO;

import com.bookmyshow.payments.Models.BookingStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingStatusDTO {

    private int BookingId;
    private int PaymentID;
    private BookingStatus BookingStatus;
}
