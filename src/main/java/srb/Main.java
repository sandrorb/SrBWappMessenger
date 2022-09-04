package srb;

import srb.readexcel.ReadExcel;
import srb.sendmsg.SendMsg;

public class Main {

	public static void main(String[] args) {

//		SendMsg.execute();
//		ReadExcel.execute();
		
		ReadExcel.getInstance().print();
	}

}
