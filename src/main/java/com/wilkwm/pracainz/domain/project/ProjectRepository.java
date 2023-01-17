package com.wilkwm.pracainz.domain.project;


import com.wilkwm.pracainz.domain.project.dto.ProjectDto;
import com.wilkwm.pracainz.domain.user.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends CrudRepository<Project, Long> {
    List<Project> findAll();
    Optional<Project> findByUser_Email(String userEmail);
    List<Project> findAllByField_NameIgnoreCase(String field);
    List<Project> findAllByUser_Name(String name);

}
