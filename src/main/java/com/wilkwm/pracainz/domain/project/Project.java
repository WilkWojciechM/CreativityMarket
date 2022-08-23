package com.wilkwm.pracainz.domain.project;

import com.wilkwm.pracainz.domain.field.Field;

import javax.persistence.*;

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String creator;

    @ManyToOne
    @JoinColumn(name = "field_id", referencedColumnName = "id")
    private Field field;
    private boolean promoted;

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

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
}
