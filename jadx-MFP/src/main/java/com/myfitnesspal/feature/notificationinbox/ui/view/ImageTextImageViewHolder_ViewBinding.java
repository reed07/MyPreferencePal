package com.myfitnesspal.feature.notificationinbox.ui.view;

import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class ImageTextImageViewHolder_ViewBinding extends BaseNotificationViewHolder_ViewBinding {
    private ImageTextImageViewHolder target;

    @UiThread
    public ImageTextImageViewHolder_ViewBinding(ImageTextImageViewHolder imageTextImageViewHolder, View view) {
        super(imageTextImageViewHolder, view);
        this.target = imageTextImageViewHolder;
        imageTextImageViewHolder.notifSecondImage = (ImageView) Utils.findRequiredViewAsType(view, R.id.notif_second_image, "field 'notifSecondImage'", ImageView.class);
    }

    public void unbind() {
        ImageTextImageViewHolder imageTextImageViewHolder = this.target;
        if (imageTextImageViewHolder != null) {
            this.target = null;
            imageTextImageViewHolder.notifSecondImage = null;
            super.unbind();
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
