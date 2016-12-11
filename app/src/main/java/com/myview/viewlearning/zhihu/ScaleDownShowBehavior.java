package com.myview.viewlearning.zhihu;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.myview.viewlearning.R;
import com.myview.viewlearning.utils.AnimatorUtils;

import static android.R.attr.y;

/**
 * 作者：tracyxia 时间：16/12/11/17:24 描述： 版本：
 */

public class ScaleDownShowBehavior extends FloatingActionButton.Behavior {

    /**
     * 退出动画是否正在执行。
     */
    private boolean isAnimatingOut = false;

    private OnStateChangedListener mOnStateChangedListener;

    private int mFloatTranslateY;
    private int mBottomTranslateY;
    private int mMinFabScroolY;
    private boolean isFirstScroll = true;

    private static final String TAG = "ScaleDownShowBehavior";

    private int mMinBottomScrollY;

    private int mScreenHeight;

    private int mStartY;

    public ScaleDownShowBehavior(Context context, AttributeSet attributeSet) {
        super();
        mMinFabScroolY = mMinBottomScrollY = context.getResources()
                .getDimensionPixelOffset(R.dimen.min_hide_bottom_y);
        mScreenHeight = context.getResources().getDisplayMetrics().heightPixels;
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout,
                                       FloatingActionButton child, View directTargetChild, View target,
                                       int nestedScrollAxes) {
        if (isFirstScroll) {
            mFloatTranslateY = child.getHeight()
                    + ((CoordinatorLayout.LayoutParams) child.getLayoutParams()).bottomMargin;
            isFirstScroll = false;
        }


        if (child.getVisibility() == View.VISIBLE) {
            mBottomTranslateY = 0;
        }

        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout,
                               FloatingActionButton child, View target, int dxConsumed,
                               int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        mStartY = child.getHeight()
                + ((CoordinatorLayout.LayoutParams) child.getLayoutParams()).bottomMargin;
        if ((dyConsumed > 0 || dyUnconsumed > 0) && !isAnimatingOut
                && child.getVisibility() == View.VISIBLE && mStartY == mFloatTranslateY) {
            // 往下滑隐藏
            AnimatorUtils.translateHide(child, viewPropertyAnimatorListener,
                    mFloatTranslateY);
        } else if ((dyConsumed < 0 || dyUnconsumed < 0)
                && child.getVisibility() != View.VISIBLE && mStartY == mFloatTranslateY) {
            // 往上滑显示
            AnimatorUtils.translateShow(child, null, mFloatTranslateY);
            if (mOnStateChangedListener != null) {
                mOnStateChangedListener.onChanged(true);
            }
        }

        if (child.getVisibility() != View.VISIBLE) {
            mBottomTranslateY += y;
            if (mBottomTranslateY > mMinBottomScrollY) {
                mOnStateChangedListener.onChanged(false);
            }
        }
    }

    @Override
    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout,
                                   FloatingActionButton child, View target) {
        super.onStopNestedScroll(coordinatorLayout, child, target);

        Log.i(TAG, "onStopNestedScroll: " + child.getVisibility());
    }

    public void setOnStateChangedListener(
            OnStateChangedListener mOnStateChangedListener) {
        this.mOnStateChangedListener = mOnStateChangedListener;
    }

    // 外部监听显示和隐藏。
    public interface OnStateChangedListener {
        void onChanged(boolean isShow);
    }

    public static <V extends View> ScaleDownShowBehavior from(V view) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        if (!(params instanceof CoordinatorLayout.LayoutParams)) {
            throw new IllegalArgumentException(
                    "The view is not a child of CoordinatorLayout");
        }
        CoordinatorLayout.Behavior behavior = ((CoordinatorLayout.LayoutParams) params)
                .getBehavior();
        if (!(behavior instanceof ScaleDownShowBehavior)) {
            throw new IllegalArgumentException(
                    "The view is not associated with ScaleDownShowBehavior");
        }
        return (ScaleDownShowBehavior) behavior;
    }

    private ViewPropertyAnimatorListener viewPropertyAnimatorListener = new ViewPropertyAnimatorListener() {

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
        public void onAnimationCancel(View arg0) {
            isAnimatingOut = false;
        }
    };
}
