package org.arzimanoff.http.service;

import lombok.SneakyThrows;
import org.arzimanoff.http.dao.FlightDao;
import org.arzimanoff.http.dao.UserDao;
import org.arzimanoff.http.dto.CreateUserDto;
import org.arzimanoff.http.dto.UserDto;
import org.arzimanoff.http.entity.User;
import org.arzimanoff.http.exception.ValidationException;
import org.arzimanoff.http.mapper.CreateUserMapper;
import org.arzimanoff.http.mapper.UserMapper;
import org.arzimanoff.http.validator.CreateUserValidator;
import org.arzimanoff.http.validator.ValidationResult;

import java.util.Optional;

public class UserService {

    private static final UserService INSTANCE = new UserService();
    private final CreateUserValidator createUserValidator = CreateUserValidator.getInstance();
    private final UserDao userDao = UserDao.getInstance();
    private final CreateUserMapper createUserMapper = CreateUserMapper.getInstance();
    private final ImageService imageService = ImageService.getInstance();
    private final UserMapper userMapper =  UserMapper.getInstance();

    private UserService() {
    }

    public Optional<UserDto> login(String email, String password){
        return userDao.findByEmailAndPassword(email, password)
                .map(userMapper::mapFrom);

    }

    public Integer create(CreateUserDto userDto) {
        var validationResult = createUserValidator.isValid(userDto);
        if (!validationResult.isValid()) {
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
