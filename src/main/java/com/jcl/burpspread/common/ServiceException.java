package com.jcl.burpspread.common;

public class ServiceException extends RuntimeException {
    static final long serialVersionUID = -7034897190745766939L;
    private String message;

    @Override
    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public ServiceException(final String message, Throwable throwable){
        super(message,throwable);
        this.message = message;

    }

    public ServiceException(final String message){
        this.message = message;
    }

    public static void throwExp(String message){
        throw new ServiceException(message);
    }
}
