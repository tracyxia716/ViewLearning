package com.myview.viewlearning.zhihu;

import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.myview.viewlearning.R;
import com.myview.viewlearning.coordinator.adapter.ListRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：tracyxia 时间：16/12/11/17:12 描述： 版本：
 */

public class ZhihuActivity extends AppCompatActivity {

	@BindView(R.id.toolbar)
	Toolbar mToolbar;
	@BindView(R.id.recyclerView)
	RecyclerView mRecyclerView;
	@BindView(R.id.tab_layout)
	LinearLayout mTabLayout;
	@BindView(R.id.fab)
	FloatingActionButton mFab;

	private LinearLayoutManager mLinearLayoutManager;
	private BottomSheetBehavior mBottomSheetBehavior;
	private BottomSheetBehavior mFabSheetBehavior;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.zhihu);
		ButterKnife.bind(this);

		setSupportActionBar(mToolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		mRecyclerView.setHasFixedSize(true);
		List<String> list = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			list.add("我是第" + i + "个");
		}
		mLinearLayoutManager = new LinearLayoutManager(this);
		mLinearLayoutManager.setSmoothScrollbarEnabled(true);
		mRecyclerView.setLayoutManager(mLinearLayoutManager);
		ListRecyclerAdapter adapter = new ListRecyclerAdapter(list);
		mRecyclerView.setAdapter(adapter);

		mFab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Snackbar.make(mFab, "fab", Snackbar.LENGTH_SHORT).show();
			}
		});

		// ScaleDownShowBehavior scaleDownShowBehavior = ScaleDownShowBehavior
		// .from(mFab);
		// scaleDownShowBehavior.setOnStateChangedListener(onStateChangedListener);

		mBottomSheetBehavior = BottomSheetBehavior.from(mTabLayout);
		mFabSheetBehavior = BottomSheetBehavior.from(mFab);

	}
	private ScaleDownShowBehavior.OnStateChangedListener onStateChangedListener = new ScaleDownShowBehavior.OnStateChangedListener() {
		@Override
		public void onChanged(boolean isShow) {
			mBottomSheetBehavior.setState(isShow
					? BottomSheetBehavior.STATE_EXPANDED
					: BottomSheetBehavior.STATE_COLLAPSED);
		}
	};

	private boolean initialize = false;

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		if (!initialize) {
			initialize = true;
			mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			finish();
		}
		return true;
	}
}
