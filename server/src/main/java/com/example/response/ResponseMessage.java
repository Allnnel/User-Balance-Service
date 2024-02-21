package com.example.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseMessage {
  @JsonProperty("status")
  private String status;

  @JsonProperty("code")
  private String code;

  @JsonProperty("object")
  private Object object;

  public ResponseMessage(String status, String code, Object object) {
    this.status = status;
    this.code = code;
    this.object = object;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public Object getBalance() {
    return object;
  }

  public void setBalance(Object balance) {
    this.object = balance;
  }

}
