package com.myview.viewlearning.utils;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * 作者：tracyxia 时间：16/12/11/16:03 描述： 版本：
 */

public class AnimatorUtils {

    public static final LinearInterpolator DEFAULT_INTERPOLATOR = new LinearInterpolator();

    public static void scaleShow(View view,
                                 ViewPropertyAnimatorListener viewPropertyAnimatorListener) {
        view.setVisibility(View.VISIBLE);
        ViewCompat.animate(view).scaleX(1.0f).scaleY(1.0f).alpha(1.0f)
                .setDuration(800).setListener(viewPropertyAnimatorListener)
                .setInterpolator(DEFAULT_INTERPOLATOR).start();
    }

    public static void scaleHide(View view,
                                 ViewPropertyAnimatorListener viewPropertyAnimatorListener) {
        ViewCompat.animate(view).scaleX(0.0f).scaleY(0.0f).alpha(0.0f)
                .setDuration(800).setInterpolator(DEFAULT_INTERPOLATOR)
                .setListener(viewPropertyAnimatorListener).start();
    }

    public static void translateHide(View view,
                                     ViewPropertyAnimatorListener viewPropertyAnimatorListener, int y) {
        ViewCompat.animate(view).translationYBy(y).setDuration(200)
                .setInterpolator(DEFAULT_INTERPOLATOR)
                .setListener(viewPropertyAnimatorListener).start();
    }

    public static void translateShow(View view,
                                     ViewPropertyAnimatorListener viewPropertyAnimatorListener, int y) {
        Log.i("translate", "translateShow: " + y);
        view.setVisibility(View.VISIBLE);
        ViewCompat.animate(view).translationYBy(-y)
                .setDuration(200).setInterpolator(DEFAULT_INTERPOLATOR)
                .setListener(viewPropertyAnimatorListener).start();
    }
}
