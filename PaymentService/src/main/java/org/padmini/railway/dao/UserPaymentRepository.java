package org.padmini.railway.dao;

import org.padmini.railway.entity.PaymentDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserPaymentRepository extends MongoRepository<PaymentDetails, Long>
{

}
