package srb.webservice;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.twilio.Twilio;
import com.twilio.exception.ApiException;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import srb.readexcel.ReadExcel;

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

	
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX	
	
	@RequestMapping(value = "/teste", method = RequestMethod.GET)
	public String testeGet(@RequestParam(value = "nome") String nome) {
		System.out.println("YYYYYYYYYYYYY" + nome);
		return nome;
	}		
	
	@PostMapping(path  = "/teste", value = "/teste")
	@ResponseBody
	public ResponseEntity<String> testePost(@RequestBody MsgModel msgModel) {
		StringBuilder sb = new StringBuilder();
		sb.append("       De: " + msgModel.getPhoneNumberFrom());
		sb.append("     Para: " + msgModel.getPhoneNumberTo());
		sb.append(" Mensagem: " + msgModel.getMessage());
		System.out.println(sb.toString());
		return new ResponseEntity<String>(sb.toString(), HttpStatus.OK);
	}
	
	@PostMapping(path  = "/enviaMsg")
	@ResponseBody
	public ResponseEntity<String> meuMetodoComQQNome(@RequestBody MsgModel msgModel) {
		System.out.println(msgModel.toString());
		return new ResponseEntity<String>(msgModel.toString(), HttpStatus.OK);
	}	

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX	
		
	
	
	/*
	 * Ao tentar passar os 3 parâmetros pelo postman, os sinais de + dos telefones desaparecem.
	 * Então, em vez de fazer @RequestParam String phoneNumberFrom, vou tentar passar por Json e uma classe. Estava assim:
	 * public String teste(@RequestParam String phoneNumberFrom, @RequestParam String phoneNumberTo, @RequestParam String message) {
	 * public String teste(@RequestBody MsgModel msgModel) {
	 */
	@PostMapping(value = "/sendmsg")
	@ResponseBody
	public ResponseEntity<String> sendMsg(@RequestBody MsgModel msgModel) {
		
		String resposta = "";
		resposta = enviaMensagem(msgModel);
		//resposta = msgModel.getMessage();
		//System.out.println("Executado o endpoint /sendmsg");
		
		return new ResponseEntity<String>(resposta, HttpStatus.OK);	
	}
	

	
	

	public String enviaMensagem(MsgModel msgModel) {
		
		String accountSid = System.getenv("ACCOUNT_SID");
		String authToken = System.getenv("AUTH_TOKEN");
		
		
		Twilio.init(accountSid, authToken);

		String phoneNumberOriginStr = "whatsapp:" + msgModel.getPhoneNumberFrom();
		String phoneNumberDestStr = "whatsapp:" + msgModel.getPhoneNumberTo();
		
		PhoneNumber phoneNumberOrigin = new PhoneNumber(phoneNumberOriginStr);
		PhoneNumber phoneNumberDest = new PhoneNumber(phoneNumberDestStr);
		
		String bodyMsg = msgModel.getMessage();
		
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

		return sb.toString();
	}

	
	 @PostMapping("/upload")
	 @ResponseBody
	  public ResponseEntity< List<ClienteContato> > uploadFile(@RequestParam("file") MultipartFile file) {
	      String content = "";
	      List<ClienteContato> clientes = new ArrayList<ClienteContato>();
	      try {
			InputStream is = file.getInputStream();
//			content = ReadExcel.getInstance().toString(is);
			clientes = ReadExcel.getInstance().getClientes(is);
	      } catch (IOException e) {
	    	  e.printStackTrace();
	      }
	      System.out.println(content);
	      return ResponseEntity.status(HttpStatus.OK).body(clientes);
	  }
	
}

