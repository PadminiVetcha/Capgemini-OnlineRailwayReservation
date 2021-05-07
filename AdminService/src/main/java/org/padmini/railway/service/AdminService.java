package org.padmini.railway.service;

import java.util.List;

import org.padmini.railway.entity.TrainDetails;
import org.springframework.http.ResponseEntity;

public interface AdminService 
{
	public List<TrainDetails> getAllDetails();
	public TrainDetails getDetailsByTrainNo(int trainNo);
	public void addTrainDetails(TrainDetails trainDetails);
	public TrainDetails updateTrainDetails(int trainNo,TrainDetails trainDetails);
	public ResponseEntity<TrainDetails> deleteTrainDetails(int trainNo);
}
