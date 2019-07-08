package com.myfitnesspal.feature.profile.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.View.MeasureSpec;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.android.R;

public class ProgressGraphView extends View {
    private static final int INNER_STROKE_DP = 5;
    private static final int OUTER_STROKE_DP = 10;
    private Rect bounds = new Rect();
    private Paint innerPaint;
    private float innerStrokePx;
    private Paint outerPaint;
    private float outerStrokePx;
    private RectF ovalF = new RectF();
    private float progress = BitmapDescriptorFactory.HUE_RED;

    public ProgressGraphView(Context context) {
        super(context);
        init();
    }

    public ProgressGraphView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public ProgressGraphView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int i3;
        int i4;
        if (MeasureSpec.getMode(i2) == 1073741824) {
            i4 = MeasureSpec.getSize(i2);
            i3 = i4 * 2;
        } else {
            i3 = MeasureSpec.getSize(i);
            i4 = i3 / 2;
        }
        setMeasuredDimension(MeasureSpec.makeMeasureSpec(i3, 1073741824), MeasureSpec.makeMeasureSpec(i4, 1073741824));
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float f = this.outerStrokePx;
        float f2 = f / 2.0f;
        float f3 = f * 1.5f;
        canvas.getClipBounds(this.bounds);
        this.ovalF.left = ((float) this.bounds.left) + f3;
        this.ovalF.right = ((float) this.bounds.right) - f3;
        this.ovalF.top = (((float) this.bounds.top) + f3) - f2;
        RectF rectF = this.ovalF;
        rectF.bottom = ((rectF.top + ((float) (this.bounds.height() * 2))) - (f3 * 2.0f)) - f2;
        canvas.drawArc(this.ovalF, 180.0f, 180.0f, false, this.innerPaint);
        canvas.drawArc(this.ovalF, 180.0f, this.progress * 180.0f, false, this.outerPaint);
    }

    public void setProgress(float f) {
        this.progress = (float) Math.min(1.0d, Math.max(0.0d, (double) f));
        invalidate();
    }

    private void init() {
        Context context = getContext();
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        this.innerStrokePx = TypedValue.applyDimension(1, 5.0f, displayMetrics);
        this.outerStrokePx = TypedValue.applyDimension(1, 10.0f, displayMetrics);
        this.innerPaint = new Paint();
        this.innerPaint.setStyle(Style.STROKE);
        this.innerPaint.setStrokeWidth(this.innerStrokePx);
        this.innerPaint.setStrokeCap(Cap.ROUND);
        this.innerPaint.setColor(ContextCompat.getColor(context, R.color.progress_graph_track));
        this.innerPaint.setAntiAlias(true);
        this.outerPaint = new Paint();
        this.outerPaint.setStyle(Style.STROKE);
        this.outerPaint.setStrokeWidth(this.outerStrokePx);
        this.outerPaint.setStrokeCap(Cap.ROUND);
        this.outerPaint.setColor(ContextCompat.getColor(context, R.color.progress_graph_fill));
        this.outerPaint.setAntiAlias(true);
    }
}
