package org.padmini.railway;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.*;
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
import org.padmini.railway.dao.AdminRepository;
import org.padmini.railway.entity.TrainClassFares;
import org.padmini.railway.entity.TrainDetails;
import org.padmini.railway.service.AdminServiceImpl;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AdminServiceApplicationTests 
{
	@InjectMocks
	private AdminServiceImpl adminService;	
	
	@Mock
	private AdminRepository adminRepo;
	
	@BeforeAll 
	public void init()  { 
		MockitoAnnotations.initMocks(this);
	}
	 
	@Nested
	@DisplayName("Testing getAllTrainDetails method")
	class getAllDetailsTest
	{
		@Test
		@DisplayName("Testing whether train details database is empty")
		public void getAllDetailsTest1() 
		{
			List<TrainDetails> details=adminService.getAllDetails();
			assertTrue(details.isEmpty());
		}
	
		@Test
		@DisplayName("Testing whether getAllDetails method is returning the records of db")
		public void getAllDetailsTest2() 
		{
			//Added train details
			List<TrainDetails> detailsList=new ArrayList<TrainDetails>();
			TrainClassFares fares=new TrainClassFares(1000,750,500,430);
			TrainDetails details=new TrainDetails(12345, "Abc Express", "Vizianagaram", 
					"Hyderabad", "09:00Am", "05:00Pm", "8H", 50, fares);
			detailsList.add(details);
			//checking whether it returns correct values
			when(adminRepo.findAll()).thenReturn(detailsList);
			List<TrainDetails> detailsListNew=adminService.getAllDetails();
			assertEquals(1, detailsListNew.size());
		}
	}
	
	@Test 
	@DisplayName("Testing getTrainDetailsByTrainNo method")
	public void getDetailsByTrainNoTest() 
	{ 		
		//Added train details
		TrainClassFares fares=new TrainClassFares(1000,750,500,430);
		Optional<TrainDetails> details=Optional.of(new TrainDetails(11111, "Abc Express", "Vizianagaram", 
				"Hyderabad", "09:00Am", "05:00Pm", "8H", 50, fares));
		//Checking whether they are returning correct values or not
		when(adminRepo.findById(11111)).thenReturn(details);
		TrainDetails det=adminService.getDetailsByTrainNo(11111);
		assertEquals("Abc Express",det.getTrainName());
		assertEquals("Vizianagaram",det.getStartStation());
		assertEquals("Hyderabad",det.getDestStation());
		assertEquals("09:00Am",det.getArrivalTime());
		assertEquals("05:00Pm", det.getDeptTime());
		assertEquals("8H", det.getDuration());
		assertEquals(50, det.getNoOfSeats());
		assertEquals(fares, det.getTrainClassFares());
	}
	 
	@Test
	@DisplayName("Testing addTrainDetails method")
	public void addTrainDetailsTest()
	{
		//Added train details
		TrainClassFares fares=new TrainClassFares(1000,750,500,430);
		TrainDetails details=new TrainDetails(12345, "Abc Express", "Vizianagaram", 
				"Hyderabad", "09:00Am", "05:00Pm", "8H", 50, fares);
		adminService.addTrainDetails(details);
		//checking whether train details are added or not
		verify(adminRepo,times(1)).save(details);
	}
	
	@Test
	@DisplayName("Testing updateTrainDetails method")
	public void updateTrainDetailsTest()
	{
		//Added train details
		TrainClassFares fares=new TrainClassFares(1000,750,500,430);
		TrainDetails details=new TrainDetails(12345, "Abc Express", "Vizianagaram", 
				"Hyderabad", "09:00Am", "05:00Pm", "8H", 50, fares);
		//Checking whether added or not
		//System.out.println(details);
		TrainDetails updatedDetails=new TrainDetails();
		//Updated train details
		updatedDetails.setTrainNo(details.getTrainNo());
		updatedDetails.setTrainName("Xyz Express");
		updatedDetails.setStartStation(details.getStartStation());
		updatedDetails.setDestStation(details.getDestStation());
		updatedDetails.setArrivalTime(details.getArrivalTime());
		updatedDetails.setDeptTime(details.getDeptTime());
		updatedDetails.setDuration(details.getDuration());
		updatedDetails.setNoOfSeats(details.getNoOfSeats());
		updatedDetails.setTrainClassFares(details.getTrainClassFares());
		//get train details with no 12345
		given(adminRepo.findById(12345)).willReturn(Optional.of(details));
		//update train details
		adminService.updateTrainDetails(details.getTrainNo(), updatedDetails);
		//Check whether updated or not
		//System.out.println(updatedDetails);
		verify(adminRepo).findById(12345);
	}
	
	
	
	@Test
	@DisplayName("Testing deleteTrainDetailsByTrainNo method")
	public void deleteTrainDetailsTest()
	{
		//Added train details
		TrainClassFares fares=new TrainClassFares(1000,750,500,430);
		TrainDetails details=new TrainDetails(12345, "Abc Express", "Vizianagaram", 
				"Hyderabad", "09:00Am", "05:00Pm", "8H", 50, fares);
		//get train details with no 12345
		when(adminRepo.findById(12345)).thenReturn(Optional.of(details));
		//delete train details with no 12345
		adminService.deleteTrainDetails(12345);
		//verify whether delete is successful or not
		List<TrainDetails> detailsListNew=adminService.getAllDetails();
		assertEquals(0, detailsListNew.size());
	}
}
