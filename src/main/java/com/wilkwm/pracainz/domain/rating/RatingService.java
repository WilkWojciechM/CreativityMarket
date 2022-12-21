package com.wilkwm.pracainz.domain.rating;

import com.wilkwm.pracainz.domain.project.Project;
import com.wilkwm.pracainz.domain.project.ProjectRepository;
import com.wilkwm.pracainz.domain.user.User;
import com.wilkwm.pracainz.domain.user.UserRepository;

import java.util.Optional;

public class RatingService {

    private final RatingRepository ratingRepository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    public RatingService(RatingRepository ratingRepository, UserRepository userRepository, ProjectRepository projectRepository) {
        this.ratingRepository = ratingRepository;
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
    }

    public void addRaiting(String user_name, Long project_id, int rating) {
        Rating ratingSave = ratingRepository.findByUser_NameAndProject_id(user_name, project_id).orElseGet(Rating::new);
        User user = userRepository.findByName(user_name).orElseThrow();
        Project project = projectRepository.findById(project_id).orElseThrow();
        ratingSave.setUser(user);
        ratingSave.setProject(project);
        ratingSave.setRating(rating);
        ratingRepository.save(ratingSave);
    }

    public Optional<Integer> getUserRating(String user_name, long project_id) {
        return ratingRepository.findByUser_NameAndProject_id(user_name,project_id)
                .map(Rating::getRating);
    }

}
