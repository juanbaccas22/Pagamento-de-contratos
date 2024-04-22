package services;

public interface OnlinePaymentService {

	double paymentFee (double amount); // recebendo a quantia
	double interest(double amount , int months);
}
