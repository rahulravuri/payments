package com.bookmyshow.payments.Services;

import org.springframework.stereotype.Service;

import com.bookmyshow.payments.Gateways.RazorPayGateway;
import com.bookmyshow.payments.Gateways.paymentGateway;

@Service
public class PaymentGatewayFactory {
	private RazorPayGateway RazorPayGateway;
	
	public PaymentGatewayFactory(RazorPayGateway RazorPayGateway) {
		this.RazorPayGateway=RazorPayGateway;
		
	}
	
	public paymentGateway getrequestedGateway() {
		return RazorPayGateway;
	}
	
	
	
	

}
