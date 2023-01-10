package com.wilkwm.pracainz.domain.favoritePList;

import com.wilkwm.pracainz.domain.project.Project;
import com.wilkwm.pracainz.domain.user.User;

import javax.persistence.*;

@Entity
@Table(name= "favorite_project")
public class FavoriteProject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="project_id")
    private Project project;

    public FavoriteProject() {
    }

    public FavoriteProject(User user, Project project) {
        this.user = user;
        this.project = project;
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
}
