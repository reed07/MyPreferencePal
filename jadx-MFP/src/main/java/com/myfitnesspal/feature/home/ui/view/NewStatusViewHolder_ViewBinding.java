package com.myfitnesspal.feature.home.ui.view;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.ui.view.MfpImageView;

public class NewStatusViewHolder_ViewBinding implements Unbinder {
    private NewStatusViewHolder target;

    @UiThread
    public NewStatusViewHolder_ViewBinding(NewStatusViewHolder newStatusViewHolder, View view) {
        this.target = newStatusViewHolder;
        newStatusViewHolder.newStatusContainer = Utils.findRequiredView(view, R.id.new_status_container, "field 'newStatusContainer'");
        newStatusViewHolder.profileImageView = (MfpImageView) Utils.findRequiredViewAsType(view, R.id.profile_image_view, "field 'profileImageView'", MfpImageView.class);
        newStatusViewHolder.cameraButton = Utils.findRequiredView(view, R.id.camera_button, "field 'cameraButton'");
    }

    @CallSuper
    public void unbind() {
        NewStatusViewHolder newStatusViewHolder = this.target;
        if (newStatusViewHolder != null) {
            this.target = null;
            newStatusViewHolder.newStatusContainer = null;
            newStatusViewHolder.profileImageView = null;
            newStatusViewHolder.cameraButton = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
