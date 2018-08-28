package TFM.microservice.geoposition.VO;

import org.springframework.data.mongodb.core.mapping.Document;

import TFM.microservice.geoposition.VO.LocationVO.LocationCoordinate;

@Document(collection = "locations")
public class LocationVO {
	private String mac_user;
	private String locationMapHierarchy;
	private LocationCoordinate locationCoordinate;
	private GeoCoordinate geoCoordinate;
	
	public class LocationCoordinate{
		private Double x;
		private Double y;
		private Double z;
		private String unit;
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
		public String getUnit() {
			return unit;
		}
		public void setUnit(String unit) {
			this.unit = unit;
		}
		
		
	}
	
	public class GeoCoordinate{
		private String latitude;
		private String longitude;
		private String unit;
		public String getLatitude() {
			return latitude;
		}
		public void setLatitude(String latitude) {
			this.latitude = latitude;
		}
		public String getLongitude() {
			return longitude;
		}
		public void setLongitude(String longitude) {
			this.longitude = longitude;
		}
		public String getUnit() {
			return unit;
		}
		public void setUnit(String unit) {
			this.unit = unit;
		}
		
		
	}

	public String getMac_user() {
		return mac_user;
	}

	public void setMac_user(String mac_user) {
		this.mac_user = mac_user;
	}

	public LocationCoordinate getLocationCoordinate() {
		return locationCoordinate;
	}

	public void setLocationCoordinate(LocationCoordinate locationCoordinate) {
		this.locationCoordinate = locationCoordinate;
	}

	public GeoCoordinate getGeoCoordinate() {
		return geoCoordinate;
	}

	public void setGeoCoordinate(GeoCoordinate geoCoordinate) {
		this.geoCoordinate = geoCoordinate;
	}

	public String getLocationMapHierarchy() {
		return locationMapHierarchy;
	}

	public void setLocationMapHierarchy(String locationMapHierarchy) {
		this.locationMapHierarchy = locationMapHierarchy;
	}
	
	public LocationVO createLocation() {
		LocationCoordinate co = new LocationCoordinate();
		co.setUnit("FEET");
		co.setX(35.4);
		co.setY(65.4);
		co.setZ(0.0);
		this.setLocationCoordinate(co);
		this.setLocationMapHierarchy("DevNetCampus>DevNetBuilding>DevNetZone");
		return this;
	}
	
}
