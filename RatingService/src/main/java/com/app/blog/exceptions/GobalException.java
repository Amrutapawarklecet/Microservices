package com.app.blog.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.app.blog.playloads.ApiResponse;


@RestControllerAdvice
public class GobalException {

	@ExceptionHandler(ResourceNotFoundExpection.class)
	public ResponseEntity<ApiResponse> resourceNotFoundHandler(ResourceNotFoundExpection ex) {

		String message = ex.getMessage();
		ApiResponse responseApi = new ApiResponse(message, false);
		return new ResponseEntity<>(responseApi, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> MethodArgumentNotValidExceptionHandler(
			MethodArgumentNotValidException ex) {
		Map<String, String> resp = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String message = error.getDefaultMessage();
			String fieldName = ((FieldError) error).getField();
			resp.put(fieldName, message);
		});

		return new ResponseEntity<Map<String, String>>(resp, HttpStatus.BAD_REQUEST);
	}

}
