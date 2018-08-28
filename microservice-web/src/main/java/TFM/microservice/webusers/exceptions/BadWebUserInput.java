package TFM.microservice.webusers.exceptions;

public class BadWebUserInput extends Exception{

	/**
	 * 
	 */
	private String detail;
	private static final long serialVersionUID = 1L;

	public BadWebUserInput(String detaill) {
		this.detail = detaill;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	
}
