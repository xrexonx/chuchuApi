package com.api.chuchu.address;


import com.api.chuchu.user.User;

import javax.persistence.*;

@Entity
public class Address {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private String street;

    @Column
    private String city;

    @Column
    private String suite;

    @Column
    private String zipcode;

    @OneToOne(mappedBy = "address", cascade = CascadeType.ALL)
    private User user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSuite() {
        return suite;
    }

    public void setSuite(String suite) {
        this.suite = suite;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
