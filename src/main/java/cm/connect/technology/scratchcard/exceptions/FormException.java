package cm.connect.technology.scratchcard.exceptions;

import org.springframework.http.HttpStatus;

public class FormException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	private HttpStatus status;
	public FormException(String messasge, HttpStatus status) {
		super(messasge);
		this.status = status;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}
}
