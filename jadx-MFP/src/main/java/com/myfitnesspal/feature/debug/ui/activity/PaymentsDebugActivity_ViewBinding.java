package com.myfitnesspal.feature.debug.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.ui.view.LinearLayoutListAdapterView;

public class PaymentsDebugActivity_ViewBinding implements Unbinder {
    private PaymentsDebugActivity target;

    @UiThread
    public PaymentsDebugActivity_ViewBinding(PaymentsDebugActivity paymentsDebugActivity) {
        this(paymentsDebugActivity, paymentsDebugActivity.getWindow().getDecorView());
    }

    @UiThread
    public PaymentsDebugActivity_ViewBinding(PaymentsDebugActivity paymentsDebugActivity, View view) {
        this.target = paymentsDebugActivity;
        paymentsDebugActivity.listView = (LinearLayoutListAdapterView) Utils.findRequiredViewAsType(view, R.id.list_view, "field 'listView'", LinearLayoutListAdapterView.class);
    }

    @CallSuper
    public void unbind() {
        PaymentsDebugActivity paymentsDebugActivity = this.target;
        if (paymentsDebugActivity != null) {
            this.target = null;
            paymentsDebugActivity.listView = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
