package android.support.v4.graphics;

import android.graphics.Rect;
import android.support.v4.util.Pair;

public final class PaintCompat {
    private static final ThreadLocal<Pair<Rect, Rect>> sRectThreadLocal = new ThreadLocal<>();

    private PaintCompat() {
    }
}
