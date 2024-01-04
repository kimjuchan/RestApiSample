package co.kr.api.restapisample.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> hadleAllExceptions(Exception ex , WebRequest request){
        ExceptionReponse exceptionReponse =
                new ExceptionReponse(new Date(),ex.getMessage(),request.getDescription(false));
        return new ResponseEntity<>(exceptionReponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object> hadleUserNotFoundExceptions(Exception ex , WebRequest request){
        ExceptionReponse exceptionReponse =
                new ExceptionReponse(new Date(),ex.getMessage(),request.getDescription(false));
        return new ResponseEntity<>(exceptionReponse, HttpStatus.NOT_FOUND);
    }

    //ResponseEntity  커스텀해서 사용하는 방법도 있음.

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ExceptionReponse exceptionReponse =
                //날짜, MSG, DETAIL 정보
                new ExceptionReponse(new Date(),"valid fail","sss");
        return new ResponseEntity<>(exceptionReponse, HttpStatus.BAD_REQUEST);

    }
}
