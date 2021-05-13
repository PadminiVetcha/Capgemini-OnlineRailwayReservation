package org.padmini.railway;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserServiceApplicationTests {

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
		@DisplayName("1.Testing whether getAllDetails method is returning the records of db")
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
		
		@Test
		@DisplayName("2.Testing whether getAllDetails method is returning the records of db")
		public void getAllDetailsTest3() 
		{
			//Added train details
			List<TrainDetails> detailsList=new ArrayList<TrainDetails>();
			TrainClassFares fares=new TrainClassFares(1500,1204,876,754);
			TrainDetails details=new TrainDetails(67892, "Howrah Express", "Chennai", 
					"Howrah", "09:00Am", "05:00Pm", "8H", 50, fares);
			detailsList.add(details);
			//checking whether it returns correct values
			when(adminRepo.findAll()).thenReturn(detailsList);
			List<TrainDetails> detailsListNew=adminService.getAllDetails();
			assertEquals(1, detailsListNew.size());
		}
		
		@Test
		@DisplayName("3.Testing whether getAllDetails method is returning the records of db")
		public void getAllDetailsTest4() 
		{
			//Added train details
			List<TrainDetails> detailsList=new ArrayList<TrainDetails>();
			TrainClassFares fares=new TrainClassFares(900,750,500,300);
			TrainDetails details=new TrainDetails(11111, "Vishaka Express", "Berhampur", 
					"Vishakapatnam", "09:00Am", "05:00Pm", "8H", 50, fares);
			detailsList.add(details);
			//checking whether it returns correct values
			when(adminRepo.findAll()).thenReturn(detailsList);
			List<TrainDetails> detailsListNew=adminService.getAllDetails();
			assertEquals(1, detailsListNew.size());
		}
	}
	
	@Nested
	@DisplayName("Testing getTrainDetailsByTrainNo method")
	class getDetailsByTrainNoTest
	{
		@Test 
		@DisplayName("1.Testing getTrainDetailsByTrainNo method")
		public void getDetailsByTrainNoTest1() 
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
		@DisplayName("2.Testing getTrainDetailsByTrainNo method")
		public void getDetailsByTrainNoTest2() 
		{ 	//Added train details
			TrainClassFares fares=new TrainClassFares(1500,1204,876,754);
			Optional<TrainDetails> details=Optional.of(new TrainDetails(67892, "Howrah Express", "Chennai", 
					"Howrah", "09:00Am", "05:00Pm", "8H", 50, fares));
			//Checking whether they are returning correct values or not
			when(adminRepo.findById(67892)).thenReturn(details);
			TrainDetails det=adminService.getDetailsByTrainNo(67892);
			assertEquals("Howrah Express",det.getTrainName());
			assertEquals("Chennai",det.getStartStation());
			assertEquals("Howrah",det.getDestStation());
			assertEquals("09:00Am",det.getArrivalTime());
			assertEquals("05:00Pm", det.getDeptTime());
			assertEquals("8H", det.getDuration());
			assertEquals(50, det.getNoOfSeats());
			assertEquals(fares, det.getTrainClassFares());
		}
		
		@Test 
		@DisplayName("3.Testing getTrainDetailsByTrainNo method")
		public void getDetailsByTrainNoTest3() 
		{ 	
			//Added train details
			TrainClassFares fares=new TrainClassFares(900,750,500,300);
			Optional<TrainDetails> details=Optional.of(new TrainDetails(11111, "Vishaka Express", "Berhampur", 
					"Vishakapatnam", "09:00Am", "05:00Pm", "8H", 50, fares));
			//Checking whether they are returning correct values or not
			when(adminRepo.findById(11111)).thenReturn(details);
			TrainDetails det=adminService.getDetailsByTrainNo(11111);
			assertEquals("Vishaka Express",det.getTrainName());
			assertEquals("Berhampur",det.getStartStation());
			assertEquals("Vishakapatnam",det.getDestStation());
			assertEquals("09:00Am",det.getArrivalTime());
			assertEquals("05:00Pm", det.getDeptTime());
			assertEquals("8H", det.getDuration());
			assertEquals(50, det.getNoOfSeats());
			assertEquals(fares, det.getTrainClassFares());
		}
	}
	
	@Nested
	@DisplayName("Testing getTrainDetailsByStations method")
	class getDetailsByStations
	{
		@Test
		@DisplayName("1.Testing getTrainDetailsByStations method")
		public void getDetailsByStations1()
		{
			List<TrainDetails> detailsList=new ArrayList<TrainDetails>();
			TrainClassFares fares=new TrainClassFares(1000,750,500,430);
			TrainDetails details=new TrainDetails(12345, "Abc Express", "Vizianagaram", 
					"Hyderabad", "09:00Am", "05:00Pm", "8H", 50, fares);
			detailsList.add(details);
			assertEquals("Vizianagaram", details.getStartStation());
			assertEquals("Hyderabad", details.getDestStation());
	
		}
		
		@Test
		@DisplayName("2.Testing getTrainDetailsByStations method")
		public void getDetailsByStations2()
		{
			List<TrainDetails> detailsList=new ArrayList<TrainDetails>();
			TrainClassFares fares=new TrainClassFares(1500,1204,876,754);
			TrainDetails details=new TrainDetails(67892, "Howrah Express", "Chennai", 
					"Howrah", "09:00Am", "05:00Pm", "8H", 50, fares);
			detailsList.add(details);
			assertEquals("Chennai", details.getStartStation());
			assertEquals("Howrah", details.getDestStation());
	
		}
		
		@Test
		@DisplayName("3.Testing getTrainDetailsByStations method")
		public void getDetailsByStations3()
		{
			List<TrainDetails> detailsList=new ArrayList<TrainDetails>();
			TrainClassFares fares=new TrainClassFares(900,750,500,300);
			TrainDetails details=new TrainDetails(11111, "Vishaka Express", "Berhampur", 
					"Vishakapatnam", "09:00Am", "05:00Pm", "8H", 50, fares);
			detailsList.add(details);
			assertEquals("Berhampur", details.getStartStation());
			assertEquals("Vishakapatnam", details.getDestStation());
		}
	}
}