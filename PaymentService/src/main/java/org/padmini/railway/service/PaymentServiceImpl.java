package org.padmini.railway.service;
import java.nio.charset.StandardCharsets;
import java.util.List;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import org.padmini.railway.dao.UserPaymentRepository;
import org.padmini.railway.dao.UserRepository;
import org.padmini.railway.entity.PaymentDetails;
import org.padmini.railway.entity.UserDetails;
import org.padmini.railway.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.common.collect.Lists;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import it.ozimov.springboot.mail.model.Email;
import it.ozimov.springboot.mail.model.defaultimpl.DefaultEmail;
import it.ozimov.springboot.mail.service.EmailService;

@Service
public class PaymentServiceImpl implements PaymentService
{
	int id;

	@Autowired
	public EmailService emailService;
	
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
		userRepo.findById(id)
					.orElseThrow(()->new ResourceNotFoundException("Cannot proceed the payment request as booking is not done with PNR Number : "+pnrNo));
		userPayRepo.save(payment); 
		//For email notification after successful payment 
		//Use sendEmail(paymentDetails payment) method
		/*
		 * try {sendEmail(payment);} catch (AddressException e) { e.printStackTrace();}
		 */
	}
	
	@Override
	public String deletePayment(long pnrNo) {
		userPayRepo.deleteById(pnrNo);
		return "You payment for "+pnrNo+ " will be credited to your account within 7 days..";
	}
	
	//to update payment field in user details after successful payment
	 public void updateUserPaymentDetails(long pnrNo)
	 {
		  List<UserDetails> details=userRepo.findAll();
		  for(UserDetails x:details) {
			  //System.out.println(x);
				if(x.getPnrNo()==pnrNo) {
					x.setPayment("Successful");
					userRepo.save(x);
				}
		  }
	  }
	  
	//For email notification after successful payment
	public void sendEmail(PaymentDetails payment) throws AddressException{
		final Email email = DefaultEmail.builder()
			      .from(new InternetAddress("vetchapaddu13@gmail.com"))
			      .replyTo(new InternetAddress("vetchapaddu13@gmail.com"))
			      .to(Lists.newArrayList(new InternetAddress("vetchapaddu13@gmail.com")))
			      .subject("Payment is Successful")
			      .body("Your payment for PNR Number "+payment.getPnrNo()+" is Successful...!!!")
			      .encoding("UTF-8")
			      .build();
			 emailService.send(email);
	}
	
	 public String sendNotification(String notification)
	 {
		 ConnectionFactory factory=new ConnectionFactory();
		 factory.setHost("localhost");
		 try(Connection connection=factory.newConnection();
			 Channel channel=connection.createChannel()) {
			 channel.queueDeclare("booking", false, false, false, null);
			 channel.basicPublish("", "booking", null, notification.getBytes(StandardCharsets.UTF_8));
			 System.out.println("Sent Message : "+notification);
		 } catch(Exception e) {
			 e.printStackTrace();
		 }
		 return "Payment is done..!!";
	 }
	 
	 public void receiveNotification() throws Exception
	 {
		 ConnectionFactory connFactory=new ConnectionFactory();
		 connFactory.setHost("localhost");
		 Connection connection=connFactory.newConnection();
		 Channel channel=connection.createChannel();
		 channel.queueDeclare("booking", false, false, false, null);
		 //System.out.println("Waiting for msgs..!! press ctrl+c to quit");
		 DeliverCallback deliverCallBack=(ct,delivery)->{
			 String msg=new String(delivery.getBody(),StandardCharsets.UTF_8);
			 //response=msg;
			 System.out.println("Received Message : "+msg);
		 };
		 channel.basicConsume("booking", true, deliverCallBack, c->{});
	 }
}
