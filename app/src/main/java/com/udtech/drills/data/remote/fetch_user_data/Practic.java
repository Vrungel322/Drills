package com.udtech.drills.data.remote.fetch_user_data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Practic implements Parcelable {

  @SerializedName("id") @Expose private Integer id;
  @SerializedName("dryPracticsID") @Expose private String dryPracticsID;
  @SerializedName("dryPracticsName") @Expose private String dryPracticsName;
  @SerializedName("dryPracticsDate") @Expose private Double dryPracticsDate;
  @SerializedName("dryPracticsTime") @Expose private Double dryPracticsTime;
  @SerializedName("dryPracticsFirstSignalDelay") @Expose private Integer
      dryPracticsFirstSignalDelay;
  @SerializedName("boolIsRandPracticsFirstSignalDelay") @Expose private Integer
      boolIsRandPracticsFirstSignalDelay;
  @SerializedName("dryPracticsTimeBetweenSets") @Expose private Integer dryPracticsTimeBetweenSets;
  @SerializedName("boolIsRandPracticsTimeBetweenSets") @Expose private Integer
      boolIsRandPracticsTimeBetweenSets;
  @SerializedName("dryPracticsSets") @Expose private Integer dryPracticsSets;
  @SerializedName("dryPracticsDescription") @Expose private String dryPracticsDescription;
  @SerializedName("user_id") @Expose private Integer userId;

  private boolean isChecked;

  /**
   * No args constructor for use in serialization
   */
  public Practic() {
  }

  /**
   *
   * @param dryPracticsName
   * @param id
   * @param boolIsRandPracticsFirstSignalDelay
   * @param userId
   * @param dryPracticsDescription
   * @param dryPracticsID
   * @param dryPracticsSets
   * @param dryPracticsFirstSignalDelay
   * @param boolIsRandPracticsTimeBetweenSets
   * @param dryPracticsTimeBetweenSets
   * @param dryPracticsTime
   * @param dryPracticsDate
   */
  public Practic(Integer id, String dryPracticsID, String dryPracticsName, Double dryPracticsDate,
      Double dryPracticsTime, Integer dryPracticsFirstSignalDelay,
      Integer boolIsRandPracticsFirstSignalDelay, Integer dryPracticsTimeBetweenSets,
      Integer boolIsRandPracticsTimeBetweenSets, Integer dryPracticsSets,
      String dryPracticsDescription, Integer userId) {
    super();
    this.id = id;
    this.dryPracticsID = dryPracticsID;
    this.dryPracticsName = dryPracticsName;
    this.dryPracticsDate = dryPracticsDate;
    this.dryPracticsTime = dryPracticsTime;
    this.dryPracticsFirstSignalDelay = dryPracticsFirstSignalDelay;
    this.boolIsRandPracticsFirstSignalDelay = boolIsRandPracticsFirstSignalDelay;
    this.dryPracticsTimeBetweenSets = dryPracticsTimeBetweenSets;
    this.boolIsRandPracticsTimeBetweenSets = boolIsRandPracticsTimeBetweenSets;
    this.dryPracticsSets = dryPracticsSets;
    this.dryPracticsDescription = dryPracticsDescription;
    this.userId = userId;
  }

  protected Practic(Parcel in) {
    dryPracticsID = in.readString();
    dryPracticsName = in.readString();
    dryPracticsDescription = in.readString();
  }

  public static final Creator<Practic> CREATOR = new Creator<Practic>() {
    @Override public Practic createFromParcel(Parcel in) {
      return new Practic(in);
    }

    @Override public Practic[] newArray(int size) {
      return new Practic[size];
    }
  };

  public boolean isChecked() {
    return isChecked;
  }

  public void setChecked(boolean checked) {
    isChecked = checked;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getDryPracticsID() {
    return dryPracticsID;
  }

  public void setDryPracticsID(String dryPracticsID) {
    this.dryPracticsID = dryPracticsID;
  }

  public String getDryPracticsName() {
    return dryPracticsName;
  }

  public void setDryPracticsName(String dryPracticsName) {
    this.dryPracticsName = dryPracticsName;
  }

  public Double getDryPracticsDate() {
    return dryPracticsDate;
  }

  public void setDryPracticsDate(Double dryPracticsDate) {
    this.dryPracticsDate = dryPracticsDate;
  }

  public Double getDryPracticsTime() {
    return dryPracticsTime;
  }

  public void setDryPracticsTime(Double dryPracticsTime) {
    this.dryPracticsTime = dryPracticsTime;
  }

  public Integer getDryPracticsFirstSignalDelay() {
    return dryPracticsFirstSignalDelay;
  }

  public void setDryPracticsFirstSignalDelay(Integer dryPracticsFirstSignalDelay) {
    this.dryPracticsFirstSignalDelay = dryPracticsFirstSignalDelay;
  }

  public Integer getBoolIsRandPracticsFirstSignalDelay() {
    return boolIsRandPracticsFirstSignalDelay;
  }

  public void setBoolIsRandPracticsFirstSignalDelay(Integer boolIsRandPracticsFirstSignalDelay) {
    this.boolIsRandPracticsFirstSignalDelay = boolIsRandPracticsFirstSignalDelay;
  }

  public Integer getDryPracticsTimeBetweenSets() {
    return dryPracticsTimeBetweenSets;
  }

  public void setDryPracticsTimeBetweenSets(Integer dryPracticsTimeBetweenSets) {
    this.dryPracticsTimeBetweenSets = dryPracticsTimeBetweenSets;
  }

  public Integer getBoolIsRandPracticsTimeBetweenSets() {
    return boolIsRandPracticsTimeBetweenSets;
  }

  public void setBoolIsRandPracticsTimeBetweenSets(Integer boolIsRandPracticsTimeBetweenSets) {
    this.boolIsRandPracticsTimeBetweenSets = boolIsRandPracticsTimeBetweenSets;
  }

  public Integer getDryPracticsSets() {
    return dryPracticsSets;
  }

  public void setDryPracticsSets(Integer dryPracticsSets) {
    this.dryPracticsSets = dryPracticsSets;
  }

  public String getDryPracticsDescription() {
    return dryPracticsDescription;
  }

  public void setDryPracticsDescription(String dryPracticsDescription) {
    this.dryPracticsDescription = dryPracticsDescription;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(dryPracticsID);
    dest.writeString(dryPracticsName);
    dest.writeString(dryPracticsDescription);
  }
}
