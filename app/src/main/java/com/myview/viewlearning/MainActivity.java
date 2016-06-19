package com.myview.viewlearning;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.myview.viewlearning.widget.SlideMenu;

public class MainActivity extends AppCompatActivity {
	private SlideMenu mSlide;
	private Button mScrollBy;
	private Button mSwitchSlide;
	private Button mScrollTo;
	private TextView mMainText;
	private RelativeLayout mMainArea;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mSlide = (SlideMenu) findViewById(R.id.slide);
		mScrollBy = (Button) findViewById(R.id.scrollby);
		mMainText = (TextView) findViewById(R.id.main_text);
		mSwitchSlide = (Button) findViewById(R.id.switchslide);
		mScrollTo = (Button) findViewById(R.id.scrollTo);
		mMainArea = (RelativeLayout) findViewById(R.id.main_area);
		mSwitchSlide.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mSlide.switchMenu();
			}
		});
		mScrollBy.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mMainArea.scrollBy(100, 0);
			}
		});
		mScrollTo.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mMainArea.scrollTo(0, 0);
			}
		});
	}
}
