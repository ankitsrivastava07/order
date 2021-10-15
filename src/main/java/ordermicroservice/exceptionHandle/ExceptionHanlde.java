package ordermicroservice.exceptionHandle;

import ordermicroservice.api.error.ApiError;
import ordermicroservice.api.error.ApiErrorMissingAuthenticationToken;
import ordermicroservice.constant.ConstantResponse;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;

@ControllerAdvice
public class ExceptionHanlde {

    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<?> missingAuthenticationToken(MissingRequestHeaderException exception, HttpServletRequest request){
        ApiErrorMissingAuthenticationToken apiError = new ApiErrorMissingAuthenticationToken(new Date(), HttpStatus.UNAUTHORIZED.value(),
                HttpStatus.UNAUTHORIZED.name(), ConstantResponse.HEADER_TOKEN_MISSING +" ("+exception.getHeaderName()+") "+" in header", request.getRequestURL().toString());
        return new ResponseEntity<>(apiError,HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> sqlException(DataIntegrityViolationException exception, HttpServletRequest request){
        ApiError apiError = new ApiError(new Date(), HttpStatus.SERVICE_UNAVAILABLE.value(),
                HttpStatus.SERVICE_UNAVAILABLE.name(), ConstantResponse.SERVER_DOWN , request.getRequestURL().toString(),Arrays.asList(request.getMethod()).toArray(new String[0]));
        return new ResponseEntity<>(apiError,HttpStatus.SERVICE_UNAVAILABLE);
    }

}
