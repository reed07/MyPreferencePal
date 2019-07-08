package com.myfitnesspal.shared.ui.dialog;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class EditTextBaseDialogFragment_ViewBinding implements Unbinder {
    private EditTextBaseDialogFragment target;

    @UiThread
    public EditTextBaseDialogFragment_ViewBinding(EditTextBaseDialogFragment editTextBaseDialogFragment, View view) {
        this.target = editTextBaseDialogFragment;
        editTextBaseDialogFragment.editText = (TextView) Utils.findRequiredViewAsType(view, R.id.edit_text, "field 'editText'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        EditTextBaseDialogFragment editTextBaseDialogFragment = this.target;
        if (editTextBaseDialogFragment != null) {
            this.target = null;
            editTextBaseDialogFragment.editText = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
