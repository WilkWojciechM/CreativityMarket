package com.wilkwm.pracainz.domain.commission;

import com.wilkwm.pracainz.domain.field.Field;
import com.wilkwm.pracainz.domain.user.User;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Commission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    private String description;
    private String scope;
    private String timeNeeded;
    private String preferredCooperation;
    private BigDecimal pricingFrom;
    private BigDecimal pricingTo;
    private boolean availability;

    @ManyToOne
    @JoinColumn(name = "field_id", referencedColumnName = "id")
    private Field field;

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

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getTimeNeeded() {
        return timeNeeded;
    }

    public void setTimeNeeded(String timeNeeded) {
        this.timeNeeded = timeNeeded;
    }

    public String getPreferredCooperation() {
        return preferredCooperation;
    }

    public void setPreferredCooperation(String preferredCooperation) {
        this.preferredCooperation = preferredCooperation;
    }

    public BigDecimal getPricingFrom() {
        return pricingFrom;
    }

    public void setPricingFrom(BigDecimal pricingFrom) {
        this.pricingFrom = pricingFrom;
    }

    public BigDecimal getPricingTo() {
        return pricingTo;
    }

    public void setPricingTo(BigDecimal pricingTo) {
        this.pricingTo = pricingTo;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }
}
