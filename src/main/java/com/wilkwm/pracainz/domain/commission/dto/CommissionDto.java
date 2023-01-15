package com.wilkwm.pracainz.domain.commission.dto;

import java.math.BigDecimal;

public class CommissionDto {
    private Long id;
    private String name;
    private String description;
    private String timeNeeded;
    private String preferredCooperation;
    private BigDecimal pricingFrom;
    private BigDecimal pricingTo;
    private boolean availability;
    private boolean jobOffer;



    private String user;
    private String field;

    public CommissionDto(Long id, String name, String description, String timeNeeded, String preferredCooperation, BigDecimal pricingFrom, BigDecimal pricingTo, boolean availability, boolean jobOffer, String user, String field) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.timeNeeded = timeNeeded;
        this.preferredCooperation = preferredCooperation;
        this.pricingFrom = pricingFrom;
        this.pricingTo = pricingTo;
        this.availability = availability;
        this.jobOffer = jobOffer;
        this.user = user;
        this.field = field;

    }
    public CommissionDto(){};

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public boolean isJobOffer() {
        return jobOffer;
    }

    public void setJobOffer(boolean jobOffer) {
        this.jobOffer = jobOffer;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}
