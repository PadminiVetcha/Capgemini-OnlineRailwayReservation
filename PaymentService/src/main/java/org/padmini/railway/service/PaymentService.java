package org.padmini.railway.service;
import java.util.List;

import org.padmini.railway.entity.PaymentDetails;

public interface PaymentService 
{
	public List<PaymentDetails> getAll();
	public void proceedToPay(PaymentDetails payment);
	public String deletePayment(long pnrNo);
}
