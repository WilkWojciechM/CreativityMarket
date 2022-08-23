package com.wilkwm.pracainz.domain.project.dto;

public class ProjectDto {
    private Long id;
    private String name;
    private String field;
    private String creator;
    private boolean promoted;

    public ProjectDto(Long id, String name, String creator, String field, boolean promoted) {
        this.id = id;
        this.name = name;
        this.creator = creator;
        this.field = field;
        this.promoted = promoted;
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
}
