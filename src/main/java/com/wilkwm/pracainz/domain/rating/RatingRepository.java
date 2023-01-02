package com.wilkwm.pracainz.domain.rating;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RatingRepository extends CrudRepository<Rating, Long> {

    Optional<Rating> findByUser_EmailAndProject_id(String userEmail, Long projectId);
}
