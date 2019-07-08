package com.myfitnesspal.shared.ui.graphics;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class TextDrawable extends Drawable {
    private static final int COLOR_BACKGROUND_DEFAULT = -1118482;
    private static final int COLOR_TEXT_DEFAULT = -6381922;
    private static final float textSizeRatio = 0.6f;
    private final int backgroundColor;
    private final int fontSize;
    private final String text;
    private final Paint textPaint;

    public static class Builder {
        private int backgroundColor = TextDrawable.COLOR_BACKGROUND_DEFAULT;
        private int fontSize;
        private String text;
        private int textColor = TextDrawable.COLOR_TEXT_DEFAULT;
        private Typeface textTypeface = Typeface.create(Typeface.DEFAULT, 0);

        public Builder text(String str) {
            this.text = str;
            return this;
        }

        public Builder fontSize(int i) {
            this.fontSize = i;
            return this;
        }

        public Builder textTypeface(Typeface typeface) {
            this.textTypeface = typeface;
            return this;
        }

        public Builder textColor(int i) {
            this.textColor = i;
            return this;
        }

        public Builder backgroundColor(int i) {
            this.backgroundColor = i;
            return this;
        }

        public TextDrawable build() {
            TextDrawable textDrawable = new TextDrawable(this.text, this.fontSize, this.textTypeface, this.textColor, this.backgroundColor);
            return textDrawable;
        }
    }

    public int getOpacity() {
        return -3;
    }

    public void setAlpha(@IntRange int i) {
    }

    public void setColorFilter(@Nullable ColorFilter colorFilter) {
    }

    private TextDrawable(String str, int i, Typeface typeface, int i2, int i3) {
        this.text = str;
        this.fontSize = i;
        this.backgroundColor = i3;
        this.textPaint = new Paint();
        this.textPaint.setAntiAlias(true);
        this.textPaint.setColor(i2);
        this.textPaint.setTypeface(typeface);
        this.textPaint.setStyle(Style.FILL_AND_STROKE);
        this.textPaint.setTextAlign(Align.CENTER);
    }

    public void draw(@NonNull Canvas canvas) {
        drawBackground(canvas);
        drawText(canvas);
    }

    private void drawBackground(@NonNull Canvas canvas) {
        canvas.drawColor(this.backgroundColor);
    }

    private void drawText(@NonNull Canvas canvas) {
        Rect bounds = getBounds();
        int i = this.fontSize;
        if (i <= 0) {
            i = (int) (((float) bounds.height()) * 0.6f);
        }
        this.textPaint.setTextSize((float) i);
        canvas.drawText(this.text, (float) (bounds.width() / 2), ((float) (bounds.height() / 2)) - ((this.textPaint.descent() + this.textPaint.ascent()) / 2.0f), this.textPaint);
    }
}
