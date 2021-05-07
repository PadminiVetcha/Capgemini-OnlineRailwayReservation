package org.padmini.railway;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.padmini.railway.dao.UserRepository;
import org.padmini.railway.entity.Passengers;
import org.padmini.railway.entity.PaymentDetails;
import org.padmini.railway.entity.UserDetails;
import org.padmini.railway.service.UserServiceImpl;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TicketMgmtServiceApplicationTests {

	@InjectMocks
	private UserServiceImpl userSerImpl;
	
	@Mock
	private UserRepository userRepo;

	@BeforeAll public void init() { MockitoAnnotations.initMocks(this); }
	
	@Test
	@DisplayName("Testing getAll method")
	public void getAllTest()
	{
		List<UserDetails> detailsList=new ArrayList<UserDetails>();
		Passengers passengers = new Passengers(2, 4);
		UserDetails details=new UserDetails("Padmini", 23, "Female", "Vzm", 12345, "Vishaka Express", "Vzm", "Vizag", "FirstClassAc", passengers);
		detailsList.add(details);
		when(userRepo.findAll()).thenReturn(detailsList);
		List<UserDetails> detailsListNew=userSerImpl.getAll();
		assertEquals(1, detailsListNew.size());
	}
	
	@Test
	@DisplayName("Testing addUserBookingDetails method")
	public void addUserBookingDetailsTest()
	{
		Passengers passengers = new Passengers(2, 4);
		UserDetails details=new UserDetails("Padmini", 23, "Female", "Vzm", 12345, "Vishaka Express", "Vzm", "Vizag", "FirstClassAc", passengers);
		userSerImpl.addUserBookingDetails(details);
		verify(userRepo,times(1)).save(details);
	}
	
	@Test
	@DisplayName("Testing getUserDetailsById method")
	public void getUserDetailsByIdTest()
	{
		Passengers passengers = new Passengers(2, 4);
		UserDetails details=new UserDetails("Padmini", 23, "Female", "Vzm", 12345, "Vishaka Express", "Vzm", "Vizag", "FirstClassAc", passengers);
		long x=details.getPnrNo();
		int y=details.getId();
		when(userRepo.findById(y)).thenReturn(Optional.of(details));
		UserDetails det=userSerImpl.getUserDetailsById(x);
		assertEquals("Padmini", det.getName());
		assertEquals(23, det.getAge());
		assertEquals("Female", det.getSex());
		assertEquals("Vzm", det.getAddress());
		assertEquals(12345, det.getTrainNo());
		assertEquals("Vishaka Express", det.getTrainName());
		assertEquals("Vzm", det.getStartStation());
		assertEquals("Vizag", det.getDestStation());
		assertEquals("FirstClassAc", det.getClassType());
		assertEquals(2, det.getPassengers().getAdults());
		assertEquals(4, det.getPassengers().getChildren());
	}
	
	@Test
	@DisplayName("Testing deleteUserBookingDetails method")
	public void deleteUserBookingDetailsTest()
	{
		Passengers passengers = new Passengers(2, 4);
		UserDetails details=new UserDetails("Padmini", 23, "Female", "Vzm", 12345, "Vishaka Express", "Vzm", "Vizag", "FirstClassAc", passengers);
		long x=details.getPnrNo();
		int y=details.getId();
		when(userRepo.findById(y)).thenReturn(Optional.of(details));
		userSerImpl.deleteUserBookingDetails(x);
		List<UserDetails> detailsListNew=userSerImpl.getAll();
		assertEquals(0, detailsListNew.size());
	}
}
