package TFM.microservice.geoposition.VO;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Document(collection = "info_maps")
public class InfoMapVO {
	private Integer floorNumber;
	private DimensionVO dimension;
	private ImageVO image;
	private List<ZoneVO> zones;
	
	public class DimensionVO{
		private Integer length;
		private Integer width;
		private Integer height;
		private Integer z;
		private String unit;
		
		
		public Integer getWidth() {
			return width;
		}
		public void setWidth(Integer width) {
			this.width = width;
		}
		public Integer getLength() {
			return length;
		}
		public void setLength(Integer length) {
			this.length = length;
		}
		public Integer getHeight() {
			return height;
		}
		public void setHeight(Integer height) {
			this.height = height;
		}
		public Integer getZ() {
			return z;
		}
		public void setZ(Integer z) {
			this.z = z;
		}
		public String getUnit() {
			return unit;
		}
		public void setUnit(String unit) {
			this.unit = unit;
		}
		
		
	}
	
	public class ImageVO{
		private Integer width;
		private Integer height;
		public Integer getWidth() {
			return width;
		}
		public void setWidth(Integer width) {
			this.width = width;
		}
		public Integer getHeight() {
			return height;
		}
		public void setHeight(Integer height) {
			this.height = height;
		}
		
		
		
	}
	
	public class ZoneVO{
		private String name;
		@JsonIgnore
		private List<ZoneCoordinate> zoneCoordinate;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		
		
		public List<ZoneCoordinate> getZoneCoordinate() {
			return zoneCoordinate;
		}

		public void setZoneCoordinate(List<ZoneCoordinate> zoneCoordinate) {
			this.zoneCoordinate = zoneCoordinate;
		}
		
		public ZoneCoordinate middlePoint() {
			Double min_x = this.zoneCoordinate.get(0).getX();
			Double max_x = this.zoneCoordinate.get(0).getX();

			Double min_y = this.zoneCoordinate.get(0).getY();
			Double max_y = this.zoneCoordinate.get(0).getY();
			
			Double min_z = this.zoneCoordinate.get(0).getZ();
			Double max_z = this.zoneCoordinate.get(0).getZ();
			for (int i = 1; i < this.zoneCoordinate.size(); i++) {
				Double x = this.zoneCoordinate.get(i).getX();
				Double y = this.zoneCoordinate.get(i).getY();
				Double z = this.zoneCoordinate.get(i).getZ();
				
				if(x > max_x) max_x = x;
				if(x < min_x) min_x = x;
				
				if(y > max_y) max_y = y;
				if(y < min_y) min_y = y;
				
				if(z > max_z) max_z = z;
				if(z < min_z) min_z = z;
			}
			
			Double xCenter = (min_x + max_x)/2;
			Double yCenter = (min_y + max_y)/2;
			Double zCenter = (min_z + max_z)/2;
			return new ZoneCoordinate(xCenter, yCenter, zCenter);
		}



		public class ZoneCoordinate {
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
			public ZoneCoordinate(Double x, Double y, Double z) {
				super();
				this.x = x;
				this.y = y;
				this.z = z;
			}
			public ZoneCoordinate() {super();}
			
		}
		
	}

	public Integer getFloorNumber() {
		return floorNumber;
	}

	public void setFloorNumber(Integer floorNumber) {
		this.floorNumber = floorNumber;
	}

	public DimensionVO getDimension() {
		return dimension;
	}

	public void setDimension(DimensionVO dimension) {
		this.dimension = dimension;
	}

	public ImageVO getImage() {
		return image;
	}

	public void setImage(ImageVO image) {
		this.image = image;
	}

	public List<ZoneVO> getZones() {
		return zones;
	}

	public void setZones(List<ZoneVO> zones) {
		this.zones = zones;
	}

	

	
}
