package com.api.chuchu.dto;

import java.util.Date;

public class StateDTO {

    private Long id;

    private String name;

    private String abbreviation;

    private Date createdAt;

    private Date updatedAt;

    public StateDTO() {}

    public StateDTO(Long id, String name, String abbreviation, Date createdAt, Date updatedAt) {
        this.id = id;
        this.name = name;
        this.abbreviation = abbreviation;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

//    public Date getUpdatedAt() {
//        return updatedAt;
//    }

//    public void setUpdatedAt(Date updatedAt) {
//        this.updatedAt = updatedAt;
//    }

    @Override
    public String toString() {
        return "StateDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", abbreviation='" + abbreviation + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
