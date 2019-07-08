package com.myfitnesspal.feature.progress.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class AddWeightImageImportActivity_ViewBinding implements Unbinder {
    private AddWeightImageImportActivity target;

    @UiThread
    public AddWeightImageImportActivity_ViewBinding(AddWeightImageImportActivity addWeightImageImportActivity) {
        this(addWeightImageImportActivity, addWeightImageImportActivity.getWindow().getDecorView());
    }

    @UiThread
    public AddWeightImageImportActivity_ViewBinding(AddWeightImageImportActivity addWeightImageImportActivity, View view) {
        this.target = addWeightImageImportActivity;
        addWeightImageImportActivity.imported_image = (ImageView) Utils.findRequiredViewAsType(view, R.id.imported_image, "field 'imported_image'", ImageView.class);
    }

    @CallSuper
    public void unbind() {
        AddWeightImageImportActivity addWeightImageImportActivity = this.target;
        if (addWeightImageImportActivity != null) {
            this.target = null;
            addWeightImageImportActivity.imported_image = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
