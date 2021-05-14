package org.padmini.railway;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.padmini.railway.dao.UserPaymentRepository;
import org.padmini.railway.dao.UserRepository;
import org.padmini.railway.entity.PaymentDetails;
import org.padmini.railway.service.PaymentServiceImpl;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PaymentServiceApplicationTests {
	
	@InjectMocks
	private PaymentServiceImpl paySerImpl;
	
	@Mock
	private UserRepository userRepo;
	
	@Mock
	private UserPaymentRepository userPayRepo;

	@BeforeAll public void init() { MockitoAnnotations.initMocks(this); }
	
	/*
	 * @Test
	 * 
	 * @DisplayName("Testing addUserBookingDetails method") public void
	 * addUserBookingDetailsTest() { Passengers passengers = new Passengers(2, 4);
	 * UserDetails details=new UserDetails("Padmini", 23, "Female", "Vzm", 12345,
	 * "Vishaka Express", "Vzm", "Vizag", "FirstClassAc", passengers);
	 * userSerImpl.addUserBookingDetails(details); userRepo.save(details);
	 * verify(userRepo,times(1)).save(details);
	 * System.out.println("PNR NUMBER IS"+details.getPnrNo()); PaymentDetails
	 * payDetails=new PaymentDetails("1234567887654321", 0,123, "Bank Of Baroda",
	 * "FirstClassAc"); paySerImpl.proceedToPay(payDetails);
	 * verify(userPayRepo,times(1)).save(payDetails); }
	 */

	@Nested
	@DisplayName("Testing deletePaymentDetails method")
	class deletePaymentDetails
	{
		@Test
		@DisplayName("1.Testing deletePaymentDetails Method")
		public void deletePaymentDetails1()
		{
			PaymentDetails payDetails=new PaymentDetails("1234567887654321", 1111111111 ,123, "Bank Of Baroda", "FirstClassAc");
			when(userPayRepo.findById((long) 1111111111)).thenReturn(Optional.of(payDetails));
			//delete train details with no 12345
			paySerImpl.deletePayment(1111111111);
			//verify whether delete is successful or not
			List<PaymentDetails> detailsListNew=paySerImpl.getAll();
			assertEquals(0, detailsListNew.size());
		}
		
		@Test
		@DisplayName("2.Testing deletePaymentDetails Method")
		public void deletePaymentDetails2()
		{
			PaymentDetails payDetails=new PaymentDetails("1122334455667788", 1111111111 ,123, "Andhra Bank", "FirstClassAc");
			when(userPayRepo.findById((long) 1111111111)).thenReturn(Optional.of(payDetails));
			//delete train details with no 12345
			paySerImpl.deletePayment(1111111111);
			//verify whether delete is successful or not
			List<PaymentDetails> detailsListNew=paySerImpl.getAll();
			assertEquals(0, detailsListNew.size());
		}
		
		@Test
		@DisplayName("3.Testing deletePaymentDetails Method")
		public void deletePaymentDetails3()
		{
			PaymentDetails payDetails=new PaymentDetails("9988776655443322", 1111111111 ,123, "Axis Bank", "Sleeper");
			when(userPayRepo.findById((long) 1111111111)).thenReturn(Optional.of(payDetails));
			//delete train details with no 12345
			paySerImpl.deletePayment(1111111111);
			//verify whether delete is successful or not
			List<PaymentDetails> detailsListNew=paySerImpl.getAll();
			assertEquals(0, detailsListNew.size());
		}
	}
	
}
