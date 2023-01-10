package com.wilkwm.pracainz.domain.favoritePList;

import com.wilkwm.pracainz.domain.project.Project;
import com.wilkwm.pracainz.domain.project.ProjectRepository;
import com.wilkwm.pracainz.domain.user.User;
import com.wilkwm.pracainz.domain.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FavoriteProjectService {

    private final FavoriteProjectRepository favoriteProjectRepository;

    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    public FavoriteProjectService(FavoriteProjectRepository favoriteProjectRepository, UserRepository userRepository, ProjectRepository projectRepository) {
        this.favoriteProjectRepository = favoriteProjectRepository;
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
    }


    public void addToFavorites(String userEmail, Long projectId) {
    FavoriteProject favoriteProject = favoriteProjectRepository.findByUser_EmailAndProject_id(userEmail, projectId).orElseGet(FavoriteProject::new);
    User user = userRepository.findByEmail(userEmail).orElseThrow();
    Project project = projectRepository.findById(projectId).orElseThrow();
    favoriteProject.setUser(user);
    favoriteProject.setProject(project);
    favoriteProjectRepository.save(favoriteProject);
}

    public void removeFromFavorites(String userEmail, Long projectId) {
        FavoriteProject favoriteProject = favoriteProjectRepository.findByUser_EmailAndProject_id(userEmail, projectId).orElseThrow();
        favoriteProjectRepository.delete(favoriteProject);
    }

    public List<Project> getFavoriteProjects(String userEmail) {
        User user = userRepository.findByEmail(userEmail).orElseThrow();
        List<FavoriteProject> favoriteProjects = favoriteProjectRepository.findAllByUser_Email(user.getEmail());
        return favoriteProjects.stream()
                .map(FavoriteProject::getProject)
                .collect(Collectors.toList());
    }
}
