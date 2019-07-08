package com.myfitnesspal.feature.progress.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class ProgressCongratsActivity_ViewBinding implements Unbinder {
    private ProgressCongratsActivity target;

    @UiThread
    public ProgressCongratsActivity_ViewBinding(ProgressCongratsActivity progressCongratsActivity) {
        this(progressCongratsActivity, progressCongratsActivity.getWindow().getDecorView());
    }

    @UiThread
    public ProgressCongratsActivity_ViewBinding(ProgressCongratsActivity progressCongratsActivity, View view) {
        this.target = progressCongratsActivity;
        progressCongratsActivity.headerView = Utils.findRequiredView(view, R.id.header, "field 'headerView'");
        progressCongratsActivity.valueText = (TextView) Utils.findRequiredViewAsType(view, R.id.value_text, "field 'valueText'", TextView.class);
        progressCongratsActivity.valueSubtext = (TextView) Utils.findRequiredViewAsType(view, R.id.value_subtext, "field 'valueSubtext'", TextView.class);
        progressCongratsActivity.bodyHeader = (TextView) Utils.findRequiredViewAsType(view, R.id.body_header, "field 'bodyHeader'", TextView.class);
        progressCongratsActivity.bodyText = (TextView) Utils.findRequiredViewAsType(view, R.id.body_text, "field 'bodyText'", TextView.class);
        progressCongratsActivity.sharePhotoButton = Utils.findRequiredView(view, R.id.share_photo_button, "field 'sharePhotoButton'");
        progressCongratsActivity.notNowButton = Utils.findRequiredView(view, R.id.not_now_button, "field 'notNowButton'");
    }

    @CallSuper
    public void unbind() {
        ProgressCongratsActivity progressCongratsActivity = this.target;
        if (progressCongratsActivity != null) {
            this.target = null;
            progressCongratsActivity.headerView = null;
            progressCongratsActivity.valueText = null;
            progressCongratsActivity.valueSubtext = null;
            progressCongratsActivity.bodyHeader = null;
            progressCongratsActivity.bodyText = null;
            progressCongratsActivity.sharePhotoButton = null;
            progressCongratsActivity.notNowButton = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
