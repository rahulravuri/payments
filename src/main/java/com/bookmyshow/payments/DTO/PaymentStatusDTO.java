package com.bookmyshow.payments.DTO;

import com.bookmyshow.payments.Models.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentStatusDTO {

    private int bookingId;
    private int paymentID;
    private PaymentStatus status;
}
