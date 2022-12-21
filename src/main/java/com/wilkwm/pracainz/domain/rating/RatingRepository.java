package com.wilkwm.pracainz.domain.rating;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RatingRepository extends CrudRepository<Rating, Long> {

    Optional<Rating> findByUser_NameAndProject_id(String userName, Long aLong);
}
