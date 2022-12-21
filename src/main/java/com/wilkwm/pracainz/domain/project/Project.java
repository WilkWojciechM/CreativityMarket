package com.wilkwm.pracainz.domain.project;

import com.wilkwm.pracainz.domain.field.Field;
import com.wilkwm.pracainz.domain.rating.Rating;
import com.wilkwm.pracainz.domain.user.User;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    private String description;
    private String youtubeId;
    @ManyToOne
    @JoinColumn(name = "field_id", referencedColumnName = "id")
    private Field field;
    @OneToMany(mappedBy = "project")
    private Set<Rating> ratings = new HashSet<>();
    private boolean promoted;
    private String projectPic;

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

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public boolean isPromoted(){
        return promoted;
    }

    public void setPromoted(boolean promoted){
        this.promoted = promoted;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public Set<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(Set<Rating> ratings) {
        this.ratings = ratings;
    }
}
