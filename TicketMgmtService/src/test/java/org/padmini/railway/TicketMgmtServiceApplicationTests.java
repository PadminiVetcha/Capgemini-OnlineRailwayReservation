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
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.padmini.railway.dao.UserRepository;
import org.padmini.railway.entity.Passengers;
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
	
	@Nested
	@DisplayName("Testing getAll method")
	class getAllTest
	{
		@Test
		@DisplayName("1.Testing getAll method")
		public void getAllTest1()
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
		@DisplayName("2.Testing getAll method")
		public void getAllTest2()
		{
			List<UserDetails> detailsList=new ArrayList<UserDetails>();
			Passengers passengers = new Passengers(1, 0);
			UserDetails details=new UserDetails("Jyothi", 17, "Female", "Vskp", 67891, "Coramandal Express", "Vskp", "Hyd", "FirstClassAc", passengers);
			detailsList.add(details);
			when(userRepo.findAll()).thenReturn(detailsList);
			List<UserDetails> detailsListNew=userSerImpl.getAll();
			assertEquals(1, detailsListNew.size());
		}
		
		@Test
		@DisplayName("3.Testing getAll method")
		public void getAllTest3()
		{
			List<UserDetails> detailsList=new ArrayList<UserDetails>();
			Passengers passengers = new Passengers(1, 0);
			UserDetails details=new UserDetails("Mani", 48, "Female", "Vskp", 11111, "Coramandal Express", "Vskp", "Hyd", "FirstClassAc", passengers);
			detailsList.add(details);
			when(userRepo.findAll()).thenReturn(detailsList);
			List<UserDetails> detailsListNew=userSerImpl.getAll();
			assertEquals(1, detailsListNew.size());
		}
	}
	
	/*
	 * @Test
	 * 
	 * @DisplayName("Testing addUserBookingDetails method") public void
	 * addUserBookingDetailsTest() { Passengers passengers = new Passengers(2, 4);
	 * UserDetails details=new UserDetails("Padmini", 23, "Female", "Vzm", 12345,
	 * "Vishaka Express", "Vzm", "Vizag", "FirstClassAc", passengers);
	 * userSerImpl.addUserBookingDetails(details);
	 * verify(userRepo,times(1)).save(details); }
	 */
	
	@Nested
	@DisplayName("Testing getUserDetailsById method")
	class getUserDetailsByIdTest
	{
		@Test
		@DisplayName("1.Testing getUserDetailsById method")
		public void getUserDetailsByIdTest1()
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
		@DisplayName("2.Testing getUserDetailsById method")
		public void getUserDetailsByIdTest2()
		{
			Passengers passengers = new Passengers(1, 0);
			UserDetails details=new UserDetails("Jyothi", 17, "Female", "Vskp", 67891, "Coramandal Express", "Vskp", "Hyd", "FirstClassAc", passengers);
			long x=details.getPnrNo();
			int y=details.getId();
			when(userRepo.findById(y)).thenReturn(Optional.of(details));
			UserDetails det=userSerImpl.getUserDetailsById(x);
			assertEquals("Jyothi", det.getName());
			assertEquals(17, det.getAge());
			assertEquals("Female", det.getSex());
			assertEquals("Vskp", det.getAddress());
			assertEquals(67891, det.getTrainNo());
			assertEquals("Coramandal Express", det.getTrainName());
			assertEquals("Vskp", det.getStartStation());
			assertEquals("Hyd", det.getDestStation());
			assertEquals("FirstClassAc", det.getClassType());
			assertEquals(1, det.getPassengers().getAdults());
			assertEquals(0, det.getPassengers().getChildren());
		}
		
		@Test
		@DisplayName("3.Testing getUserDetailsById method")
		public void getUserDetailsByIdTest3()
		{
			Passengers passengers = new Passengers(1, 0);
			UserDetails details=new UserDetails("Mani", 48, "Female", "Vskp", 11111, "Coramandal Express", "Vskp", "Hyd", "FirstClassAc", passengers);
			long x=details.getPnrNo();
			int y=details.getId();
			when(userRepo.findById(y)).thenReturn(Optional.of(details));
			UserDetails det=userSerImpl.getUserDetailsById(x);
			assertEquals("Mani", det.getName());
			assertEquals(48, det.getAge());
			assertEquals("Female", det.getSex());
			assertEquals("Vskp", det.getAddress());
			assertEquals(11111, det.getTrainNo());
			assertEquals("Coramandal Express", det.getTrainName());
			assertEquals("Vskp", det.getStartStation());
			assertEquals("Hyd", det.getDestStation());
			assertEquals("FirstClassAc", det.getClassType());
			assertEquals(1, det.getPassengers().getAdults());
			assertEquals(0, det.getPassengers().getChildren());
		}
	}
	
	@Nested
	@DisplayName("Testing deleteUserBookingDetails method")
	class deleteUserBookingDetailsTest
	{
		@Test
		@DisplayName("1.Testing deleteUserBookingDetails method")
		public void deleteUserBookingDetailsTest1()
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
		
		@Test
		@DisplayName("2.Testing deleteUserBookingDetails method")
		public void deleteUserBookingDetailsTest2()
		{
			Passengers passengers = new Passengers(1, 0);
			UserDetails details=new UserDetails("Jyothi", 17, "Female", "Vskp", 67891, "Coramandal Express", "Vskp", "Hyd", "FirstClassAc", passengers);
			long x=details.getPnrNo();
			int y=details.getId();
			when(userRepo.findById(y)).thenReturn(Optional.of(details));
			userSerImpl.deleteUserBookingDetails(x);
			List<UserDetails> detailsListNew=userSerImpl.getAll();
			assertEquals(0, detailsListNew.size());
		}
		
		@Test
		@DisplayName("3.Testing deleteUserBookingDetails method")
		public void deleteUserBookingDetailsTest3()
		{
			Passengers passengers = new Passengers(1, 0);
			UserDetails details=new UserDetails("Mani", 48, "Female", "Vskp", 11111, "Coramandal Express", "Vskp", "Hyd", "FirstClassAc", passengers);
			long x=details.getPnrNo();
			int y=details.getId();
			when(userRepo.findById(y)).thenReturn(Optional.of(details));
			userSerImpl.deleteUserBookingDetails(x);
			List<UserDetails> detailsListNew=userSerImpl.getAll();
			assertEquals(0, detailsListNew.size());
		}
	}
}
