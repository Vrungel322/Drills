package com.udtech.drills.data.remote.login;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Country implements Parcelable {

  @SerializedName("id") @Expose private Integer id;
  @SerializedName("name") @Expose private String name;
  @SerializedName("phone_code") @Expose private Integer phoneCode;
  public final static Parcelable.Creator<Country> CREATOR = new Creator<Country>() {

    @SuppressWarnings({
        "unchecked"
    }) public Country createFromParcel(Parcel in) {
      Country instance = new Country();
      instance.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
      instance.name = ((String) in.readValue((String.class.getClassLoader())));
      instance.phoneCode = ((Integer) in.readValue((Integer.class.getClassLoader())));
      return instance;
    }

    public Country[] newArray(int size) {
      return (new Country[size]);
    }
  };

  /**
   * No args constructor for use in serialization
   */
  public Country() {
  }

  /**
   *
   * @param id
   * @param phoneCode
   * @param name
   */
  public Country(Integer id, String name, Integer phoneCode) {
    super();
    this.id = id;
    this.name = name;
    this.phoneCode = phoneCode;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getPhoneCode() {
    return phoneCode;
  }

  public void setPhoneCode(Integer phoneCode) {
    this.phoneCode = phoneCode;
  }

  public void writeToParcel(Parcel dest, int flags) {
    dest.writeValue(id);
    dest.writeValue(name);
    dest.writeValue(phoneCode);
  }

  public int describeContents() {
    return 0;
  }
}
