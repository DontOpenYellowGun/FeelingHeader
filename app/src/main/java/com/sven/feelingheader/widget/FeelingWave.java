package com.sven.feelingheader.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.BitmapShader;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by Sven on 2016/7/23 0023.
 *
 */

public class FeelingWave extends ImageView{

    // callback fired at first onSizeChanged
    private AnimationSetupCallback animationSetupCallback;
    // wave shader coordinates
    private float maskX, maskY;
    // if true, the shader will display the wave
    private boolean sinking;
    // true after the first onSizeChanged
    private boolean setUp;
    // shader containing a repeated wave
    /*渲染器*/
    private BitmapShader shader;
    // shader matrix
    private Matrix shaderMatrix;
    // wave drawable
    private Drawable wave;
    // (getHeight() - waveHeight) / 2
    private float offsetY;

    interface AnimationSetupCallback {
        void onSetupAnimation(FeelingWave titanicTextView);
    }

    public FeelingWave(Context context) {
        super(context);
        init();
    }

    public FeelingWave(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FeelingWave(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public FeelingWave(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
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
//        createShader();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
//        createShader();
        if (!setUp) {
            setUp = true;
            if (animationSetupCallback != null) {
                animationSetupCallback.onSetupAnimation(FeelingWave.this);
            }
        }
    }

/*    *//**
     * Create the shader
     * draw the wave with current color for a background
     * repeat the bitmap horizontally, and clamp colors vertically
     *//*
    private void createShader() {

        *//*获取到波浪图*//*
        if (wave == null) {
            wave = getResources().getDrawable(R.drawable.wave);
        }
        *//*获取到波浪图的宽高*//*
        int waveW = wave.getIntrinsicWidth();
        int waveH = wave.getIntrinsicHeight();

        *//*创建画布*//*
        Bitmap b = Bitmap.createBitmap(waveW, waveH, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);

        c.drawColor(getDrawingCacheBackgroundColor());

        wave.setBounds(0, 0, waveW, waveH);
        wave.draw(c);

        shader = new BitmapShader(b, Shader.TileMode.REPEAT, Shader.TileMode.CLAMP);
        getPaint().setShader(shader);

        offsetY = (getHeight() - waveH) / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        // modify text paint shader according to sinking state
        if (sinking && shader != null) {
            // first call after sinking, assign it to our paint
            if (getPaint().getShader() == null) {
                getPaint().setShader(shader);
            }

            *//*设置x.y轴的的位移*//*
            // translate shader accordingly to maskX maskY positions
            // maskY is affected by the offset to vertically center the wave
//            shaderMatrix.setTranslate(maskX, maskY + offsetY);
            shaderMatrix.setTranslate(maskX, 0);

            // assign matrix to invalidate the shader
            shader.setLocalMatrix(shaderMatrix);
        } else {
            getPaint().setShader(null);
        }
        super.onDraw(canvas);
    }*/
}
