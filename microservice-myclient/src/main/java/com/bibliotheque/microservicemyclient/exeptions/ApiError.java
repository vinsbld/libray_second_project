package com.bibliotheque.microservicemyclient.exeptions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiError {

    private String message;

    private String apiError;

    public ApiError() {
    }

    public ApiError(String message, String apiError) {
        this.message = message;
        this.apiError = apiError;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getApiError() {
        return apiError;
    }

    public void setApiError(String apiError) {
        this.apiError = apiError;
    }

    @Override
    public String toString() {
        return "ApiError{" +
                "message='" + message + '\'' +
                ", apiError='" + apiError + '\'' +
                '}';
    }
}
