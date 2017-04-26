package com.udtech.drills.data.remote.send_user_data;

/**
 * Created by Vrungel on 26.04.2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class SendUserDataEntity {

  @SerializedName("history") @Expose private List<HistoryForSend> history = null;
  @SerializedName("practic") @Expose private List<PracticForSend> practic = null;

  /**
   * No args constructor for use in serialization
   */
  public SendUserDataEntity() {
  }

  /**
   *
   * @param history
   * @param practic
   */
  public SendUserDataEntity(List<HistoryForSend> history, List<PracticForSend> practic) {
    super();
    this.history = history;
    this.practic = practic;
  }

  public List<HistoryForSend> getHistory() {
    return history;
  }

  public void setHistory(List<HistoryForSend> history) {
    this.history = history;
  }

  public List<PracticForSend> getPractic() {
    return practic;
  }

  public void setPractic(List<PracticForSend> practic) {
    this.practic = practic;
  }
}