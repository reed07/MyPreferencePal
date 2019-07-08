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

public class StatusUpdateActivity_ViewBinding implements Unbinder {
    private StatusUpdateActivity target;

    @UiThread
    public StatusUpdateActivity_ViewBinding(StatusUpdateActivity statusUpdateActivity) {
        this(statusUpdateActivity, statusUpdateActivity.getWindow().getDecorView());
    }

    @UiThread
    public StatusUpdateActivity_ViewBinding(StatusUpdateActivity statusUpdateActivity, View view) {
        this.target = statusUpdateActivity;
        statusUpdateActivity.statusUpdateContainer = Utils.findRequiredView(view, R.id.status_update_container, "field 'statusUpdateContainer'");
        statusUpdateActivity.headerContainer = Utils.findRequiredView(view, R.id.rlHeader, "field 'headerContainer'");
        statusUpdateActivity.shareArtifact = (ImageView) Utils.findRequiredViewAsType(view, R.id.ivShareArtifact, "field 'shareArtifact'", ImageView.class);
        statusUpdateActivity.shareStatus = (EditText) Utils.findRequiredViewAsType(view, R.id.etShareStatus, "field 'shareStatus'", EditText.class);
        statusUpdateActivity.statusShareWith = (TextView) Utils.findRequiredViewAsType(view, R.id.tvStatusShareWith, "field 'statusShareWith'", TextView.class);
        statusUpdateActivity.mealNotesHint = Utils.findRequiredView(view, R.id.llMealNotesHint, "field 'mealNotesHint'");
        statusUpdateActivity.instagram = Utils.findRequiredView(view, R.id.instagram_button, "field 'instagram'");
        statusUpdateActivity.facebook = Utils.findRequiredView(view, R.id.facebook_button, "field 'facebook'");
        statusUpdateActivity.saveToGalleryButton = Utils.findRequiredView(view, R.id.save_to_gallery_button, "field 'saveToGalleryButton'");
        statusUpdateActivity.moreButton = Utils.findRequiredView(view, R.id.more_button, "field 'moreButton'");
        statusUpdateActivity.buttonContainer = Utils.findRequiredView(view, R.id.button_container, "field 'buttonContainer'");
        statusUpdateActivity.shareWithContainer = Utils.findRequiredView(view, R.id.share_with_container, "field 'shareWithContainer'");
    }

    @CallSuper
    public void unbind() {
        StatusUpdateActivity statusUpdateActivity = this.target;
        if (statusUpdateActivity != null) {
            this.target = null;
            statusUpdateActivity.statusUpdateContainer = null;
            statusUpdateActivity.headerContainer = null;
            statusUpdateActivity.shareArtifact = null;
            statusUpdateActivity.shareStatus = null;
            statusUpdateActivity.statusShareWith = null;
            statusUpdateActivity.mealNotesHint = null;
            statusUpdateActivity.instagram = null;
            statusUpdateActivity.facebook = null;
            statusUpdateActivity.saveToGalleryButton = null;
            statusUpdateActivity.moreButton = null;
            statusUpdateActivity.buttonContainer = null;
            statusUpdateActivity.shareWithContainer = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
