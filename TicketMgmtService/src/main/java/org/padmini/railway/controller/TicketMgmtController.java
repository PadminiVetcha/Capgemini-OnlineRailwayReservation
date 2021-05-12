package org.padmini.railway.controller;
import java.util.List;
import javax.validation.Valid;
import org.padmini.railway.entity.UserDetails;
import org.padmini.railway.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/user")
public class TicketMgmtController 
{
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@GetMapping("/all")
	@ApiOperation(value="Get all user details who booked their tickets")
	public List<UserDetails> getAll()
	{
		return userServiceImpl.getAll();
	}
	
	@GetMapping("/{pnrNo}")
	@ApiOperation(value="Get user details by Pnr Number")
	public UserDetails getUserDetailsById(@PathVariable long pnrNo)
	{
		return userServiceImpl.getUserDetailsById(pnrNo);
	}
	
	@PostMapping("/book/add")
	@ApiOperation(value="Book a ticket")
	public String addUserDetails(@Valid @RequestBody UserDetails userDetails)
	{
		userDetails.setId(UserServiceImpl.getNextSequence(userDetails.SEQUENCE_NAME));
		userDetails.setPnrNo();
		userDetails.setPayment("Pending");
		return userServiceImpl.addUserBookingDetails(userDetails);	
	}
	
	@DeleteMapping("/cancel/{pnrNo}")
	@ApiOperation(value="Cancel a ticket")
	public String deleteUserDetailsById(@PathVariable long pnrNo)
	{
		return userServiceImpl.deleteUserBookingDetails(pnrNo);
	}
}
