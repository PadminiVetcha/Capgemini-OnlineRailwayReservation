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
	
	@Override
	public List<TrainDetails> getAllDetails()
	{
		List<TrainDetails> trainDetails=new ArrayList<TrainDetails>();
		adminRepo.findAll().forEach(trainDetails1 -> trainDetails.add(trainDetails1));
		return trainDetails;
	}
	
	@Override
	public TrainDetails getDetailsByTrainNo(int trainNo)
	{
		return adminRepo.findById(trainNo)
		.orElseThrow(()->new ResourceNotFoundException("Train not found with number : "+ trainNo));
	}

	@Override
	public List<TrainDetails> getTrainDetailsByStartStation(String destStation) {
		List<TrainDetails> detList=adminRepo.findAll();
		List<TrainDetails> req=new ArrayList<TrainDetails>();
		for(TrainDetails tr:detList)
		{
			String stat=tr.getDestStation();
			if(stat.equals(destStation))
			{
				req.add(tr);
			}
		}
		return req;
	}
}
