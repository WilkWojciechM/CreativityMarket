package com.wilkwm.pracainz.domain.project;

import com.wilkwm.pracainz.domain.project.dto.ProjectDto;

public class ProjectDtoMapper {
    static ProjectDto map(Project project){
        return new ProjectDto(
                project.getId(),
                project.getName(),
                project.getCreator(),
                project.isPromoted(),
                project.getField().getName(),
                project.getDescription(),
                project.getYoutubeId()
        );
    }
}
