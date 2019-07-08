package com.myfitnesspal.feature.barcode.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.text.SpannableStringBuilder;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.uacf.core.util.Ln;

public class ScannerOverlay extends View implements Overlay {
    private static final float LASER_LINE_WIDTH_160_DPI = 7.0f;
    private SpannableStringBuilder mBarcode = new SpannableStringBuilder();
    private Paint mBarcodeLinePaint;
    private Rect mBarcodeLineRect = new Rect(-1, -1, -1, -1);
    private int mBarcodeOmnidirLineEndX;
    private int mBarcodeOmnidirLineEndY;
    private float mBarcodeOmnidirLineLeft;
    private float mBarcodeOmnidirLineRight;
    private int mBarcodeOmnidirLineStartX;
    private int mBarcodeOmnidirLineStartY;
    private Rect mCanvasClipRect = new Rect();
    private Paint mLaserLinePaint;
    private Rect mLaserLineRect = new Rect();
    private int mLaserPosition;
    private float mScreenDensity;
    private Point mScreenResolution;

    public ScannerOverlay(Context context) {
        super(context);
        initialize(context);
    }

    public ScannerOverlay(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initialize(context);
    }

    public ScannerOverlay(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initialize(context);
    }

    private void initialize(Context context) {
        Ln.d("initialize()", new Object[0]);
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        this.mScreenResolution = new Point(defaultDisplay.getWidth(), defaultDisplay.getHeight());
        this.mScreenDensity = getContext().getResources().getDisplayMetrics().density;
        this.mLaserPosition = Math.round((float) (this.mScreenResolution.y / 2));
        float f = this.mScreenDensity * LASER_LINE_WIDTH_160_DPI;
        this.mLaserLinePaint = new Paint();
        this.mLaserLinePaint.setStyle(Style.FILL);
        this.mLaserLinePaint.setColor(-65536);
        this.mLaserLinePaint.setAlpha(255);
        this.mLaserLinePaint.setStrokeWidth(f);
        this.mBarcodeLinePaint = new Paint();
        this.mBarcodeLinePaint.setStyle(Style.FILL);
        this.mBarcodeLinePaint.setColor(-16711936);
        this.mBarcodeLinePaint.setAlpha(255);
        this.mBarcodeLinePaint.setStrokeWidth(f);
        this.mBarcode.clear();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        Ln.d("onDraw()", new Object[0]);
        Matrix matrix = canvas.getMatrix();
        if (canvas.getClipBounds(this.mCanvasClipRect)) {
            if (this.mCanvasClipRect.height() == canvas.getHeight()) {
                Ln.d("draw full canvas", new Object[0]);
                int round = Math.round(this.mScreenDensity * LASER_LINE_WIDTH_160_DPI);
                int i = this.mLaserPosition;
                this.mLaserLineRect.set(this.mCanvasClipRect.width() / 8, i - (round / 2), (this.mCanvasClipRect.width() * 7) / 8, i + ((round - 1) / 2));
                this.mBarcodeLineRect.top = this.mLaserLineRect.top;
                this.mBarcodeLineRect.bottom = this.mLaserLineRect.bottom;
            }
            if (this.mBarcodeOmnidirLineStartX >= 0) {
                canvas.save();
                canvas.setMatrix(matrix);
                canvas.drawLine((float) this.mBarcodeOmnidirLineStartX, (float) this.mBarcodeOmnidirLineStartY, (float) this.mBarcodeOmnidirLineEndX, (float) this.mBarcodeOmnidirLineEndY, this.mLaserLinePaint);
                float f = this.mBarcodeOmnidirLineLeft;
                if (f >= BitmapDescriptorFactory.HUE_RED) {
                    float f2 = this.mBarcodeOmnidirLineRight;
                    if (f2 >= BitmapDescriptorFactory.HUE_RED) {
                        int i2 = this.mBarcodeOmnidirLineStartX;
                        float f3 = (float) i2;
                        int i3 = this.mBarcodeOmnidirLineEndX;
                        float f4 = f3 + (((float) (i3 - i2)) * f);
                        int i4 = this.mBarcodeOmnidirLineStartY;
                        float f5 = (float) i4;
                        int i5 = this.mBarcodeOmnidirLineEndY;
                        canvas.drawLine(f4, (((float) (i5 - i4)) * f) + f5, ((float) i2) + (((float) (i3 - i2)) * f2), ((float) i4) + (((float) (i5 - i4)) * f2), this.mBarcodeLinePaint);
                    }
                }
                canvas.restore();
            } else {
                canvas.drawRect(this.mLaserLineRect, this.mLaserLinePaint);
                if (this.mBarcodeLineRect.left >= 0 && this.mBarcodeLineRect.right >= 0) {
                    canvas.drawRect(this.mBarcodeLineRect, this.mBarcodeLinePaint);
                }
            }
        }
    }

    public float getNormalizedLaserLinePosition() {
        return ((float) this.mLaserPosition) / ((float) this.mScreenResolution.y);
    }

    public void setNormalizedBarcodeLocation(float f, float f2) {
        this.mBarcodeOmnidirLineStartX = -1;
        int i = this.mScreenResolution.x;
        float f3 = (float) i;
        this.mBarcodeLineRect.left = Math.round(f * f3);
        this.mBarcodeLineRect.right = Math.round(f2 * f3);
        postInvalidate(0, this.mLaserLineRect.top, this.mScreenResolution.x - 1, this.mLaserLineRect.bottom);
    }

    public void setNormalizedBarcodeLocation(PointF pointF, PointF pointF2, float f, float f2) {
        if (pointF.x >= BitmapDescriptorFactory.HUE_RED) {
            int i = this.mScreenResolution.x;
            int i2 = this.mScreenResolution.y;
            float f3 = (float) i;
            this.mBarcodeOmnidirLineStartX = Math.round(pointF.x * f3);
            float f4 = (float) i2;
            this.mBarcodeOmnidirLineStartY = Math.round(pointF.y * f4);
            this.mBarcodeOmnidirLineEndX = Math.round(pointF2.x * f3);
            this.mBarcodeOmnidirLineEndY = Math.round(pointF2.y * f4);
            this.mBarcodeOmnidirLineLeft = f;
            this.mBarcodeOmnidirLineRight = f2;
            postInvalidate();
            return;
        }
        setNormalizedBarcodeLocation(f, f2);
    }

    public void setNoBarcodeLocation() {
        Rect rect = this.mBarcodeLineRect;
        rect.left = -1;
        rect.right = -1;
        postInvalidate(this.mLaserLineRect.left, 0, this.mLaserLineRect.right, this.mScreenResolution.y - 1);
    }

    public void setBarcode(String str) {
        synchronized (this.mBarcode) {
            this.mBarcode.replace(0, this.mBarcode.length(), str);
        }
        postInvalidate();
    }
}
