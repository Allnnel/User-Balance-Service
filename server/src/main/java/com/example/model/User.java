package com.example.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user", schema = "server")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "login", nullable = false)
    private String login;
    @Column(name = "passwordHash", nullable = false)
    private String passwordHash;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "birthDay", nullable = true)
    private  String birthDay;
    @Column(name = "mobilePhone", nullable = true)
    private String mobilePhone;

    public User() {
    }

    public User( String login, String passwordHash, String email, String birthDay, String mobilePhone) {
        this.login = login;
        this.passwordHash = passwordHash;
        this.email = email;
        this.birthDay = birthDay;
        this.mobilePhone = mobilePhone;
    }
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

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
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
