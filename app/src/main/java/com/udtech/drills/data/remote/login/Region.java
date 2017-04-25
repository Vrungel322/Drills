package com.udtech.drills.data.remote.login;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Region implements Parcelable {

  @SerializedName("id") @Expose private Integer id;
  @SerializedName("country_id") @Expose private Integer countryId;
  @SerializedName("name") @Expose private String name;
  public final static Parcelable.Creator<Region> CREATOR = new Creator<Region>() {

    @SuppressWarnings({
        "unchecked"
    }) public Region createFromParcel(Parcel in) {
      Region instance = new Region();
      instance.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
      instance.countryId = ((Integer) in.readValue((Integer.class.getClassLoader())));
      instance.name = ((String) in.readValue((String.class.getClassLoader())));
      return instance;
    }

    public Region[] newArray(int size) {
      return (new Region[size]);
    }
  };

  /**
   * No args constructor for use in serialization
   */
  public Region() {
  }

  /**
   *
   * @param countryId
   * @param id
   * @param name
   */
  public Region(Integer id, Integer countryId, String name) {
    super();
    this.id = id;
    this.countryId = countryId;
    this.name = name;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getCountryId() {
    return countryId;
  }

  public void setCountryId(Integer countryId) {
    this.countryId = countryId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void writeToParcel(Parcel dest, int flags) {
    dest.writeValue(id);
    dest.writeValue(countryId);
    dest.writeValue(name);
  }

  public int describeContents() {
    return 0;
  }
}
