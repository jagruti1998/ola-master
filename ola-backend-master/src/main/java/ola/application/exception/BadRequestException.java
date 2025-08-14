package ola.application.exception;

public class BadRequestException extends RuntimeException{
    private final int statusCode;

    public BadRequestException(int statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatus(){
        return statusCode;
    }


}
