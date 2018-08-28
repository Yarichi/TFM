package TFM.microservice.routines.exceptions;

public class BadRoutineInput extends Exception{

	/**
	 * 
	 */
	private String detail;
	private static final long serialVersionUID = 1L;

	public BadRoutineInput(String detaill) {
		this.detail = detaill;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	
}
