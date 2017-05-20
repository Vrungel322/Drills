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
import com.udtech.drills.data.local.mappers.show_history.GroupedPractices;
import com.udtech.drills.utils.Converters;
import java.util.ArrayList;
import java.util.List;
import timber.log.Timber;

/**
 * Created by Vrungel on 19.05.2017.
 */

public class PracticsGroupAdapter
    extends RecyclerView.Adapter<PracticsGroupAdapter.PracticsGroupViewHolder> {
  private ArrayList<GroupedPractices> mGroupedPractices = new ArrayList<>();
  private boolean isCBshows = false;
  private List<GroupedPractices> mListToRemove = new ArrayList<>();

  public void addListPracticsGroup(List<GroupedPractices> groupedPractices) {
    mGroupedPractices.clear();
    mGroupedPractices.addAll(groupedPractices);
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

  @Override public PracticsGroupViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new PracticsGroupAdapter.PracticsGroupViewHolder(LayoutInflater.from(parent.getContext())
        .inflate(R.layout.item_group_practics, parent, false));
  }

  @Override public void onBindViewHolder(PracticsGroupViewHolder holder, int position) {
    //ChB
    if (isCBshows) {
      holder.mCheckBox.setVisibility(View.VISIBLE);
      holder.mCheckBox.setChecked(mGroupedPractices.get(position).isChecked());
      holder.mCheckBox.setOnClickListener((v) -> {
        mGroupedPractices.get(position).setChecked(holder.mCheckBox.isChecked());
      });
    } else {
      holder.mCheckBox.setVisibility(View.GONE);
    }

    holder.mTextViewStartPracticsTime.setText(Converters.hoursMinsFromSeconds(
        String.valueOf(mGroupedPractices.get(position).getPracticesDateFirst())));
    holder.mTextViewGroupName.setText(mGroupedPractices.get(position).getPracticeName());
    holder.mTextViewSetsCountInGroup.setText(
        String.valueOf(mGroupedPractices.get(position).getSetsCount()));
    holder.mTextViewGroupTime.setText(
        Converters.timeFromSeconds(mGroupedPractices.get(position).getStringTimePractice()));
  }

  @Override public int getItemCount() {
    return mGroupedPractices.size();
  }

  public boolean isCBEnabled() {
    return isCBshows;
  }

  public List<GroupedPractices> getListToRemove() {
    for (int i = 0; i < mGroupedPractices.size(); i++) {
      if (mGroupedPractices.get(i).isChecked()){
        mListToRemove.add(mGroupedPractices.get(i));
      }
    }
    return mListToRemove;
  }

  public void removeItemsByListOfPos(List<GroupedPractices> listToRemove) {
    mGroupedPractices.removeAll(listToRemove);
    notifyDataSetChanged();
  }

  static class PracticsGroupViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.checkBox) CheckBox mCheckBox;
    @BindView(R.id.tvStartPracticsTime) TextView mTextViewStartPracticsTime;
    @BindView(R.id.tvGroupName) TextView mTextViewGroupName;
    @BindView(R.id.tvSetsCountInGroup) TextView mTextViewSetsCountInGroup;
    @BindView(R.id.tvGroupTime) TextView mTextViewGroupTime;

    PracticsGroupViewHolder(View view) {
      super(view);
      ButterKnife.bind(this, view);
    }
  }
}
