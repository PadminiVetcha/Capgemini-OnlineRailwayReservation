package org.padmini.railway.service;
import java.util.List;
import org.padmini.railway.entity.TrainDetails;

public interface AdminService 
{
	public List<TrainDetails> getAllDetails();
	public String pnrStatus(long pnrNo);
	public TrainDetails getDetailsByTrainNo(int trainNo);
	public List<TrainDetails> getTrainDetailsByStartStation(String startStation,String destStation);
	
}
