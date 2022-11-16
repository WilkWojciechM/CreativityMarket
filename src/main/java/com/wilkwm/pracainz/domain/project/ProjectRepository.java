package com.wilkwm.pracainz.domain.project;


import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface ProjectRepository extends CrudRepository<Project, Long> {
    List<Project> findAllByPromotedIsTrue();

    //metoda pozwalajca wyszukać filmy na podstawie gatunku
    List<Project> findAllByField_NameIgnoreCase(String field);
}
