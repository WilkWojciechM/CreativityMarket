package com.wilkwm.pracainz.favoriteUser;

import com.wilkwm.pracainz.domain.user.User;
import com.wilkwm.pracainz.domain.user.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FavoriteUserService {

    private final FavoriteUserRepository favoriteUserRepository;
    private final UserRepository userRepository;

    public FavoriteUserService(FavoriteUserRepository favoriteUserRepository, UserRepository userRepository) {
        this.favoriteUserRepository = favoriteUserRepository;
        this.userRepository = userRepository;
    }

    public void addToFavorites(String userEmail, String favoriteUserEmail) {
        User user = userRepository.findByEmail(userEmail).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        User favoriteUser = userRepository.findByEmail(favoriteUserEmail).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Optional<FavoriteUser> existingFavorite = favoriteUserRepository.findByUserIdAndFavoriteUserId(user.getId(), favoriteUser.getId());
        if (existingFavorite.isPresent()) {
            existingFavorite.get().setUser(user);
            existingFavorite.get().setFavoriteUser(favoriteUser);
            favoriteUserRepository.save(existingFavorite.get());
        } else {
            favoriteUserRepository.save(new FavoriteUser(user, favoriteUser));
        }
    }

    public void removeFromFavorites(String userEmail, String favoriteUserEmail) {
        User user = userRepository.findByEmail(userEmail).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        User favoriteUser = userRepository.findByEmail(favoriteUserEmail).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        FavoriteUser favorite = favoriteUserRepository.findByUser_EmailAndFavoriteUser_Email(userEmail, favoriteUserEmail)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        favoriteUserRepository.delete(favorite);
    }
    public List<User> getFavoriteUsers(String userEmail) {
        List<FavoriteUser> favoriteUsers = favoriteUserRepository.findAllByUser_Email(userEmail);
        return favoriteUsers.stream().map(FavoriteUser::getFavoriteUser).collect(Collectors.toList());
    }
}