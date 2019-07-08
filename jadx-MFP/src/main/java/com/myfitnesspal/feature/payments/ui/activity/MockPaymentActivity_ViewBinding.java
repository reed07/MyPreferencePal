package com.myfitnesspal.feature.payments.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class MockPaymentActivity_ViewBinding implements Unbinder {
    private MockPaymentActivity target;

    @UiThread
    public MockPaymentActivity_ViewBinding(MockPaymentActivity mockPaymentActivity) {
        this(mockPaymentActivity, mockPaymentActivity.getWindow().getDecorView());
    }

    @UiThread
    public MockPaymentActivity_ViewBinding(MockPaymentActivity mockPaymentActivity, View view) {
        this.target = mockPaymentActivity;
        mockPaymentActivity.successButton = Utils.findRequiredView(view, R.id.button_successful, "field 'successButton'");
        mockPaymentActivity.cancelButton = Utils.findRequiredView(view, R.id.button_cancel, "field 'cancelButton'");
        mockPaymentActivity.failButton = Utils.findRequiredView(view, R.id.button_fail_with_error, "field 'failButton'");
        mockPaymentActivity.errorCodeEdit = (EditText) Utils.findRequiredViewAsType(view, R.id.error_code_edit, "field 'errorCodeEdit'", EditText.class);
    }

    @CallSuper
    public void unbind() {
        MockPaymentActivity mockPaymentActivity = this.target;
        if (mockPaymentActivity != null) {
            this.target = null;
            mockPaymentActivity.successButton = null;
            mockPaymentActivity.cancelButton = null;
            mockPaymentActivity.failButton = null;
            mockPaymentActivity.errorCodeEdit = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
