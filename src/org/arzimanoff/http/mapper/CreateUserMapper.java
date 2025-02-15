package org.arzimanoff.http.mapper;

import org.arzimanoff.http.dto.CreateUserDto;
import org.arzimanoff.http.entity.Gender;
import org.arzimanoff.http.entity.Role;
import org.arzimanoff.http.entity.User;
import org.arzimanoff.http.service.UserService;
import org.arzimanoff.http.util.LocalDateFormatter;

public class CreateUserMapper implements Mapper<CreateUserDto, User>{

    private static final String IMAGE_FOLDER = "users/";
    private static final CreateUserMapper INSTANCE = new CreateUserMapper();
    private CreateUserMapper() {
    }

    @Override
    public User mapFrom(CreateUserDto object) {
        return User.builder()
                .name(object.getName())
                .birthday(LocalDateFormatter.format(object.getBirthday()))
                .email(object.getEmail())
                .password(object.getPassword())
                .gender(Gender.valueOf(object.getGender()))
                .role(Role.valueOf(object.getRole()))
                .build();
    }

    public static CreateUserMapper getInstance() {
        return INSTANCE;
    }
}
