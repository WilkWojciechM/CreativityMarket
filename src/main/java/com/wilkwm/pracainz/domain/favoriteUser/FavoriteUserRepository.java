package com.wilkwm.pracainz.domain.favoriteUser;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface FavoriteUserRepository extends CrudRepository<FavoriteUser, Long> {
    Optional<FavoriteUser> findByUser_EmailAndFavoriteUser_Email(String userEmail, String favoriteUserEmail);
    List<FavoriteUser> findAllByUser_Email(String userEmail);

    Optional<FavoriteUser> findByUserIdAndFavoriteUserId(Long userId, Long favoriteUserId);
}