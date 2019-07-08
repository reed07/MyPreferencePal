package com.myfitnesspal.feature.debug.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class ProgressPhotosDebugActivity_ViewBinding implements Unbinder {
    private ProgressPhotosDebugActivity target;

    @UiThread
    public ProgressPhotosDebugActivity_ViewBinding(ProgressPhotosDebugActivity progressPhotosDebugActivity) {
        this(progressPhotosDebugActivity, progressPhotosDebugActivity.getWindow().getDecorView());
    }

    @UiThread
    public ProgressPhotosDebugActivity_ViewBinding(ProgressPhotosDebugActivity progressPhotosDebugActivity, View view) {
        this.target = progressPhotosDebugActivity;
        progressPhotosDebugActivity.createLocal = Utils.findRequiredView(view, R.id.create_local_button, "field 'createLocal'");
        progressPhotosDebugActivity.sync = Utils.findRequiredView(view, R.id.image_sync_service, "field 'sync'");
        progressPhotosDebugActivity.delete = Utils.findRequiredView(view, R.id.delete, "field 'delete'");
        progressPhotosDebugActivity.showWeightPicker = Utils.findRequiredView(view, R.id.show_weight_picker, "field 'showWeightPicker'");
        progressPhotosDebugActivity.showProgress = Utils.findRequiredView(view, R.id.show_progress, "field 'showProgress'");
        progressPhotosDebugActivity.showGalleryView = Utils.findRequiredView(view, R.id.show_gallery_view, "field 'showGalleryView'");
        progressPhotosDebugActivity.showGalleryImport = Utils.findRequiredView(view, R.id.show_gallery_import, "field 'showGalleryImport'");
        progressPhotosDebugActivity.getMessages = Utils.findRequiredView(view, R.id.get_messages, "field 'getMessages'");
        progressPhotosDebugActivity.markMessages = Utils.findRequiredView(view, R.id.mark_messages, "field 'markMessages'");
        progressPhotosDebugActivity.resetMessages = Utils.findRequiredView(view, R.id.reset_messages, "field 'resetMessages'");
        progressPhotosDebugActivity.introActivity = Utils.findRequiredView(view, R.id.intro_activity, "field 'introActivity'");
        progressPhotosDebugActivity.congratsActivity = Utils.findRequiredView(view, R.id.congrats_activity, "field 'congratsActivity'");
    }

    @CallSuper
    public void unbind() {
        ProgressPhotosDebugActivity progressPhotosDebugActivity = this.target;
        if (progressPhotosDebugActivity != null) {
            this.target = null;
            progressPhotosDebugActivity.createLocal = null;
            progressPhotosDebugActivity.sync = null;
            progressPhotosDebugActivity.delete = null;
            progressPhotosDebugActivity.showWeightPicker = null;
            progressPhotosDebugActivity.showProgress = null;
            progressPhotosDebugActivity.showGalleryView = null;
            progressPhotosDebugActivity.showGalleryImport = null;
            progressPhotosDebugActivity.getMessages = null;
            progressPhotosDebugActivity.markMessages = null;
            progressPhotosDebugActivity.resetMessages = null;
            progressPhotosDebugActivity.introActivity = null;
            progressPhotosDebugActivity.congratsActivity = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
