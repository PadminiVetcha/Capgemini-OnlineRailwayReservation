package org.padmini.railway.dao;
import org.padmini.railway.entity.TrainDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

//Dao Layer
public interface AdminRepository extends MongoRepository<TrainDetails, Integer>
{

}
