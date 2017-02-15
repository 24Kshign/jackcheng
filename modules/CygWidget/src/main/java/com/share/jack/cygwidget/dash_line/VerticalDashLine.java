package com.share.jack.cygwidget.dash_line;

import android.content.Context;
import android.graphics.Path;
import android.util.AttributeSet;

/**
 * 虚线控件-纵向
 */
public class VerticalDashLine extends BaseDashLine {

    public VerticalDashLine(Context context) {
        super(context);
    }

    public VerticalDashLine(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VerticalDashLine(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    protected Path path() {
        Path path = new Path();
        path.moveTo(getWidth(), this.getTop());
        path.lineTo(getWidth(), this.getBottom());
        return path;
    }
}
