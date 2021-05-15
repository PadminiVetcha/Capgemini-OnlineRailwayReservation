package org.padmini.railway.controller;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.util.List;

//imports
import javax.validation.Valid;
import org.padmini.railway.dao.UserPaymentRepository;
import org.padmini.railway.entity.PaymentDetails;
import org.padmini.railway.entity.UserDetails;
import org.padmini.railway.service.PaymentServiceImpl;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rabbitmq.client.Channel;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/pay")
public class PaymentController implements ChannelAwareMessageListener
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
		String msg=("Your payment is successful for PNR Number " +pnrNo);
		paySerImpl.proceedToPay(payment); 
		paySerImpl.updateUserPaymentDetails(payment.getPnrNo());
		return msg;  
	 }
	 
	 //@DeleteMapping("/cancel/{pnrNo}")
	// @GetMapping("/cancel/{pnrNo}")
	 @RequestMapping(
			  value = "/cancel/{pnrNo}", 
			  method = {RequestMethod.GET, RequestMethod.DELETE})
	 
	 @ApiOperation(value="Inorder to cancel your payment")
	 public String deletePaymentDetails(@PathVariable long pnrNo)
	 {
		 return paySerImpl.deletePayment(pnrNo);
	 }
	 
	 @Override
	public void onMessage(Message message, Channel channel) throws Exception {
			System.out.println("Received < " +message + " >");
			byte[] byteArray=message.getBody();
			UserDetails userDetails=(UserDetails) getObject(byteArray);
			System.out.println("TICKET IS BOOKED...............................!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			//userServiceImpl.addUserBookingDetails(userDetails);
			channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
			
		}
		
		private static Object getObject(byte[] byteArray) throws IOException,ClassNotFoundException
		{
			ByteArrayInputStream bis=new ByteArrayInputStream(byteArray);
			ObjectInput in=new ObjectInputStream(bis); 
			return in.readObject();
		}
}
