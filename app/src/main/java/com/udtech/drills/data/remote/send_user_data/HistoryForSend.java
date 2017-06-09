package com.udtech.drills.data.remote.send_user_data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HistoryForSend {

  @SerializedName("historyPracticsSets") @Expose private Integer historyPracticsSets;
  @SerializedName("historyPracticsName") @Expose private String historyPracticsName;
  @SerializedName("historyPracticsTime") @Expose private String historyPracticsTime;
  @SerializedName("historyPracticsID") @Expose private String historyPracticsID;
  @SerializedName("objectType") @Expose private String objectType;
  @SerializedName("historyPracticsDate") @Expose private Double historyPracticsDate;

  /**
   * No args constructor for use in serialization
   */
  public HistoryForSend() {
  }

  /**
   *
   * @param historyPracticsID
   * @param historyPracticsSets
   * @param historyPracticsTime
   * @param historyPracticsDate
   * @param objectType
   * @param historyPracticsName
   */
  public HistoryForSend(Integer historyPracticsSets, String historyPracticsName,
      String historyPracticsTime, String historyPracticsID, String objectType,
      Double historyPracticsDate) {
    super();
    this.historyPracticsSets = historyPracticsSets;
    this.historyPracticsName = historyPracticsName;
    this.historyPracticsTime = historyPracticsTime;
    this.historyPracticsID = historyPracticsID;
    this.objectType = objectType;
    this.historyPracticsDate = historyPracticsDate;
  }

  public Integer getHistoryPracticsSets() {
    return historyPracticsSets;
  }

  public void setHistoryPracticsSets(Integer historyPracticsSets) {
    this.historyPracticsSets = historyPracticsSets;
  }

  public String getHistoryPracticsName() {
    return historyPracticsName;
  }

  public void setHistoryPracticsName(String historyPracticsName) {
    this.historyPracticsName = historyPracticsName;
  }

  public String  getHistoryPracticsTime() {
    return historyPracticsTime;
  }

  public void setHistoryPracticsTime(String historyPracticsTime) {
    this.historyPracticsTime = historyPracticsTime;
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

  public Double getHistoryPracticsDate() {
    return historyPracticsDate;
  }

  public void setHistoryPracticsDate(Double historyPracticsDate) {
    this.historyPracticsDate = historyPracticsDate;
  }
}
