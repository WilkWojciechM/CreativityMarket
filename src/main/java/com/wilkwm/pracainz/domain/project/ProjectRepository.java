package com.wilkwm.pracainz.domain.project;


import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface ProjectRepository extends CrudRepository<Project, Long> {
    List<Project> findAllByPromotedIsTrue();

    //metoda pozwalajca wyszukać projekty na podstawie dziedziny
    List<Project> findAllByField_NameIgnoreCase(String field);
    //metoda pozwalająca wyszukać projekty na podstawie twórcy
    List<Project> findAllByName(String name);
}
