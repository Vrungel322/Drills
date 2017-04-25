package com.udtech.drills.data.remote.login;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class City implements Parcelable {

  @SerializedName("id") @Expose private Integer id;
  @SerializedName("region_id") @Expose private Integer regionId;
  @SerializedName("name") @Expose private String name;
  public final static Parcelable.Creator<City> CREATOR = new Creator<City>() {

    @SuppressWarnings({
        "unchecked"
    }) public City createFromParcel(Parcel in) {
      City instance = new City();
      instance.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
      instance.regionId = ((Integer) in.readValue((Integer.class.getClassLoader())));
      instance.name = ((String) in.readValue((String.class.getClassLoader())));
      return instance;
    }

    public City[] newArray(int size) {
      return (new City[size]);
    }
  };

  /**
   * No args constructor for use in serialization
   */
  public City() {
  }

  /**
   *
   * @param id
   * @param name
   * @param regionId
   */
  public City(Integer id, Integer regionId, String name) {
    super();
    this.id = id;
    this.regionId = regionId;
    this.name = name;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getRegionId() {
    return regionId;
  }

  public void setRegionId(Integer regionId) {
    this.regionId = regionId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void writeToParcel(Parcel dest, int flags) {
    dest.writeValue(id);
    dest.writeValue(regionId);
    dest.writeValue(name);
  }

  public int describeContents() {
    return 0;
  }
}
