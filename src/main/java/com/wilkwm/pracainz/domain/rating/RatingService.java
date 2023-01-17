package com.wilkwm.pracainz.domain.rating;

import com.wilkwm.pracainz.domain.project.Project;
import com.wilkwm.pracainz.domain.project.ProjectRepository;
import com.wilkwm.pracainz.domain.user.User;
import com.wilkwm.pracainz.domain.user.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
@Service
public class RatingService {

    private final RatingRepository ratingRepository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    public RatingService(RatingRepository ratingRepository, UserRepository userRepository, ProjectRepository projectRepository) {
        this.ratingRepository = ratingRepository;
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
    }

    public void addRating(String userEmail, Long projectId, int rating) {
        Rating ratingSave = ratingRepository.findByUser_EmailAndProject_id(userEmail, projectId).orElseGet(Rating::new);
        User user = userRepository.findByEmail(userEmail).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        ratingSave.setUser(user);
        ratingSave.setProject(project);
        ratingSave.setRating(rating);
        ratingRepository.save(ratingSave);
    }

    public Optional<Integer> getUserRating(String userEmail, long projectId) {
        return ratingRepository.findByUser_EmailAndProject_id(userEmail,projectId)
                .map(Rating::getRating);
    }

}
