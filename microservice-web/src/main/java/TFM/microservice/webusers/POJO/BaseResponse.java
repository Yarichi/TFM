package TFM.microservice.webusers.POJO;

public class BaseResponse {
	private String detail;
	
	public BaseResponse(String detaill) {
		this.detail = detaill;
	}
	
	public String getDetail() {
		return detail;
	}
	
	public void setDetail(String detaill) {
		this.detail = detaill;
	}
	
	
}
