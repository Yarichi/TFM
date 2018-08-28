package TFM.microservice.webusers.controllers;

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

import TFM.microservice.webusers.POJO.BaseResponse;
import TFM.microservice.webusers.VO.PermissionsInputVO;
import TFM.microservice.webusers.VO.PermissionsOutputVO;
import TFM.microservice.webusers.VO.RolesInputVO;
import TFM.microservice.webusers.VO.RolesOutputVO;
import TFM.microservice.webusers.VO.UserChatIdVO;
import TFM.microservice.webusers.VO.WebUserInputVO;
import TFM.microservice.webusers.VO.WebUserOutputVO;
import TFM.microservice.webusers.VO.WebUserSelectOutputVO;
import TFM.microservice.webusers.constants.URLConstants;
import TFM.microservice.webusers.constants.MessagesConstants;
import TFM.microservice.webusers.exceptions.BadWebUserInput;
import TFM.microservice.webusers.services.WebUsersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin(maxAge=3600)
@Api(tags="WebUser Controller", value="/webUser", description="Operations about web users.")
@RestController
public class WebUsersController {
	
	@Autowired
	WebUsersService service;
	
	
	@ResponseBody
	@RequestMapping(value = URLConstants.URL_WEBUSER_BASE, method=RequestMethod.POST)
	@ApiOperation(value="Create a new web user",
				response=BaseResponse.class,
				httpMethod="POST",
				produces="application/json")
	@ApiResponses(value= {
			@ApiResponse(code=201, message=MessagesConstants.MESSAGE_OK_POST_WEBUSER),
			@ApiResponse(code=400, message=MessagesConstants.MESSAGE_KO_POST_WEBUSER)
	})
	public ResponseEntity<BaseResponse> createWebUser(
			@ApiParam(value="User to be inserted", required=true) @RequestBody WebUserInputVO user) throws BadWebUserInput {
		BaseResponse response = new BaseResponse("The request wasn't as expected.");
		HttpStatus status = HttpStatus.BAD_REQUEST;
		user.validateInput();
		service.setWebUser(user);
		response.setDetail("The user was created succesfully.");
		status = HttpStatus.OK;
		
		return new ResponseEntity<BaseResponse>(response, status);
	}
	
	
	@ResponseBody
	@RequestMapping(value = URLConstants.URL_WEBUSER_ID, method=RequestMethod.GET)
	@ApiOperation(value="Obtain a web user by its user id",
		response=WebUserOutputVO.class,
		httpMethod="GET",
		produces="application/json")
	@ApiResponses(value= {
		@ApiResponse(code=201, message=MessagesConstants.MESSAGE_OK_GET_WEBUSER),
		@ApiResponse(code=400, message=MessagesConstants.MESSAGE_KO_GET_WEBUSER)
	})
	public ResponseEntity<WebUserInputVO> getWebUser(
			@ApiParam(value="User id to look for the user", required=true) @PathVariable("userId") String userId) {
		WebUserInputVO user = service.getWebUser(userId);
		return new ResponseEntity<WebUserInputVO>(user, HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value = URLConstants.URL_WEBUSER_MAIL, method=RequestMethod.GET)
	@ApiOperation(value="Obtain a web user by its mail",
		response=WebUserOutputVO.class,
		httpMethod="GET",
		produces="application/json")
	@ApiResponses(value= {
		@ApiResponse(code=201, message=MessagesConstants.MESSAGE_OK_GET_WEBUSER),
		@ApiResponse(code=400, message=MessagesConstants.MESSAGE_KO_GET_WEBUSER)
	})
	public ResponseEntity<WebUserInputVO> getWebUserByMail(
			@ApiParam(value="Mail to look for the user", required=true) @PathVariable("mail") String mail) {
		WebUserInputVO user = service.getWebUserByMail(mail);
		return new ResponseEntity<WebUserInputVO>(user, HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value = URLConstants.URL_WEBUSER_BASE, method=RequestMethod.GET)
	@ApiOperation(value="Obtain all the web users",
	response=WebUserOutputVO.class,
	responseContainer = "List",
	httpMethod="GET",
	produces="application/json")
	@ApiResponses(value= {
		@ApiResponse(code=201, message=MessagesConstants.MESSAGE_OK_GET_ALL_WEBUSER),
		@ApiResponse(code=400, message=MessagesConstants.MESSAGE_KO_GET_ALL_WEBUSER)
	})
	public ResponseEntity<List<WebUserInputVO>> getWebUsers() {
		List<WebUserInputVO> users = service.getWebUsers();
		return new ResponseEntity<List<WebUserInputVO>>(users, HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value = URLConstants.URL_WEBUSER_ID, method=RequestMethod.PUT)
	@ApiOperation(value="Update an existing web user",
	response=BaseResponse.class,
	httpMethod="PUT",
	produces="application/json")
	@ApiResponses(value= {
		@ApiResponse(code=201, message=MessagesConstants.MESSAGE_OK_PUT_WEBUSER),
		@ApiResponse(code=400, message=MessagesConstants.MESSAGE_KO_PUT_WEBUSER)
	})
	public ResponseEntity<BaseResponse> updateWebUser(
			@ApiParam(value="User id to look for the user", required=true) @PathVariable("userId") String userId,
			@ApiParam(value="User information to update", required=true) @RequestBody WebUserInputVO user) {
		BaseResponse response = new BaseResponse("The request wasn't as expected.");
		HttpStatus status = HttpStatus.BAD_REQUEST;
		Boolean updated = service.updateWebUser(userId, user);
		if(updated) {
			response.setDetail("The user was updated correctly");
			status = HttpStatus.OK;
		}
		return new ResponseEntity<BaseResponse>(response, status);
	}
	
	@ResponseBody
	@RequestMapping(value = URLConstants.URL_WEBUSER_ID, method=RequestMethod.DELETE)
	@ApiOperation(value="Delete an existing web user",
	response=BaseResponse.class,
	httpMethod="DELETE",
	produces="application/json")
	@ApiResponses(value= {
		@ApiResponse(code=201, message=MessagesConstants.MESSAGE_OK_DELETE_WEBUSER),
		@ApiResponse(code=400, message=MessagesConstants.MESSAGE_KO_DELETE_WEBUSER)
	})
	public ResponseEntity<BaseResponse> deleteWebUser(
			@ApiParam(value="User id to look for the user", required=true) @PathVariable("userId") String userId) {
		BaseResponse response = new BaseResponse("The request wasn't as expected.");
		HttpStatus status = HttpStatus.BAD_REQUEST;
		Boolean deleted = service.deleteWebUser(userId);
		if(deleted) {
			response.setDetail("The user was deleted correctly");
			status = HttpStatus.OK;
		}
		return new ResponseEntity<BaseResponse>(response, status);
	}
	
	@ResponseBody
	@RequestMapping(value = URLConstants.URL_WEBUSER_ROLE, method=RequestMethod.POST)
	@ApiOperation(value="Add role to user",
				response=BaseResponse.class,
				httpMethod="POST",
				produces="application/json")
	@ApiResponses(value= {
			@ApiResponse(code=201, message=MessagesConstants.MESSAGE_OK_POST_WEBUSER),
			@ApiResponse(code=400, message=MessagesConstants.MESSAGE_KO_POST_WEBUSER)
	})
	public ResponseEntity<BaseResponse> addRoleUser(
			@ApiParam(value="User to be inserted", required=true)@PathVariable("userId") String userId,
			@ApiParam(value="Role to be inserted", required=true)@PathVariable("roleId") String roleId) {
		BaseResponse response = new BaseResponse("The request wasn't as expected.");
		HttpStatus status = HttpStatus.BAD_REQUEST;
		service.addRoleToUser(userId, roleId);
		response.setDetail("The role was added succesfully.");
		status = HttpStatus.OK;
		
		return new ResponseEntity<BaseResponse>(response, status);
	}
	
	/**
	 * ROLES
	 */
	
	@ResponseBody
	@RequestMapping(value = URLConstants.URL_ROLE_ID, method=RequestMethod.DELETE)
	@ApiOperation(value="Delete an existing role",
	response=BaseResponse.class,
	httpMethod="DELETE",
	produces="application/json")
	@ApiResponses(value= {
		@ApiResponse(code=201, message=MessagesConstants.MESSAGE_OK_DELETE_ROLE),
		@ApiResponse(code=400, message=MessagesConstants.MESSAGE_KO_DELETE_ROLE)
	})
	public ResponseEntity<BaseResponse> deleteRole(
			@ApiParam(value="Role id to look for the role", required=true) @PathVariable("roleId") String roleId) {
		BaseResponse response = new BaseResponse("The request wasn't as expected.");
		HttpStatus status = HttpStatus.BAD_REQUEST;
		Boolean deleted = service.deleteRole(roleId);
		if(deleted) {
			response.setDetail("The role was deleted correctly");
			status = HttpStatus.OK;
		}
		return new ResponseEntity<BaseResponse>(response, status);
	}
	
	@ResponseBody
	@RequestMapping(value = URLConstants.URL_ROLE_ID, method=RequestMethod.PUT)
	@ApiOperation(value="Update an existing role",
	response=BaseResponse.class,
	httpMethod="PUT",
	produces="application/json")
	@ApiResponses(value= {
		@ApiResponse(code=201, message=MessagesConstants.MESSAGE_OK_PUT_ROLE),
		@ApiResponse(code=400, message=MessagesConstants.MESSAGE_KO_PUT_ROLE)
	})
	public ResponseEntity<BaseResponse> updateRole(
			@ApiParam(value="Role id to look for the role", required=true) @PathVariable("roleId") String roleId,
			@ApiParam(value="Role information to update", required=true) @RequestBody RolesInputVO role) {
		BaseResponse response = new BaseResponse("The request wasn't as expected.");
		HttpStatus status = HttpStatus.BAD_REQUEST;
		Boolean updated = service.updateRole(roleId, role);
		if(updated) {
			response.setDetail("The role was updated correctly");
			status = HttpStatus.OK;
		}
		return new ResponseEntity<BaseResponse>(response, status);
	}
	
	@ResponseBody
	@RequestMapping(value = URLConstants.URL_ROLE_BASE, method=RequestMethod.GET)
	@ApiOperation(value="Obtain all the roles",
	response=RolesOutputVO.class,
	responseContainer = "List",
	httpMethod="GET",
	produces="application/json")
	@ApiResponses(value= {
		@ApiResponse(code=201, message=MessagesConstants.MESSAGE_OK_GET_ALL_ROLE),
		@ApiResponse(code=400, message=MessagesConstants.MESSAGE_KO_GET_ALL_ROLE)
	})
	public ResponseEntity<List<RolesInputVO>> getRoles() {
		List<RolesInputVO> roles = service.getRoles();
		return new ResponseEntity<List<RolesInputVO>>(roles, HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value = URLConstants.URL_ROLE_BASE, method=RequestMethod.POST)
	@ApiOperation(value="Create a new role",
				response=BaseResponse.class,
				httpMethod="POST",
				produces="application/json")
	@ApiResponses(value= {
			@ApiResponse(code=201, message=MessagesConstants.MESSAGE_OK_POST_ROLE),
			@ApiResponse(code=400, message=MessagesConstants.MESSAGE_KO_POST_ROLE)
	})
	public ResponseEntity<BaseResponse> createRole(
			@ApiParam(value="Role to be inserted", required=true) @RequestBody RolesInputVO role) {
		BaseResponse response = new BaseResponse("The request wasn't as expected.");
		HttpStatus status = HttpStatus.BAD_REQUEST;
		service.setRole(role);
		response.setDetail("The role was created succesfully.");
		status = HttpStatus.OK;
		
		return new ResponseEntity<BaseResponse>(response, status);
	}
	
	@ResponseBody
	@RequestMapping(value = URLConstants.URL_ROLE_PERM, method=RequestMethod.GET)
	@ApiOperation(value="Obtain permissions of a role",
	response=PermissionsOutputVO.class,
	responseContainer = "List",
	httpMethod="GET",
	produces="application/json")
	@ApiResponses(value= {
		@ApiResponse(code=201, message=MessagesConstants.MESSAGE_OK_GET_ALL_ROLE),
		@ApiResponse(code=400, message=MessagesConstants.MESSAGE_KO_GET_ALL_ROLE)
	})
	public ResponseEntity<List<PermissionsInputVO>> getPermissionsRole(
			@ApiParam(value="Role to be inserted", required=true) @PathVariable("roleId") String roleId) {
		List<PermissionsInputVO> perms = service.getPermissionsRole(roleId);
		return new ResponseEntity<List<PermissionsInputVO>>(perms, HttpStatus.OK);
	}
	
	/**
	 * PERMISSIONS
	 */
	
	@ResponseBody
	@RequestMapping(value = URLConstants.URL_PERM_ID, method=RequestMethod.DELETE)
	@ApiOperation(value="Delete an existing permission",
	response=BaseResponse.class,
	httpMethod="DELETE",
	produces="application/json")
	@ApiResponses(value= {
		@ApiResponse(code=201, message=MessagesConstants.MESSAGE_OK_DELETE_PERM),
		@ApiResponse(code=400, message=MessagesConstants.MESSAGE_KO_DELETE_PERM)
	})
	public ResponseEntity<BaseResponse> deletePerm(
			@ApiParam(value="Id to look for the permission", required=true) @PathVariable("id") String id) {
		BaseResponse response = new BaseResponse("The request wasn't as expected.");
		HttpStatus status = HttpStatus.BAD_REQUEST;
		Boolean deleted = service.deletePerm(id);
		if(deleted) {
			response.setDetail("The permission was deleted correctly");
			status = HttpStatus.OK;
		}
		return new ResponseEntity<BaseResponse>(response, status);
	}
	
	@ResponseBody
	@RequestMapping(value = URLConstants.URL_PERM_ID, method=RequestMethod.PUT)
	@ApiOperation(value="Update an existing permission",
	response=BaseResponse.class,
	httpMethod="PUT",
	produces="application/json")
	@ApiResponses(value= {
		@ApiResponse(code=201, message=MessagesConstants.MESSAGE_OK_PUT_PERM),
		@ApiResponse(code=400, message=MessagesConstants.MESSAGE_KO_PUT_PERM)
	})
	public ResponseEntity<BaseResponse> updatePerm(
			@ApiParam(value="Id to look for the permission", required=true) @PathVariable("id") String id,
			@ApiParam(value="Permission information to update", required=true) @RequestBody PermissionsInputVO perm) {
		BaseResponse response = new BaseResponse("The request wasn't as expected.");
		HttpStatus status = HttpStatus.BAD_REQUEST;
		Boolean updated = service.updatePerm(id, perm);
		if(updated) {
			response.setDetail("The permission was updated correctly");
			status = HttpStatus.OK;
		}
		return new ResponseEntity<BaseResponse>(response, status);
	}
	
	@ResponseBody
	@RequestMapping(value = URLConstants.URL_PERM_BASE, method=RequestMethod.GET)
	@ApiOperation(value="Obtain all the permissions",
	response=PermissionsOutputVO.class,
	responseContainer = "List",
	httpMethod="GET",
	produces="application/json")
	@ApiResponses(value= {
		@ApiResponse(code=201, message=MessagesConstants.MESSAGE_OK_GET_ALL_PERM),
		@ApiResponse(code=400, message=MessagesConstants.MESSAGE_KO_GET_ALL_PERM)
	})
	public ResponseEntity<List<PermissionsInputVO>> getPermissions() {
		List<PermissionsInputVO> perms = service.getPermissions();
		return new ResponseEntity<List<PermissionsInputVO>>(perms, HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value = URLConstants.URL_PERM_BASE, method=RequestMethod.POST)
	@ApiOperation(value="Create a new permission",
				response=BaseResponse.class,
				httpMethod="POST",
				produces="application/json")
	@ApiResponses(value= {
			@ApiResponse(code=201, message=MessagesConstants.MESSAGE_OK_POST_PERM),
			@ApiResponse(code=400, message=MessagesConstants.MESSAGE_KO_POST_PERM)
	})
	public ResponseEntity<BaseResponse> createPerm(
			@ApiParam(value="Permission to be inserted", required=true) @RequestBody PermissionsInputVO perm) {
		BaseResponse response = new BaseResponse("The request wasn't as expected.");
		HttpStatus status = HttpStatus.BAD_REQUEST;
		service.setPerm(perm);
		response.setDetail("The permission was created succesfully.");
		status = HttpStatus.OK;
		
		return new ResponseEntity<BaseResponse>(response, status);
	}

	
	@ResponseBody
	@RequestMapping(value = URLConstants.URL_USER_PERM, method=RequestMethod.POST)
	@ApiOperation(value="Obtain user permission",
				response=BaseResponse.class,
				httpMethod="POST",
				produces="application/json")
	@ApiResponses(value= {
			@ApiResponse(code=201, message=MessagesConstants.MESSAGE_OK_POST_PERM),
			@ApiResponse(code=400, message=MessagesConstants.MESSAGE_KO_POST_PERM)
	})
	public ResponseEntity<BaseResponse> getUserPerm(
			@ApiParam(value="Permission to be inserted", required=true) @RequestBody PermissionsInputVO perm) {
		BaseResponse response = new BaseResponse("The request wasn't as expected.");
		HttpStatus status = HttpStatus.BAD_REQUEST;
		service.setPerm(perm);
		response.setDetail("The permission was created succesfully.");
		status = HttpStatus.OK;
		
		return new ResponseEntity<BaseResponse>(response, status);
	}	
	
	@ResponseBody
	@RequestMapping(value = URLConstants.URL_WEBUSER_SELECT, method=RequestMethod.GET)
	@ApiOperation(value="Obtain all the web users for select",
	response=WebUserSelectOutputVO.class,
	responseContainer = "List",
	httpMethod="GET",
	produces="application/json")
	@ApiResponses(value= {
		@ApiResponse(code=201, message=MessagesConstants.MESSAGE_OK_GET_ALL_WEBUSER),
		@ApiResponse(code=400, message=MessagesConstants.MESSAGE_KO_GET_ALL_WEBUSER)
	})
	public ResponseEntity<List<WebUserSelectOutputVO>> getWebUsersSelect() {
		List<WebUserSelectOutputVO> users = service.getWebUsersSelect();
		return new ResponseEntity<List<WebUserSelectOutputVO>>(users, HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value = URLConstants.URL_BOT_CHAT_ID, method=RequestMethod.GET)
	@ApiOperation(value="Obtain all the web users for select",
	response=UserChatIdVO.class,
	httpMethod="GET",
	produces="application/json")
	@ApiResponses(value= {
		@ApiResponse(code=201, message=MessagesConstants.MESSAGE_OK_GET_ALL_WEBUSER),
		@ApiResponse(code=400, message=MessagesConstants.MESSAGE_KO_GET_ALL_WEBUSER)
	})
	public ResponseEntity<UserChatIdVO> getWebUsersSelect(
			@ApiParam(value="Id to look for the permission", required=true) @PathVariable("userId") String userId) {
		UserChatIdVO chat = service.getChatId(userId);
		return new ResponseEntity<UserChatIdVO>(chat, HttpStatus.OK);
	}
	
}
