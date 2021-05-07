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
	
	/*
	 * @Override public String abc(PaymentDetails payment) { long
	 * pnr=payment.getPnrNo(); List<UserDetails> det=userRepo.findAll();
	 * for(UserDetails x:det) { if(x.getPnrNo()==pnr) { userPayRepo.save(payment);
	 * return "Your payment is successful..!!"; } else { new
	 * ResourceNotFoundException("Booking is not done with PNR Number : "+pnr); } }
	 * return null; }
	 */
	@Override
	public String proceedToPay(PaymentDetails payment)
	{
		long pnrNo=payment.getPnrNo();
		String msg=("Your payment is successful with PNR Number " +pnrNo);
		 List<UserDetails> det=userRepo.findAll();
		  for(UserDetails x:det) {
				if(x.getPnrNo()==pnrNo) {
					id=x.getId();
				}	
		}
		UserDetails existingDetails=userRepo.findById(id)
					.orElseThrow(()->new ResourceNotFoundException("Cannot delete as booking is done with PNR Number : "+pnrNo));
		userPayRepo.save(payment); 
		return msg;
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
	
}
