package com.wilkwm.pracainz.domain.project;


import com.wilkwm.pracainz.domain.project.dto.ProjectDto;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    public  ProjectService(ProjectRepository projectRepository){
        this.projectRepository = projectRepository;
    }

    public List<ProjectDto> findAllPromotedProjects(){
        return projectRepository.findAllByPromotedIsTrue().stream().map(ProjectDtoMapper::map).toList();
    }
}
