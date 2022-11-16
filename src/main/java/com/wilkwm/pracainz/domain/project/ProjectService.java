package com.wilkwm.pracainz.domain.project;


import com.wilkwm.pracainz.domain.project.dto.ProjectDto;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    public  ProjectService(ProjectRepository projectRepository){
        this.projectRepository = projectRepository;
    }

    public List<ProjectDto> findAllPromotedProjects(){
        return projectRepository.findAllByPromotedIsTrue().stream().map(ProjectDtoMapper::map).toList();
    }
    public Optional<ProjectDto> findProjectById(long id) {
        return projectRepository.findById(id).map(ProjectDtoMapper::map);
    }
    public List<ProjectDto> findProjectByFieldName(String field) {
        return projectRepository.findAllByField_NameIgnoreCase(field).stream()
                .map(ProjectDtoMapper::map)
                .toList();
    }
}
