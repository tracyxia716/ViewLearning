package com.myview.viewlearning.coordinator.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.util.AttributeSet;
import android.view.View;

import com.myview.viewlearning.utils.AnimatorUtils;

/**
 * 作者：tracyxia 时间：16/12/11/15:48 描述： 版本：
 */

public class ScaleUpShowBehavior extends FloatingActionButton.Behavior {

	private boolean isAnimatingOut = false;

	public ScaleUpShowBehavior(Context context, AttributeSet attrs) {
		super();
	}

	@Override
	public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout,
			FloatingActionButton child, View directTargetChild, View target,
			int nestedScrollAxes) {
		return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL;
	}

	@Override
	public void onNestedScroll(CoordinatorLayout coordinatorLayout,
			FloatingActionButton child, View target, int dxConsumed,
			int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
		if (dyConsumed >= 0 && dyUnconsumed >= 0
				&& child.getVisibility() != View.VISIBLE) {
			// 上滑的时候显示
			AnimatorUtils.scaleShow(child, null);
		} else if (dyConsumed <= 0 && dyUnconsumed <= 0) {
			// 下滑的时候隐藏
			AnimatorUtils.scaleHide(child, hidePropertyAnimatorListener);
		}
	}
	@Override
	public void onStopNestedScroll(CoordinatorLayout coordinatorLayout,
			FloatingActionButton child, View target) {
		super.onStopNestedScroll(coordinatorLayout, child, target);
	}

	ViewPropertyAnimatorListener hidePropertyAnimatorListener = new ViewPropertyAnimatorListener() {
		@Override
		public void onAnimationStart(View view) {
			isAnimatingOut = true;
		}

		@Override
		public void onAnimationEnd(View view) {
			isAnimatingOut = false;
			view.setVisibility(View.GONE);
		}

		@Override
		public void onAnimationCancel(View view) {
			isAnimatingOut = false;
		}
	};
}
