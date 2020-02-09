package com.haapp.formicary.infrastructure.exception;

import com.haapp.formicary.api.message.BaseResponse;
import com.haapp.formicary.api.model.Error;
import com.haapp.formicary.domain.service.MessageSourceFacade;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.haapp.formicary.infrastructure.exception.ErrorMessage.INTERNAL_SERVICE_ERROR;
import static com.haapp.formicary.infrastructure.exception.ErrorMessage.VALIDATION_ERROR;

@AllArgsConstructor
@RestControllerAdvice
public class GlobalExceptionHandler {

    private final MessageSourceFacade messageFacade;

    private final BaseResponse response;

    @ExceptionHandler({ServiceException.class})
    public final ResponseEntity<BaseResponse> handleServiceException(ServiceException e) {
        List<String> messages = new ArrayList<>();
        messages.add(messageFacade.getMessage(e.getCode()));
        Error error = new Error();
        error.setCode(e.getCode());
        error.setMessages(messages);
        response.setError(error);
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler({NotFoundException.class})
    public final ResponseEntity<BaseResponse> handleNotFoundException(NotFoundException e) {
        List<String> messages = new ArrayList<>();
        messages.add(messageFacade.getMessage(e.getCode()));
        Error error = new Error();
        error.setCode(e.getCode());
        error.setMessages(messages);
        response.setError(error);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public final ResponseEntity<BaseResponse> handleMethodArgumentException(MethodArgumentTypeMismatchException e){
        List<String> messages = new ArrayList<>();
        messages.add(e.getMessage());
        Error error = new Error();
        error.setCode("method.argument.error");
        error.setMessages(messages);
        response.setError(error);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    public final ResponseEntity<BaseResponse> handleJsonParseException(HttpMessageNotReadableException e){
        List<String> messages = new ArrayList<>();
        messages.add(e.getMessage());
        Error error = new Error();
        error.setCode("json.parse.error");
        error.setMessages(messages);
        response.setError(error);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public final ResponseEntity<BaseResponse> handleHttpMethodNotSupportException(HttpRequestMethodNotSupportedException e){
        List<String> messages = new ArrayList<>();
        messages.add(e.getMessage());
        Error error = new Error();
        error.setCode("http.method.not.support");
        error.setMessages(messages);
        response.setError(error);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({Throwable.class})
    public final ResponseEntity<BaseResponse> handleAllException(Throwable e) {
        List<String> messages = new ArrayList<>();
        messages.add(messageFacade.getMessage(INTERNAL_SERVICE_ERROR));
        messages.add(e.getMessage());//todo
        Error error = new Error();
        error.setCode(INTERNAL_SERVICE_ERROR);
        error.setMessages(messages);
        response.setError(error);
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public final ResponseEntity<BaseResponse> handleValidationException(MethodArgumentNotValidException e) {
        List<String> messages = new ArrayList<>();
        messages = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getField() + " -> " + fieldError.getDefaultMessage())
                .collect(Collectors.toList());
        Error error = new Error();
        error.setCode(VALIDATION_ERROR);
        error.setMessages(messages);
        response.setError(error);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
