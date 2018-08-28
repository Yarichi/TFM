package TFM.microservice.geoposition.POJO;

public class BaseResponse {
	private byte[] detail;

	public byte[] getDetail() {
		return detail;
	}

	public void setDetail(byte[] detail) {
		this.detail = detail;
	}

	public BaseResponse(byte[] detail) {
		super();
		this.detail = detail;
	}
	
	public BaseResponse() {
		super();
	}
	
}
