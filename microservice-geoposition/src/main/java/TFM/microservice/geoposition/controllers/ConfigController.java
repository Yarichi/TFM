package TFM.microservice.geoposition.controllers;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import TFM.microservice.geoposition.POJO.BaseResponse;
import TFM.microservice.geoposition.VO.ConfigVO;
import TFM.microservice.geoposition.constants.MessagesConstants;
import TFM.microservice.geoposition.constants.URLConstants;
import TFM.microservice.geoposition.services.LocationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin(maxAge = 3600)
@Api(tags = "Config Controller", value = "/config", description = "Operations about indoor positioning.")
@RestController
@RequestMapping
public class ConfigController {

	@Autowired
	public LocationService service;
	
	@ResponseBody
	@RequestMapping(value = URLConstants.URL_CONFIG_BASE, method=RequestMethod.GET)
	@ApiOperation(value="Obtain the map of a location by its name",
	response=ConfigVO.class,
	httpMethod="GET",
	produces="application/json")
	@ApiResponses(value= {
		@ApiResponse(code=201, message=MessagesConstants.MESSAGE_OK_GET_LOCATION),
		@ApiResponse(code=400, message=MessagesConstants.MESSAGE_KO_GET_LOCATION)
	})
	public ResponseEntity<ConfigVO> getMapByName() throws IOException {
		ConfigVO config = service.getSettings();
		if (config != null) {
			return new ResponseEntity<ConfigVO>(config, HttpStatus.OK);
		}
		else return new ResponseEntity<ConfigVO>(new ConfigVO(), HttpStatus.BAD_REQUEST);
	}
	
	@ResponseBody
	@RequestMapping(value = URLConstants.URL_CONFIG_BASE, method=RequestMethod.POST)
	@ApiOperation(value="Obtain the map of a location by its name",
	response=BaseResponse.class,
	httpMethod="POST",
	produces="application/json")
	@ApiResponses(value= {
		@ApiResponse(code=201, message=MessagesConstants.MESSAGE_OK_GET_LOCATION),
		@ApiResponse(code=400, message=MessagesConstants.MESSAGE_KO_GET_LOCATION)
	})
	public ResponseEntity<BaseResponse> getMapByName(@RequestBody ConfigVO config) throws IOException {
		service.setSettings(config);
		if (config != null) {
			return new ResponseEntity<BaseResponse>(new BaseResponse("Success".getBytes()), HttpStatus.OK);
		}
		else return new ResponseEntity<BaseResponse>(new BaseResponse("Failure".getBytes()), HttpStatus.BAD_REQUEST);
	}
	
}
