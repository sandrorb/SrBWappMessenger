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
	
	
	@PostMapping(value = "/sendmsgold")
	public String teste(@RequestBody WmsgModel wmsgModel) {
		
		StringBuilder sb = new StringBuilder();
		
		Twilio.init(wmsgModel.getAccountSid(), wmsgModel.getAuthToken());
		
		String phoneNumberOriginStr = "whatsapp:" + wmsgModel.getPhoneNumberOriginStr();
		String phoneNumberDestStr = "whatsapp:" + wmsgModel.getPhoneNumberDestStr();
		
		PhoneNumber phoneNumberOrigin = new PhoneNumber(phoneNumberOriginStr);
		PhoneNumber phoneNumberDest = new PhoneNumber(phoneNumberDestStr);
		
		String bodyMsg = wmsgModel.getBodyMsg();
		
		Message msg = null;
		
		try {
			msg = Message.creator( phoneNumberDest, phoneNumberOrigin, bodyMsg).create();
		}catch(ApiException e) {
			sb.append("ERRO!!!\n");
			sb.append(e.toString() + "\n");
		}
		
		sb.append("  De = " + phoneNumberOrigin.toString() + "\n");
		sb.append("Para = " + phoneNumberDest.toString() + "\n");
		
		sb.append("Mensagem: " + bodyMsg);
		
		if(msg != null) {
//			sb.append("\n\n\n" + msg.toString()+"\n\n");
		}
		
		return sb.toString();	
	}
	
	
	@PostMapping(value = "/sendmsg")
	public String teste(@RequestParam String phoneNumberFrom, @RequestParam String phoneNumberTo, @RequestParam String message) {
	
		String accountSid = System.getenv("ACCOUNT_SID");
		String authToken = System.getenv("AUTH_TOKEN");
		
		Twilio.init(accountSid, authToken);

		String phoneNumberOriginStr = "whatsapp:" + phoneNumberFrom;
		String phoneNumberDestStr = "whatsapp:" + phoneNumberTo;
		
		PhoneNumber phoneNumberOrigin = new PhoneNumber(phoneNumberOriginStr);
		PhoneNumber phoneNumberDest = new PhoneNumber(phoneNumberDestStr);
		
		String bodyMsg = message;
		
		Message msg = null;

		StringBuilder sb = new StringBuilder();
		
		try {
			msg = Message.creator( phoneNumberDest, phoneNumberOrigin, bodyMsg).create();
		}catch(ApiException e) {
			sb.append("ERRO!!!\n");
			sb.append(e.toString() + "\n");
		}
		
		sb.append("  De = " + phoneNumberOrigin.toString() + "\n");
		sb.append("Para = " + phoneNumberDest.toString() + "\n");
		sb.append("Mensagem: " + bodyMsg+"\n");
		
		if(msg != null) {
//			sb.append("\n\n\n" + msg.toString()+"\n\n");
		}
		
		sb.append("As credenciais são obtidas via variáveis de ambiente\n");
		
		return sb.toString();	
	}
	
	
}

