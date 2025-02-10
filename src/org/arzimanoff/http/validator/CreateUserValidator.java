package org.arzimanoff.http.validator;

import org.arzimanoff.http.dto.CreateUserDto;
import org.arzimanoff.http.entity.Gender;
import org.arzimanoff.http.util.LocalDateFormatter;

import java.util.Optional;


public class CreateUserValidator implements Validator<CreateUserDto> {

    private static final CreateUserValidator INSTANCE = new CreateUserValidator();

    private CreateUserValidator() {
    }

    @Override
    public ValidationResult isValid(CreateUserDto object) {
        var validationResult = new ValidationResult();
        if (!LocalDateFormatter.isValid(object.getBirthday())){
            validationResult.add(Error.of("invalid.birthday", "Неверная дата рождения"));
        }
        if (Gender.find(object.getGender()).isEmpty()){
            validationResult.add(Error.of("invalid.gender", "Неверный гендер"));
        }
        return validationResult;
    }

    public static CreateUserValidator getInstance() {
        return INSTANCE;
    }
}
