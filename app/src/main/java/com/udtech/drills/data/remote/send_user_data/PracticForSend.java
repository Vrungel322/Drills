package com.udtech.drills.data.remote.send_user_data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PracticForSend {

  @SerializedName("dryPracticsName") @Expose private String dryPracticsName;
  @SerializedName("boolIsRandPracticsTimeBetweenSets") @Expose private Integer
      boolIsRandPracticsTimeBetweenSets;
  @SerializedName("dryPracticsFirstSignalDelay") @Expose private String
      dryPracticsFirstSignalDelay;
  @SerializedName("dryPracticsDate") @Expose private Double dryPracticsDate;
  @SerializedName("dryPracticsDescription") @Expose private String dryPracticsDescription;
  @SerializedName("dryPracticsSets") @Expose private Integer dryPracticsSets;
  @SerializedName("dryPracticsTime") @Expose private String  dryPracticsTime;
  @SerializedName("boolIsRandPracticsFirstSignalDelay") @Expose private Integer
      boolIsRandPracticsFirstSignalDelay;
  @SerializedName("dryPracticsID") @Expose private String dryPracticsID;
  @SerializedName("dryPracticsTimeBetweenSets") @Expose private String dryPracticsTimeBetweenSets;

  public PracticForSend() {
  }

  public PracticForSend(String dryPracticsName, Integer boolIsRandPracticsTimeBetweenSets,
      String dryPracticsFirstSignalDelay, Double dryPracticsDate, String dryPracticsDescription,
      Integer dryPracticsSets, String  dryPracticsTime, Integer boolIsRandPracticsFirstSignalDelay,
      String dryPracticsID, String dryPracticsTimeBetweenSets) {
    super();
    this.dryPracticsName = dryPracticsName;
    this.boolIsRandPracticsTimeBetweenSets = boolIsRandPracticsTimeBetweenSets;
    this.dryPracticsFirstSignalDelay = dryPracticsFirstSignalDelay;
    this.dryPracticsDate = dryPracticsDate;
    this.dryPracticsDescription = dryPracticsDescription;
    this.dryPracticsSets = dryPracticsSets;
    this.dryPracticsTime = dryPracticsTime;
    this.boolIsRandPracticsFirstSignalDelay = boolIsRandPracticsFirstSignalDelay;
    this.dryPracticsID = dryPracticsID;
    this.dryPracticsTimeBetweenSets = dryPracticsTimeBetweenSets;
  }

  public String getDryPracticsName() {
    return dryPracticsName;
  }

  public void setDryPracticsName(String dryPracticsName) {
    this.dryPracticsName = dryPracticsName;
  }

  public Integer getBoolIsRandPracticsTimeBetweenSets() {
    return boolIsRandPracticsTimeBetweenSets;
  }

  public void setBoolIsRandPracticsTimeBetweenSets(Integer boolIsRandPracticsTimeBetweenSets) {
    this.boolIsRandPracticsTimeBetweenSets = boolIsRandPracticsTimeBetweenSets;
  }

  public String getDryPracticsFirstSignalDelay() {
    return dryPracticsFirstSignalDelay;
  }

  public void setDryPracticsFirstSignalDelay(String dryPracticsFirstSignalDelay) {
    this.dryPracticsFirstSignalDelay = dryPracticsFirstSignalDelay;
  }

  public Double getDryPracticsDate() {
    return dryPracticsDate;
  }

  public void setDryPracticsDate(Double dryPracticsDate) {
    this.dryPracticsDate = dryPracticsDate;
  }

  public String getDryPracticsDescription() {
    return dryPracticsDescription;
  }

  public void setDryPracticsDescription(String dryPracticsDescription) {
    this.dryPracticsDescription = dryPracticsDescription;
  }

  public Integer getDryPracticsSets() {
    return dryPracticsSets;
  }

  public void setDryPracticsSets(Integer dryPracticsSets) {
    this.dryPracticsSets = dryPracticsSets;
  }

  public String getDryPracticsTime() {
    return dryPracticsTime;
  }

  public void setDryPracticsTime(String dryPracticsTime) {
    this.dryPracticsTime = dryPracticsTime;
  }

  public Integer getBoolIsRandPracticsFirstSignalDelay() {
    return boolIsRandPracticsFirstSignalDelay;
  }

  public void setBoolIsRandPracticsFirstSignalDelay(Integer boolIsRandPracticsFirstSignalDelay) {
    this.boolIsRandPracticsFirstSignalDelay = boolIsRandPracticsFirstSignalDelay;
  }

  public String getDryPracticsID() {
    return dryPracticsID;
  }

  public void setDryPracticsID(String dryPracticsID) {
    this.dryPracticsID = dryPracticsID;
  }

  public String getDryPracticsTimeBetweenSets() {
    return dryPracticsTimeBetweenSets;
  }

  public void setDryPracticsTimeBetweenSets(String dryPracticsTimeBetweenSets) {
    this.dryPracticsTimeBetweenSets = dryPracticsTimeBetweenSets;
  }
}
