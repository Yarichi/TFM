package TFM.microservice.appusers.controllers;

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

import TFM.microservice.appusers.POJO.BaseResponse;
import TFM.microservice.appusers.VO.AppUserFriendsVO;
import TFM.microservice.appusers.VO.AppUserInputVO;
import TFM.microservice.appusers.VO.AppUserOutputVO;
import TFM.microservice.appusers.VO.ExperienceMessage;
import TFM.microservice.appusers.constants.URLConstants;
import TFM.microservice.appusers.constants.MessagesConstants;
import TFM.microservice.appusers.exceptions.BadAppUserInput;
import TFM.microservice.appusers.services.AppUsersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin(maxAge = 3600)
@Api(tags = "AppUser Controller", value = "/appUser", description = "Operations about app users.")
@RestController
@RequestMapping
public class AppUsersController {

	@Autowired
	AppUsersService service;

	@ResponseBody
	@RequestMapping(value = URLConstants.URL_APPUSER_BASE, method = RequestMethod.POST)
	@ApiOperation(value = "Create a new app user", response = BaseResponse.class, httpMethod = "POST", produces = "application/json")
	@ApiResponses(value = { @ApiResponse(code = 201, message = MessagesConstants.MESSAGE_OK_POST_APPUSER),
			@ApiResponse(code = 400, message = MessagesConstants.MESSAGE_KO_POST_APPUSER) })
	public ResponseEntity<BaseResponse> createAppUser(
			@ApiParam(value = "User to be inserted", required = true) @RequestBody AppUserInputVO user)
			throws BadAppUserInput {
		BaseResponse response = new BaseResponse("The request wasn't as expected.");
		HttpStatus status = HttpStatus.BAD_REQUEST;
		user.validateInput();
		service.setAppUser(user);
		response.setDetail("The user was created succesfully.");
		status = HttpStatus.OK;

		return new ResponseEntity<BaseResponse>(response, status);
	}

	@ResponseBody
	@RequestMapping(value = URLConstants.URL_APPUSER_ALIAS, method = RequestMethod.GET)
	@ApiOperation(value = "Obtain a App user by its alias", response = AppUserOutputVO.class, httpMethod = "GET", produces = "application/json")
	@ApiResponses(value = { @ApiResponse(code = 201, message = MessagesConstants.MESSAGE_OK_GET_APPUSER),
			@ApiResponse(code = 400, message = MessagesConstants.MESSAGE_KO_GET_APPUSER) })
	public ResponseEntity<AppUserInputVO> getAppUser(
			@ApiParam(value = "alias to look for the user", required = true) @PathVariable("alias") String alias) {
		AppUserInputVO user = service.getAppUser(alias);
		return new ResponseEntity<AppUserInputVO>(user, HttpStatus.OK);
	}

	@ResponseBody
	@RequestMapping(value = URLConstants.URL_APPUSER_BASE, method = RequestMethod.GET)
	@ApiOperation(value = "Obtain all the App users", response = AppUserOutputVO.class, responseContainer = "List", httpMethod = "GET", produces = "application/json")
	@ApiResponses(value = { @ApiResponse(code = 201, message = MessagesConstants.MESSAGE_OK_GET_ALL_APPUSER),
			@ApiResponse(code = 400, message = MessagesConstants.MESSAGE_KO_GET_ALL_APPUSER) })
	public ResponseEntity<List<AppUserInputVO>> getAppUsers() {
		List<AppUserInputVO> users = service.getAppUsers();
		return new ResponseEntity<List<AppUserInputVO>>(users, HttpStatus.OK);
	}

	@ResponseBody
	@RequestMapping(value = URLConstants.URL_APPUSER_ALIAS, method = RequestMethod.PUT)
	@ApiOperation(value = "Update an existing App user", response = BaseResponse.class, httpMethod = "PUT", produces = "application/json")
	@ApiResponses(value = { @ApiResponse(code = 201, message = MessagesConstants.MESSAGE_OK_PUT_APPUSER),
			@ApiResponse(code = 400, message = MessagesConstants.MESSAGE_KO_PUT_APPUSER) })
	public ResponseEntity<BaseResponse> updateAppUser(
			@ApiParam(value = "alias to look for the user", required = true) @PathVariable("alias") String alias,
			@ApiParam(value = "User information to update", required = true) @RequestBody AppUserInputVO user) {
		BaseResponse response = new BaseResponse(MessagesConstants.MESSAGE_KO_PUT_APPUSER);
		HttpStatus status = HttpStatus.BAD_REQUEST;
		Boolean updated = service.updateAppUser(alias, user);
		if (updated) {
			response.setDetail("The user was updated correctly");
			status = HttpStatus.OK;
		}
		return new ResponseEntity<BaseResponse>(response, status);
	}

	@ResponseBody
	@RequestMapping(value = URLConstants.URL_APPUSER_ALIAS, method = RequestMethod.DELETE)
	@ApiOperation(value = "Delete an App user by its alias", response = AppUserInputVO.class, httpMethod = "DELETE", produces = "application/json")
	@ApiResponses(value = { @ApiResponse(code = 201, message = MessagesConstants.MESSAGE_OK_DELETE_APPUSER),
			@ApiResponse(code = 400, message = MessagesConstants.MESSAGE_KO_DELETE_APPUSER) })
	public ResponseEntity<BaseResponse> deleteAppUser(
			@ApiParam(value = "alias to look for the user", required = true) @PathVariable("alias") String alias) {
		BaseResponse response = new BaseResponse(MessagesConstants.MESSAGE_KO_DELETE_APPUSER);
		HttpStatus status = HttpStatus.BAD_REQUEST;
		Boolean deleted = service.deleteAppUser(alias);
		if (deleted) {
			response.setDetail(MessagesConstants.MESSAGE_OK_DELETE_APPUSER);
			status = HttpStatus.OK;
		}
		return new ResponseEntity<BaseResponse>(response, status);
	}

	
	
	@ResponseBody
	@RequestMapping(value = URLConstants.URL_APPUSER_FRIENDS, method = RequestMethod.GET)
	@ApiOperation(value = "Obtain all the friends from an user", response = AppUserFriendsVO.class, httpMethod = "GET", produces = "application/json")
	@ApiResponses(value = { @ApiResponse(code = 201, message = MessagesConstants.MESSAGE_OK_GET_ALL_APPUSER_FRIENDS),
			@ApiResponse(code = 400, message = MessagesConstants.MESSAGE_KO_GET_ALL_APPUSER_FRIENDS) })
	public ResponseEntity<AppUserFriendsVO> getAppUserFriends(
			@ApiParam(value ="Alias to look for the user") @PathVariable("alias") String alias) {
		AppUserFriendsVO friends = service.getAppUserFriends(alias);
		return new ResponseEntity<AppUserFriendsVO>(friends, HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value = URLConstants.URL_APPUSER_FRIENDS_ADD, method = RequestMethod.PUT)
	@ApiOperation(value = "Add a friend to an user", response = BaseResponse.class, httpMethod = "PUT", produces = "application/json")
	@ApiResponses(value = { @ApiResponse(code = 201, message = MessagesConstants.MESSAGE_OK_GET_ALL_APPUSER_FRIENDS),
			@ApiResponse(code = 400, message = MessagesConstants.MESSAGE_KO_GET_ALL_APPUSER_FRIENDS) })
	public ResponseEntity<BaseResponse> addAppUserFriend(
			@ApiParam(value ="Alias to look for the user") @PathVariable("alias") String alias,
			@ApiParam(value ="Alias of the friend to add") @PathVariable("aliasFriend") String aliasFriend) {
		System.out.println("HOLA");
		BaseResponse base = new BaseResponse("Friend added succesfully");
		Boolean friends = service.addAppUserFriend(alias, aliasFriend);
		return new ResponseEntity<BaseResponse>(base, HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value = URLConstants.URL_APPUSER_FRIENDS_ADD, method = RequestMethod.DELETE)
	@ApiOperation(value = "Delete a friend from an user", response = BaseResponse.class, httpMethod = "DELETE", produces = "application/json")
	@ApiResponses(value = { @ApiResponse(code = 201, message = MessagesConstants.MESSAGE_OK_GET_ALL_APPUSER_FRIENDS),
			@ApiResponse(code = 400, message = MessagesConstants.MESSAGE_KO_GET_ALL_APPUSER_FRIENDS) })
	public ResponseEntity<BaseResponse> deleteAppUserFriend(
			@ApiParam(value ="Alias to look for the user") @PathVariable("alias") String alias,
			@ApiParam(value ="Alias of the friend to delete") @PathVariable("aliasFriend") String aliasFriend) {
		System.out.println("HOLA");
		BaseResponse base = new BaseResponse("Friend added succesfully");
		Boolean friends = service.deleteAppUserFriend(alias, aliasFriend);
		return new ResponseEntity<BaseResponse>(base, HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value = URLConstants.URL_EXPERIENCE_USER, method = RequestMethod.POST)
	@ApiOperation(value = "Update an user experience", response = BaseResponse.class, httpMethod = "POST", produces = "application/json")
	@ApiResponses(value = { @ApiResponse(code = 201, message = MessagesConstants.MESSAGE_OK_POST_APPUSER),
			@ApiResponse(code = 400, message = MessagesConstants.MESSAGE_KO_POST_APPUSER) })
	public ResponseEntity<BaseResponse> createAppUser(
			@ApiParam(value = "User to update") @PathVariable("alias") String alias,
			@ApiParam(value = "Experience", required = true) @RequestBody ExperienceMessage experience) {
		BaseResponse response = new BaseResponse("The request wasn't as expected.");
		HttpStatus status = HttpStatus.BAD_REQUEST;
		service.updateExperienceUser(alias, experience);
		response.setDetail("The user was updated succesfully.");
		status = HttpStatus.OK;

		return new ResponseEntity<BaseResponse>(response, status);
	}
}
