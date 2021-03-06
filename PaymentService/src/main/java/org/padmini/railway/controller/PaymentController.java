package org.padmini.railway.controller;
import java.util.List;
import javax.validation.Valid;
import org.padmini.railway.dao.UserPaymentRepository;
import org.padmini.railway.entity.PaymentDetails;
import org.padmini.railway.service.PaymentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/pay")
public class PaymentController 
{
	@Autowired
	PaymentServiceImpl paySerImpl;
	
	@Autowired
	UserPaymentRepository userPayRepo;
	
	@GetMapping("/all")
	@ApiOperation(value="Get all users who completed payment")
	public List<PaymentDetails> getAll()
	{
		return paySerImpl.getAll();
	}
	
	 @PostMapping("/add/") 
	 @ApiOperation(value="Inorder to proceed to payment")
	 public String addPaymentDetails(@Valid @RequestBody PaymentDetails payment) 
	 { 
		long pnrNo=payment.getPnrNo();
		paySerImpl.proceedToPay(payment); 
		paySerImpl.updateUserPaymentDetails(payment.getPnrNo());
		String sentMsg=paySerImpl.sendNotification("Your payment is successful for PNR Number " +pnrNo,pnrNo);
		return sentMsg;  
	 }
	 
	 @RequestMapping( value = "/cancel/{pnrNo}", method = {RequestMethod.GET, RequestMethod.DELETE})
	 @ApiOperation(value="Inorder to cancel your payment")
	 public String deletePaymentDetails(@PathVariable long pnrNo)
	 {
		 return paySerImpl.deletePayment(pnrNo);
	 }
	 
	 @GetMapping("/add/receiveNotification")
	 public void ReceiveMsg()  {
		 try {
			paySerImpl.receiveNotification();
		} catch (Exception e) {
			e.printStackTrace();
		}
	 }
}