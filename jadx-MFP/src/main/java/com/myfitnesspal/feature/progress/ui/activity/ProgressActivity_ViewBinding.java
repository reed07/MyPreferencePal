package com.myfitnesspal.feature.progress.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ListView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class ProgressActivity_ViewBinding implements Unbinder {
    private ProgressActivity target;

    @UiThread
    public ProgressActivity_ViewBinding(ProgressActivity progressActivity) {
        this(progressActivity, progressActivity.getWindow().getDecorView());
    }

    @UiThread
    public ProgressActivity_ViewBinding(ProgressActivity progressActivity, View view) {
        this.target = progressActivity;
        progressActivity.measurementButton = Utils.findRequiredView(view, R.id.button_measurement_type, "field 'measurementButton'");
        progressActivity.periodButton = Utils.findRequiredView(view, R.id.button_time_period, "field 'periodButton'");
        progressActivity.entryListView = (ListView) Utils.findRequiredViewAsType(view, R.id.entries_list, "field 'entryListView'", ListView.class);
    }

    @CallSuper
    public void unbind() {
        ProgressActivity progressActivity = this.target;
        if (progressActivity != null) {
            this.target = null;
            progressActivity.measurementButton = null;
            progressActivity.periodButton = null;
            progressActivity.entryListView = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
