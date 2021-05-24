package org.padmini.railway.service;
import java.util.ArrayList;
import java.util.List;

import org.padmini.railway.dao.AdminRepository;
import org.padmini.railway.entity.TrainDetails;
import org.padmini.railway.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService
{
	@Autowired
	private AdminRepository adminRepo;
	
	public List<TrainDetails> getAllDetails()
	{
		List<TrainDetails> trainDetails=new ArrayList<TrainDetails>();
		adminRepo.findAll().forEach(trainDetails1 -> trainDetails.add(trainDetails1));
		//To check while tesing..
		//System.out.println(trainDetails);
		return trainDetails;
	}
	
	public TrainDetails getDetailsByTrainNo(int trainNo)
	{
		return adminRepo.findById(trainNo)
		.orElseThrow(()->new ResourceNotFoundException("Train not found with number : "+ trainNo));
	}
	
	public void addTrainDetails(TrainDetails trainDetails)
	{
		adminRepo.save(trainDetails);
	}
	
	public TrainDetails updateTrainDetails(int trainNo,TrainDetails trainDetails)
	{
		TrainDetails existingDetails=adminRepo.findById(trainNo)
				.orElseThrow(()->new ResourceNotFoundException("Cannot update the given train details, as train not found with number : "+ trainNo));
		existingDetails.setTrainNo(trainDetails.getTrainNo());
		existingDetails.setTrainName(trainDetails.getTrainName());
		existingDetails.setStartStation(trainDetails.getStartStation());
		existingDetails.setDestStation(trainDetails.getDestStation());
		existingDetails.setArrivalTime(trainDetails.getArrivalTime());
		existingDetails.setDeptTime(trainDetails.getDeptTime());
		existingDetails.setDuration(trainDetails.getDuration());
		existingDetails.setNoOfSeats(trainDetails.getNoOfSeats());
		existingDetails.setTrainClassFares(trainDetails.getTrainClassFares());
		return adminRepo.save(existingDetails);
	}
	
	public ResponseEntity<TrainDetails> deleteTrainDetails(int trainNo)
	{
		TrainDetails existingDetails=adminRepo.findById(trainNo)
				.orElseThrow(()->new ResourceNotFoundException("Cannot delete as train not found with number : "+ trainNo));
		adminRepo.delete(existingDetails);
		//adminRepo.deleteById(trainNo);
		return ResponseEntity.ok().build();

	}
}