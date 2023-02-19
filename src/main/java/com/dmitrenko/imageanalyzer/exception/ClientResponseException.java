package com.dmitrenko.imageanalyzer.exception;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;

@Getter
@ToString
public class ClientResponseException extends RuntimeException {
    private final HttpStatusCode httpStatus;
    private final HttpHeaders httpHeaders;
    private final String bodyErrorResponse;

    public ClientResponseException(HttpStatusCode httpStatus, HttpHeaders httpHeaders, String bodyErrorResponse) {
        this.httpStatus = httpStatus;
        this.httpHeaders = httpHeaders;
        this.bodyErrorResponse = bodyErrorResponse;
    }
}