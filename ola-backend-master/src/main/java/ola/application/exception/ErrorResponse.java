package ola.application.exception;

public class ErrorResponse {
    private String message;
    private int status;

    public ErrorResponse(int status, String message) {
        this.message = message;
        this.status = status;
    }

    // Getters for status and message


    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "status=" + status +
                ", message='" + message + '\'' +
                '}';
    }
}
