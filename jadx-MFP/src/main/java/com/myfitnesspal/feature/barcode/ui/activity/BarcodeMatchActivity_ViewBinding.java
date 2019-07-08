package com.myfitnesspal.feature.barcode.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.ui.view.ClearableEditText;

public class BarcodeMatchActivity_ViewBinding implements Unbinder {
    private BarcodeMatchActivity target;

    @UiThread
    public BarcodeMatchActivity_ViewBinding(BarcodeMatchActivity barcodeMatchActivity) {
        this(barcodeMatchActivity, barcodeMatchActivity.getWindow().getDecorView());
    }

    @UiThread
    public BarcodeMatchActivity_ViewBinding(BarcodeMatchActivity barcodeMatchActivity, View view) {
        this.target = barcodeMatchActivity;
        barcodeMatchActivity.listView = (ListView) Utils.findRequiredViewAsType(view, R.id.listView, "field 'listView'", ListView.class);
        barcodeMatchActivity.searchEdit = (ClearableEditText) Utils.findRequiredViewAsType(view, R.id.searchEdit, "field 'searchEdit'", ClearableEditText.class);
        barcodeMatchActivity.noMatches = (TextView) Utils.findRequiredViewAsType(view, R.id.noMatches, "field 'noMatches'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        BarcodeMatchActivity barcodeMatchActivity = this.target;
        if (barcodeMatchActivity != null) {
            this.target = null;
            barcodeMatchActivity.listView = null;
            barcodeMatchActivity.searchEdit = null;
            barcodeMatchActivity.noMatches = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
