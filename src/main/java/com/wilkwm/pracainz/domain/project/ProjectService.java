package com.wilkwm.pracainz.domain.project;


import com.wilkwm.pracainz.domain.field.Field;
import com.wilkwm.pracainz.domain.field.FieldRepository;
import com.wilkwm.pracainz.domain.project.dto.ProjectDto;
import com.wilkwm.pracainz.domain.project.dto.SaveProjectDto;
import com.wilkwm.pracainz.domain.rating.RatingService;
import com.wilkwm.pracainz.domain.user.User;
import com.wilkwm.pracainz.domain.user.UserRepository;
import com.wilkwm.pracainz.storage.FileStorageService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final FieldRepository fieldRepository;
    private final FileStorageService fileStorageService;
    private  final UserRepository userRepository;
  //  private final RatingService ratingService;


    public ProjectService(ProjectRepository projectRepository, FieldRepository fieldRepository, FileStorageService fileStorageService, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.fieldRepository = fieldRepository;
        this.fileStorageService = fileStorageService;
        this.userRepository = userRepository;
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
    public List<ProjectDto> findProjectsByCreatorName(String name) {
        return projectRepository.findAllByUser_Name(name).stream()
                .map(ProjectDtoMapper::map)
                .toList();
    }

    public void addProject(SaveProjectDto saveProject) {
        Project project = new Project();
        project.setName(saveProject.getName());

        Field field = fieldRepository.findByNameIgnoreCase(saveProject.getField()).orElseThrow();
        project.setField(field);

        User user = userRepository.findByName(saveProject.getUser()).orElseThrow();
        project.setUser(user);


        project.setPromoted(saveProject.isPromoted());
        project.setDescription(saveProject.getDescription());
        project.setYoutubeId(saveProject.getYoutubeId());

        if (saveProject.getProjectPic() != null) {
            String savedFileName = fileStorageService.saveImage(saveProject.getProjectPic());
            project.setProjectPic(savedFileName);
        }
        projectRepository.save(project);
    }

    public void updateProject(Long id, SaveProjectDto projectDto) {
        Project project = projectRepository.findById(id).orElseThrow();
        project.setName(projectDto.getName());
        project.setDescription(projectDto.getDescription());
        project.setYoutubeId(projectDto.getYoutubeId());
        project.setField(fieldRepository.findByNameIgnoreCase(projectDto.getField()).orElseThrow());
        project.setPromoted(projectDto.isPromoted());
        if (projectDto.getProjectPic() != null) {
            String savedFileName = fileStorageService.saveImage(projectDto.getProjectPic());
            project.setProjectPic(savedFileName);
        }
        projectRepository.save(project);
    }

    public void deleteProject(Long id){
      //  ratingService.deleteByProjectId(id);
        projectRepository.deleteById(id);
    }
}
