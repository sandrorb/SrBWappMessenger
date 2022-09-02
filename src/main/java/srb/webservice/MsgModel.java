package srb.webservice;

public class MsgModel {
	
	public static final long serialVersionUID = 1L;
	
	private String phoneNumberFrom;
	private String phoneNumberTo;
	private String message;
	
	public String getPhoneNumberFrom() {
		return phoneNumberFrom;
	}
	public void setPhoneNumberFrom(String phoneNumberFrom) {
		this.phoneNumberFrom = phoneNumberFrom;
	}
	public String getPhoneNumberTo() {
		return phoneNumberTo;
	}
	public void setPhoneNumberTo(String phoneNumberTo) {
		this.phoneNumberTo = phoneNumberTo;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
