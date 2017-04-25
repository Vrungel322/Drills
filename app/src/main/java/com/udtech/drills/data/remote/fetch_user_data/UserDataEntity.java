package com.udtech.drills.data.remote.fetch_user_data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class UserDataEntity {

  @SerializedName("practic") @Expose private List<Practic> practic = null;
  @SerializedName("history") @Expose private List<History> history = null;

  /**
   * No args constructor for use in serialization
   */
  public UserDataEntity() {
  }

  /**
   *
   * @param history
   * @param practic
   */
  public UserDataEntity(List<Practic> practic, List<History> history) {
    super();
    this.practic = practic;
    this.history = history;
  }

  public List<Practic> getPractic() {
    return practic;
  }

  public void setPractic(List<Practic> practic) {
    this.practic = practic;
  }

  public List<History> getHistory() {
    return history;
  }

  public void setHistory(List<History> history) {
    this.history = history;
  }
}
