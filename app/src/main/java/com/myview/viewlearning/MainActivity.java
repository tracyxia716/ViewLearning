package com.myview.viewlearning;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.myview.viewlearning.coordinator.CoordinatorActivity;
import com.myview.viewlearning.scrolled.ScrolledActivity;
import com.myview.viewlearning.zhihu.ZhihuActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 作者：tracyxia 时间：16/12/11/09:04 描述： 版本：
 */

public class MainActivity extends Activity {

	Unbinder mUnbinder;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		mUnbinder = ButterKnife.bind(this);
	}

	@OnClick({R.id.btn_scrolled, R.id.btn_coordinatorlayout, R.id.btn_zhihu})
	void onClick(View view) {
		Intent intent = new Intent();
		switch (view.getId()) {
			case R.id.btn_scrolled :
				intent.setClass(this, ScrolledActivity.class);
				startActivity(intent);
				break;
			case R.id.btn_coordinatorlayout :
				intent.setClass(this, CoordinatorActivity.class);
				startActivity(intent);
				break;
			case R.id.btn_zhihu :
				intent.setClass(this, ZhihuActivity.class);
				startActivity(intent);
				break;
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		mUnbinder.unbind();
	}
}
