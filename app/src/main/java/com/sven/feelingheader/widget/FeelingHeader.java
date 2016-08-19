package com.sven.feelingheader.widget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

import com.aspsine.swipetoloadlayout.SwipeRefreshHeaderLayout;
import com.sven.feelingheader.R;


/**
 * Created by sven on 2015/9/9.
 */
public class FeelingHeader extends SwipeRefreshHeaderLayout {


    private WaveText tvLoading;

    private ImageView ivShadow;

    private WaveManager waveManager;

    private Context context;

    private int mHeaderHeight;

    private boolean rotated = false;

    private ObjectAnimator transAnim;

    private ObjectAnimator scaleXAnim;
    private ObjectAnimator scaleYAnim;

    public FeelingHeader(Context context) {
        this(context, null);
        this.context = context;
    }

    public FeelingHeader(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        this.context = context;
    }

    public FeelingHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        mHeaderHeight = getResources().getDimensionPixelOffset(R.dimen.refresh_header_height_twitter);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        tvLoading = (WaveText) findViewById(R.id.my_text_view);
        /*设置字体*/
//      tvLoading.setTypeface(Typefaces.get(getContext(), "Fashion.ttf"));
        ivShadow = (ImageView) findViewById(R.id.ivShadow);
        waveManager = new WaveManager();

        float curTranslationY = tvLoading.getTranslationY();
        transAnim = ObjectAnimator.ofFloat(tvLoading, "translationY", curTranslationY, -30f, curTranslationY);
        transAnim.setRepeatCount(1000);
        transAnim.setDuration(800);

        scaleXAnim = ObjectAnimator.ofFloat(ivShadow, "scaleX", 2f, 1f, 2f);
        scaleXAnim.setRepeatCount(1000);
        scaleXAnim.setDuration(800);

        /*scaleYAnim = ObjectAnimator.ofFloat(ivShadow, "scaleY", 1f, 0f, 1f);
        scaleYAnim.setRepeatCount(1000);
        scaleYAnim.setDuration(1000);*/
    }

    @Override
    public void onRefresh() {
        Log.e("FeelingHeader", "onRefresh()");
        transAnim.start();
        scaleXAnim.start();
//        scaleYAnim.start();
        waveManager.start(tvLoading);
    }

    @Override
    public void onPrepare() {
        Log.e("FeelingHeader", "onPrepare()");
        transAnim.start();
        scaleXAnim.start();
//        scaleYAnim.start();
        waveManager.start(tvLoading);
    }

    @Override
    public void onMove(int y, boolean isComplete, boolean automatic) {
//        Log.e("FeelingHeader", "onMove()____Y:" + y + "____isComplete:" + isComplete);
        transAnim.start();
        scaleXAnim.start();
//        scaleYAnim.start();
        waveManager.start(tvLoading);
    }

    @Override
    public void onRelease() {
        Log.e("FeelingHeader", "onRelease()");
        transAnim.start();
        scaleXAnim.start();
//        scaleYAnim.start();
        waveManager.start(tvLoading);
    }

    @Override
    public void onComplete() {
        Log.e("FeelingHeader", "onComplete()");
        transAnim.cancel();
        scaleXAnim.cancel();
//        scaleYAnim.cancel();
        waveManager.cancel();
    }

    @Override
    public void onReset() {
        Log.e("FeelingHeader", "onReset()");
    }
}
