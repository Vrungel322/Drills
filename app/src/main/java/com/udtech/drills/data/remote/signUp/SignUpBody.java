package com.udtech.drills.data.remote.signUp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignUpBody {

  @SerializedName("phone_email") @Expose private String phoneEmail;

  public SignUpBody(String phoneEmail) {
    super();
    this.phoneEmail = phoneEmail;
  }

  public String getPhoneEmail() {
    return phoneEmail;
  }

  public void setPhoneEmail(String phoneEmail) {
    this.phoneEmail = phoneEmail;
  }

  @Override public String toString() {
    return "phone_email" + this.getPhoneEmail();
  }
}