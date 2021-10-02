package com.jozwik.empik.model;

import java.util.Objects;

public class ErrorWsm {
    private final String developerMessage;
    private final String userMessage;
    private final String errorId;
    private final String documentationUrl;

    public ErrorWsm(String developerMessage, String userMessage, String errorId, String documentationUrl) {
        this.developerMessage = developerMessage;
        this.userMessage = userMessage;
        this.errorId = errorId;
        this.documentationUrl = documentationUrl;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public String getErrorId() {
        return errorId;
    }

    public String getDocumentationUrl() {
        return documentationUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ErrorWsm)) return false;
        ErrorWsm errorWsm = (ErrorWsm) o;
        return Objects.equals(developerMessage, errorWsm.developerMessage) &&
                Objects.equals(userMessage, errorWsm.userMessage) &&
                Objects.equals(errorId, errorWsm.errorId) &&
                Objects.equals(documentationUrl, errorWsm.documentationUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(developerMessage, userMessage, errorId, documentationUrl);
    }

    @Override
    public String toString() {
        return "ErrorWsm{" +
                "developerMessage='" + developerMessage + '\'' +
                ", userMessage='" + userMessage + '\'' +
                ", errorId='" + errorId + '\'' +
                ", documentationUrl='" + documentationUrl + '\'' +
                '}';
    }
}
