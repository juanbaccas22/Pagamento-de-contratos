package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import entities.Contract;
import entities.Installment;
import services.ContractService;
import services.PaypalService;

public class ProgramaPrincipal {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // passar para o padrão iso 
		
		System.out.println("Entre com os dados do contrato : ");
		System.out.print("Numero : ");
		int number = sc.nextInt();
		System.out.print("Data (dd/MM/yyyy) : ");
		LocalDate date = LocalDate.parse(sc.next(), fmt); // parse passa para data, e o fmt passa para o padrão
		System.out.print("Valor do contrato : ");
		double totalValue = sc.nextDouble();
		
		Contract obj = new Contract(number, date , totalValue); // cria um contrato da classe contrato + passa os argumentos 
		
		System.out.print("Entre com o numero de parcelas : ");
		int n = sc.nextInt();
		
		ContractService contractService = new ContractService(new PaypalService());
		
		contractService.processContract(obj, n);
		
		System.out.println("Parcelas : ");
		for (Installment installment : obj.getInstallment()) {
			System.out.println(installment);
		}
		
		
		sc.close();
	}

}
