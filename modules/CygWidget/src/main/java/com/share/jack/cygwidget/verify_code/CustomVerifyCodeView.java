package com.share.jack.cygwidget.verify_code;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.share.jack.cygwidget.R;


/**
 * Created by jack on 17/3/21
 */

public class CustomVerifyCodeView extends View {

    private float textSize;
    private int textColorEnable;
    private int textColorDisable;
    private Paint paint;

    private String text;
    private Rect rect;
    private int timeCount;
    private String default_text = " s重发";

    private ValueAnimator animator;

    public CustomVerifyCodeView(Context context) {
        this(context, null);
    }

    public CustomVerifyCodeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomVerifyCodeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomVerifyCodeView, defStyleAttr, 0);
        textSize = array.getDimension(R.styleable.CustomVerifyCodeView_cus_text_size, 30.0f);
        timeCount = array.getInt(R.styleable.CustomVerifyCodeView_cus_time_count, 60);
        textColorEnable = array.getColor(R.styleable.CustomVerifyCodeView_cus_text_color_enable, Color.BLACK);
        textColorDisable = array.getColor(R.styleable.CustomVerifyCodeView_cus_text_color_disable, Color.GRAY);
        text = timeCount + "s";
        array.recycle();

        paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (isEnabled()) {
            paint.setColor(textColorEnable);
        } else {
            paint.setColor(textColorDisable);
        }
        paint.setTextSize(textSize);
        rect = new Rect();
        paint.setTextSize(textSize);
        paint.setStrokeWidth(0);
        paint.getTextBounds(text, 0, text.length(), rect);
        Paint.FontMetricsInt fontMetrics = paint.getFontMetricsInt();
        int baseline = (getMeasuredHeight() - fontMetrics.bottom + fontMetrics.top) / 2 - fontMetrics.top;  //获得文字的基准线
        canvas.drawText(text, getMeasuredWidth() / 2 - rect.width() / 2, baseline, paint);
    }

    public void startAnim() {
        setEnabled(false);
        animator = ValueAnimator.ofInt(timeCount, 0);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int time = (int) animation.getAnimatedValue();
                text = time + default_text;
                postInvalidate();
            }
        });
        animator.addListener(new CustomAnimListener() {
            @Override
            public void onAnimationEnd(Animator animation) {
                text = "重发";
                setEnabled(true);
                postInvalidate();
            }
        });
        animator.setDuration(timeCount * 1000);
        animator.setInterpolator(new LinearInterpolator());
        animator.start();
    }

    public float getTextSize() {
        return textSize;
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
    }

    public int getTextColorEnable() {
        return textColorEnable;
    }

    public void setTextColorEnable(int textColorEnable) {
        this.textColorEnable = textColorEnable;
    }

    public int getTextColorDisable() {
        return textColorDisable;
    }

    public void setTextColorDisable(int textColorDisable) {
        this.textColorDisable = textColorDisable;
    }

    public int getTimeCount() {
        return timeCount;
    }

    public void setTimeCount(int timeCount) {
        if (timeCount < 0) {
            throw new IllegalArgumentException("timeCount should not be less than 0");
        }
        this.timeCount = timeCount;
        text = timeCount + "s";
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int size = MeasureSpec.getSize(widthMeasureSpec);
        int mode = MeasureSpec.getMode(widthMeasureSpec);

        int width = 0;
        int height = 0;

        if (mode == MeasureSpec.EXACTLY) {
            width = size;
        }
        size = MeasureSpec.getSize(heightMeasureSpec);
        mode = MeasureSpec.getMode(heightMeasureSpec);

        if (mode == MeasureSpec.EXACTLY) {
            height = size;
        }

        setMeasuredDimension(width, height);
    }
}