package com.bookmyshow.payments.DTO;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentDTO {
    private int bookingId;
    private int userId;
    private int amount;
}
