package org.arzimanoff.http.dto;

import lombok.Builder;
import lombok.Value;
import org.arzimanoff.http.entity.Gender;
import org.arzimanoff.http.entity.Role;

import java.time.LocalDate;

@Value
@Builder
public class UserDto {
    Integer id;
    String name;
    LocalDate birthday;
    String email;
    Role role;
    Gender gender;
}

