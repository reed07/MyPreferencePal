package com.shinobicontrols.charts;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.Typeface;
import com.shinobicontrols.charts.Axis.Position;

public final class ChartUtils {
    private final ar a = new ar();
    private final PointF b = new PointF();
    private final Rect c = new Rect();

    ChartUtils() {
    }

    public static void drawText(Canvas canvas, String str, int i, int i2, Paint paint) {
        int descent = (int) ((paint.descent() + paint.ascent()) / 2.0f);
        if (str != null) {
            String[] split = str.split("\n");
            if (split.length > 1) {
                i2 = (int) (((float) i2) - ((paint.getFontSpacing() / 2.0f) * ((float) (split.length - 1))));
            }
            for (String drawText : split) {
                canvas.drawText(drawText, (float) i, (float) (i2 - descent), paint);
                i2 = (int) (((float) i2) + paint.getFontSpacing());
            }
        }
    }

    public static void drawTextBackground(Canvas canvas, Rect rect, Paint paint) {
        canvas.drawRect(rect, paint);
    }

    /* access modifiers changed from: 0000 */
    public Rect a(int i, int i2, String str, float f, Typeface typeface, v vVar) {
        if (str == null) {
            this.c.set(0, 0, 0, 0);
            return this.c;
        }
        this.a.a(this.b, str, f, typeface, vVar);
        this.c.set(0, 0, (int) (this.b.x + 5.0f), (int) this.b.y);
        Rect rect = this.c;
        rect.offset(i - (rect.width() / 2), i2 - (this.c.height() / 2));
        String[] split = str.split("\n");
        if (split.length > 1) {
            int height = this.c.height() / 2;
            for (int i3 = 1; i3 < split.length; i3++) {
                this.c.top -= height;
                this.c.bottom += height;
            }
        }
        return this.c;
    }

    public static void drawTickMarkLine(Canvas canvas, TickMark tickMark) {
        canvas.drawRect(tickMark.g, tickMark.getLinePaint());
    }

    public static void updateTooltipContent(Tooltip tooltip, DataPoint<?, ?> dataPoint) {
        if (tooltip == null) {
            throw new IllegalArgumentException("Cannot update null tooltip.");
        } else if (dataPoint != null) {
            tooltip.b.a(tooltip, dataPoint);
        } else {
            throw new IllegalArgumentException(tooltip.getContext().getString(R.string.TooltipNullView));
        }
    }

    public static void drawCrosshair(ShinobiChart shinobiChart, Canvas canvas, Rect rect, float f, float f2, float f3, Paint paint) {
        a(canvas, f, f2, f3, paint);
        if (shinobiChart.getCrosshair().d()) {
            a(shinobiChart, canvas, rect, f, f2, f3, paint);
            b(shinobiChart, canvas, rect, f, f2, f3, paint);
        }
    }

    private static void a(Canvas canvas, float f, float f2, float f3, Paint paint) {
        canvas.drawCircle(f, f2, f3, paint);
    }

    private static void a(ShinobiChart shinobiChart, Canvas canvas, Rect rect, float f, float f2, float f3, Paint paint) {
        Crosshair crosshair = shinobiChart.getCrosshair();
        boolean z = (crosshair.f != null ? crosshair.f.getYAxis() : shinobiChart.getYAxis()).d == Position.NORMAL;
        canvas.drawLine(f + (f3 * (z ? -1.0f : 1.0f)), f2, (float) (z ? rect.left : rect.right), f2, paint);
    }

    private static void b(ShinobiChart shinobiChart, Canvas canvas, Rect rect, float f, float f2, float f3, Paint paint) {
        Crosshair crosshair = shinobiChart.getCrosshair();
        boolean z = (crosshair.f != null ? crosshair.f.getXAxis() : shinobiChart.getXAxis()).d == Position.NORMAL;
        canvas.drawLine(f, f2 + (f3 * (z ? 1.0f : -1.0f)), f, (float) (z ? rect.bottom : rect.top), paint);
    }
}
