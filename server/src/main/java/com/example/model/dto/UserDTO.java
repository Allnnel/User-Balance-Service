package com.example.model.dto;

import com.example.model.User;
import java.util.Date;

public class UserDTO {

  private String login;
  private String mobilePhone;
  private String email;
  private Date birthDay;

  public UserDTO(User user) {
    mobilePhone = user.getMobilePhone();
    login = user.getLogin();
    email = user.getEmail();
    birthDay = user.getBirthDay();
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getMobilePhone() {
    return mobilePhone;
  }

  public void setMobilePhone(String mobilePhone) {
    this.mobilePhone = mobilePhone;
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
}
