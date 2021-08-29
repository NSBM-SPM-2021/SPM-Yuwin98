package com.spmapp.api.models;

import java.util.Optional;

public class Response<T> {

    private String message;
    private Integer statusCode;
    private Optional<T> data;

    public Response() {

    }

    public Optional<T> getData() {
        return data;
    }

    public void setData(Optional<T> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }
}
