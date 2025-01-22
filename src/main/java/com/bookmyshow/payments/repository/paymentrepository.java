package com.bookmyshow.payments.repository;

import com.bookmyshow.payments.Models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface paymentrepository extends JpaRepository<Payment, Integer> {



}