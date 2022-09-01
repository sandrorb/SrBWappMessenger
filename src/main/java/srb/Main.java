package srb;

import readexcel.ReadExcel;
import srb.sendmsg.SendMsg;

public class Main {

	public static void main(String[] args) {

		
		String env = System.getenv("windir");
		System.out.println(env);
		
		System.out.println("Executando o main() de dentro de Main.");
		
//		SendMsg.execute();
//		ReadExcel.execute();

	}

}
