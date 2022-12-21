package com.wilkwm.pracainz.domain.project;

import com.wilkwm.pracainz.domain.project.dto.ProjectDto;

public class ProjectDtoMapper {

    static ProjectDto map(Project project){
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
                ratingCount);
    }
}
