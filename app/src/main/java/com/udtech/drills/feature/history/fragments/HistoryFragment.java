package com.udtech.drills.feature.history.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.udtech.drills.R;
import com.udtech.drills.base.BaseFragment;
import com.udtech.drills.data.local.mappers.show_history.DayGroupToHistoryId;
import com.udtech.drills.data.local.mappers.show_history.HistoryDay;
import com.udtech.drills.feature.content.fragments.ContentFragment;
import com.udtech.drills.feature.history.adapters.HistoryDayAdapter;
import com.udtech.drills.feature.history.adapters.PracticsGroupAdapter;
import com.udtech.drills.feature.history.presenters.HistoryFragmentPresenter;
import com.udtech.drills.feature.history.views.IHistoryFragmentView;
import com.udtech.drills.utils.Converters;
import com.udtech.drills.utils.ItemClickSupport;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vrungel on 11.05.2017.
 */

public class HistoryFragment extends BaseFragment implements IHistoryFragmentView {
  @InjectPresenter HistoryFragmentPresenter mHistoryFragmentPresenter;

  @BindView(R.id.tvDoneDelete) TextView mTextViewDoneDelete;
  @BindView(R.id.tvOtmenaIzmenit) TextView mTextViewOtmenaIzmenit;
  @BindView(R.id.tvHistory) TextView mTextViewHistory;
  @BindView(R.id.rvHistory) RecyclerView mRecyclerViewHistory;

  private HistoryDayAdapter mHistoryDayAdapter;
  private PracticsGroupAdapter mPracticsGroupAdapter;

  public static HistoryFragment newInstance() {
    Bundle args = new Bundle();
    HistoryFragment fragment = new HistoryFragment();
    fragment.setArguments(args);
    return fragment;
  }

  public HistoryFragment() {
    super(R.layout.fragment_history);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    mHistoryDayAdapter = new HistoryDayAdapter();
    mRecyclerViewHistory.setLayoutManager(new LinearLayoutManager(getContext()));
    mRecyclerViewHistory.setAdapter(mHistoryDayAdapter);
    mPracticsGroupAdapter = new PracticsGroupAdapter();

    ItemClickSupport.addTo(mRecyclerViewHistory)
        .setOnItemClickListener((recyclerView, position, v) -> {
          if (mRecyclerViewHistory.getAdapter() instanceof HistoryDayAdapter
              && !mHistoryDayAdapter.isCBEnabled()) {
            mTextViewHistory.setText(Converters.fullDateWithTimeFromSeconds(
                mHistoryDayAdapter.getHistoryItem(position).getStringDate()));
            mPracticsGroupAdapter.addListPracticsGroup(
                mHistoryDayAdapter.getHistoryItem(position).getGroupsOfPractics());
            mRecyclerViewHistory.setAdapter(mPracticsGroupAdapter);
          }
        });
  }

  @OnClick(R.id.tvOtmenaIzmenit) public void tvOtmenaIzmenitClicked() {
    if (mRecyclerViewHistory.getAdapter() instanceof HistoryDayAdapter) {
      mHistoryDayAdapter.enableCheckBox(!mHistoryDayAdapter.isCBEnabled());
      if (mHistoryDayAdapter.isCBEnabled()) {
        mTextViewOtmenaIzmenit.setText(getText(R.string.cancel));
        mTextViewDoneDelete.setText(getText(R.string.dell));
      } else {
        mTextViewOtmenaIzmenit.setText(getText(R.string.change));
        mTextViewDoneDelete.setText(getText(R.string.done));
      }
    } else {
      mPracticsGroupAdapter.enableCheckBox(!mPracticsGroupAdapter.isCBEnabled());
      if (mPracticsGroupAdapter.isCBEnabled()) {
        mTextViewOtmenaIzmenit.setText(getText(R.string.cancel));
        mTextViewDoneDelete.setText(getText(R.string.dell));
      } else {
        mTextViewOtmenaIzmenit.setText(getText(R.string.change));
        mTextViewDoneDelete.setText(getText(R.string.done));
      }
    }
  }

  @OnClick(R.id.tvDoneDelete) public void tvDoneDeleteClicked() {
    if (mTextViewOtmenaIzmenit.getText().equals(getText(R.string.change))) {
      if (mRecyclerViewHistory.getAdapter() instanceof PracticsGroupAdapter) {
        mTextViewHistory.setText(getText(R.string.history));
        mRecyclerViewHistory.setAdapter(mHistoryDayAdapter);
      } else {
        mNavigator.replaceFragment((AppCompatActivity) getActivity(), R.id.contentContainer,
            ContentFragment.newInstance());
      }
    } else {
      mTextViewOtmenaIzmenit.setText(getText(R.string.change));
      mTextViewDoneDelete.setText(getText(R.string.done));
      if (mRecyclerViewHistory.getAdapter() instanceof HistoryDayAdapter) {
        mHistoryDayAdapter.enableCheckBox(false);
        mHistoryFragmentPresenter.removeHistoryForSendFromDbByID(
            new DayGroupToHistoryId().getListIdByDay(mHistoryDayAdapter.getListToRemove()));
      } else {
        mPracticsGroupAdapter.enableCheckBox(false);
        mHistoryFragmentPresenter.removeHistoryForSendFromDbByID(
            new DayGroupToHistoryId().getListIdByPracticeGroup(
                mPracticsGroupAdapter.getListToRemove()));
      }
    }
  }

  @Override public void setHistoryList(List<HistoryDay> history) {
    mHistoryDayAdapter.addListHistory(history);
  }

  @Override public void removeFromView() {
    if (mRecyclerViewHistory.getAdapter() instanceof HistoryDayAdapter) {
      mHistoryDayAdapter.removeItemsByListOfPos(mHistoryDayAdapter.getListToRemove());
    } else {
      mPracticsGroupAdapter.removeItemsByListOfPos(mPracticsGroupAdapter.getListToRemove());
    }
  }

  @Override public void removeAllRowsFromHistoryList() {
    mHistoryDayAdapter.addListHistory(new ArrayList<>());
  }
}
