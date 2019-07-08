package com.myfitnesspal.feature.barcode.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.barcode.ui.view.LiveView;
import com.myfitnesspal.feature.barcode.ui.view.Overlay;

public class BarcodeScannerActivity_ViewBinding implements Unbinder {
    private BarcodeScannerActivity target;

    @UiThread
    public BarcodeScannerActivity_ViewBinding(BarcodeScannerActivity barcodeScannerActivity) {
        this(barcodeScannerActivity, barcodeScannerActivity.getWindow().getDecorView());
    }

    @UiThread
    public BarcodeScannerActivity_ViewBinding(BarcodeScannerActivity barcodeScannerActivity, View view) {
        this.target = barcodeScannerActivity;
        barcodeScannerActivity.rootView = Utils.findRequiredView(view, R.id.scanner, "field 'rootView'");
        barcodeScannerActivity.scannerOverlay = (Overlay) Utils.findRequiredViewAsType(view, R.id.scanner_overlay, "field 'scannerOverlay'", Overlay.class);
        barcodeScannerActivity.scannerLiveView = (LiveView) Utils.findRequiredViewAsType(view, R.id.scanner_live_view, "field 'scannerLiveView'", LiveView.class);
        barcodeScannerActivity.manualEntryEdit = (EditText) Utils.findOptionalViewAsType(view, R.id.manual_entry_edit, "field 'manualEntryEdit'", EditText.class);
    }

    @CallSuper
    public void unbind() {
        BarcodeScannerActivity barcodeScannerActivity = this.target;
        if (barcodeScannerActivity != null) {
            this.target = null;
            barcodeScannerActivity.rootView = null;
            barcodeScannerActivity.scannerOverlay = null;
            barcodeScannerActivity.scannerLiveView = null;
            barcodeScannerActivity.manualEntryEdit = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
