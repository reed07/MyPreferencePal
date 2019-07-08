package com.myfitnesspal.feature.meals.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.View.MeasureSpec;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.android.R;

public class MacroWheel extends View {
    private static final int DEFAULT_SIZE_PX = 68;
    private static final float DEFAULT_STROKE_WIDTH_PX = 2.5f;
    private static final int MINIMUM_PERCENT = 4;
    private static final int OFFSET = 270;
    private Rect bounds;
    private RectF boundsF;
    private float carbs = 0.34f;
    private Paint carbsPaint;
    private Paint clearPaint;
    private Bitmap defaultBitmap = null;
    private boolean disabled;
    private Paint disabledPaint;
    private Path dividerLine;
    private float fat = 0.33f;
    private Paint fatPaint;
    private Paint fillPaint;
    private float gapWidth;
    private float protein = 0.33f;
    private Paint proteinPaint;
    private float strokeWidth;

    public MacroWheel(Context context) {
        super(context);
        init();
    }

    public MacroWheel(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setupAttributes(attributeSet);
        init();
    }

    public MacroWheel(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setupAttributes(attributeSet);
        init();
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Bitmap bitmap = this.defaultBitmap;
        if (bitmap != null) {
            bitmap.recycle();
            this.defaultBitmap = null;
        }
    }

    public void setFillColor(int i) {
        if (i != 0) {
            this.fillPaint = new Paint();
            this.fillPaint.setColor(getResources().getColor(i));
            this.fillPaint.setStrokeWidth(this.strokeWidth);
            this.fillPaint.setStyle(Style.FILL);
            this.fillPaint.setAntiAlias(true);
            return;
        }
        this.fillPaint = null;
    }

    public void setValues(int i, int i2, int i3) {
        if (i2 + i3 + i != 100) {
            this.disabled = true;
        } else {
            this.disabled = false;
            if (i > 0 && i < 4) {
                i = 4;
            }
            if (i2 > 0 && i2 < 4) {
                i2 = 4;
            }
            if (i3 > 0 && i3 < 4) {
                i3 = 4;
            }
            float f = (float) (i2 + i + i3);
            this.carbs = ((float) i) / f;
            this.fat = ((float) i2) / f;
            this.protein = ((float) i3) / f;
        }
        invalidate();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.getClipBounds(this.bounds);
        Bitmap drawToBitmap = drawToBitmap(this.bounds);
        if (this.fillPaint != null) {
            canvas.drawCircle((float) this.bounds.centerX(), (float) this.bounds.centerY(), (((this.boundsF.right - this.boundsF.left) + this.strokeWidth) - 0.5f) / 2.0f, this.fillPaint);
        }
        canvas.drawBitmap(drawToBitmap, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, null);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int min = Math.min(MeasureSpec.getSize(i), MeasureSpec.getSize(i2));
        if (min <= 0) {
            min = 68;
        }
        int makeMeasureSpec = MeasureSpec.makeMeasureSpec(min, 1073741824);
        setMeasuredDimension(makeMeasureSpec, makeMeasureSpec);
    }

    private void setupAttributes(AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(attributeSet, R.styleable.MacroWheel, 0, 0);
        float applyDimension = TypedValue.applyDimension(1, DEFAULT_STROKE_WIDTH_PX, getContext().getResources().getDisplayMetrics());
        try {
            this.strokeWidth = obtainStyledAttributes.getDimension(1, applyDimension);
            this.gapWidth = obtainStyledAttributes.getDimension(0, applyDimension);
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    private Bitmap drawToBitmap(Rect rect) {
        int i;
        float f;
        float f2;
        Bitmap bitmap = this.defaultBitmap;
        if (bitmap == null || !(bitmap.getWidth() == getWidth() || this.defaultBitmap.getHeight() == getHeight())) {
            Bitmap bitmap2 = this.defaultBitmap;
            if (bitmap2 != null) {
                bitmap2.recycle();
            }
            this.defaultBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Config.ARGB_8888);
        }
        Canvas canvas = new Canvas(this.defaultBitmap);
        this.boundsF.set(rect);
        this.boundsF.left += this.strokeWidth / 2.0f;
        this.boundsF.right -= this.strokeWidth / 2.0f;
        this.boundsF.top += this.strokeWidth / 2.0f;
        this.boundsF.bottom -= this.strokeWidth / 2.0f;
        if (this.disabled) {
            canvas.drawArc(this.boundsF, 270.0f, 360.0f, false, this.disabledPaint);
        } else {
            float f3 = this.carbs * 360.0f;
            float f4 = this.fat * 360.0f;
            float f5 = this.protein * 360.0f;
            float f6 = 270.0f;
            if (f3 > BitmapDescriptorFactory.HUE_RED) {
                canvas.drawArc(this.boundsF, 270.0f, f3, false, this.carbsPaint);
                f = f3 + 270.0f;
                i = 1;
            } else {
                f = 270.0f;
                i = 0;
            }
            if (f4 > BitmapDescriptorFactory.HUE_RED) {
                canvas.drawArc(this.boundsF, f, f4, false, this.fatPaint);
                i++;
                f2 = f + f4;
            } else {
                f2 = f;
            }
            if (f5 > BitmapDescriptorFactory.HUE_RED) {
                canvas.drawArc(this.boundsF, f2, f5, false, this.proteinPaint);
                i++;
            }
            if (i > 1) {
                if (((double) f3) > 0.0d) {
                    f6 = 270.0f + f3;
                    eraseLineFromCenter(canvas, f6);
                }
                if (((double) f4) > 0.0d) {
                    f6 += f4;
                    eraseLineFromCenter(canvas, f6);
                }
                if (((double) f5) > 0.0d) {
                    eraseLineFromCenter(canvas, f6 + f5);
                }
            }
        }
        return this.defaultBitmap;
    }

    private void eraseLineFromCenter(Canvas canvas, float f) {
        double radians = (double) ((float) Math.toRadians((double) f));
        float round = (float) ((int) Math.round(((double) this.bounds.centerY()) + (((double) this.bounds.height()) * Math.cos(radians))));
        float round2 = (float) ((int) Math.round(((double) this.bounds.centerX()) + (((double) this.bounds.width()) * Math.sin(radians))));
        this.dividerLine.reset();
        this.dividerLine.moveTo((float) this.bounds.centerX(), (float) this.bounds.centerY());
        this.dividerLine.lineTo(round, round2);
        canvas.drawPath(this.dividerLine, this.clearPaint);
    }

    private Paint initPaint(int i) {
        Paint paint = new Paint();
        paint.setColor(getResources().getColor(i));
        paint.setStrokeWidth(this.strokeWidth);
        paint.setStyle(Style.STROKE);
        paint.setAntiAlias(true);
        return paint;
    }

    private void resetClearPaint() {
        this.clearPaint = new Paint();
        this.clearPaint.setStyle(Style.STROKE);
        this.clearPaint.setStrokeWidth(this.gapWidth);
        this.clearPaint.setColor(0);
        this.clearPaint.setXfermode(new PorterDuffXfermode(Mode.CLEAR));
        this.clearPaint.setAntiAlias(true);
    }

    private void init() {
        this.dividerLine = new Path();
        this.bounds = new Rect();
        this.boundsF = new RectF();
        this.carbsPaint = initPaint(R.color.macro_wheel_carbs);
        this.fatPaint = initPaint(R.color.macro_wheel_fat);
        this.proteinPaint = initPaint(R.color.macro_wheel_protein);
        this.disabledPaint = initPaint(R.color.macro_wheel_disabled);
        resetClearPaint();
    }
}
