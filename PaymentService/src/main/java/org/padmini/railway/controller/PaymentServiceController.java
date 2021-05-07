package org.padmini.railway.controller;
//imports
import javax.validation.Valid;

import org.padmini.railway.entity.PaymentDetails;
import org.padmini.railway.service.PaymentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pay")
public class PaymentServiceController 
{
	@Autowired
	PaymentServiceImpl paySerImpl;
	
	 @PostMapping("/add/") 
	 public void addPaymentDetails(@Valid @RequestBody PaymentDetails payment) 
	 { 
		  paySerImpl.proceedToPay(payment); 
		 paySerImpl.updateUserPaymentDetails(payment.getPnrNo());
		  
	 }
}
