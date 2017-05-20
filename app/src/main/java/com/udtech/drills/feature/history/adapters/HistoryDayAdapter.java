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
import com.udtech.drills.data.local.mappers.show_history.HistoryDay;
import com.udtech.drills.utils.Converters;
import java.util.ArrayList;
import java.util.List;
import timber.log.Timber;

/**
 * Created by Vrungel on 11.05.2017.
 */

public class HistoryDayAdapter extends RecyclerView.Adapter<HistoryDayAdapter.HistoryViewHolder> {
  private ArrayList<HistoryDay> mHistories = new ArrayList<>();
  private boolean isCBshows = false;
  private List<HistoryDay> mListToRemove = new ArrayList<>();

  public void addListHistory(List<HistoryDay> historyList) {
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
      holder.mCheckBox.setChecked(mHistories.get(position).isChecked());
      holder.mCheckBox.setOnClickListener((v) -> {
        mHistories.get(position).setChecked(holder.mCheckBox.isChecked());
        Timber.e(String.valueOf(holder.mCheckBox.isChecked()));
      });
    } else {
      holder.mCheckBox.setVisibility(View.GONE);
    }

    //tv Timing
    holder.mTextViewHistoryDate.setText(
        Converters.fullDateWithTimeFromSeconds(mHistories.get(position).getStringDate()));

    //tv Total time
    holder.mTextViewTotalTime.setText(
        Converters.timeFromSeconds(mHistories.get(position).getStringTimeDay()));
  }

  public boolean isCBEnabled() {
    return isCBshows;
  }

  @Override public int getItemCount() {
    return mHistories.size();
  }

  public HistoryDay getHistoryItem(int position) {
    return mHistories.get(position);
  }

  public List<HistoryDay> getListToRemove() {
    for (int i = 0; i < mHistories.size(); i++) {
      if (mHistories.get(i).isChecked()) {
        mListToRemove.add(mHistories.get(i));
      }
    }
    return mListToRemove;
  }

  public void removeItemsByListOfPos(List<HistoryDay> listToRemove) {
    mHistories.removeAll(listToRemove);
    notifyDataSetChanged();
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