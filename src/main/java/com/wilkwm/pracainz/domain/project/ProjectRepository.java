package com.wilkwm.pracainz.domain.project;


import com.wilkwm.pracainz.domain.user.User;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends CrudRepository<Project, Long> {
    List<Project> findAllByPromotedIsTrue();

    List<Project> findAll();

    Optional<Project> findByUser_Email(String userEmail);

    //metoda pozwalajca wyszukać projekty na podstawie dziedziny
    List<Project> findAllByField_NameIgnoreCase(String field);
    //metoda pozwalająca wyszukać projekty na podstawie twórcy
    List<Project> findAllByUser_Name(String name);
}
