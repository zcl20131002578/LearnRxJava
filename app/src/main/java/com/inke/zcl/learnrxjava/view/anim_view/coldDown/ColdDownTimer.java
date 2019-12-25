package com.inke.zcl.learnrxjava.view.anim_view.coldDown;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

import com.inke.zcl.learnrxjava.R;

/**
 * 冷却时间倒计时
 *
 */
public class ColdDownTimer extends ImageView {
    private Context mContext;
    private ScaleType SCALE_TYPE = ScaleType.CENTER_CROP;
    private Bitmap.Config BITMAP_CONFIG = Bitmap.Config.ARGB_8888;
    private int COLORDRAWABLE_DIMENSION = 1;
    private RectF mDrawableRect = new RectF();
    private Matrix mShaderMatrix = new Matrix();
    private Paint mBitmapPaint = new Paint();
    private Paint mArcPaint = new Paint();
    private Bitmap mBitmap;
    private BitmapShader mBitmapShader;
    private int mBitmapWidth;
    private int mBitmapHeight;
    private float mDrawableRadius;
    private boolean mReady;
    private boolean mSetupPending;
    /**
     * 冷却中提示
     */
    private String waitHint;
    /**
     * 已过时间百分比
     */
    private float timePercent;
    /**
     * 当前冷却时间
     */
    private int curCountDownTime;
    /**
     * 总冷却时间(默认5秒)
     */
    private int countdownTime = 5;
    /**
     * 动画持续时间
     */
    private int animDuration;
    private boolean isCountOver = true;
    private CountDownTimeListener countDownTimeListener;
    private Handler handler = new Handler();

    public ColdDownTimer(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ColdDownTimer(Context context, AttributeSet attrs,
                         int defStyle) {
        super(context, attrs, defStyle);
        super.setScaleType(SCALE_TYPE);
        this.mContext = context;
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.ColdDownTimer, defStyle, 0);
        waitHint = a.getString(R.styleable.ColdDownTimer_waitHint);
        a.recycle();
        mReady = true;
        if (mSetupPending) {
            setup();
            mSetupPending = false;
        }
    }

    @Override
    public ScaleType getScaleType() {
        return SCALE_TYPE;
    }


    public boolean isCountDownOver() {
        return isCountOver;
    }

    /**
     * 设置当前冷却时间
     *
     * @param time
     * @throws Exception
     */
    public void setCurCountTime(int time) {
        curCountDownTime = animDuration = time;
        if (curCountDownTime > countdownTime) {
            throw new IllegalArgumentException(Utils.getFunctionName(mContext, "当前冷却时间大于总冷却时间"));
        }
    }

    /**
     * 设置总冷却时间
     *
     * @param time
     * @throws Exception
     */
    public void setCountTime(int time) {
        countdownTime = time;
        if (curCountDownTime > countdownTime) {
            throw new IllegalArgumentException(Utils.getFunctionName(mContext, "当前冷却时间大于总冷却时间"));
        }
    }

    /**
     * 设置冷却中提示
     *
     * @param waitHint
     */
    public void setWaitHint(String waitHint) {
        this.waitHint = waitHint;
    }

    /**
     * 设置冷却监听器
     *
     * @param countDownTimeListener
     */
    public void setOnCountDownTimeListener(CountDownTimeListener countDownTimeListener) {
        this.countDownTimeListener = countDownTimeListener;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (getDrawable() == null) {
            return;
        }
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, mDrawableRadius,
                mBitmapPaint);
        if (!isCountOver) {
            canvas.drawArc(mDrawableRect, 270, -(360 * (1 - timePercent)),
                    true, mArcPaint);
        }

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        setup();
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        super.setImageBitmap(bm);
        mBitmap = bm;
        setup();
    }

    @Override
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        mBitmap = getBitmapFromDrawable(drawable);
        setup();
    }

    @Override
    public void setImageResource(int resId) {
        super.setImageResource(resId);
        mBitmap = getBitmapFromDrawable(getDrawable());
        setup();
    }

    private Bitmap getBitmapFromDrawable(Drawable drawable) {
        if (drawable == null) {
            return null;
        }
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        try {
            Bitmap bitmap;
            if (drawable instanceof ColorDrawable) {
                bitmap = Bitmap.createBitmap(COLORDRAWABLE_DIMENSION,
                        COLORDRAWABLE_DIMENSION, BITMAP_CONFIG);
            } else {
                bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                        drawable.getIntrinsicHeight(), BITMAP_CONFIG);
            }
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return bitmap;
        } catch (OutOfMemoryError e) {
            return null;
        }
    }

    private void setup() {
        if (!mReady) {
            mSetupPending = true;
            return;
        }
        if (mBitmap == null) {
            return;
        }
        mBitmapShader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP,
                Shader.TileMode.CLAMP);
        mBitmapPaint.setAntiAlias(true);
        mBitmapPaint.setShader(mBitmapShader);
        mArcPaint.setAntiAlias(true);
        mArcPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mArcPaint.setAlpha(122);
        mBitmapHeight = mBitmap.getHeight();
        mBitmapWidth = mBitmap.getWidth();

        mDrawableRect.set(0, 0, getWidth(), getHeight());
        mDrawableRadius = Math.min(mDrawableRect.height() / 2,
                mDrawableRect.width() / 2);
        updateShaderMatrix();
        invalidate();
    }

    private void updateShaderMatrix() {
        float scale;
        mShaderMatrix.set(null);
        if (mBitmapWidth * mDrawableRect.height() > mDrawableRect.width()
                * mBitmapHeight) {
            scale = mDrawableRect.height() / (float) mBitmapHeight;
        } else {
            scale = mDrawableRect.width() / (float) mBitmapWidth;
        }
        mShaderMatrix.setScale(scale, scale);
        mBitmapShader.setLocalMatrix(mShaderMatrix);
    }

    /**
     * 开始倒计时
     */
    public void startCountdown() {
        if (!isCountOver) {
            Toast.makeText(mContext, waitHint, Toast.LENGTH_SHORT).show();
        } else {
            if (curCountDownTime == 0) {
                curCountDownTime = animDuration = countdownTime;
            }
            isCountOver = false;
            ValueAnimator valueA = getValueAnimator();
            valueA.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    timePercent = (Float) animation.getAnimatedValue();
                    invalidate();

                }
            });
            valueA.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    if (curCountDownTime > 0) {
                        isCountOver = false;
                    } else {
                        if (null != countDownTimeListener) {
                            countDownTimeListener.countDownFinish();
                        }
                        curCountDownTime = animDuration;
                        isCountOver = true;
                        handler.removeCallbacks(runnable);
                    }
                }
            });
            valueA.start();
            handler.post(runnable);
        }
    }

    /**
     * 获取值动画
     *
     * @return
     */
    private ValueAnimator getValueAnimator() {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(
                (1 - (float) curCountDownTime / countdownTime), 1.F);
        valueAnimator.setDuration(animDuration * 1000);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setRepeatCount(0);
        return valueAnimator;
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (null != countDownTimeListener) {
                countDownTimeListener.getCurCountDownTime(curCountDownTime);
            }
            curCountDownTime--;
            handler.postDelayed(this, 1000);
        }
    };

    public interface CountDownTimeListener {
        /**
         * 获取当前冷却时间
         * @param time
         */
        void getCurCountDownTime(int time);

        /**
         * 冷却计时结束
         */
        void countDownFinish();
    }
}