package com.api.chuchu.zipcode;

import javax.persistence.*;

@Entity
public class Zipcode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String code;

    @Column
    private String type;

    @Column
    private String area_code;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAreaCode() {
        return area_code;
    }

    public void setAreaCode(String area_code) {
        this.area_code = area_code;
    }
}
