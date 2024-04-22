package services;

import java.time.LocalDate;

import entities.Contract;
import entities.Installment;

public class ContractService {

	private OnlinePaymentService onlinePaymentService; // para ligar com a interface onlinePaymentService
	//processContract(contract contract, int months);

	public ContractService(OnlinePaymentService onlinePaymentService) {
		this.onlinePaymentService = onlinePaymentService;
	}
	
	public void processContract(Contract contract, int months) { // importou o contrato e months(meses)
		
		double basicQuota = contract.getTotalValue() / months; //quantidade de valor dividido por meses
		
		for(int i=1; i<= months;i++) {
			LocalDate dueDate = contract.getDate().plusMonths(i);// pega a quantidade de meses que sera realizada, adiciona data original + quant d meses
			
			double interest = onlinePaymentService.interest(basicQuota, i);
			double fee = onlinePaymentService.paymentFee(basicQuota + interest);
			double quota = basicQuota + interest + fee;
			
			contract.getInstallment().add(new Installment(dueDate, quota));
			
			
			
			
		}
		
	}
	
	
	
	
}
