package com.wilkwm.pracainz.domain.field;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface FieldRepository extends CrudRepository<Field, Long> {
    Optional<Field> findByNameIgnoreCase(String name);
    Optional<Field> findFieldById(Long id);
}
