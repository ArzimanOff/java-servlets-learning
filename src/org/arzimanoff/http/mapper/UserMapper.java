package org.arzimanoff.http.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.arzimanoff.http.dao.UserDao;
import org.arzimanoff.http.dto.UserDto;
import org.arzimanoff.http.entity.User;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMapper implements Mapper<User, UserDto> {

    private static final UserMapper INSTANCE = new UserMapper();

    @Override
    public UserDto mapFrom(User object) {
        return UserDto.builder()
                .id(object.getId())
                .name(object.getName())
                .gender(object.getGender())
                .role(object.getRole())
                .email(object.getEmail())
                .birthday(object.getBirthday())
                .build();
    }

    public static UserMapper getInstance() {
        return INSTANCE;
    }
}
