package TFM.microservice.routines.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import TFM.microservice.routines.constants.MessagesConstants;

public class ExceptionHandler extends ResponseEntityExceptionHandler{
	
	@org.springframework.web.bind.annotation.ExceptionHandler({AccessDeniedException.class})
	public ResponseEntity<Object> handleAccessDenied(Exception ex, WebRequest request){
		return new ResponseEntity<Object>(
				MessagesConstants.MESSAGE_ACCESS_DENIED_HANDLER,
				new HttpHeaders(),
				HttpStatus.FORBIDDEN);
	}
}
