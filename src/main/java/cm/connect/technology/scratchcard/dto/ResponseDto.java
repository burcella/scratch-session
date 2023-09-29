package cm.connect.technology.scratchcard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ResponseDto<Body> implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Body body;
	private HttpStatus status;
	List<String> messages;

}
