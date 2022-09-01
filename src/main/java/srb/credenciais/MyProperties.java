package srb.credenciais;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class MyProperties {
	
	public String accountSid;
	public String authToken;
	public String phoneNumberOrigin;
	public String phoneNumberDest;
	

	public MyProperties() {
		this.init();
	}
	
	
	public void init() {
		String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		String appConfigPath = rootPath + "app.properties";
//		String catalogConfigPath = rootPath + "catalog";

		Properties appProps = new Properties();
		try {
			appProps.load(new FileInputStream(appConfigPath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

//		Properties catalogProps = new Properties();
//		try {
//			catalogProps.load(new FileInputStream(catalogConfigPath));
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		this.accountSid = appProps.getProperty("accountSid");
		 this.authToken = appProps.getProperty("authToken");
		 
		 this.phoneNumberOrigin = appProps.getProperty("phoneNumberOrigin"); 
		   this.phoneNumberDest = appProps.getProperty("phoneNumberDest");
	}

	public String getAccountSid() {
		return accountSid;
	}


	public void setAccountSid(String accountSid) {
		this.accountSid = accountSid;
	}


	public String getAuthToken() {
		return authToken;
	}


	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}


	public String getPhoneNumberOrigin() {
		return phoneNumberOrigin;
	}


	public void setPhoneNumberOrigin(String phoneNumberOrigin) {
		this.phoneNumberOrigin = phoneNumberOrigin;
	}


	public String getPhoneNumberDest() {
		return phoneNumberDest;
	}


	public void setPhoneNumberDest(String phoneNumberDest) {
		this.phoneNumberDest = phoneNumberDest;
	}
	
}
