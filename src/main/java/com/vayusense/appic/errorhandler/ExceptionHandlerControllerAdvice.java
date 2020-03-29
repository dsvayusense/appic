package com.vayusense.appic.errorhandler;

import com.mongodb.DuplicateKeyException;
import com.mongodb.MongoException;
import com.mongodb.MongoWriteException;
import com.vayusense.appic.dto.ResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.MethodNotAllowedException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebInputException;
import javax.validation.ConstraintViolationException;
import javax.validation.UnexpectedTypeException;
import javax.validation.ValidationException;
import java.time.LocalDateTime;


@ControllerAdvice(annotations = RestController.class )
public class ExceptionHandlerControllerAdvice  {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ResponseError> ResponseStatusException(final ResponseStatusException exception, final ServerHttpRequest request) {
        ResponseError error = new ResponseError();
        error.setTimestamp(LocalDateTime.now());
        error.setMessage(exception.getMessage());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setUri(request.getURI().toString());
        return ResponseEntity.status(error.getStatus()).body(error);
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResponseError> handleResourceNotFound(final ResourceNotFoundException exception, final ServerHttpRequest request) {
        ResponseError error = new ResponseError();
        error.setTimestamp(LocalDateTime.now());
        error.setMessage(exception.getMessage());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setUri(request.getURI().toString());
        return ResponseEntity.status(error.getStatus()).body(error);
    }

    @ExceptionHandler(ResourceBadReqException.class)
    public ResponseEntity<ResponseError> BadReqException(final ResourceBadReqException exception, final ServerHttpRequest request) {
        ResponseError error = new ResponseError();
        error.setTimestamp(LocalDateTime.now());
        error.setMessage(exception.getMessage());
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setUri(request.getURI().toString());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ResponseError> handleServerException(final RuntimeException exception , final ServerHttpRequest request){
        ResponseError error = new ResponseError();
        error.setTimestamp(LocalDateTime.now());
        error.setMessage(exception.getMessage());
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setUri(request.getURI().toString());

        return ResponseEntity.status(error.getStatus()).body(error);

    }
    @ExceptionHandler(ServerWebInputException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseError> handleBadReqWebException(final ServerWebInputException exception , final ServerHttpRequest request){
        ResponseError error = new ResponseError();
        error.setTimestamp(LocalDateTime.now());
        error.setMessage(exception.getMessage());
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setUri(request.getURI().toString());
        return ResponseEntity.status(error.getStatus()).body(error);

    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseError> constraintViolationException(final ConstraintViolationException exception ,final ServerHttpRequest request) {
        ResponseError error = new ResponseError();
        error.setTimestamp(LocalDateTime.now());
        error.setMessage(exception.getMessage());
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setUri(request.getURI().toString());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(MethodNotAllowedException.class)
    @ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
    public ResponseEntity<ResponseError> MethodNotAllowedException(final MethodNotAllowedException exception ,ServerHttpRequest request){
        ResponseError error = new ResponseError();
        error.setTimestamp(LocalDateTime.now());
        error.setMessage(exception.getMessage());
        error.setStatus(HttpStatus.METHOD_NOT_ALLOWED.value());
        error.setUri(request.getURI().toString());
        return ResponseEntity.status(error.getStatus()).body(error);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ResponseError> illegalStateException(final ServerHttpRequest request,IllegalStateException exception) {
        ResponseError error = new ResponseError();
        error.setTimestamp(LocalDateTime.now());
        error.setMessage(exception.getMessage());
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setUri(request.getURI().toString());
        return ResponseEntity.status(error.getStatus()).body(error);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UnexpectedTypeException.class)
    public ResponseEntity<ResponseError> unexpectedTypeException(final ServerHttpRequest request, UnexpectedTypeException exception) {
        ResponseError error = new ResponseError();
        error.setTimestamp(LocalDateTime.now());
        error.setMessage(exception.getMessage());
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setUri(request.getURI().toString());
        return ResponseEntity.status(error.getStatus()).body(error);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {HttpMessageNotReadableException.class, MethodArgumentNotValidException.class, ValidationException.class})
    public ResponseEntity<ResponseError> MethodNotAllowedAndValidationException(final ServerHttpRequest request, RuntimeException exception) {
        ResponseError error = new ResponseError();
        error.setTimestamp(LocalDateTime.now());
        error.setMessage(exception.getMessage());
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setUri(request.getURI().toString());
        return ResponseEntity.status(error.getStatus()).body(error);
    }

    @ExceptionHandler(value = BusinessException.class)
    public ResponseEntity<ResponseError> businessException(final ServerHttpRequest request, BusinessException exception) {
        ResponseError error = new ResponseError();
        error.setTimestamp(LocalDateTime.now());
        error.setMessage(exception.getMessage());
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setUri(request.getURI().toString());
        return ResponseEntity.status(error.getStatus()).body(error);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = MongoException.class)
    public ResponseEntity<ResponseError> mongoException(final ServerHttpRequest request, MongoException exception) {
        ResponseError error = new ResponseError();
        error.setTimestamp(LocalDateTime.now());
        error.setMessage(exception.getMessage());
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setUri(request.getURI().toString());
        return ResponseEntity.status(error.getStatus()).body(error);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = DuplicateKeyException.class)
    public ResponseEntity<ResponseError> mongoKeyException(final ServerHttpRequest request, MongoException exception) {
        ResponseError error = new ResponseError();
        error.setTimestamp(LocalDateTime.now());
        error.setMessage(exception.getMessage());
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setUri(request.getURI().toString());
        return ResponseEntity.status(error.getStatus()).body(error);
    }


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = MongoWriteException.class)
    public ResponseEntity<ResponseError> mongoWriteException(final ServerHttpRequest request, MongoException exception) {
        ResponseError error = new ResponseError();
        error.setTimestamp(LocalDateTime.now());
        error.setMessage(exception.getMessage());
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setUri(request.getURI().toString());
        return ResponseEntity.status(error.getStatus()).body(error);
    }




}
