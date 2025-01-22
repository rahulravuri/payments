package com.bookmyshow.payments.Mapper;

import com.bookmyshow.payments.DTO.PaymentDTO;
import com.bookmyshow.payments.Models.Booking;
import com.bookmyshow.payments.Models.Payment;
import com.bookmyshow.payments.Models.PaymentStatus;

import java.time.LocalDateTime;

public class PaymentMapper {

    public static Payment createPayment(PaymentDTO PaymentDTO, Booking Booking){

        return  Payment.builder().price(PaymentDTO.getAmount()).paymentStatus(PaymentStatus.Pending)
                .booking(Booking)
                .paymenttime(LocalDateTime.now())
                .build();
    }
}
