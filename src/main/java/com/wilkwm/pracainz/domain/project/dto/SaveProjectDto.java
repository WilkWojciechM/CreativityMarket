package com.wilkwm.pracainz.domain.project.dto;

import org.springframework.web.multipart.MultipartFile;

public class SaveProjectDto {
    private String name;
    private String field;
    private String creator;
    private boolean promoted;
    private String description;
    private String youtubeId;
    private MultipartFile projectPic;

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

    public MultipartFile getProjectPic() {
        return projectPic;
    }

    public void setProjectPic(MultipartFile projectPic) {
        this.projectPic = projectPic;
    }
}
