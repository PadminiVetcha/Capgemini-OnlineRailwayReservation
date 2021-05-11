package org.padmini.railway.controller;
import java.util.List;

//imports
import javax.validation.Valid;
import org.padmini.railway.dao.UserPaymentRepository;
import org.padmini.railway.entity.PaymentDetails;
import org.padmini.railway.service.PaymentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pay")
public class PaymentController 
{
	@Autowired
	PaymentServiceImpl paySerImpl;
	
	@Autowired
	UserPaymentRepository userPayRepo;
	
	@GetMapping("/all")
	public List<PaymentDetails> getAll()
	{
		return paySerImpl.getAll();
	}
	
	 @PostMapping("/add/") 
	 public String addPaymentDetails(@Valid @RequestBody PaymentDetails payment) 
	 { 
		long pnrNo=payment.getPnrNo();
		String msg=("Your payment is successful for PNR Number " +pnrNo);
		paySerImpl.proceedToPay(payment); 
		paySerImpl.updateUserPaymentDetails(payment.getPnrNo());
		return msg;  
	 }
	 
	 @DeleteMapping("/cancel/{pnrNo}")
	 public String deletePaymentDetails(@PathVariable long pnrNo)
	 {
		 return paySerImpl.deletePayment(pnrNo);
	 }
}
