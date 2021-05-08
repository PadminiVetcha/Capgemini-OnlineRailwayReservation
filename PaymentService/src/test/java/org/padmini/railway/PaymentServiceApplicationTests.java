package org.padmini.railway;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.padmini.railway.dao.UserPaymentRepository;
import org.padmini.railway.dao.UserRepository;
import org.padmini.railway.entity.Passengers;
import org.padmini.railway.entity.PaymentDetails;
import org.padmini.railway.entity.UserDetails;
import org.padmini.railway.service.PaymentServiceImpl;
import org.padmini.railway.service.UserServiceImpl;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PaymentServiceApplicationTests {
	
	@InjectMocks
	private PaymentServiceImpl paySerImpl;
	
	@InjectMocks
	private UserServiceImpl userSerImpl;
	
	@Mock
	private UserRepository userRepo;
	
	@Mock
	private UserPaymentRepository userPayRepo;

	@BeforeAll public void init() { MockitoAnnotations.initMocks(this); }

	/*
	 * @Test
	 * 
	 * @DisplayName("Testing addUserBookingDetails method") public void
	 * addUserBookingDetailsTest() { List<UserDetails> detailsList=new
	 * ArrayList<UserDetails>(); Passengers passengers = new Passengers(2, 4);
	 * UserDetails det=new UserDetails("Padmini", 23, "Female", "Vzm", 12345,
	 * "Vishaka Express", "Vzm", "Vizag", "FirstClassAc", passengers);
	 * PaymentDetails details=new PaymentDetails("1234567887654321", det.getPnrNo(),
	 * 123, "Bank Of Baroda", "FirstClassAc");
	 * userSerImpl.addUserBookingDetails(det); long x=det.getPnrNo();
	 * System.out.println(x); detailsList.add(det); for(UserDetails de:detailsList)
	 * { if(de.getPnrNo()==details.getPnrNo()) { paySerImpl.proceedToPay(details); }
	 * } verify(userPayRepo,times(1)).save(details); }
	 */

}
