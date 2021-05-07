package org.padmini.railway.entity;

import javax.validation.constraints.Max;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class TrainDetails 
{
	@Id
	@NotNull(message="Train number cannot be null")
	@Max(value=99999,message="Train number cannot exceed 5 digits")
	private int trainNo;
	
	@NotNull
	@Size(min=5,message="Train name should be minimum of 5 characters")
	private String trainName;
	
	@NotNull
	@Size(min=2,message="Start Station should be minimum of 2 characters")
	private String startStation;
	
	@NotNull
	@Size(min=2,message="Destination Station should be minimum of 2 characters")
	private String destStation;
	
	@NotNull
	@Size(max=7,message="Arrival Time should be maximum of 7 characters")
	private String arrivalTime;
	
	@NotNull
	@Size(max=7,message="Departure Time should be maximum of 7 characters")
	private String deptTime;
	private String duration;
	private int noOfSeats;
	@NotNull
	private TrainClassFares trainClassFares;
	
	
	public TrainDetails() {
		super();
	}

	public TrainDetails(int trainNo, String trainName, String startStation, String destStation, String arrivalTime,
			String deptTime, String duration, int noOfSeats, TrainClassFares trainClassFares) {
		super();
		this.trainNo = trainNo;
		this.trainName = trainName;
		this.startStation = startStation;
		this.destStation = destStation;
		this.arrivalTime = arrivalTime;
		this.deptTime = deptTime;
		this.duration = duration;
		this.noOfSeats = noOfSeats;
		this.trainClassFares = trainClassFares;
	}

	public int getTrainNo() {
		return trainNo;
	}

	public void setTrainNo(int trainNo) {
		this.trainNo = trainNo;
	}

	public String getTrainName() {
		return trainName;
	}

	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}

	public String getStartStation() {
		return startStation;
	}

	public void setStartStation(String startStation) {
		this.startStation = startStation;
	}

	public String getDestStation() {
		return destStation;
	}

	public void setDestStation(String destStation) {
		this.destStation = destStation;
	}

	public String getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public String getDeptTime() {
		return deptTime;
	}

	public void setDeptTime(String deptTime) {
		this.deptTime = deptTime;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public int getNoOfSeats() {
		return noOfSeats;
	}

	public void setNoOfSeats(int noOfSeats) {
		this.noOfSeats = noOfSeats;
	}

	public TrainClassFares getTrainClassFares() {
		return trainClassFares;
	}

	public void setTrainClassFares(TrainClassFares trainClassFares) {
		this.trainClassFares = trainClassFares;
	}

	@Override
	public String toString() {
		return "TrainDetails [trainNo=" + trainNo + ", trainName=" + trainName + ", startStation=" + startStation
				+ ", destStation=" + destStation + ", arrivalTime=" + arrivalTime + ", deptTime=" + deptTime
				+ ", duration=" + duration + ", noOfSeats=" + noOfSeats + ", trainClassFares=" + trainClassFares + "]";
	}
	
	
}
