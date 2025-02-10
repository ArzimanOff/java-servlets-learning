package org.arzimanoff.http.service;

import org.arzimanoff.http.dao.FlightDao;
import org.arzimanoff.http.dao.UserDao;
import org.arzimanoff.http.dto.CreateUserDto;
import org.arzimanoff.http.entity.User;
import org.arzimanoff.http.exception.ValidationException;
import org.arzimanoff.http.mapper.CreateUserMapper;
import org.arzimanoff.http.validator.CreateUserValidator;
import org.arzimanoff.http.validator.ValidationResult;

public class UserService {
    private static final UserService INSTANCE = new UserService();
    private final CreateUserValidator createUserValidator = CreateUserValidator.getInstance();
    private final UserDao userDao = UserDao.getInstance();
    private final CreateUserMapper createUserMapper = CreateUserMapper.getInstance();

    private UserService() {
    }

    public Integer create(CreateUserDto userDto){
        var validationResult = createUserValidator.isValid(userDto);
        if (!validationResult.isValid()){
            throw new ValidationException(validationResult.getErrors());
        }
        var userEntity = createUserMapper.mapFrom(userDto);
        userDao.save(userEntity);
        return userEntity.getId();
    }


    public static UserService getInstance() {
        return INSTANCE;
    }
}
