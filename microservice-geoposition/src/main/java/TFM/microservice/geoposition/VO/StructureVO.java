package TFM.microservice.geoposition.VO;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "structure_maps")
public class StructureVO {

	private List<CampusVO> campusCounts;
	
	public class CampusVO {
		private String campusName;
		private List<BuildingVO> buildingCounts;
		
		public class BuildingVO {
			private String buildingName;
			private List<FloorVO> floorCounts;
			
			private class FloorVO {
				private String floorName;

				public String getFloorName() {
					return floorName;
				}

				public void setFloorName(String floorName) {
					this.floorName = floorName;
				}
			}

			public String getBuildingName() {
				return buildingName;
			}

			public void setBuildingName(String buildingName) {
				this.buildingName = buildingName;
			}

			public List<FloorVO> getFloorCounts() {
				return floorCounts;
			}

			public void setFloorCounts(List<FloorVO> floorCounts) {
				this.floorCounts = floorCounts;
			}
			
			
		}

		public String getCampusName() {
			return campusName;
		}

		public void setCampusName(String campusName) {
			this.campusName = campusName;
		}

		public List<BuildingVO> getBuildingCounts() {
			return buildingCounts;
		}

		public void setBuildingCounts(List<BuildingVO> buildingCounts) {
			this.buildingCounts = buildingCounts;
		}
		
		
	}

	public List<CampusVO> getCampusCounts() {
		return campusCounts;
	}

	public void setCampusCounts(List<CampusVO> campusCounts) {
		this.campusCounts = campusCounts;
	}
	
	
}
