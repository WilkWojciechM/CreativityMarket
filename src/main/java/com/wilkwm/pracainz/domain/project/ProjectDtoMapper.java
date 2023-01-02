package com.wilkwm.pracainz.domain.project;

import com.wilkwm.pracainz.domain.project.dto.ProjectDto;
import com.wilkwm.pracainz.domain.rating.Rating;

public class ProjectDtoMapper {

    static ProjectDto map(Project project){
        double avgRating = project.getRatings().stream()
                .map(Rating::getRating)
                .mapToDouble(val -> val)
                .average().orElse(0);
        int ratingCount = project.getRatings().size();
        return new ProjectDto(
                project.getId(),
                project.getName(),
                project.getField().getName(),
                project.getUser().getName(),
                project.isPromoted(),
                project.getDescription(),
                project.getYoutubeId(),
                project.getProjectPic(),
                avgRating,
                ratingCount);
    }
}
