package srb;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class MyProperties {
	
	public static String accountSid;
	public static String authToken;
	public static String phoneNumberOrigin;
	public static String phoneNumberDest;
	
	
	
	public static void init() {
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
		
		accountSid = appProps.getProperty("accountSid");
		 authToken = appProps.getProperty("authToken");
		 
		 phoneNumberOrigin = appProps.getProperty("phoneNumberOrigin"); 
		   phoneNumberDest = appProps.getProperty("phoneNumberDest");
	}

}
