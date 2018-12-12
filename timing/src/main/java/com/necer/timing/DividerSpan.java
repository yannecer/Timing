package com.necer.timing;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextPaint;
import android.text.style.ReplacementSpan;

/**
 * Created by necer on 2018/12/12.
 */
public class DividerSpan extends ReplacementSpan {

    private boolean isVerticalCenter;
    private float dividerDistance;
    private TextPaint dividerPaint;

    public DividerSpan(float dividerTextSize, int dividerColor, float dividerDistance, boolean isVerticalCenter) {

        this.dividerDistance = dividerDistance;
        this.isVerticalCenter = isVerticalCenter;
        dividerPaint = getDividerPaint(dividerColor, dividerTextSize);
    }

    @Override
    public int getSize(Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fm) {
        text = text.subSequence(start, end);
        return (int) (dividerPaint.measureText(text.toString()) + dividerDistance);
    }

    @Override
    public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
        if (isVerticalCenter) {
            text = text.subSequence(start, end);
            Paint.FontMetricsInt fm = dividerPaint.getFontMetricsInt();
            canvas.drawText(text.toString(), x + dividerDistance / 2, y - ((y + fm.descent + y + fm.ascent) / 2 - (bottom + top) / 2), dividerPaint);    //此处重新计算y坐标，使字体居中
        } else {
            text = text.subSequence(start, end);
            canvas.drawText(text.toString(), x + dividerDistance / 2, y, dividerPaint);
        }
    }


    private TextPaint getDividerPaint(int dividerColor, float dividerTextSize) {
        TextPaint textPaint = new TextPaint();
        textPaint.setColor(dividerColor);
        textPaint.setTextSize(dividerTextSize);
        textPaint.setAntiAlias(true);
        return textPaint;

    }

}
