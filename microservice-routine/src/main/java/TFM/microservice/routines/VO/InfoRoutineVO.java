package TFM.microservice.routines.VO;

public class InfoRoutineVO {
	private String username;
	private Integer day;
	private Integer month;
	private Integer year;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getDay() {
		return day;
	}
	public void setDay(Integer day) {
		this.day = day;
	}
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	@Override
	public String toString() {
		return String.format("%s_%d_%d_%d", this.username, this.day, this.month, this.year);
	}
	
	
}
