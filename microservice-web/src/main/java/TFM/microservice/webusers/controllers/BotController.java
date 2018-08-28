package TFM.microservice.webusers.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import TFM.microservice.webusers.POJO.BaseResponse;
import TFM.microservice.webusers.VO.MessageBotVO;
import TFM.microservice.webusers.VO.WebLoginVO;
import TFM.microservice.webusers.constants.MessagesConstants;
import TFM.microservice.webusers.constants.URLConstants;
import TFM.microservice.webusers.repositories.RolesRepository;
import TFM.microservice.webusers.repositories.WebUserRepository;
import TFM.microservice.webusers.security.JwtTokenProvider;
import TFM.microservice.webusers.services.BotService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;

@CrossOrigin(maxAge=3600)
@RestController
@RequestMapping
public class BotController {

	@Autowired
	BotService service;
	
    @ResponseBody
	@RequestMapping(value = URLConstants.URL_BOT, method=RequestMethod.POST)
	@ApiOperation(value="Set chat id",
				response=BaseResponse.class,
				httpMethod="POST",
				produces="application/json")
	@ApiResponses(value= {
			@ApiResponse(code=201, message=MessagesConstants.MESSAGE_OK_POST_WEBUSER),
			@ApiResponse(code=400, message=MessagesConstants.MESSAGE_KO_POST_WEBUSER)
	})
    public ResponseEntity<BaseResponse> setChatId(@RequestBody MessageBotVO message) throws BadCredentialsException{
    	Boolean created = service.setChatId(message);
    	if (created) return new ResponseEntity<BaseResponse>(new BaseResponse("Success"), HttpStatus.OK);
    	else return new ResponseEntity<BaseResponse>(new BaseResponse("Failure"), HttpStatus.BAD_REQUEST);
    }

}
