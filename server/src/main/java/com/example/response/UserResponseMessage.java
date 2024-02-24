package com.example.response;

import com.example.model.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponseMessage extends ResponseMessage{

    @JsonProperty("user")
    private Object user;
    public UserResponseMessage(String status, String message, String code, Object user) {
        super(status, message, code);
        this.user = user;
    }

    public Object getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
