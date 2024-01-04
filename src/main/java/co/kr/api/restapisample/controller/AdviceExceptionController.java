package co.kr.api.restapisample.controller;


import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdviceExceptionController {
   /* @ExceptionHandler({NullPointerException.class, ClassCastException.class})
    public String exceptionHandler(Exception ex) {
        return "Exception Handle >> NullPointer Exception 예외처리";
    }*/
}
