package com.wilkwm.pracainz.domain.project.dto;

public class ProjectDto {
    private Long id;
    private String name;
    private String field;
    private String user;
    private boolean promoted;
    private String description;
    private String youtubeId;
    private String projectPic;

    public ProjectDto(Long id, String name, String field, String user, boolean promoted, String description, String youtubeId, String projectPic) {
        this.id = id;
        this.name = name;
        this.field = field;
        this.user = user;
        this.promoted = promoted;
        this.description = description;
        this.youtubeId = youtubeId;
        this.projectPic = projectPic;
    }

    public String getProjectPic() {
        return projectPic;
    }

    public void setProjectPic(String projectPic) {
        this.projectPic = projectPic;
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
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
