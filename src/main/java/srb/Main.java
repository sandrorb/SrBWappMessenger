package srb;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class Main {

	public static void main(String[] args) {

		
		MyProperties.init();
		
//		String accountSid = Credencial.getAccountSid();
//		String authToken = Credencial.authToken();
		
		String accountSid = MyProperties.accountSid;
		String  authToken = MyProperties.authToken;
			
		Twilio.init(accountSid, authToken);
		
//		String phoneNumberOriginStr = Credencial.getOriginPhoneNumber();
//		String   phoneNumberDestStr = Credencial.getDestPhoneNumber();
		
		String phoneNumberOriginStr = MyProperties.phoneNumberOrigin;
		String   phoneNumberDestStr = MyProperties.phoneNumberDest;		
		
		phoneNumberOriginStr = "whatsapp:" + phoneNumberOriginStr;
		  phoneNumberDestStr = "whatsapp:" + phoneNumberDestStr;
		
		PhoneNumber phoneNumberOrigin = new PhoneNumber(phoneNumberOriginStr);
		PhoneNumber phoneNumberDest = new PhoneNumber(phoneNumberDestStr);
	
		String body = "SrB: Isso é um teste";
		 
		Message msg = Message.creator( phoneNumberDest, phoneNumberOrigin, body).create();
		
		System.out.println(msg.getSid());		

	}

}
