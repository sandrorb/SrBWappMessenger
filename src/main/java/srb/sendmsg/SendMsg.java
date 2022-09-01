package srb.sendmsg;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import srb.credenciais.MyProperties;

public class SendMsg {
	
	public static void execute() {

		MyProperties myProp = new MyProperties();
		
	//	String accountSid = CredencialCommandLine.getAccountSid();
	//	String authToken = CredencialCommandLine.authToken();
		
		String accountSid = myProp.getAccountSid();
		String  authToken = myProp.getAuthToken();
			
		Twilio.init(accountSid, authToken);
		
	//	String phoneNumberOriginStr = CredencialCommandLine.getOriginPhoneNumber();
	//	String   phoneNumberDestStr = CredencialCommandLine.getDestPhoneNumber();
		
		String phoneNumberOriginStr = myProp.getPhoneNumberOrigin();
		String   phoneNumberDestStr = myProp.getPhoneNumberDest();	
		
		phoneNumberOriginStr = "whatsapp:" + phoneNumberOriginStr;
		  phoneNumberDestStr = "whatsapp:" + phoneNumberDestStr;
		
		PhoneNumber phoneNumberOrigin = new PhoneNumber(phoneNumberOriginStr);
		PhoneNumber phoneNumberDest = new PhoneNumber(phoneNumberDestStr);
	
		String body = "SrB: Isso é um teste";
		 
		Message msg = Message.creator( phoneNumberDest, phoneNumberOrigin, body).create();
		
		System.out.println(msg.getSid());	
	
	}

}
