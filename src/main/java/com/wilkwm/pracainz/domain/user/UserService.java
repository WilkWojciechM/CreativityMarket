package com.wilkwm.pracainz.domain.user;

import com.wilkwm.pracainz.domain.user.dto.UserDto;
import com.wilkwm.pracainz.domain.user.dto.UserRegistrationDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserService {
    private static final String DEFAULT_USER_ROLE = "CREATOR";
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
    

    @Transactional
    public void registerUserCeatorRole(UserRegistrationDto userRegistrationDto) {
        UserRole defRole = userRoleRepository.findByName(DEFAULT_USER_ROLE).orElseThrow();
        User user = new User();
        user.setEmail(userRegistrationDto.getEmail());
        user.setPassword(passwordEncoder.encode(userRegistrationDto.getPassword()));
        user.getRoles().add(defRole);
        userRepository.save(user);
    }
}

