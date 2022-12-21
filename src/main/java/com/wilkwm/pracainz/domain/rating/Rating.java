package com.wilkwm.pracainz.domain.rating;

import com.wilkwm.pracainz.domain.project.Project;
import com.wilkwm.pracainz.domain.user.User;

import javax.persistence.*;

@Entity
@Table(name="project_rating")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="project_id")
    private Project project;
    private Integer rating;

    public Rating() {
    }

    public Rating(Long id, User user, Project project, Integer rating) {
        this.id = id;
        this.user = user;
        this.project = project;
        this.rating = rating;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
