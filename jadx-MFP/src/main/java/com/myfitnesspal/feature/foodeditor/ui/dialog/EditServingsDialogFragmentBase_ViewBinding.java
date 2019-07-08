package com.myfitnesspal.feature.foodeditor.ui.dialog;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class EditServingsDialogFragmentBase_ViewBinding implements Unbinder {
    private EditServingsDialogFragmentBase target;

    @UiThread
    public EditServingsDialogFragmentBase_ViewBinding(EditServingsDialogFragmentBase editServingsDialogFragmentBase, View view) {
        this.target = editServingsDialogFragmentBase;
        editServingsDialogFragmentBase.servingSizeSpinner = (Spinner) Utils.findRequiredViewAsType(view, R.id.serving_size, "field 'servingSizeSpinner'", Spinner.class);
        editServingsDialogFragmentBase.noServingsEditText = (EditText) Utils.findRequiredViewAsType(view, R.id.numOfServings, "field 'noServingsEditText'", EditText.class);
        editServingsDialogFragmentBase.servingsOfTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.txtServingsOf, "field 'servingsOfTextView'", TextView.class);
        editServingsDialogFragmentBase.invalidServings = Utils.findRequiredView(view, R.id.invalid_serving, "field 'invalidServings'");
    }

    @CallSuper
    public void unbind() {
        EditServingsDialogFragmentBase editServingsDialogFragmentBase = this.target;
        if (editServingsDialogFragmentBase != null) {
            this.target = null;
            editServingsDialogFragmentBase.servingSizeSpinner = null;
            editServingsDialogFragmentBase.noServingsEditText = null;
            editServingsDialogFragmentBase.servingsOfTextView = null;
            editServingsDialogFragmentBase.invalidServings = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
