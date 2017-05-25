package com.udtech.drills.feature.holoshenie.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.udtech.drills.R;
import com.udtech.drills.data.remote.fetch_user_data.Practic;
import java.util.ArrayList;
import java.util.List;
import timber.log.Timber;

/**
 * Created by Vrungel on 28.04.2017.
 */

public class PracticeAdapter extends RecyclerView.Adapter<PracticeAdapter.PracticeViewHolder> {
  private ArrayList<Practic> mPractics = new ArrayList<>();
  private boolean isCBshows = false;
  private List<Practic> mListToRemove = new ArrayList<>();


  public void addListPractic(List<Practic> practics) {
    mPractics.clear();
    mPractics.addAll(practics);
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

  @Override public PracticeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new PracticeViewHolder(
        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_practice, parent, false));
  }

  @Override public void onBindViewHolder(PracticeViewHolder holder, int position) {
    //ChB
    if (isCBshows) {
      holder.mCheckBox.setVisibility(View.VISIBLE);
      holder.mCheckBox.setChecked(mPractics.get(position).isChecked());
      holder.mCheckBox.setOnClickListener((v) -> {
        mPractics.get(position).setChecked(holder.mCheckBox.isChecked());
        Timber.e(String.valueOf(holder.mCheckBox.isChecked()));
      });
    } else {
      holder.mCheckBox.setVisibility(View.GONE);
    }

    holder.mTextViewPracticeName.setText(mPractics.get(position).getDryPracticsName());
    holder.mTextViewPracticeTime.setText(
        String.valueOf(mPractics.get(position).getDryPracticsTime()) + " sec");
  }

  @Override public int getItemCount() {
    return mPractics.size();
  }

  public boolean isCBEnabled() {
    return isCBshows;
  }

  public List<Practic> getListToRemove() {
    for (int i = 0; i < mPractics.size(); i++) {
      if (mPractics.get(i).isChecked()) {
        mListToRemove.add(mPractics.get(i));
      }
    }
    return mListToRemove;
  }

  public void removeItemsByListOfPos(List<Practic> listToRemove) {
    mPractics.removeAll(listToRemove);
    notifyDataSetChanged();
  }

  public Practic getItem(int position) {
    return mPractics.get(position);
  }

  static class PracticeViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.tvPracticeName) TextView mTextViewPracticeName;
    @BindView(R.id.tvPracticeTime) TextView mTextViewPracticeTime;
    @BindView(R.id.cbCheckBox) CheckBox mCheckBox;

    PracticeViewHolder(View view) {
      super(view);
      ButterKnife.bind(this, view);
    }
  }
}
