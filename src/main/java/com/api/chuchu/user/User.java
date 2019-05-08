package com.api.chuchu.user;

import com.api.chuchu.address.Address;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue
    private long id;

    @Column
    private String name;

    @Column
    private String username;

    @Column
    private String email;

    @Column
    private String phone;

    @Column
    private String website;

    @JsonIgnore
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "address_id")
    private Address address;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
