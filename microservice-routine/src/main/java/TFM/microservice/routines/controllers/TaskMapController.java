package TFM.microservice.routines.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import TFM.microservice.routines.VO.RoutineInputVO;
import TFM.microservice.routines.VO.TaskMapVO;
import TFM.microservice.routines.VO.TemplateOutputVO;
import TFM.microservice.routines.constants.MessagesConstants;
import TFM.microservice.routines.constants.URLConstants;
import TFM.microservice.routines.exceptions.BadRoutineInput;
import TFM.microservice.routines.services.TaskMapService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin(maxAge = 3600)
@Api(tags="TaskMap Controller", value="/taskMap", description="Operations about routines.")
@RestController
public class TaskMapController {
	
	@Autowired
	TaskMapService service;
	
	
	
	@ResponseBody
	@RequestMapping(value = URLConstants.URL_TASKMAP_BASE, method=RequestMethod.GET)
	@ApiOperation(value="Get all task maps",
				response=TaskMapVO.class,
				responseContainer = "List",
				httpMethod="GET",
				produces="application/json")
	@ApiResponses(value= {
			@ApiResponse(code=201, message=MessagesConstants.MESSAGE_OK_POST_ROUTINE),
			@ApiResponse(code=400, message=MessagesConstants.MESSAGE_KO_POST_ROUTINE)
	})
	public ResponseEntity<List<TaskMapVO>> getTaskMaps() throws BadRoutineInput {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		List<TaskMapVO> response = service.getTaskMaps();
		status = HttpStatus.OK;
		
		return new ResponseEntity<List<TaskMapVO>>(response, status);
	}
	
	@ResponseBody
	@RequestMapping(value = URLConstants.URL_TASKMAP_TEMPLATES, method=RequestMethod.GET)
	@ApiOperation(value="Get all templates of a task map",
				response=TemplateOutputVO.class,
				responseContainer = "List",
				httpMethod="GET",
				produces="application/json")
	@ApiResponses(value= {
			@ApiResponse(code=201, message=MessagesConstants.MESSAGE_OK_POST_ROUTINE),
			@ApiResponse(code=400, message=MessagesConstants.MESSAGE_KO_POST_ROUTINE)
	})
	public ResponseEntity<List<TemplateOutputVO>> getTemplates(
			@ApiParam("Task map to look for") @PathVariable("taskMap") String taskMap) throws BadRoutineInput {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		List<TemplateOutputVO> response = service.getTemplates(taskMap);
		status = HttpStatus.OK;
		
		return new ResponseEntity<List<TemplateOutputVO>>(response, status);
	}
	
	
	
	
}
