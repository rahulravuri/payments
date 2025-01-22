package com.bookmyshow.payments.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookingID;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="paymentId")
    private Payment payment;

    @Enumerated(EnumType.STRING)
    private BookingStatus BookingStatus;

    private LocalDateTime bookingTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;
}
