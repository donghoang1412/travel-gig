package com.synergisticit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.synergisticit.client.StripeClient;
import com.synergisticit.domain.MailRequest;
import com.synergisticit.domain.MailResponse;
import com.synergisticit.service.EmailSenderService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/payment")
public class PaymentGatewayController {
    
    private StripeClient stripeClient;
    @Autowired
    PaymentGatewayController(StripeClient stripeClient) {
        this.stripeClient = stripeClient;
    }
    @Autowired
	private EmailSenderService emailSenderService;
    
    @PostMapping("/charge")
    public ResponseEntity<?> chargeCard(@RequestHeader(value="token") String token, @RequestHeader(value="amount") long amount) throws Exception {
    	System.out.println("token "+token);
    	return ResponseEntity.ok(this.stripeClient.chargeCustomerMoney(token, amount));
    }
    
    @PostMapping(value="/sendMail")
    public ResponseEntity<?> sendMail(@RequestBody MailRequest mailRequest) {
    	emailSenderService.sendSimpleEmail(mailRequest.getTo(), "You have successfully booked a room from a hotel in our website", mailRequest.getSubject());
    	MailResponse mailResponse = new MailResponse();
    	mailResponse.setStatus("success");
    	return ResponseEntity.ok(mailResponse);
    }
    
    
    
    

}