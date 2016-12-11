package com.myview.viewlearning.scrolled;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

public class SlideMenu extends ViewGroup {
	// x轴的上一次位置
	private int mLastx;
	// 模拟弹性滑动
	private Scroller mScrooler;

	// 标记当前现实状态,默认为true显示的是主界面
	private boolean mIsMain = true;

	public SlideMenu(Context context) {
		this(context, null);
	}

	public SlideMenu(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public SlideMenu(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		mScrooler = new Scroller(context);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		View left = this.getChildAt(0);
		left.measure(left.getLayoutParams().width, heightMeasureSpec);
		View main = this.getChildAt(1);
		main.measure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		View left = this.getChildAt(0);
		left.layout(-left.getLayoutParams().width, 0, 0, b);

		View main = this.getChildAt(1);
		main.layout(l, t, r, b);
	}
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN :
				// getX是返回的相对于当前view左边缘的横轴距离,getRawx是返回的是相对于屏幕左边缘的距离,
				// 因为这里的viewgroup是撑满全屏的所以用这两个方法的效果都是一样的
				mLastx = (int) event.getX();
				break;
			case MotionEvent.ACTION_MOVE :
				int x = (int) event.getX();
				//上一次x轴位置减去当前X轴的值为这一次滑动的增量
				int deltaX = mLastx - x;
				//加上原本内容在X轴的偏移量得到它将要便宜的量
				int scrollX = getScrollX() + deltaX;
				if (scrollX > 0) {
					//大于0的时候代表侧滑菜单已经出来了所以直接scrollTo(0,0)即可
					scrollTo(0, 0);
				} else if (scrollX < -getChildAt(0).getWidth()) {
					//小于侧滑菜单的宽度的负值的时候则代表侧滑菜单已经完全隐藏起来了
					scrollTo(-this.getChildAt(0).getWidth(), 0);
				} else {
					//此时侧滑菜单既没有完全隐藏也没有完全现实根据增量值进行正常的scrollBy即可
					scrollBy(deltaX, 0);
				}
				mLastx = x;
				break;
			case MotionEvent.ACTION_UP :
				// 获取手指抬起时候的内容偏移量
				int upX = getScrollX();
				if (upX > -this.getChildAt(0).getWidth() / 2) {
					// 滑动距离大于了左侧菜单的一半则将菜单显示出来
					mIsMain = true;
				} else {
					// 反之菜单收起
					mIsMain = false;
				}
				switchScreen();
				break;
		}
		return true;
	}

	private void switchScreen() {
		int startX = getScrollX();// 起始的X轴偏移量
		int deltaX;
		if (mIsMain) {
			// 如果是要显示的主界面则用目标的偏移x点是0
			deltaX = 0 - startX;
		} else {
			// 如果要显示的是菜单界面则目标的偏移x点是菜单的左边界
			deltaX = -getChildAt(0).getLayoutParams().width - startX;
		}
		mScrooler.startScroll(startX, 0, deltaX, 0, Math.abs(deltaX) * 3);
		invalidate();
	}

	@Override
	public void computeScroll() {
		if (mScrooler.computeScrollOffset()) {
			scrollTo(mScrooler.getCurrX(), mScrooler.getCurrY());
			postInvalidate();
		}
	}

	public void switchMenu() {
		mIsMain = !mIsMain;
		switchScreen();
	}
}
