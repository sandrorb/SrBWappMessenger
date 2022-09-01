package srb.webservice;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.twilio.Twilio;
import com.twilio.exception.ApiException;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@RestController
public class Wapp {
	
	@PostMapping(value = "/wapp")
	public String resposta(@RequestParam String From, @RequestParam String Body) {
		String numTelefone = From.substring(From.indexOf(":")+1);
		StringBuilder sb = new StringBuilder();
		sb.append("Olá, " + numTelefone + "!\n");
		sb.append("Esta é uma mensagem automática criada e enviada por um webservice escrito em Java por Sandro Boschetti.\n");
		sb.append("Você digitou: " + Body);
		return sb.toString();
	}
	
	
	@PostMapping(value = "/sendmsg")
	public String teste(@RequestBody WmsgModel wmsgModel) {
		
		StringBuilder sb = new StringBuilder();
		
		Twilio.init(wmsgModel.getAccountSid(), wmsgModel.getAuthToken());
		
		String phoneNumberOriginStr = "whatsapp:" + wmsgModel.getPhoneNumberOriginStr();
		String phoneNumberDestStr = "whatsapp:" + wmsgModel.getPhoneNumberDestStr();
		
		PhoneNumber phoneNumberOrigin = new PhoneNumber(phoneNumberOriginStr);
		PhoneNumber phoneNumberDest = new PhoneNumber(phoneNumberDestStr);
		
		String bodyMsg = wmsgModel.getBodyMsg();
		
		try {
			Message msg = Message.creator( phoneNumberDest, phoneNumberOrigin, bodyMsg).create();
		}catch(ApiException e) {
			sb.append("ERRO!!!\n");
			sb.append(e.toString() + "\n");
		}
		
		sb.append("  De = " + phoneNumberOrigin.toString() + "\n");
		sb.append("Para = " + phoneNumberDest.toString() + "\n");
		sb.append("Mensagem: " + bodyMsg);
		
		return sb.toString();	
	}
	
	
}

