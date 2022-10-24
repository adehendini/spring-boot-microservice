/*
 * Author : Ade Hendini
 * Email : adehendini@gmail.com
 * Date : 10/24/22, 5:18 AM
 */

package ade.hendini.utils;

import ade.hendini.helpers.WebResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class CustomRestExeptionHandler {
    /*
     * Handling error data tidak ditemukan
     * */
    @ExceptionHandler
    public ResponseEntity<WebResponse> handleExeption(CustomNotFoundException exc){
        WebResponse error = new WebResponse(false, exc.getMessage(), null);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    /*
     * Handling error forbidden
     * */
    @ExceptionHandler
    public ResponseEntity<WebResponse> handleExeption(CustomForbiddenException exc){
        WebResponse error = new WebResponse(false, exc.getMessage(), null);
        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }

    /*
     * Error Handle umum
     * */
    @ExceptionHandler
    public ResponseEntity<WebResponse> handleExeption(Exception exc){
        List<String> details = new ArrayList<>();
        details.add(HttpStatus.BAD_REQUEST.toString());
        WebResponse error = new WebResponse(false,  exc.getMessage(), details);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    /*
     * Handling error validasi
     * */
    @ExceptionHandler
    public ResponseEntity<WebResponse> handleExeption(MethodArgumentNotValidException exc){
        List<String> details = new ArrayList<>();
        for(ObjectError error : exc.getBindingResult().getAllErrors()) {
            details.add(error.getDefaultMessage());
        }
        WebResponse error = new WebResponse(false,"Validation Failed", details);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
