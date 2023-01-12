package com.wilkwm.pracainz.domain.user.dto;

import java.util.Set;

public class UserDto {

    private final String email;

    private final String name;

    private final String password;
    private final Set<String> roles;

    public UserDto(String email, String name, String password, Set<String> roles) {

        this.email = email;
        this.name = name;
        this.password = password;
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public Set<String> getRoles() {
        return roles;
    }
}
