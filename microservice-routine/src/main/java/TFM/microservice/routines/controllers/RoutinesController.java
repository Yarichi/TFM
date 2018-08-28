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

import TFM.microservice.routines.POJO.BaseResponse;
import TFM.microservice.routines.VO.InfoRoutineVO;
import TFM.microservice.routines.VO.MissionVO;
import TFM.microservice.routines.VO.RoutineInputVO;
import TFM.microservice.routines.VO.RoutineOutputVO;
import TFM.microservice.routines.constants.MessagesConstants;
import TFM.microservice.routines.constants.URLConstants;
import TFM.microservice.routines.exceptions.BadRoutineInput;
import TFM.microservice.routines.services.MissionGenerator;
import TFM.microservice.routines.services.RoutinesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin(maxAge = 3600)
@Api(tags="Routine Controller", value="/routine", description="Operations about routines.")
@RestController
public class RoutinesController {
	
	@Autowired
	RoutinesService service;
	
	@Autowired
	MissionGenerator generator;
	
	
	@ResponseBody
	@RequestMapping(value = URLConstants.URL_ROUTINE_BASE, method=RequestMethod.POST)
	@ApiOperation(value="Create a new routine",
				response=BaseResponse.class,
				httpMethod="POST",
				produces="application/json")
	@ApiResponses(value= {
			@ApiResponse(code=201, message=MessagesConstants.MESSAGE_OK_POST_ROUTINE),
			@ApiResponse(code=400, message=MessagesConstants.MESSAGE_KO_POST_ROUTINE)
	})
	public ResponseEntity<BaseResponse> createRoutine(
			@ApiParam(value="Routine to be inserted", required=true) @RequestBody RoutineInputVO routine) throws Exception {
		BaseResponse response = new BaseResponse("The request wasn't as expected.");
		HttpStatus status = HttpStatus.BAD_REQUEST;
		routine.validateInput();
		service.setRoutine(routine);
		response.setDetail("The routine was created succesfully.");
		status = HttpStatus.OK;
		
		return new ResponseEntity<BaseResponse>(response, status);
	}
	
	
	@ResponseBody
	@RequestMapping(value = URLConstants.URL_ROUTINE_NAME, method=RequestMethod.GET)
	@ApiOperation(value="Obtain a routine by its id",
		response=RoutineOutputVO.class,
		httpMethod="GET",
		produces="application/json")
	@ApiResponses(value= {
		@ApiResponse(code=201, message=MessagesConstants.MESSAGE_OK_GET_ROUTINE),
		@ApiResponse(code=400, message=MessagesConstants.MESSAGE_KO_GET_ROUTINE)
	})
	public ResponseEntity<RoutineInputVO> getRoutine(
			@ApiParam(value="Id to look for the routine", required=true) @PathVariable("routineId") String routineId) {
		RoutineInputVO routine = service.getRoutine(routineId);
		return new ResponseEntity<RoutineInputVO>(routine, HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value = URLConstants.URL_ROUTINE_BASE, method=RequestMethod.GET)
	@ApiOperation(value="Obtain all the routines",
	response=RoutineOutputVO.class,
	responseContainer = "List",
	httpMethod="GET",
	produces="application/json")
	@ApiResponses(value= {
		@ApiResponse(code=201, message=MessagesConstants.MESSAGE_OK_GET_ALL_ROUTINE),
		@ApiResponse(code=400, message=MessagesConstants.MESSAGE_KO_GET_ALL_ROUTINE)
	})
	public ResponseEntity<List<RoutineInputVO>> getRoutines() {
		List<RoutineInputVO> routines = service.getRoutines();
		return new ResponseEntity<List<RoutineInputVO>>(routines, HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value = URLConstants.URL_ROUTINE_NAME, method=RequestMethod.PUT)
	@ApiOperation(value="Update an existing routine",
	response=BaseResponse.class,
	httpMethod="PUT",
	produces="application/json")
	@ApiResponses(value= {
		@ApiResponse(code=201, message=MessagesConstants.MESSAGE_OK_PUT_ROUTINE),
		@ApiResponse(code=400, message=MessagesConstants.MESSAGE_KO_PUT_ROUTINE)
	})
	public ResponseEntity<BaseResponse> updateRoutine(
			@ApiParam(value="Id to look for the routine", required=true) @PathVariable("routineId") String routineId,
			@ApiParam(value="Routine information to update", required=true) @RequestBody RoutineInputVO routine) throws Exception {
		BaseResponse response = new BaseResponse("The request wasn't as expected.");
		HttpStatus status = HttpStatus.BAD_REQUEST;
		Boolean updated = service.updateRoutine(routineId, routine);
		if(updated) {
			response.setDetail("The routine was updated correctly");
			status = HttpStatus.OK;
		}
		return new ResponseEntity<BaseResponse>(response, status);
	}
	
	
	@ResponseBody
	@RequestMapping(value = URLConstants.URL_GENERATE_MISSIONS, method=RequestMethod.POST)
	@ApiOperation(value="Generate missions of a routine",
	response=MissionVO.class,
	responseContainer = "List",
	httpMethod="POST",
	produces="application/json")
	@ApiResponses(value= {
		@ApiResponse(code=201, message=MessagesConstants.MESSAGE_OK_GET_MISSIONS),
		@ApiResponse(code=400, message=MessagesConstants.MESSAGE_KO_GET_MISSIONS)
	})
	public ResponseEntity<List<MissionVO>> getMissions(
			@ApiParam(value="Routine information to look for it", required=true) @RequestBody InfoRoutineVO info) throws Exception {
		BaseResponse response = new BaseResponse("The request wasn't as expected.");
		HttpStatus status = HttpStatus.BAD_REQUEST;
		List<MissionVO> missions = generator.generateMissions(info.toString());
		if(missions != null) {
			response.setDetail("The missions were created successfully");
			status = HttpStatus.OK;
		}
		return new ResponseEntity<List<MissionVO>>(missions, status);
	}
	
	@ResponseBody
	@RequestMapping(value = URLConstants.URL_ROUTINE_NAME, method=RequestMethod.DELETE)
	@ApiOperation(value="Delete a routine by its id",
	response=BaseResponse.class,
	httpMethod="DELETE",
	produces="application/json")
	@ApiResponses(value= {
		@ApiResponse(code=201, message=MessagesConstants.MESSAGE_OK_GET_MISSIONS),
		@ApiResponse(code=400, message=MessagesConstants.MESSAGE_KO_GET_MISSIONS)
	})
	public ResponseEntity<BaseResponse> deleteRoutine(
			@ApiParam(value="Id to look for the routine", required=true) @PathVariable("routineId") String routineId) throws Exception {
		BaseResponse response = new BaseResponse("The request wasn't as expected.");
		HttpStatus status = HttpStatus.BAD_REQUEST;
		Boolean deleted = service.deleteRoutine(routineId);
		if(deleted) {
			response.setDetail("The routine was removed successfully");
			status = HttpStatus.OK;
		}
		return new ResponseEntity<BaseResponse>(response, status);
	}
	
	
	@ResponseBody
	@RequestMapping(value = URLConstants.URL_MISSION_COMPLETED, method=RequestMethod.POST)
	@ApiOperation(value="Generate missions of a routine",
	response=BaseResponse.class,
	httpMethod="POST",
	produces="application/json")
	@ApiResponses(value= {
		@ApiResponse(code=201, message=MessagesConstants.MESSAGE_OK_GET_MISSIONS),
		@ApiResponse(code=400, message=MessagesConstants.MESSAGE_KO_GET_MISSIONS)
	})
	public ResponseEntity<BaseResponse> setMissionCompleted(
			@ApiParam(value="Mission id", required=true) @PathVariable("missionId") String missionId) throws Exception {
		BaseResponse response = new BaseResponse("The request wasn't as expected.");
		HttpStatus status = HttpStatus.BAD_REQUEST;
		Boolean seted = generator.setStatus(missionId, 1);
		if(seted) {
			response.setDetail("The missions was completed");
			status = HttpStatus.OK;
		}
		return new ResponseEntity<BaseResponse>(response, status);
	}
	
	@ResponseBody
	@RequestMapping(value = URLConstants.URL_MISSION_INCOMPLETED, method=RequestMethod.POST)
	@ApiOperation(value="Generate missions of a routine",
	response=BaseResponse.class,
	httpMethod="POST",
	produces="application/json")
	@ApiResponses(value= {
		@ApiResponse(code=201, message=MessagesConstants.MESSAGE_OK_GET_MISSIONS),
		@ApiResponse(code=400, message=MessagesConstants.MESSAGE_KO_GET_MISSIONS)
	})
	public ResponseEntity<BaseResponse> setMissionIncompleted(
			@ApiParam(value="Mission id", required=true) @PathVariable("missionId") String missionId) throws Exception {
		BaseResponse response = new BaseResponse("The request wasn't as expected.");
		HttpStatus status = HttpStatus.BAD_REQUEST;
		Boolean seted = generator.setStatus(missionId, -1);
		if(seted) {
			response.setDetail("The missions was incompleted");
			status = HttpStatus.OK;
		}
		return new ResponseEntity<BaseResponse>(response, status);
	}
	
}
