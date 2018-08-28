package TFM.microservice.appusers.exceptions;

public class BadAppUserInput extends Exception{

	/**
	 * 
	 */
	private String detail;
	private static final long serialVersionUID = 1L;

	public BadAppUserInput(String detaill) {
		this.detail = detaill;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	
}
