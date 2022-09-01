package srb.webservice;

public class WmsgModel {
	
	public String accountSid;
	public String authToken;
	public String phoneNumberOriginStr;
	public String phoneNumberDestStr;
	public String bodyMsg;
	
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
	public String getPhoneNumberOriginStr() {
		return phoneNumberOriginStr;
	}
	public void setPhoneNumberOriginStr(String phoneNumberOriginStr) {
		this.phoneNumberOriginStr = phoneNumberOriginStr;
	}
	public String getPhoneNumberDestStr() {
		return phoneNumberDestStr;
	}
	public void setPhoneNumberDestStr(String phoneNumberDestStr) {
		this.phoneNumberDestStr = phoneNumberDestStr;
	}
	public String getBodyMsg() {
		return bodyMsg;
	}
	public void setBodyMsg(String bodyMsg) {
		this.bodyMsg = bodyMsg;
	}

}
