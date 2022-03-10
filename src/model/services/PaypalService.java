package model.services;

public class PaypalService implements OnlinePaymentService{
	
	public 	Double paymentFee(Double amount) {
		return amount + (amount * 0.02);
	}
	
	public Double interest(Double amount, Integer mounths) {
		return amount + (amount*(0.01*mounths)); 
	}


}
