package com.udtech.drills.feature.history.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.udtech.drills.R;
import com.udtech.drills.data.remote.fetch_user_data.History;
import com.udtech.drills.utils.Converters;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vrungel on 11.05.2017.
 */

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {
  private ArrayList<History> mHistories = new ArrayList<>();
  private boolean isCBshows = false;

  public void addListHistory(List<History> historyList) {
    mHistories.clear();
    mHistories.addAll(historyList);
    notifyDataSetChanged();
  }

  public void enableCheckBox(boolean b) {
    if (b) {
      isCBshows = true;
    } else {
      isCBshows = false;
    }
    notifyDataSetChanged();
  }

  @Override public HistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new HistoryViewHolder(
        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, parent, false));
  }

  @Override public void onBindViewHolder(HistoryViewHolder holder, int position) {
    //ChB
    if (isCBshows) {
      holder.mCheckBox.setVisibility(View.VISIBLE);
    } else {

      holder.mCheckBox.setVisibility(View.GONE);
    }

    //tv Timing
    holder.mTextViewHistoryDate.setText(Converters.fullDateWithTimeFromSeconds(
        String.valueOf(Math.round(mHistories.get(position).getHistoryPracticsDate()))));

    ///tv Total time
    holder.mTextViewTotalTime.setText(Converters.timeFromSeconds(
        String.valueOf(mHistories.get(position).getHistoryPracticsTime())));
  }

  public boolean isCBEnabled(){
    return isCBshows;
  }

  @Override public int getItemCount() {
    return mHistories.size();
  }

  static class HistoryViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.checkBox) CheckBox mCheckBox;
    @BindView(R.id.tvHistoryDate) TextView mTextViewHistoryDate;
    @BindView(R.id.tvTotalTime) TextView mTextViewTotalTime;

    HistoryViewHolder(View view) {
      super(view);
      ButterKnife.bind(this, view);
    }
  }
}