package model.services;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import model.entities.Contract;
import model.entities.Installment;

public class ContractService {
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	private Contract contract;
	private Integer mounths;
	private OnlinePaymentService pay;
	
	public ContractService(Contract contract, Integer mounths, OnlinePaymentService pay) {
		this.contract = contract;
		this.mounths = mounths;
		this.pay = pay;
	}

	public Contract getContract() {
		return contract;
	}

	public void processContract() {
		
		double amounts = contract.getTotalValue()/mounths;
		
		for (int i=1; i<=mounths; i++) {
			double fpar = pay.interest(amounts, i);
			double totalPar = pay.paymentFee(fpar);
			Calendar cal = Calendar.getInstance();
			cal.setTime(contract.getDate());
			cal.add(Calendar.MONTH, i);
			
			contract.getList().add(new Installment(cal.getTime(), totalPar));
		}
		
	}
	
	
}
