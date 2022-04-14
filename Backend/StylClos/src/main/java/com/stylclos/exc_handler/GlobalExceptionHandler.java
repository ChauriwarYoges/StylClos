package com.stylclos.exc_handler;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.stylclos.custom_exception.PasswordNotMatchedException;
import com.stylclos.custom_exception.UserNotFoundException;
import com.stylclos.dto.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		// Goal : send detailed err messages regarding validation failures
		StringBuilder sb = new StringBuilder("Validation err msgs : ");
		ex.getBindingResult().getFieldErrors().forEach(fieldErr -> sb.append(fieldErr.getDefaultMessage() + ". "));
		System.out.println(sb); // err msgs

		ErrorResponse resp = new ErrorResponse(sb.toString(), LocalDateTime.now());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<?> handleUserNotFoundException(UserNotFoundException e) {
		ErrorResponse resp = new ErrorResponse("Something went wrong -> " + e.getMessage(), LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resp);
	}

	@ExceptionHandler(PasswordNotMatchedException.class)
	public ResponseEntity<?> handlePasswordNotMatchedException(PasswordNotMatchedException e) {
		ErrorResponse resp = new ErrorResponse("Error : " + e.getMessage(), LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(resp);
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<?> handleRuntimeException(RuntimeException e) {
		System.out.println("in handle run time exception " + e);

		ErrorResponse resp = new ErrorResponse("Something went wrong ->  " + e.getMessage(), LocalDateTime.now());

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resp);
	}

}
