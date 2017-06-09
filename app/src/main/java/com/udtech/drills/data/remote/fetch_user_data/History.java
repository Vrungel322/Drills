package com.udtech.drills.data.remote.fetch_user_data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class History {

  @SerializedName("id") @Expose private Integer id;
  @SerializedName("user_id") @Expose private Integer userId;
  @SerializedName("historyPracticsID") @Expose private String historyPracticsID;
  @SerializedName("objectType") @Expose private String objectType;
  @SerializedName("historyPracticsName") @Expose private String historyPracticsName;
  @SerializedName("historyPracticsDate") @Expose private Double historyPracticsDate;
  @SerializedName("historyPracticsTime") @Expose private Double historyPracticsTime;
  @SerializedName("historyPracticsSets") @Expose private Integer historyPracticsSets;

  /**
   * No args constructor for use in serialization
   */
  public History() {
  }

  /**
   *
   * @param id
   * @param historyPracticsID
   * @param historyPracticsSets
   * @param userId
   * @param historyPracticsTime
   * @param historyPracticsDate
   * @param objectType
   * @param historyPracticsName
   */
  public History(Integer id, Integer userId, String historyPracticsID, String objectType,
      String historyPracticsName, Double historyPracticsDate, Double historyPracticsTime,
      Integer historyPracticsSets) {
    super();
    this.id = id;
    this.userId = userId;
    this.historyPracticsID = historyPracticsID;
    this.objectType = objectType;
    this.historyPracticsName = historyPracticsName;
    this.historyPracticsDate = historyPracticsDate;
    this.historyPracticsTime = historyPracticsTime;
    this.historyPracticsSets = historyPracticsSets;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public String getHistoryPracticsID() {
    return historyPracticsID;
  }

  public void setHistoryPracticsID(String historyPracticsID) {
    this.historyPracticsID = historyPracticsID;
  }

  public String getObjectType() {
    return objectType;
  }

  public void setObjectType(String objectType) {
    this.objectType = objectType;
  }

  public String getHistoryPracticsName() {
    return historyPracticsName;
  }

  public void setHistoryPracticsName(String historyPracticsName) {
    this.historyPracticsName = historyPracticsName;
  }

  public Double getHistoryPracticsDate() {
    return historyPracticsDate;
  }

  public void setHistoryPracticsDate(Double historyPracticsDate) {
    this.historyPracticsDate = historyPracticsDate;
  }

  public Double getHistoryPracticsTime() {
    return historyPracticsTime;
  }

  public void setHistoryPracticsTime(Double historyPracticsTime) {
    this.historyPracticsTime = historyPracticsTime;
  }

  public Integer getHistoryPracticsSets() {
    return historyPracticsSets;
  }

  public void setHistoryPracticsSets(Integer historyPracticsSets) {
    this.historyPracticsSets = historyPracticsSets;
  }
}
