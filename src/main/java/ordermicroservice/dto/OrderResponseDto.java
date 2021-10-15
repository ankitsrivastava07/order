package ordermicroservice.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class OrderResponseDto {

    private String orderStatus;
    private String orderId;
    private Boolean status;
    private Boolean isNewTokenCreated=Boolean.FALSE;
    private String accessToken="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";
    private String message;
    private Integer httpStatus= HttpStatus.OK.value();
}
