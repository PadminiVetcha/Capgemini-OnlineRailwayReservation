package org.padmini.railway.controller;
import java.util.List;
import javax.validation.Valid;
import org.padmini.railway.entity.UserDetails;
import org.padmini.railway.entity.AuthenticationRequest;
import org.padmini.railway.entity.AuthenticationResponse;
import org.padmini.railway.entity.TrainDetails;
import org.padmini.railway.service.MyUserDetailsService;
import org.padmini.railway.service.UserServiceImpl;
import org.padmini.railway.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import io.swagger.annotations.ApiOperation;

@Component
@RestController
@RequestMapping("/user")
public class TicketMgmtController 
{
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@GetMapping("/all")
	@ApiOperation(value="Get all user details who booked their tickets")
	public List<UserDetails> getAll()
	{
		return userServiceImpl.getAll();
	}
	
	@GetMapping("/get/{pnrNo}")
	@ApiOperation(value="Get user details by Pnr Number")
	public UserDetails getUserDetailsById(@PathVariable long pnrNo)
	{
		return userServiceImpl.getUserDetailsById(pnrNo);
	}
	
	@PostMapping("/book/add")
	@ApiOperation(value="Book a ticket")
	public String addUserDetails(@Valid @RequestBody UserDetails userDetails)
	{
		RestTemplate restTemplate=new RestTemplate();
		userDetails.setId(UserServiceImpl.getNextSequence(userDetails.SEQUENCE_NAME));
		userDetails.setPnrNo();
		userDetails.setPayment("Pending");
		int trainNo=userDetails.getTrainNo();
		int p1=userDetails.getPassengers().getAdults();
		int p2=userDetails.getPassengers().getChildren();
		int pass=p1+p2;
		TrainDetails s=restTemplate.getForObject("http://localhost:8081/admin/updateSeats/"+trainNo+"/"+pass, TrainDetails.class);
		return userServiceImpl.addUserBookingDetails(userDetails);	
	}
	
	@DeleteMapping("/cancel/{pnrNo}")
	@ApiOperation(value="Cancel a ticket")
	public String deleteUserDetailsById(@PathVariable long pnrNo)
	{
		RestTemplate restTemplate=new RestTemplate();
		String s=restTemplate.getForObject("http://localhost:8083/pay/cancel/"+pnrNo, String.class);
		//System.out.println(s);
		return userServiceImpl.deleteUserBookingDetails(pnrNo);
	}
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
			);
		}
		catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}
		final org.springframework.security.core.userdetails.UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String jwt = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
}