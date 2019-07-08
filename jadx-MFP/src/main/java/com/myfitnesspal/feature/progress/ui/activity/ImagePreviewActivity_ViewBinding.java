package com.myfitnesspal.feature.progress.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class ImagePreviewActivity_ViewBinding implements Unbinder {
    private ImagePreviewActivity target;

    @UiThread
    public ImagePreviewActivity_ViewBinding(ImagePreviewActivity imagePreviewActivity) {
        this(imagePreviewActivity, imagePreviewActivity.getWindow().getDecorView());
    }

    @UiThread
    public ImagePreviewActivity_ViewBinding(ImagePreviewActivity imagePreviewActivity, View view) {
        this.target = imagePreviewActivity;
        imagePreviewActivity.imagePreview = (ImageView) Utils.findRequiredViewAsType(view, R.id.image_preview, "field 'imagePreview'", ImageView.class);
    }

    @CallSuper
    public void unbind() {
        ImagePreviewActivity imagePreviewActivity = this.target;
        if (imagePreviewActivity != null) {
            this.target = null;
            imagePreviewActivity.imagePreview = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
