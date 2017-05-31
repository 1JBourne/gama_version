package com.nojac.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by nickolas on 5/30/17.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadDateFormatException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public BadDateFormatException(String dateString) {
        super(dateString);
    }
}
