package com.vsrdev.terminux_review_api.exception;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.vsrdev.terminux_review_api.api.response.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<ApiResponse<Map<String, String>>> handleValidation(
                        MethodArgumentNotValidException ex) {

                Map<String, String> errors = new HashMap<>();

                ex.getBindingResult().getFieldErrors()
                                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

                ApiResponse<Map<String, String>> response = new ApiResponse<>(false, errors, "Erro de validação");

                return ResponseEntity.badRequest().body(response);
        }

        @ExceptionHandler(Exception.class)
        public ResponseEntity<ApiResponse<String>> handleGeneric(Exception ex) {

                ApiResponse<String> response = new ApiResponse<>(false, null, "Erro interno do servidor");

                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body(response);
        }

        @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<ApiResponse<String>> handleNotFound(
                        ResourceNotFoundException ex) {

                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                                new ApiResponse<>(false, null, ex.getMessage()));
        }

        @ExceptionHandler(ConflictException.class)
        public ResponseEntity<ApiResponse<String>> handleConflict(
                        ConflictException ex) {

                return ResponseEntity.status(HttpStatus.CONFLICT).body(
                                new ApiResponse<>(false, null, ex.getMessage()));
        }

        @ExceptionHandler(BusinessException.class)
        public ResponseEntity<ApiResponse<String>> handleBusiness(
                        BusinessException ex) {

                return ResponseEntity.badRequest().body(
                                new ApiResponse<>(false, null, ex.getMessage()));
        }

        @ExceptionHandler(IllegalArgumentException.class)
        public ResponseEntity<ApiResponse<Void>> handleIllegalArgument(IllegalArgumentException ex) {

                ApiResponse<Void> response = new ApiResponse<>(
                                false,
                                null,
                                ex.getMessage());

                return ResponseEntity.badRequest().body(response);
        }

        @ExceptionHandler(org.springframework.http.converter.HttpMessageNotReadableException.class)
        public ResponseEntity<ApiResponse<String>> handleJsonError(Exception ex) {

                return ResponseEntity.badRequest().body(
                                new ApiResponse<>(false, null, "JSON inválido ou campo não permitido"));
        }

        @ExceptionHandler(org.springframework.dao.DataIntegrityViolationException.class)
        public ResponseEntity<ApiResponse<String>> handleDatabaseError(Exception ex) {

                return ResponseEntity.badRequest().body(
                                new ApiResponse<>(false, null, "Erro de integridade de dados"));
        }
}
