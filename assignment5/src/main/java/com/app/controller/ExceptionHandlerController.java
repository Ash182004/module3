package com.app.controller;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.app.dto.ErrorInfo;
import com.app.exceptions.BookNotFoundException;

@RestControllerAdvice
public class ExceptionHandlerController {
	
	@ExceptionHandler(BookNotFoundException.class)
	public ResponseEntity<ErrorInfo> handle404(BookNotFoundException e) {
		ErrorInfo errorInfo=new ErrorInfo(e.getMessage(), HttpStatus.NOT_FOUND.toString(),
				"it@ymsli.com", LocalDateTime.now());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorInfo);
	}
}
