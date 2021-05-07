package org.padmini.railway.controller;
import java.util.List;
import javax.validation.Valid;
import org.padmini.railway.entity.TrainDetails;
import org.padmini.railway.service.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController 
{
	@Autowired
	private AdminServiceImpl adminServiceImpl;
	
	@GetMapping("/all")
	public List<TrainDetails> getAllDetails()
	{
		return adminServiceImpl.getAllDetails();
	}
	
	@GetMapping("/{trainNo}")
	public TrainDetails getDetailsByTrainNo(@PathVariable Integer trainNo) 
	{
		return adminServiceImpl.getDetailsByTrainNo(trainNo);
	}
	
	@PostMapping("/add")
	public String addTrainDetails(@Valid @RequestBody TrainDetails trainDetails)
	{
		adminServiceImpl.addTrainDetails(trainDetails);
		return ("Added train details with train number - "+trainDetails.getTrainNo()+" successfully..!!");
	}
	
	@PutMapping("/update/{trainNo}")
	public TrainDetails updateTrainDetails(@PathVariable Integer trainNo,@Valid @RequestBody TrainDetails trainDetails)
	{
		return adminServiceImpl.updateTrainDetails(trainNo,trainDetails);
	}
	
	@DeleteMapping("/delete/{trainNo}")
	public ResponseEntity<TrainDetails> deleteTrainDetails(@PathVariable Integer trainNo)
	{
		return adminServiceImpl.deleteTrainDetails(trainNo);
	}
}
