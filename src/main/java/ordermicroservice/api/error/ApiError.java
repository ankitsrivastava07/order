package ordermicroservice.api.error;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ApiError {

	private Date timestamp;
	private Integer status;
	private String error;
	private String message;
	private String path;
	private String supportedMethod[];

	public ApiError(Date timestamp, Integer status, String error, String message, String path,
                    String supportedMethod[]) {
		this.timestamp = timestamp;
		this.status = status;
		this.error = error;
		this.message = message;
		this.path = path;
		this.supportedMethod = supportedMethod;
	}
}