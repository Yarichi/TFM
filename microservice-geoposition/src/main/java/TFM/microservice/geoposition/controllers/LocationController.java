package TFM.microservice.geoposition.controllers;


import java.io.IOException;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import TFM.microservice.geoposition.VO.InfoMapVO;
import TFM.microservice.geoposition.VO.InfoMapVO.ZoneVO.ZoneCoordinate;
import TFM.microservice.geoposition.VO.LocationVO;
import TFM.microservice.geoposition.VO.StructureVO;
import TFM.microservice.geoposition.VO.ZoneCoordinateVO;
import TFM.microservice.geoposition.constants.MessagesConstants;
import TFM.microservice.geoposition.constants.URLConstants;
import TFM.microservice.geoposition.services.LocationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin(maxAge = 3600)
@Api(tags = "Geoposition Controller", value = "/location", description = "Operations about indoor positioning.")
@RestController
@RequestMapping
public class LocationController {

	@Autowired
	public LocationService service;
	
	@ResponseBody
	@RequestMapping(value = URLConstants.URL_IMAGE_BY_NAME, method=RequestMethod.GET)
	@ApiOperation(value="Obtain the map of a location by its name",
	response=byte[].class,
	httpMethod="GET",
	produces="application/json")
	@ApiResponses(value= {
		@ApiResponse(code=201, message=MessagesConstants.MESSAGE_OK_GET_LOCATION),
		@ApiResponse(code=400, message=MessagesConstants.MESSAGE_KO_GET_LOCATION)
	})
	public ResponseEntity<byte[]> getMapByName(
			@ApiParam(value = "alias to look for the user", required = true) @PathVariable("name") String name) throws IOException {
		byte[] map = service.getMapImageByName(name);
		if (map != null) {
			return new ResponseEntity<byte[]>(map, HttpStatus.OK);
		}
		else return new ResponseEntity<byte[]>(new byte[0], HttpStatus.BAD_REQUEST);
	}
	
	@ResponseBody
	@RequestMapping(value = URLConstants.URL_IMAGE_BY_CBF, method=RequestMethod.GET)
	@ApiOperation(value="Obtain the map of a location by its campus, building and floor",
	response=byte[].class,
	httpMethod="GET",
	produces="text/plain")
	@ApiResponses(value= {
		@ApiResponse(code=201, message=MessagesConstants.MESSAGE_OK_GET_LOCATION),
		@ApiResponse(code=400, message=MessagesConstants.MESSAGE_KO_GET_LOCATION)
	})
	public ResponseEntity<byte[]> getMapByCBF(
			@ApiParam(value = "alias to look for the user", required = true) @PathVariable("campus")String campus,
			@ApiParam(value = "alias to look for the user", required = true) @PathVariable("building")String building,
			@ApiParam(value = "alias to look for the user", required = true) @PathVariable("floor")String floor) throws IOException {
		byte[] map = service.getMapImageByCBF(campus, building, floor);
		if (map != null) {
			return new ResponseEntity<byte[]>(map, HttpStatus.OK);
		}
		else return new ResponseEntity<byte[]>(new byte[0], HttpStatus.BAD_REQUEST);
	}
	
	
	@ResponseBody
	@RequestMapping(value = URLConstants.URL_LOCATION_MAC, method=RequestMethod.GET)
	@ApiOperation(value="Obtain the location of an app user by its mac address",
	response=LocationVO.class,
	httpMethod="GET",
	produces="application/json")
	@ApiResponses(value= {
		@ApiResponse(code=201, message=MessagesConstants.MESSAGE_OK_GET_LOCATION),
		@ApiResponse(code=400, message=MessagesConstants.MESSAGE_KO_GET_LOCATION)
	})
	public ResponseEntity<LocationVO> getLocationAppUser(
			@ApiParam(value = "alias to look for the user", required = true) @PathVariable("ip_address") String ip_address) throws IOException {
		LocationVO location = service.getLocationByIp(new String(Base64.decodeBase64(ip_address)));
		if (location != null) {
			return new ResponseEntity<LocationVO>(location, HttpStatus.OK);
		}
		else return new ResponseEntity<LocationVO>(new LocationVO(), HttpStatus.BAD_REQUEST);
	}
	
	
	@ResponseBody
	@RequestMapping(value = URLConstants.URL_MAP_INFO, method=RequestMethod.GET)
	@ApiOperation(value="Obtain the location of an app user by its mac address",
	response=InfoMapVO.class,
	httpMethod="GET",
	produces="application/json")
	@ApiResponses(value= {
		@ApiResponse(code=201, message=MessagesConstants.MESSAGE_OK_GET_LOCATION),
		@ApiResponse(code=400, message=MessagesConstants.MESSAGE_KO_GET_LOCATION)
	})
	public ResponseEntity<InfoMapVO> getInfoMap(
			@ApiParam(value = "alias to look for the user", required = true) @PathVariable("campus")String campus,
			@ApiParam(value = "alias to look for the user", required = true) @PathVariable("building")String building,
			@ApiParam(value = "alias to look for the user", required = true) @PathVariable("floor")String floor) throws Exception {
		InfoMapVO location = service.getInfoMap(campus, building, floor);
		if (location != null) {
			return new ResponseEntity<InfoMapVO>(location, HttpStatus.OK);
		}
		else return new ResponseEntity<InfoMapVO>(new InfoMapVO(), HttpStatus.BAD_REQUEST);
	
	
	
	
	}
	
	@ResponseBody
	@RequestMapping(value = URLConstants.URL_STRUCTURE_INFO, method=RequestMethod.GET)
	@ApiOperation(value="Obtain the location of an app user by its mac address",
	response=StructureVO.class,
	httpMethod="GET",
	produces="application/json")
	@ApiResponses(value= {
		@ApiResponse(code=201, message=MessagesConstants.MESSAGE_OK_GET_LOCATION),
		@ApiResponse(code=400, message=MessagesConstants.MESSAGE_KO_GET_LOCATION)
	})
	public ResponseEntity<StructureVO> getInfoMap() throws Exception {
		StructureVO structure = service.getStructureInfo();
		if (structure != null) {
			return new ResponseEntity<StructureVO>(structure, HttpStatus.OK);
		}
		else return new ResponseEntity<StructureVO>(new StructureVO(), HttpStatus.BAD_REQUEST);
	
	}
	
	
	@ResponseBody
	@RequestMapping(value = URLConstants.URL_ZONE_COORDINATE, method=RequestMethod.GET)
	@ApiOperation(value="Obtain the coordinates of the zone",
	response=ZoneCoordinate.class,
	httpMethod="GET",
	produces="application/json")
	@ApiResponses(value= {
		@ApiResponse(code=201, message=MessagesConstants.MESSAGE_OK_GET_LOCATION),
		@ApiResponse(code=400, message=MessagesConstants.MESSAGE_KO_GET_LOCATION)
	})
	public ResponseEntity<ZoneCoordinate> getZoneCoordinates(
			@ApiParam(value = "alias to look for the user", required = true) @PathVariable("campus")String campus,
			@ApiParam(value = "alias to look for the user", required = true) @PathVariable("building")String building,
			@ApiParam(value = "alias to look for the user", required = true) @PathVariable("floor")String floor,
			@ApiParam(value = "alias to look for the user", required = true) @PathVariable("zone")String zone) throws Exception {
		ZoneCoordinate coordinate = service.getZoneCoordinate(campus, building, floor, zone);
		
		return new ResponseEntity<ZoneCoordinate>(coordinate, HttpStatus.OK);
	
	
	
	
	}
	
}
