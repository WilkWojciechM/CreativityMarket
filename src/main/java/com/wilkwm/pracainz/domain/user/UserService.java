package com.wilkwm.pracainz.domain.user;

import com.wilkwm.pracainz.domain.user.dto.UserDto;
import com.wilkwm.pracainz.domain.user.dto.UserRegistrationDto;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<UserDto> findInfoByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(UserDtoMapper::map);
    }

    public Optional<UserDto> findUserByName(String name) {
        return userRepository.findByName(name)
                .map(UserDtoMapper::map);
    }

    public List<UserDto> findAllRegisteredUsers() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .map(UserDtoMapper::map)
                .toList();
    }

    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    public Set<String> findUserRoles(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(User::getRoles)
                .orElse(Collections.emptySet())
                .stream()
                .map(UserRole::getName)
                .collect(Collectors.toSet());
    }


    @Transactional
    public void registerUser(UserRegistrationDto userRegistrationDto) {

            User user = new User();
            user.setEmail(userRegistrationDto.getEmail());
            user.setName(userRegistrationDto.getName());
            user.setPassword(passwordEncoder.encode(userRegistrationDto.getPassword()));
            UserRole role = userRoleRepository.findByName(userRegistrationDto.getRole()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            user.getRoles().add(role);
            userRepository.save(user);

    }
}
