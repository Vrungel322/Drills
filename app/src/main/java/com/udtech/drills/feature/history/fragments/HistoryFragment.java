package com.udtech.drills.feature.history.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.udtech.drills.R;
import com.udtech.drills.base.BaseFragment;
import com.udtech.drills.data.remote.send_user_data.HistoryForSend;
import com.udtech.drills.feature.history.adapters.HistoryAdapter;
import com.udtech.drills.feature.history.presenters.HistoryFragmentPresenter;
import com.udtech.drills.feature.history.views.IHistoryFragmentView;
import com.udtech.drills.utils.ItemClickSupport;
import java.util.List;

/**
 * Created by Vrungel on 11.05.2017.
 */

public class HistoryFragment extends BaseFragment implements IHistoryFragmentView {
  @InjectPresenter HistoryFragmentPresenter mHistoryFragmentPresenter;

  @BindView(R.id.tvDoneDelete) TextView mTextViewDoneDelete;
  @BindView(R.id.tvOtmenaIzmenit) TextView mTextViewOtmenaIzmenit;
  @BindView(R.id.rvHistory) RecyclerView mRecyclerViewHistory;

  private HistoryAdapter mHistoryAdapter;

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
    mHistoryAdapter = new HistoryAdapter();
    mRecyclerViewHistory.setLayoutManager(new LinearLayoutManager(getContext()));
    mRecyclerViewHistory.setAdapter(mHistoryAdapter);

    ItemClickSupport.addTo(mRecyclerViewHistory)
        .setOnItemLongClickListener((recyclerView, position, v) -> {
          mHistoryAdapter.enableCheckBox(!mHistoryAdapter.isCBEnabled());
          return true;
        })
        .setOnItemClickListener((recyclerView, position, v) -> {
        });
  }

  @OnClick(R.id.tvOtmenaIzmenit) public void tvOtmenaIzmenitClicked() {
    mHistoryAdapter.enableCheckBox(!mHistoryAdapter.isCBEnabled());
    if (mHistoryAdapter.isCBEnabled()){
      mTextViewOtmenaIzmenit.setText(getText(R.string.cancel));
    }
    else {
      mTextViewOtmenaIzmenit.setText(getText(R.string.change));
    }
  }

  @Override public void setHistoryList(List<HistoryForSend> history) {
    mHistoryAdapter.addListHistory(history);
  }
}
