package com.shinobicontrols.charts;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView;
import com.shinobicontrols.charts.PropertyChangedEvent.Handler;

@SuppressLint({"ViewConstructor"})
public class Tooltip extends FrameLayout {
    final bz a = new bz();
    dh b;
    private View c;
    private CartesianSeries<?> d;
    private Data<?, ?> e;
    private final al f = new al();
    private final GradientDrawable g = new GradientDrawable();

    Tooltip(Context context) {
        super(context);
        this.c = new DefaultTooltipView(context);
        addView(this.c, new LayoutParams(-2, -2, 17));
        setVisibility(8);
        this.b = dh.a(this);
    }

    public CartesianSeries<?> getTrackedSeries() {
        return this.d;
    }

    /* access modifiers changed from: 0000 */
    public void a(CartesianSeries<?> cartesianSeries) {
        this.d = cartesianSeries;
        this.b = dh.a(this);
    }

    public Data<?, ?> getCenter() {
        return this.e;
    }

    public void setCenter(Data<?, ?> data) {
        this.e = data;
        if (data == null || data.getX() == null || data.getY() == null) {
            throw new IllegalArgumentException(getContext().getString(R.string.TooltipNullXOrYInCenterPoint));
        }
        e();
    }

    /* access modifiers changed from: 0000 */
    public void a(CrosshairStyle crosshairStyle) {
        if (crosshairStyle != null && (this.c instanceof DefaultTooltipView)) {
            a((View) this, crosshairStyle);
            a(this.c, crosshairStyle, 0, 9);
        }
    }

    public void forceLayout() {
        super.forceLayout();
        this.c.forceLayout();
    }

    /* access modifiers changed from: 0000 */
    public void a(CartesianSeries<?> cartesianSeries, DataPoint<?, ?> dataPoint, DataPoint<?, ?> dataPoint2, DataPoint<?, ?> dataPoint3) {
        setCenter(cartesianSeries.l.a(dataPoint, dataPoint2, dataPoint3, cartesianSeries.getChart().getCrosshair().b));
        ChartUtils.updateTooltipContent(this, cartesianSeries.l.b(dataPoint, dataPoint2, dataPoint3, cartesianSeries.getChart().getCrosshair().b));
    }

    private void e() {
        if (this.d != null) {
            Data<?, ?> data = this.e;
            if (data != null) {
                this.a.b = Crosshair.a(data.getX(), this.d.getXAxis(), this.d);
                this.a.c = Crosshair.a(this.e.getY(), this.d.getYAxis(), this.d);
                return;
            }
            throw new IllegalStateException(getContext().getString(R.string.TooltipNullCenter));
        }
    }

    public View getView() {
        return this.c;
    }

    /* access modifiers changed from: 0000 */
    public void a() {
        e();
    }

    private void a(View view, CrosshairStyle crosshairStyle, int i, int i2) {
        while (i < i2) {
            a((TextView) ((DefaultTooltipView) view).getChildAt(i), crosshairStyle);
            i++;
        }
    }

    private void a(TextView textView, CrosshairStyle crosshairStyle) {
        textView.setTextColor(crosshairStyle.getTooltipTextColor());
        textView.setTypeface(crosshairStyle.getTooltipTypeface());
        textView.setTextSize(2, crosshairStyle.getTooltipTextSize());
        textView.setBackgroundColor(crosshairStyle.getTooltipLabelBackgroundColor());
    }

    private void a(View view, CrosshairStyle crosshairStyle) {
        float f2 = view.getResources().getDisplayMetrics().density;
        int a2 = at.a(f2, crosshairStyle.getTooltipPadding());
        view.setPadding(a2, a2, a2, a2);
        this.g.setColor(crosshairStyle.getTooltipBackgroundColor());
        this.g.setStroke(at.a(f2, crosshairStyle.getTooltipBorderWidth()), crosshairStyle.getTooltipBorderColor());
        this.g.setCornerRadius((float) at.a(f2, crosshairStyle.getTooltipCornerRadius()));
        a.a(view, (Drawable) this.g);
    }

    public void setView(View view) {
        if (view != null) {
            removeView(this.c);
            this.c = view;
            addView(view);
            this.f.a(new PropertyChangedEvent());
            return;
        }
        throw new IllegalArgumentException(getContext().getString(R.string.TooltipNullView));
    }

    /* access modifiers changed from: 0000 */
    public am a(Handler handler) {
        return this.f.a(PropertyChangedEvent.a, (a) handler);
    }

    /* access modifiers changed from: 0000 */
    public void b() {
        setVisibility(8);
    }

    /* access modifiers changed from: 0000 */
    public void c() {
        setVisibility(0);
    }

    /* access modifiers changed from: 0000 */
    public void d() {
        setVisibility(8);
    }
}
