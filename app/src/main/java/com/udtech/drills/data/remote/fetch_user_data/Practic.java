package com.udtech.drills.data.remote.fetch_user_data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Practic {

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
}
