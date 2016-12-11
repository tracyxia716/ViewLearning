package com.myview.viewlearning.coordinator;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.myview.viewlearning.R;
import com.myview.viewlearning.coordinator.adapter.ListRecyclerAdapter;
import com.myview.viewlearning.utils.AnimatorUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作者：tracyxia 时间：16/12/11/10:36 描述： 版本：
 */

public class CoordinatorActivity extends Activity {

	private boolean isIntializeFAB = false;

	@BindView(R.id.toolbar)
	Toolbar mToolbar;
	@BindView(R.id.recyclerview)
	RecyclerView mRecyclerview;
	@BindView(R.id.fab)
	FloatingActionButton mFab;
	@BindView(R.id.coordinatorlayout)
	CoordinatorLayout mCoordinatorlayout;

	private Unbinder mUnbinder;
	private LinearLayoutManager mLinearLayoutManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.coordinator);
		mUnbinder = ButterKnife.bind(this);

		mRecyclerview.setHasFixedSize(true);
		List<String> list = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			list.add("我是第" + i + "个");
		}
		mLinearLayoutManager = new LinearLayoutManager(this);
		mLinearLayoutManager.setSmoothScrollbarEnabled(true);
		mRecyclerview.setLayoutManager(mLinearLayoutManager);
		ListRecyclerAdapter adapter = new ListRecyclerAdapter(list);
		mRecyclerview.setAdapter(adapter);

		mFab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mLinearLayoutManager.scrollToPosition(0);
				hideFab();
			}
		});
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		if (!isIntializeFAB) {
			isIntializeFAB = true;
			hideFab();
		}
	}

	private void hideFab() {
		mFab.postDelayed(new Runnable() {
			@Override
			public void run() {
				AnimatorUtils.scaleHide(mFab,
						new ViewPropertyAnimatorListener() {
							@Override
							public void onAnimationStart(View view) {

							}

							@Override
							public void onAnimationEnd(View view) {
								mFab.setVisibility(View.GONE);
							}

							@Override
							public void onAnimationCancel(View view) {

							}
						});
			}
		}, 500);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		mUnbinder.unbind();
	}
}
