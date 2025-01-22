package com.bookmyshow.payments.Gateways;

import org.json.JSONObject;
import org.springframework.stereotype.Component;
import com.bookmyshow.payments.config.razorPayConfig;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@Component
public class RazorPayGateway implements paymentGateway {
	
	private  final RazorpayClient RazorpayClient ;
	
	
	RazorPayGateway( RazorpayClient RazorpayClient){
		this.RazorpayClient=RazorpayClient;
	}
	

	@Override
	public String generatePaymentLink(String orderId, String email, String phoneNumber, Long amount)  {
		try {
		// TODO Auto-generated method stub
		JSONObject paymentLinkRequest = new JSONObject();
		paymentLinkRequest.put("amount",1000);
		paymentLinkRequest.put("currency","INR");
		paymentLinkRequest.put("accept_partial",true);
		paymentLinkRequest.put("first_min_partial_amount",100);
		paymentLinkRequest.put("expire_by",1928331948);
		paymentLinkRequest.put("reference_id",orderId);
		paymentLinkRequest.put("description","Payment for policy no #23456");
		JSONObject customer = new JSONObject();
		customer.put("name","+919999999999");
		customer.put("contact","Gaurav Kumar");
		customer.put("email","gaurav.kumar@example.com");
		paymentLinkRequest.put("customer",customer);
		JSONObject notify = new JSONObject();
		notify.put("sms",true);
		notify.put("email",true);
		paymentLinkRequest.put("reminder_enable",true);
		JSONObject notes = new JSONObject();
		notes.put("policy_name","Jeevan Bima");
		paymentLinkRequest.put("notes",notes);
		paymentLinkRequest.put("callback_url","https://example-callback-url.com/");
		paymentLinkRequest.put("callback_method","get");
		              
		PaymentLink payment = RazorpayClient.paymentLink.create(paymentLinkRequest);
		
		return payment.get("short_url").toString();
	}catch(RazorpayException r) {
        throw new RuntimeException(r);
    }
	}

}
