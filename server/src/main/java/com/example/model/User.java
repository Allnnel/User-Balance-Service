package com.example.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user", schema = "server")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "login", nullable = false)
    private String login;
    @Column(name = "passwordHash", nullable = false)
    private String passwordHash;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "birthDay", nullable = false)
    private Date birthDay;
    @Column(name = "mobilePhone", nullable = false)
    private String mobilePhone;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", email='" + email + '\'' +
                ", birthDay=" + birthDay +
                ", mobilePhone='" + mobilePhone + '\'' +
                '}';
    }

}
