package com.myfitnesspal.feature.barcode.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.myfitnesspal.android.R;
import com.uacf.core.util.Debouncer;

public class ScannerLiveViewParent extends FrameLayout {
    private static final int MEASURE_DEBOUNCE_DURATION_MILLIS = 100;
    /* access modifiers changed from: private */
    public int height;
    final Debouncer<Void> onMeasureDebouncer = new Debouncer<Void>(100) {
        /* access modifiers changed from: protected */
        public void onDebounced(Void voidR) {
            ScannerLiveView scannerLiveView = (ScannerLiveView) ScannerLiveViewParent.this.findViewById(R.id.scanner_live_view);
            if (scannerLiveView != null) {
                scannerLiveView.onContainerSizeChanged(ScannerLiveViewParent.this.width, ScannerLiveViewParent.this.height);
            }
        }
    };
    /* access modifiers changed from: private */
    public int width;

    public ScannerLiveViewParent(Context context) {
        super(context);
    }

    public ScannerLiveViewParent(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ScannerLiveViewParent(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.height = getMeasuredHeight();
        this.width = getMeasuredWidth();
        this.onMeasureDebouncer.call();
    }
}
