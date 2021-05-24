package org.padmini.railway.entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class UserDetails 
{
	@Transient
	public static final String SEQUENCE_NAME = "users_sequence";
	
	@Id
	@NotNull
	private int id;
	
	@NotNull
	private long pnrNo;
	 
	@NotNull
	@Size(min=3,message="Name should be minimum of 3 characters")
	private String name;
	
	@NotNull
	@Max(value=99,message="Age cannot be more than 99")
	private int age;
	
	@NotNull
	@Size(max=6,message="Either male,female or other ")
	private String sex;
	
	@NotNull
	@Size(max=70,message="Address cannot exceed 70 characters..!!")
	private String address;
	
	@NotNull
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
	@Size(min=2,message="Class type should be minimum of 2 characters")
	private String classType;
	
	@NotNull
	private Passengers passengers;
	
	@NotNull
	private String payment;
	
	public UserDetails() {
		super();
	}
	
	public UserDetails(String name,int age,String sex, String address,int trainNo,String trainName,
			String startStation, String destStation,String classType, Passengers passengers) 
	{
		this.name = name;
		this.age = age;
		this.sex = sex;
		this.address = address;
		this.trainNo=trainNo;
		this.trainName=trainName;
		this.startStation = startStation;
		this.destStation = destStation;
		this.classType = classType;
		this.passengers = passengers;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getPnrNo() {
		return pnrNo;
	}

	public void setPnrNo() {
		long number = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
		this.pnrNo = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getClassType() {
		return classType;
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}

	public Passengers getPassengers() {
		return passengers;
	}

	public void setPassengers(Passengers passengers) {
		this.passengers = passengers;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	@Override
	public String toString() {
		return "UserDetails [id=" + id + ", pnrNo=" + pnrNo + ", name=" + name + ", age=" + age + ", sex=" + sex
				+ ", address=" + address + ", trainNo=" + trainNo + ", trainName=" + trainName + ", startStation="
				+ startStation + ", destStation=" + destStation + ", classType=" + classType + ", passengers="
				+ passengers + ", payment=" + payment + "]";
	}
}