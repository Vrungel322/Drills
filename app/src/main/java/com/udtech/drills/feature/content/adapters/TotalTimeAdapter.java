package com.udtech.drills.feature.content.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.udtech.drills.R;
import com.udtech.drills.utils.Converters;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vrungel on 23.05.2017.
 */

public class TotalTimeAdapter extends RecyclerView.Adapter<TotalTimeAdapter.TotalTimeViewHolder> {
  private ArrayList<Integer> mIntegers = new ArrayList<>();

  public void addListTotalTimes(List<Integer> totalTimes) {
    mIntegers.clear();
    mIntegers.addAll(totalTimes);
    notifyDataSetChanged();
  }

  @Override public TotalTimeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new TotalTimeAdapter.TotalTimeViewHolder(
        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_total_time, parent, false));
  }

  @Override public void onBindViewHolder(TotalTimeViewHolder holder, int position) {
    if (mIntegers.get(position) == 0) {
      holder.mImageViewIndicator.setVisibility(View.GONE);
      holder.mTextViewTotalTime.setVisibility(View.GONE);
    } else {
      holder.mImageViewIndicator.setVisibility(View.VISIBLE);
      holder.mTextViewTotalTime.setVisibility(View.VISIBLE);
      holder.mImageViewIndicator.setImageResource(R.drawable.item_calendar_circle_yellow);
      holder.mTextViewTotalTime.setText(
          Converters.timeFromSeconds(String.valueOf(mIntegers.get(position))));
    }
  }

  @Override public int getItemCount() {
    return mIntegers.size();
  }

  static class TotalTimeViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.ivIndicator) ImageView mImageViewIndicator;
    @BindView(R.id.tvTotalTime) TextView mTextViewTotalTime;

    TotalTimeViewHolder(View view) {
      super(view);
      ButterKnife.bind(this, view);
    }
  }
}
