package ordermicroservice.response.server_down;

import lombok.Data;
import org.springframework.http.HttpStatus;
@Data
public class OrderServiceDownResponse {

    private String message;
    private Boolean status=Boolean.FALSE;
    private Integer httpStatus= HttpStatus.OK.value();
}
