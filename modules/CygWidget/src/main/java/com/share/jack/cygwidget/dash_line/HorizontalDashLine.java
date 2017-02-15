package com.share.jack.cygwidget.dash_line;

import android.content.Context;
import android.graphics.Path;
import android.util.AttributeSet;

/**
 * 虚线控件-横向
 */
public class HorizontalDashLine extends BaseDashLine {

    public HorizontalDashLine(Context context) {
        super(context);
    }

    public HorizontalDashLine(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HorizontalDashLine(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    protected Path path() {
        Path path = new Path();
        path.moveTo(this.getLeft(), getHeight());
        path.lineTo(this.getRight(), getHeight());
        return path;
    }
}
