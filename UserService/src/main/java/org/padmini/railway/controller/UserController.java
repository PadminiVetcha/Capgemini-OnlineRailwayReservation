package org.padmini.railway.controller;
import java.util.List;
import org.padmini.railway.entity.TrainDetails;
import org.padmini.railway.service.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController 
{
	@Autowired
	private AdminServiceImpl adminServiceImpl;
	
	@GetMapping("/alltrains")
	public List<TrainDetails> getAllDetails()
	{
		return adminServiceImpl.getAllDetails();
	}
	
	@GetMapping("/trainNo/{trainNo}")
	public TrainDetails getDetailsByTrainNo(@PathVariable Integer trainNo) 
	{
		return adminServiceImpl.getDetailsByTrainNo(trainNo);
	}
	
	@GetMapping("/{startStation}/{destStation}")
	public List<TrainDetails> getTrainDetailsByStartStation(@PathVariable String startStation,@PathVariable String destStation)
	{
		return adminServiceImpl.getTrainDetailsByStartStation(startStation,destStation);
	}
	
	
}
