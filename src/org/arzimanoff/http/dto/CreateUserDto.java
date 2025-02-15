package org.arzimanoff.http.dto;

import jakarta.servlet.http.Part;
import lombok.Builder;
import lombok.Value;

import java.nio.file.Path;

@Value
@Builder
public class CreateUserDto {
    String name;
    String birthday;
    String email;
    String password;
    String role;
    String gender;
}
