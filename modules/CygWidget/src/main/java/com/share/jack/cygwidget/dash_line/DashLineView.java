package com.share.jack.cygwidget.dash_line;

import android.annotation.SuppressLint;
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

import com.share.jack.cygwidget.R;


/**
 * 虚线控件
 */
public class DashLineView extends View {

    public DashLineView(Context context) {
        super(context);
    }

    public DashLineView(Context context, AttributeSet attrs) {
        super(context, attrs);
        loadAttributes(context, attrs, 0, 0);
    }

    public DashLineView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        loadAttributes(context, attrs, defStyleAttr, 0);
    }

    private void loadAttributes(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.DashLineView, defStyleAttr, defStyleRes);

        dashWidth = array.getDimension(R.styleable.DashLineView_dash_width, 3);
        dashGap = array.getDimension(R.styleable.DashLineView_dash_gap, 3);
        dashColor = array.getColor(R.styleable.DashLineView_dash_color, ContextCompat.getColor(getContext(), android.R.color.darker_gray));
        orientation = array.getInt(R.styleable.DashLineView_dash_orientation, HORIZONTAL);
        array.recycle();

        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(dashColor);
        path = new Path();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    private float dashWidth;//虚线的长度
    private float dashGap;//虚线的间距
    private int dashColor;
    private int orientation;
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;
    private Path path;
    private Paint paint;
    private PathEffect effects;   //用来控制绘制轮廓(线条)的方式

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width;
        int height;
        int size = MeasureSpec.getSize(widthMeasureSpec);
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        if (mode == MeasureSpec.EXACTLY) {
            width = size;
        } else {
            width = getMeasuredWidth();
        }
        size = MeasureSpec.getSize(heightMeasureSpec);
        mode = MeasureSpec.getMode(heightMeasureSpec);
        if (mode == MeasureSpec.EXACTLY) {
            height = size;
        } else {
            height = getMeasuredHeight();
        }
        setMeasuredDimension(width, height);
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (orientation == VERTICAL) {
            path.lineTo(0, this.getHeight());
        } else {
            path.lineTo(this.getWidth(), 0);
        }
        /**
         * DashPathEffect方法中的参数
         * 1、第一个参数是一个float类型的数组, 数组长度>=2并且为偶数,
         * 成对的数组的意义是第一个数代表画多长的实线, 第二个数代表紧跟着后面画多长的空白
         * 2、第二个参数代表偏移量, 指定了绘制虚线的相对起始位置 (其实也就是先画多少空白然后再开始画实线)
         */
        effects = new DashPathEffect(new float[]{dashWidth, dashGap, dashWidth, dashGap}, 0);
        paint.setPathEffect(effects);
        canvas.drawPath(path, paint);
    }
}