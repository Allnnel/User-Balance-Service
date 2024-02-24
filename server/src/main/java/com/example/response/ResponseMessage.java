package com.example.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseMessage {
  @JsonProperty("status")
  private String status;

  @JsonProperty("code")
  private String code;

  @JsonProperty("message")
  private String message;

  public ResponseMessage(String status, String message, String code) {
    this.status = status;
    this.code = code;
    this.message = message;
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

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
