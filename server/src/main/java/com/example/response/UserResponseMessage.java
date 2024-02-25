package com.example.response;

import com.example.model.User;
import com.example.model.dto.UserDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponseMessage extends ResponseMessage {
  @JsonIgnore private Object user;

  @JsonProperty("user")
  private UserDTO userDTO;

  public UserResponseMessage(String status, String message, String code, Object user) {
    super(status, message, code);
    this.user = user;
    this.userDTO = new UserDTO((User) user);
    ;
  }

  public Object getUser() {
    return user;
  }

  public UserDTO getUserDTO() {
    return userDTO;
  }

  public void setUserDTO(UserDTO userDTO) {
    this.userDTO = userDTO;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public void setUser(Object user) {
    this.user = user;
  }
}
