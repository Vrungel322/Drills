package com.udtech.drills.data.remote.signUp_Reset;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignUpResetBody {

  @SerializedName("phone_email") @Expose private String phoneEmail;

  public SignUpResetBody(String phoneEmail) {
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