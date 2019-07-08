package com.myfitnesspal.feature.progress.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class AddWeightActivity_ViewBinding implements Unbinder {
    private AddWeightActivity target;

    @UiThread
    public AddWeightActivity_ViewBinding(AddWeightActivity addWeightActivity) {
        this(addWeightActivity, addWeightActivity.getWindow().getDecorView());
    }

    @UiThread
    public AddWeightActivity_ViewBinding(AddWeightActivity addWeightActivity, View view) {
        this.target = addWeightActivity;
        addWeightActivity.tvWeightWithUnit = (TextView) Utils.findRequiredViewAsType(view, R.id.weight_with_unit, "field 'tvWeightWithUnit'", TextView.class);
        addWeightActivity.etProgressWeight = (EditText) Utils.findRequiredViewAsType(view, R.id.progress_weight, "field 'etProgressWeight'", EditText.class);
        addWeightActivity.tvProgressDate = (TextView) Utils.findRequiredViewAsType(view, R.id.progress_date, "field 'tvProgressDate'", TextView.class);
        addWeightActivity.ivProgressImage = (ImageView) Utils.findRequiredViewAsType(view, R.id.progress_image, "field 'ivProgressImage'", ImageView.class);
        addWeightActivity.vSeparatorWeight = Utils.findRequiredView(view, R.id.separator_weight, "field 'vSeparatorWeight'");
        addWeightActivity.vRowWeight = Utils.findRequiredView(view, R.id.row_weight, "field 'vRowWeight'");
        addWeightActivity.vRowWeightLbs = Utils.findRequiredView(view, R.id.row_weight_lbs, "field 'vRowWeightLbs'");
        addWeightActivity.etProgressWeightLbs = (EditText) Utils.findRequiredViewAsType(view, R.id.progress_weight_lbs, "field 'etProgressWeightLbs'", EditText.class);
    }

    @CallSuper
    public void unbind() {
        AddWeightActivity addWeightActivity = this.target;
        if (addWeightActivity != null) {
            this.target = null;
            addWeightActivity.tvWeightWithUnit = null;
            addWeightActivity.etProgressWeight = null;
            addWeightActivity.tvProgressDate = null;
            addWeightActivity.ivProgressImage = null;
            addWeightActivity.vSeparatorWeight = null;
            addWeightActivity.vRowWeight = null;
            addWeightActivity.vRowWeightLbs = null;
            addWeightActivity.etProgressWeightLbs = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
