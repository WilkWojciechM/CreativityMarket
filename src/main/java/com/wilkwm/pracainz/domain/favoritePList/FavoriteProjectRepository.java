package com.wilkwm.pracainz.domain.favoritePList;

import com.wilkwm.pracainz.domain.user.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface FavoriteProjectRepository extends CrudRepository<FavoriteProject, Long> {

    Optional<FavoriteProject> findByUser_EmailAndProject_id(String userEmail, Long projectId);

    List<FavoriteProject> findAllByUser_Email(String userEmail);
}
