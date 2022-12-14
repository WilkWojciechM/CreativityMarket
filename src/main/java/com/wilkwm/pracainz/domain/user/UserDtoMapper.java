package com.wilkwm.pracainz.domain.user;

import com.wilkwm.pracainz.domain.user.dto.UserDto;

import java.util.Set;
import java.util.stream.Collectors;

public class UserDtoMapper {
    static UserDto map(User user) {
        String email = user.getEmail();
        String name = user.getName();
        String password = user.getPassword();
        Set<String> roles = user.getRoles()
                .stream()
                .map(UserRole::getName)
                .collect(Collectors.toSet());
        return new UserDto(email, name, password, roles);
    }
}
