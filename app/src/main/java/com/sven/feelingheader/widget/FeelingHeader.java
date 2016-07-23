package com.sven.feelingheader.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.aspsine.swipetoloadlayout.SwipeRefreshHeaderLayout;
import com.sven.feelingheader.R;


/**
 * Created by sven on 2015/9/9.
 *
 */
public class FeelingHeader extends SwipeRefreshHeaderLayout {



    private ImageView ivLoading;

    private Context context;

    private int mHeaderHeight;

    private Animation rotateUp;

    private Animation rotateDown;

    private boolean rotated = false;

    public FeelingHeader(Context context) {
        this(context, null);
        this.context=context;
    }

    public FeelingHeader(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        this.context=context;
    }

    public FeelingHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
        mHeaderHeight = getResources().getDimensionPixelOffset(R.dimen.refresh_header_height_twitter);
        rotateUp = AnimationUtils.loadAnimation(context, R.anim.rotate_up);
        rotateDown = AnimationUtils.loadAnimation(context, R.anim.rotate_down);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ivLoading= (ImageView) findViewById(R.id.iv_loading);
    }

    @Override
    public void onRefresh() {
        Log.e("FeelingHeader", "onRefresh()");

    }

    @Override
    public void onPrepare() {
        Log.e("FeelingHeader", "onPrepare()");
    }

    @Override
    public void onMove(int y, boolean isComplete, boolean automatic) {
        Log.e("FeelingHeader", "onMove()____Y:"+y+"____isComplete:"+isComplete);
    }

    @Override
    public void onRelease() {
        Log.e("FeelingHeader", "onRelease()");
    }

    @Override
    public void onComplete() {
        Log.e("FeelingHeader", "onComplete()");
    }

    @Override
    public void onReset() {
        Log.e("FeelingHeader", "onReset()");
    }
}
