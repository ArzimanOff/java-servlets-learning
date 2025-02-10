package org.arzimanoff.http.exception;

import lombok.Getter;
import org.arzimanoff.http.validator.Error;

import java.util.List;

@Getter
public class ValidationException extends RuntimeException {

    private final List<Error> errors;

    public ValidationException(List<Error> errors){
        this.errors = errors;
    }
}
