package com.sven.feelingheader.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.sven.feelingheader.R;

/**
 * Created by Sven on 2016/7/23 0023.
 *
 */

public class WaveImage extends ImageView{

    // 第一次尺寸改变时回调
    private AnimationSetupCallback animationSetupCallback;
    //波浪着色的坐标
    private float maskX, maskY;
    // 判断波浪是否上下移动,如果是的话就会重新绘制波浪
    private boolean sinking;
    // 第一次尺寸改变后变为true
    private boolean setUp;
    // 重复绘制波浪的着色器
    private BitmapShader shader;
    // shader matrix
    private Matrix shaderMatrix;
    //波浪图片
    private Drawable wave;
    // (getHeight() - waveHeight) / 2
    private float offsetY;

    private Paint paint;

    interface AnimationSetupCallback {
        void onSetupAnimation(WaveImage titanicTextView);
    }

    public WaveImage(Context context) {
        super(context);
        init();
    }

    public WaveImage(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public WaveImage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public WaveImage(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        shaderMatrix = new Matrix();
    }

    public AnimationSetupCallback getAnimationSetupCallback() {
        return animationSetupCallback;
    }

    void setAnimationSetupCallback(AnimationSetupCallback animationSetupCallback) {
        this.animationSetupCallback = animationSetupCallback;
    }

    public float getMaskX() {
        return maskX;
    }

    public void setMaskX(float maskX) {
        this.maskX = maskX;
        invalidate();
    }

    public float getMaskY() {
        return maskY;
    }

    public void setMaskY(float maskY) {
        this.maskY = maskY;
        invalidate();
    }

    public boolean isSinking() {
        return sinking;
    }

    void setSinking(boolean sinking) {
        this.sinking = sinking;
    }

    boolean isSetUp() {
        return setUp;
    }

    @Override
    public void setDrawingCacheBackgroundColor(int color) {
        super.setDrawingCacheBackgroundColor(color);
        createShader();
    }

    @Override
    public void setBackgroundColor(int color) {
        super.setBackgroundColor(color);
        createShader();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        createShader();
        if (!setUp) {
            setUp = true;
            if (animationSetupCallback != null) {
                animationSetupCallback.onSetupAnimation(WaveImage.this);
            }
        }
    }

    private void createShader() {

        /*获取到波浪图*/
        if (wave == null) {
            wave = getResources().getDrawable(R.drawable.wave);
        }
        /*获取到波浪图的宽高*/
        int waveW = wave.getIntrinsicWidth();
        int waveH = wave.getIntrinsicHeight();

        /*获取波浪图片的宽高创建画布*/
        Bitmap b = Bitmap.createBitmap(waveW, waveH, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);

        c.drawColor(Color.BLUE);

        wave.setBounds(0, 0, waveW, waveH);
        wave.draw(c);

        shader = new BitmapShader(b, Shader.TileMode.REPEAT, Shader.TileMode.CLAMP);
        paint = new Paint();
        paint.setShader(shader);

        offsetY = (getHeight() - waveH) / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        /* //像素点相对画布向右100sp,向下移动200sp
        matrix.setTranslate(100, 200);
        //1.原图  2.矩阵   3.画笔
        canvas.drawBitmap(bitmap, matrix, paint); */

        // modify text paint shader according to sinking state
        if (sinking && shader != null) {
            // first call after sinking, assign it to our paint
            if (paint.getShader() == null) {
                paint.setShader(shader);
            }

            /*设置x.y轴的的位移*/
            // translate shader accordingly to maskX maskY positions
            // maskY is affected by the offset to vertically center the wave
//            shaderMatrix.setTranslate(maskX, maskY + offsetY);
            shaderMatrix.setTranslate(maskX, maskY + offsetY);

            // assign matrix to invalidate the shader
            shader.setLocalMatrix(shaderMatrix);
        } else {
            paint.setShader(null);
        }
        super.onDraw(canvas);
    }
}
