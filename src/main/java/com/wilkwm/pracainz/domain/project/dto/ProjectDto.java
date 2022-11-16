package com.wilkwm.pracainz.domain.project.dto;

public class ProjectDto {
    private Long id;
    private String name;
    private String field;
    private String creator;
    private boolean promoted;
    private String description;
    private String youtubeId;

    public ProjectDto(Long id, String name, String creator, boolean promoted, String field, String description, String youtubeId) {
        this.id = id;
        this.name = name;
        this.creator = creator;
        this.promoted = promoted;
        this.field = field;
        this.description = description;
        this.youtubeId = youtubeId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public boolean isPromoted() {
        return promoted;
    }

    public void setPromoted(boolean promoted) {
        this.promoted = promoted;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getYoutubeId() {
        return youtubeId;
    }

    public void setYoutubeId(String youtubeId) {
        this.youtubeId = youtubeId;
    }

}
