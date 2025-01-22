package com.bookmyshow.payments.Controller;

import com.bookmyshow.payments.DTO.BookingStatusDTO;
import com.bookmyshow.payments.DTO.PaymentDTO;
import com.bookmyshow.payments.DTO.PaymentStatusDTO;
import com.bookmyshow.payments.Models.BookingStatus;
import com.bookmyshow.payments.Models.PaymentStatus;
import com.bookmyshow.payments.Services.PaymentService;

import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@Getter
@Setter
public class paymentController {


	private final PaymentService PaymentService;
	RestTemplate restTemplate;
	
	@Autowired
	paymentController(PaymentService PaymentService,RestTemplate RestTemplate){
		this.PaymentService=PaymentService;
		this.restTemplate=RestTemplate;
		
	}

	@PostMapping("/payments/pay")
    public ResponseEntity<Map<String, String>> initiatePayment(@RequestBody PaymentDTO PaymentDTO){
		Map<String, String> re = new HashMap<>();
		System.out.println(PaymentDTO.getBookingId());
		try{
			PaymentService.startPayment(PaymentDTO);
			re.put("Status","Payment Created please complete payment");
		} catch (Exception e) {
			re.put("Status","Error"+e.getMessage());
			System.out.println(e.getMessage());

		}
       return ResponseEntity.status(HttpStatus.OK).body(re);
    }

	@PostMapping("/payments/webhook")
	public ResponseEntity<Map<String, String>> PaymentStatus(@RequestBody PaymentStatusDTO PaymentStatusDTO) {
		Map<String, String> re = new HashMap<>();
		ResponseEntity<String> response;
		BookingStatusDTO BookingStatusDTO=new BookingStatusDTO();
		BookingStatusDTO.setPaymentID(PaymentStatusDTO.getPaymentID());
		BookingStatusDTO.setBookingId(PaymentStatusDTO.getBookingId());
		if(PaymentStatusDTO.getStatus()== PaymentStatus.Completed){
			BookingStatusDTO.setBookingStatus(BookingStatus.Booked);
		}
		else{
			BookingStatusDTO.setBookingStatus(BookingStatus.Cancelled);
		}
		try {

			PaymentService.updateStatus(PaymentStatusDTO);
			String url = "http://localhost:8080/Booking/clear"; // Replace with actual URL
			try {

				response = restTemplate.postForEntity(url, BookingStatusDTO,null);
				re.put(" Update","BookMyshow is updated");

			} catch (Exception e) {
				re.put("Error Update","BookMyshow is Notupdated, Will updated by Scheduler");
			}
			re.put("Status","Payment Action Updated");
		} catch (Exception e) {
			re.put("Error Status",e.getMessage());
		}



        return ResponseEntity.status(HttpStatus.OK).body(re);

    }
	
	
	
	
	

}
