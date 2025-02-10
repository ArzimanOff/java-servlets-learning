package org.arzimanoff.http.validator;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ValidationResult {
    private final List<Error> errors = new ArrayList<>();
    public void add(Error error){
        this.errors.add(error);
    }

    public boolean isValid(){
        return this.errors.isEmpty();
    }

}
