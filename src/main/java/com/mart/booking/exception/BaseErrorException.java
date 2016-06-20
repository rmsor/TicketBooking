package com.mart.booking.exception;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BaseErrorException extends Exception {

    private static final long serialVersionUID = 5330649850834445137L;
    
    @JsonProperty
    protected final List<Error> errors = new ArrayList<Error>();

    /**
     * @return the errors
     */
    public List<Error> getErrors() {
        return errors;
    }

    /**
     *  Constructor.
     */
    public BaseErrorException() {
        super();
    }

    /**
     * @param message
     */
    public BaseErrorException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public BaseErrorException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public BaseErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param errorList
     * @return true when errorList contained errors to be added.
     */
    public boolean addErrors(List<Error> errorList) {
        if (null != errorList && !errorList.isEmpty()) {
            errors.addAll(errorList);
            return true;
        }
        return false;
    }

}