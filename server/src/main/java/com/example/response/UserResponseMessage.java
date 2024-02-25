package com.example.response;

import com.example.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.crypto.Data;
import java.util.Date;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponseMessage extends ResponseMessage {
  @JsonIgnore
  private Object user;
  @JsonProperty("id")
  private long id;
  @JsonProperty("login")
  private String login;
  @JsonProperty("email")
  private String email;
  @JsonProperty("mobilePhone")
  private String mobilePhone;
  @JsonProperty("birthDay")
  private Date birthDay;

  public UserResponseMessage(String status, String message, String code, Object user) {
    super(status, message, code);
    this.user = user;
    User userObject = (User) user;
    id = userObject.getId();
    birthDay = userObject.getBirthDay();
    email = userObject.getEmail();
    login = userObject.getLogin();
    mobilePhone = userObject.getMobilePhone();
  }

  public Object getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public void setUser(Object user) {
    this.user = user;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public Date getBirthDay() {
    return birthDay;
  }

  public void setBirthDay(Date birthDay) {
    this.birthDay = birthDay;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getMobilePhone() {
    return mobilePhone;
  }

  public void setMobilePhone(String mobilePhone) {
    this.mobilePhone = mobilePhone;
  }
}
