package com.udtech.drills.feature.holoshenie.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.udtech.drills.R;
import com.udtech.drills.data.remote.fetch_user_data.Practic;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vrungel on 28.04.2017.
 */

public class PracticeAdapter extends RecyclerView.Adapter<PracticeAdapter.PracticeViewHolder> {
  private ArrayList<Practic> mPractics = new ArrayList<>();

  public void addListPractic(List<Practic> practics) {
    mPractics.clear();
    mPractics.addAll(practics);
    notifyDataSetChanged();
  }

  @Override public PracticeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new PracticeViewHolder(
        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_practice, parent, false));
  }

  @Override public void onBindViewHolder(PracticeViewHolder holder, int position) {

    holder.mTextViewPracticeName.setText(mPractics.get(position).getDryPracticsName());
    holder.mTextViewPracticeTime.setText(
        String.valueOf(mPractics.get(position).getDryPracticsTime()) + " sec");
  }

  @Override public int getItemCount() {
    return mPractics.size();
  }

  public Practic getItem(int position) {
    return mPractics.get(position);
  }

  static class PracticeViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.tvPracticeName) TextView mTextViewPracticeName;
    @BindView(R.id.tvPracticeTime) TextView mTextViewPracticeTime;

    PracticeViewHolder(View view) {
      super(view);
      ButterKnife.bind(this, view);
    }
  }
}
