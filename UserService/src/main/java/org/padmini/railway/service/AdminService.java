package org.padmini.railway.service;

import java.util.List;

import org.padmini.railway.entity.TrainDetails;
import org.springframework.http.ResponseEntity;

public interface AdminService 
{
	public List<TrainDetails> getAllDetails();
	public TrainDetails getDetailsByTrainNo(int trainNo);
	public List<TrainDetails> getTrainDetailsByStartStation(String destStation);
	
}
