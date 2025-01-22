package com.bookmyshow.payments.Services;

import com.bookmyshow.payments.DTO.PaymentDTO;
import com.bookmyshow.payments.DTO.PaymentStatusDTO;
import com.bookmyshow.payments.Mapper.PaymentMapper;

import com.bookmyshow.payments.Models.BookingStatus;
import com.bookmyshow.payments.Models.PaymentStatus;
import com.bookmyshow.payments.repository.paymentrepository;
import com.bookmyshow.payments.Models.Booking;
import com.bookmyshow.payments.Models.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookmyshow.payments.Gateways.paymentGateway;

@Service
public class PaymentService {
	
	private PaymentGatewayFactory PaymentGatewayFactory;

	BookingService BookingService;PaymentMapper PaymentMapper;
	paymentrepository paymentrepository;

	@Autowired
	public PaymentService(PaymentGatewayFactory PaymentGatewayFactory, BookingService BookingService, paymentrepository paymentrepository){
		this.PaymentGatewayFactory=PaymentGatewayFactory;
		this.BookingService=BookingService;this.paymentrepository=paymentrepository;
	}
	
	public String startPayment(PaymentDTO PaymentDTO) {
		try {
			Booking b = BookingService.getbookingbyid(PaymentDTO.getBookingId());
			Payment p=PaymentMapper.createPayment(PaymentDTO,b);
			b.setPayment(p);
			paymentrepository.save(p);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return PaymentGatewayFactory.getrequestedGateway().generatePaymentLink(String.valueOf(PaymentDTO.getBookingId()), null, null, null);
	}


	public String updateStatus(PaymentStatusDTO paymentStatusDTO) {

		System.out.println(paymentStatusDTO.getPaymentID());
		Payment p=paymentrepository.findById(paymentStatusDTO.getPaymentID()).orElseThrow(() -> new RuntimeException("Payment not found for ID: " + paymentStatusDTO.getPaymentID()));
		if(p.getPaymentStatus()!=PaymentStatus.Pending){
			throw new RuntimeException("Payment action Already Completed");
		}
		p.setPaymentStatus(paymentStatusDTO.getStatus());
		if(paymentStatusDTO.getStatus()!=PaymentStatus.Completed){
			p.getBooking().setBookingStatus(BookingStatus.Booked);
		}
		else{
			p.getBooking().setBookingStatus(BookingStatus.Cancelled);
		}

		paymentrepository.save(p);

		return "Status Updated";
	}
}
