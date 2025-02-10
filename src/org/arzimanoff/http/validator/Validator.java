package org.arzimanoff.http.validator;

public interface Validator<T> {
    ValidationResult isValid(T object);
}
