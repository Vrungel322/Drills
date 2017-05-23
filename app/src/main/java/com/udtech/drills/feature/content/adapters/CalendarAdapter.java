package com.udtech.drills.feature.content.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.udtech.drills.R;
import com.udtech.drills.utils.Constants;
import java.util.ArrayList;
import java.util.List;
import timber.log.Timber;

/**
 * Created by Vrungel on 22.05.2017.
 */

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder>  {
  private ArrayList<Integer> mCalendarItemEntities = new ArrayList<>();

  public void addListCalendarItemEntity(List<Integer> calendarItemEntities) {
    mCalendarItemEntities.clear();
    mCalendarItemEntities.addAll(calendarItemEntities);
    Timber.e(String.valueOf(mCalendarItemEntities.size()));
    notifyDataSetChanged();
  }


  @Override public CalendarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new CalendarAdapter.CalendarViewHolder(
        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_calendar, parent, false));
  }

  @Override public void onBindViewHolder(CalendarViewHolder holder, int position) {
    if (mCalendarItemEntities.get(position) == Constants.STATUS_DAY_HAS_NOT_COME){
      holder.mImageView.setBackgroundResource(R.drawable.item_calendar_circle_transparent);
    }
    if (mCalendarItemEntities.get(position) == Constants.STATUS_HAS_NO_PRACTICE){
      holder.mImageView.setBackgroundResource(R.drawable.item_calendar_circle_gray);
    }
    if (mCalendarItemEntities.get(position) == Constants.STATUS_HAS_PRACTICE){
      holder.mImageView.setBackgroundResource(R.drawable.item_calendar_circle_yellow);
    }

  }

  @Override public int getItemCount() {
    return mCalendarItemEntities.size();
  }

  static class CalendarViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.ivCalendarDay) ImageView mImageView;

    CalendarViewHolder(View view) {
      super(view);
      ButterKnife.bind(this, view);
    }
  }
}
