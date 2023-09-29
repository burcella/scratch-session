package cm.connect.technology.scratchcard.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppException {

	@ExceptionHandler(value = DAOException.class)
	public ResponseEntity<Object> exception(DAOException exception) {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(value = FormException.class)
	public ResponseEntity<Object> formException(FormException exception) {
		return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
	}
}
