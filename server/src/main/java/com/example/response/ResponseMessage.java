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

  @JsonProperty("balance")
  private Object balance;

  public ResponseMessage(String status, String code, Object balance) {
    this.status = status;
    this.code = code;
    this.balance = balance;
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
    return balance;
  }

  public void setBalance(Object balance) {
    this.balance = balance;
  }

  public String toJSON() {
    try {
      ObjectMapper mapper = new ObjectMapper();
      return mapper.writeValueAsString(this);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}
