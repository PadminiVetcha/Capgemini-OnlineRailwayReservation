package org.padmini.railway.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.padmini.railway.dao.UserPaymentRepository;
import org.padmini.railway.dao.AdminRepository;
import org.padmini.railway.entity.PaymentDetails;
import org.padmini.railway.entity.TrainDetails;
import org.padmini.railway.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService
{
	@Autowired
	private AdminRepository adminRepo;
	
	@Autowired
	private UserPaymentRepository userPayRepo;
	
	@Override
	public List<TrainDetails> getAllDetails()
	{
		List<TrainDetails> trainDetails=new ArrayList<TrainDetails>();
		adminRepo.findAll().forEach(trainDetails1 -> trainDetails.add(trainDetails1));
		return trainDetails;
	}
	
	@Override
	public String pnrStatus(long pnrNo) 
	{
		//int count=0;
		Random rand = new Random();
		List<String> status=new ArrayList<String>();
		status.add("Confirm");
		status.add("Waiting list");
		/*
		 * if(count==0) { count=count+1; return status.get(rand.nextInt(status.size()));
		 * }
		 */
		List<PaymentDetails> li=userPayRepo.findAll();
		for(PaymentDetails det:li) {
			if(det.getPnrNo()==pnrNo)
			{
				return status.get(rand.nextInt(status.size()));
			}
		}
		return "Ticket is not booked with PNR Number "+pnrNo;
	}
	
	@Override
	public TrainDetails getDetailsByTrainNo(int trainNo)
	{
		return adminRepo.findById(trainNo)
		.orElseThrow(()->new ResourceNotFoundException("Train not found with number : "+ trainNo));
	}

	@Override
	public List<TrainDetails> getTrainDetailsByStartStation(String startStation,String destStation) {
		List<TrainDetails> detList=adminRepo.findAll();
		List<TrainDetails> req=new ArrayList<TrainDetails>();
		for(TrainDetails tr:detList)
		{
			String stat=tr.getStartStation();
			String dest=tr.getDestStation();
			if(stat.equals(startStation) && dest.equals(destStation))
			{
				req.add(tr);
			}
		}
		return req;
	}

	
}
