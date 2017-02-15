package com.share.jack.cygwidget.dash_line;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.jack.mc.cyg.cygtools.util.CygDisplayMetrics;
import com.share.jack.cygwidget.R;


/**
 * 虚线控件基类
 */
public abstract class BaseDashLine extends View {

    protected abstract Path path();

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public BaseDashLine(Context context) {
        super(context);
    }

    public BaseDashLine(Context context, AttributeSet attrs) {
        super(context, attrs);
        loadAttributes(context, attrs, 0, 0);
    }

    public BaseDashLine(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        loadAttributes(context, attrs, defStyleAttr, 0);
    }

    private void loadAttributes(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        TypedArray typedArray = context.obtainStyledAttributes(
                attrs, R.styleable.DashLine, defStyleAttr, defStyleRes);

        dashHeight = typedArray.getFloat(R.styleable.DashLine_dash_height, (float) 1);
        dashWidth = typedArray.getFloat(R.styleable.DashLine_dash_width, 6);
        dashGap = typedArray.getFloat(R.styleable.DashLine_dash_gap, 4);
        dashColor = typedArray.getColor(R.styleable.DashLine_dash_color, ContextCompat.getColor(getContext(), android.R.color.darker_gray));

        typedArray.recycle();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    private float dashWidth;//虚线的长度
    private float dashHeight;//虚线的高度
    private float dashGap;//虚线的间距
    private int dashColor;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = measureSize(widthMeasureSpec);
        int height = measureSize(heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    private int measureSize(int measureSpec) {
        int result;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        if (mode == MeasureSpec.EXACTLY) {
            result = size;
        } else {
            result = CygDisplayMetrics.dp2px(getContext(), dashHeight);
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(dashColor);
        paint.setStrokeWidth(CygDisplayMetrics.dp2px(getContext(), dashHeight));
        PathEffect effects = new DashPathEffect(new float[]{CygDisplayMetrics.dp2px(getContext(), dashWidth), CygDisplayMetrics.dp2px(getContext(), dashGap)}, 0);
        paint.setPathEffect(effects);
        canvas.drawPath(path(), paint);
    }
}
