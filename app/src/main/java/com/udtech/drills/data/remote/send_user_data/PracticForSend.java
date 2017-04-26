package com.udtech.drills.data.remote.send_user_data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PracticForSend {

  @SerializedName("dryPracticsName") @Expose private String dryPracticsName;
  @SerializedName("boolIsRandPracticsTimeBetweenSets") @Expose private String
      boolIsRandPracticsTimeBetweenSets;
  @SerializedName("dryPracticsFirstSignalDelay") @Expose private Integer
      dryPracticsFirstSignalDelay;
  @SerializedName("dryPracticsDate") @Expose private Double dryPracticsDate;
  @SerializedName("dryPracticsDescription") @Expose private String dryPracticsDescription;
  @SerializedName("dryPracticsSets") @Expose private Integer dryPracticsSets;
  @SerializedName("dryPracticsTime") @Expose private Integer dryPracticsTime;
  @SerializedName("boolIsRandPracticsFirstSignalDelay") @Expose private String
      boolIsRandPracticsFirstSignalDelay;
  @SerializedName("dryPracticsID") @Expose private String dryPracticsID;
  @SerializedName("dryPracticsTimeBetweenSets") @Expose private Integer dryPracticsTimeBetweenSets;

  public PracticForSend() {
  }

  public PracticForSend(String dryPracticsName, String boolIsRandPracticsTimeBetweenSets,
      Integer dryPracticsFirstSignalDelay, Double dryPracticsDate, String dryPracticsDescription,
      Integer dryPracticsSets, Integer dryPracticsTime, String boolIsRandPracticsFirstSignalDelay,
      String dryPracticsID, Integer dryPracticsTimeBetweenSets) {
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

  public String getBoolIsRandPracticsTimeBetweenSets() {
    return boolIsRandPracticsTimeBetweenSets;
  }

  public void setBoolIsRandPracticsTimeBetweenSets(String boolIsRandPracticsTimeBetweenSets) {
    this.boolIsRandPracticsTimeBetweenSets = boolIsRandPracticsTimeBetweenSets;
  }

  public Integer getDryPracticsFirstSignalDelay() {
    return dryPracticsFirstSignalDelay;
  }

  public void setDryPracticsFirstSignalDelay(Integer dryPracticsFirstSignalDelay) {
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

  public Integer getDryPracticsTime() {
    return dryPracticsTime;
  }

  public void setDryPracticsTime(Integer dryPracticsTime) {
    this.dryPracticsTime = dryPracticsTime;
  }

  public String getBoolIsRandPracticsFirstSignalDelay() {
    return boolIsRandPracticsFirstSignalDelay;
  }

  public void setBoolIsRandPracticsFirstSignalDelay(String boolIsRandPracticsFirstSignalDelay) {
    this.boolIsRandPracticsFirstSignalDelay = boolIsRandPracticsFirstSignalDelay;
  }

  public String getDryPracticsID() {
    return dryPracticsID;
  }

  public void setDryPracticsID(String dryPracticsID) {
    this.dryPracticsID = dryPracticsID;
  }

  public Integer getDryPracticsTimeBetweenSets() {
    return dryPracticsTimeBetweenSets;
  }

  public void setDryPracticsTimeBetweenSets(Integer dryPracticsTimeBetweenSets) {
    this.dryPracticsTimeBetweenSets = dryPracticsTimeBetweenSets;
  }
}
