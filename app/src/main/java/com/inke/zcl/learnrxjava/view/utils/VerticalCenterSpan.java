package com.inke.zcl.learnrxjava.view.utils;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextPaint;
import android.text.style.ReplacementSpan;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.NotNull;

/**
 * Create By chunliangzhang on 2019-09-25
 * Version 1.0
 * Description:
 */
public class VerticalCenterSpan extends ReplacementSpan {

    private float fontSizePx;    //px
    private float offset;

    public VerticalCenterSpan(float fontSizePx, float offset) {
        this.fontSizePx = fontSizePx;
        this.offset = offset;
    }

    @Override
    public int getSize(@NonNull Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fm) {
        if (text == null) {
            return 0;
        }
        text = text.subSequence(start, end);
        Paint textPaint = getCustomTextPaint(paint);
        return (int) textPaint.measureText(text.toString());
    }

    @Override
    public void draw(@NonNull Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, @NotNull Paint paint) {
        if (text == null) {
            return;
        }
        text = text.subSequence(start, end);
        Paint customTextPaint = getCustomTextPaint(paint);
        Paint.FontMetricsInt fm = customTextPaint.getFontMetricsInt();
        // 此处重新计算y坐标，使字体居中
        canvas.drawText(text.toString(), x, y - ((y + fm.descent + y + fm.ascent) / 2 - (bottom + top) / 2 - offset), customTextPaint);
    }

    private TextPaint getCustomTextPaint(Paint srcPaint) {
        TextPaint paint;
        paint = srcPaint == null ? new TextPaint() : new TextPaint(srcPaint);
        paint.setTextSize(fontSizePx);   //设定字体大小, sp转换为px
        return paint;
    }
}
