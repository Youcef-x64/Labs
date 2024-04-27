package com.esihati.exception;

import com.esihati.model.dto.patient.response.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleNotFoundException(NotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDto(e.getId(), e.getMessage()));
    }

    @ExceptionHandler(WrongCredentialException.class)
    public ResponseEntity<ErrorResponseDto> handleWrongCredentialException(WrongCredentialException e){
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new ErrorResponseDto(e.getId(), e.getMessage()));
    }

    @ExceptionHandler(NotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleNotValidException(NotValidException e){
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new ErrorResponseDto(e.getId(), e.getMessage()));
    }

}
