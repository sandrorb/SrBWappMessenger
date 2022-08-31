package srb;

import java.util.Scanner;

public class Credencial {
	
	public static String getAccountSid() {
		Scanner scanner = new Scanner(System.in);
		String accountSid;
		System.out.print("Insira AccountSid: ");
		accountSid = scanner.nextLine().trim();
		return accountSid;
	}
	
	public static String authToken() {
		Scanner scanner = new Scanner(System.in);
		String accountSid;
		System.out.print("Insira authToken: ");
		accountSid = scanner.nextLine().trim();
		return accountSid;
	}
	
	public static String getDestPhoneNumber() {
		Scanner scanner = new Scanner(System.in);
		String destPhoneNumber;
		System.out.print("Insira número de telefone de destino: ");
		destPhoneNumber = scanner.nextLine().trim();
		return destPhoneNumber;
	}	
	
	public static String getOriginPhoneNumber() {
		Scanner scanner = new Scanner(System.in);
		String originPhoneNumber;
		System.out.print("Insira número de telefone de destino: ");
		originPhoneNumber = scanner.nextLine().trim();
		return originPhoneNumber;
	}	
	
}
