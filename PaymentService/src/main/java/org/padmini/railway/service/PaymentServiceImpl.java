package org.padmini.railway.service;

import java.util.List;

import org.padmini.railway.dao.UserPaymentRepository;
import org.padmini.railway.dao.UserRepository;
import org.padmini.railway.entity.PaymentDetails;
import org.padmini.railway.entity.UserDetails;
import org.padmini.railway.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService
{
	int id;
	
	@Autowired
	UserPaymentRepository userPayRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@Override
	public List<PaymentDetails> getAll() {
		List<PaymentDetails> payDetails=userPayRepo.findAll();
		return payDetails;
	}
	
	@Override
	public void proceedToPay(PaymentDetails payment)
	{
		long pnrNo=payment.getPnrNo();
		List<UserDetails> det=userRepo.findAll();
		  for(UserDetails x:det) {
				if(x.getPnrNo()==pnrNo) {
					id=x.getId();
				}	
		}
		UserDetails existingDetails=userRepo.findById(id)
					.orElseThrow(()->new ResourceNotFoundException("Cannot proceed the payment request as booking is not done with PNR Number : "+pnrNo));
		userPayRepo.save(payment); 
	}
	
	
	
	//to update payment field in user details after successful payment
	  public void updateUserPaymentDetails(long pnrNo)
	  {
		  List<UserDetails> det=userRepo.findAll();
		  for(UserDetails x:det) {
			  //System.out.println(x);
				if(x.getPnrNo()==pnrNo) {
					x.setPayment("Successful");
					userRepo.save(x);
				}
		  }
	  }

	@Override
	public String deletePayment(long pnrNo) {
		List<UserDetails> userDetails=userRepo.findAll();
		for(UserDetails x:userDetails) {
				if(x.getPnrNo()==pnrNo) {
					id=x.getId();
				}	
		}
		userPayRepo.deleteById(id);
		return "Money will be credited to your account within 7 days..";
		
	}

	
	
}
