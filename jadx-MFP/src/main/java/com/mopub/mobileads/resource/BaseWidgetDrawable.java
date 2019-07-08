package com.mopub.mobileads.resource;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;

public abstract class BaseWidgetDrawable extends Drawable {
    public int getOpacity() {
        return 0;
    }

    public void setAlpha(int i) {
    }

    public void setColorFilter(ColorFilter colorFilter) {
    }

    /* access modifiers changed from: protected */
    public void drawTextWithinBounds(@NonNull Canvas canvas, @NonNull Paint paint, @NonNull Rect rect, @NonNull String str) {
        paint.getTextBounds(str, 0, str.length(), rect);
        canvas.drawText(str, (float) getBounds().centerX(), ((float) getBounds().centerY()) + (((paint.descent() - paint.ascent()) / 2.0f) - paint.descent()), paint);
    }
}
