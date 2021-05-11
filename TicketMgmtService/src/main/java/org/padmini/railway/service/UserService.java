package org.padmini.railway.service;
import java.util.List;
import org.padmini.railway.entity.UserDetails;

public interface UserService 
{
	public List<UserDetails> getAll();
	public UserDetails getUserDetailsById(long pnrNo);
	public String addUserBookingDetails(UserDetails userDetails);
	public String deleteUserBookingDetails(long pnrNo); 
}
