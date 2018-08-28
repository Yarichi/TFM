package TFM.microservice.geoposition.VO;

public class ZoneCoordinateVO {
	private Double x;
	private Double y;
	private Double z;
	public Double getX() {
		return x;
	}
	public void setX(Double x) {
		this.x = x;
	}
	public Double getY() {
		return y;
	}
	public void setY(Double y) {
		this.y = y;
	}
	public Double getZ() {
		return z;
	}
	public void setZ(Double z) {
		this.z = z;
	}
	public ZoneCoordinateVO(Double x, Double y, Double z) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
	}
	public ZoneCoordinateVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
