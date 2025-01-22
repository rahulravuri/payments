package com.bookmyshow.payments.Gateways;

import com.razorpay.RazorpayException;

public interface paymentGateway {
	
	String generatePaymentLink(String orderId, String email, String phoneNumber, Long amount) ; 

}
