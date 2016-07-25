package com.sven.feelingheader.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.SwipeLoadMoreFooterLayout;
import com.sven.feelingheader.R;
import com.wang.avi.AVLoadingIndicatorView;

/**
 * Created by Aspsine on 2015/9/2.
 */
public class FeelingFooter extends SwipeLoadMoreFooterLayout {
    private TextView tvLoadMore;

    private AVLoadingIndicatorView ivLoading;

    private int mFooterHeight;

    public FeelingFooter(Context context) {
        this(context, null);
    }

    public FeelingFooter(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FeelingFooter(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mFooterHeight = getResources().getDimensionPixelOffset(R.dimen.load_more_footer_height_classic);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        tvLoadMore = (TextView) findViewById(R.id.tvLoadMore);
        ivLoading= (AVLoadingIndicatorView) findViewById(R.id.ivLoading);
    }

    @Override
    public void onPrepare() {

    }

    @Override
    public void onMove(int y, boolean isComplete, boolean automatic) {
        if (!isComplete) {
            ivLoading.setVisibility(GONE);
            if (-y >= mFooterHeight) {
                tvLoadMore.setText("释放加载更多");
            } else {
                tvLoadMore.setText("上拉加载更多");
            }
        }
    }

    @Override
    public void onLoadMore() {
        tvLoadMore.setText("正在加载更多的数据");
        ivLoading.setVisibility(VISIBLE);
    }

    @Override
    public void onRelease() {

    }

    @Override
    public void onComplete() {
        ivLoading.setVisibility(GONE);
    }

    @Override
    public void onReset() {
        ivLoading.setVisibility(GONE);
    }
}
