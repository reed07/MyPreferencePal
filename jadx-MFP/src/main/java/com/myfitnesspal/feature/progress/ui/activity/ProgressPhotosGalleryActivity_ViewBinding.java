package com.myfitnesspal.feature.progress.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.view.ViewGroup;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class ProgressPhotosGalleryActivity_ViewBinding implements Unbinder {
    private ProgressPhotosGalleryActivity target;

    @UiThread
    public ProgressPhotosGalleryActivity_ViewBinding(ProgressPhotosGalleryActivity progressPhotosGalleryActivity) {
        this(progressPhotosGalleryActivity, progressPhotosGalleryActivity.getWindow().getDecorView());
    }

    @UiThread
    public ProgressPhotosGalleryActivity_ViewBinding(ProgressPhotosGalleryActivity progressPhotosGalleryActivity, View view) {
        this.target = progressPhotosGalleryActivity;
        progressPhotosGalleryActivity.imagesContainer = (ViewGroup) Utils.findRequiredViewAsType(view, R.id.images_container, "field 'imagesContainer'", ViewGroup.class);
        progressPhotosGalleryActivity.leftImageContainer = (ViewGroup) Utils.findRequiredViewAsType(view, R.id.left_image_container, "field 'leftImageContainer'", ViewGroup.class);
        progressPhotosGalleryActivity.leftImageDetails = (ViewGroup) Utils.findRequiredViewAsType(view, R.id.left_image_details, "field 'leftImageDetails'", ViewGroup.class);
        progressPhotosGalleryActivity.rightImageContainer = (ViewGroup) Utils.findRequiredViewAsType(view, R.id.right_image_container, "field 'rightImageContainer'", ViewGroup.class);
        progressPhotosGalleryActivity.rightImageDetails = (ViewGroup) Utils.findRequiredViewAsType(view, R.id.right_image_details, "field 'rightImageDetails'", ViewGroup.class);
        progressPhotosGalleryActivity.singleImageButton = Utils.findRequiredView(view, R.id.single_image, "field 'singleImageButton'");
        progressPhotosGalleryActivity.splitImageButton = Utils.findRequiredView(view, R.id.split_image, "field 'splitImageButton'");
    }

    @CallSuper
    public void unbind() {
        ProgressPhotosGalleryActivity progressPhotosGalleryActivity = this.target;
        if (progressPhotosGalleryActivity != null) {
            this.target = null;
            progressPhotosGalleryActivity.imagesContainer = null;
            progressPhotosGalleryActivity.leftImageContainer = null;
            progressPhotosGalleryActivity.leftImageDetails = null;
            progressPhotosGalleryActivity.rightImageContainer = null;
            progressPhotosGalleryActivity.rightImageDetails = null;
            progressPhotosGalleryActivity.singleImageButton = null;
            progressPhotosGalleryActivity.splitImageButton = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
