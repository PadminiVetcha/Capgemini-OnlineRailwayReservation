package org.padmini.railway.dao;
import org.padmini.railway.entity.UserDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

//Dao Layer
public interface UserRepository extends MongoRepository<UserDetails, Integer>
{

}
