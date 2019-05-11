package com.api.chuchu.user;

import com.api.chuchu.address.Address;
import com.api.chuchu.company.Company;

import javax.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToOne(fetch=FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    private Address address;

    @OneToOne(fetch=FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    private Company company;

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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
