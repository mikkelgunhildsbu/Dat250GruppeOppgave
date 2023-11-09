package no.feedapp.group2.FeedApp.controllers.exceptions;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class CustomErrorHandlingController {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    CustomErrorResponse onConstraintValidationException(ConstraintViolationException e) {
        CustomErrorResponse error = new CustomErrorResponse();
        e.getConstraintViolations().forEach(violation -> error.getErrors().add(
                new CustomError(violation.getPropertyPath().toString(), violation.getMessage())));
        return error;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    CustomErrorResponse onMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        CustomErrorResponse error = new CustomErrorResponse();
        e.getBindingResult().getFieldErrors().forEach(fieldError -> error.getErrors().add(
                new CustomError(fieldError.getField(), fieldError.getDefaultMessage())));
        return error;
    }

//    @ExceptionHandler(IllegalArgumentException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ResponseBody
//    CustomErrorResponse onIllegalArgumentException(IllegalArgumentException e) {
//        CustomErrorResponse error = new CustomErrorResponse();
//        error.getErrors().add(
//                new CustomError("Illegal argument", e.getMessage()));
//        return error;
//    }

    @ExceptionHandler(CustomerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    CustomErrorResponse onCustomerNotFoundException(CustomerNotFoundException e) {
        CustomErrorResponse error = new CustomErrorResponse();
        error.getErrors().add(
                new CustomError("Customer not found", e.getMessage()));
        return error;
    }

    @ExceptionHandler(PollNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    CustomErrorResponse onPollNotFoundException(PollNotFoundException e) {
        CustomErrorResponse error = new CustomErrorResponse();
        error.getErrors().add(
                new CustomError("Poll not found", e.getMessage()));
        return error;
    }

    @ExceptionHandler(PollClosedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ResponseBody
    CustomErrorResponse onPollClosedException(PollClosedException e) {
        CustomErrorResponse error = new CustomErrorResponse();
        error.getErrors().add(
                new CustomError("Poll not found", e.getMessage()));
        return error;
    }
}
