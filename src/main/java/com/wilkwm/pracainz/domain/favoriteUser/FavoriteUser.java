package com.wilkwm.pracainz.domain.favoriteUser;

import com.wilkwm.pracainz.domain.user.User;
import com.wilkwm.pracainz.domain.user.dto.UserDto;

import javax.persistence.*;
import java.util.Optional;

@Entity
@Table(name= "favorite_users")
public class FavoriteUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id" )
    private User user;

    @ManyToOne
    @JoinColumn(name="favorite_user_id")
    private User favoriteUser;

    public FavoriteUser() {
    }

    public FavoriteUser(User user, User favoriteUser) {
        this.user = user;
        this.favoriteUser = favoriteUser;
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

    public User getFavoriteUser() {
        return favoriteUser;
    }

    public void setFavoriteUser(User favoriteUser) {
        this.favoriteUser = favoriteUser;
    }
}