package com.myfitnesspal.feature.progress.ui.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class ImportPhotoFragment_ViewBinding implements Unbinder {
    private ImportPhotoFragment target;

    @UiThread
    public ImportPhotoFragment_ViewBinding(ImportPhotoFragment importPhotoFragment, View view) {
        this.target = importPhotoFragment;
        importPhotoFragment.imageView = (ImageView) Utils.findRequiredViewAsType(view, R.id.image, "field 'imageView'", ImageView.class);
        importPhotoFragment.datePicker = (TextView) Utils.findRequiredViewAsType(view, R.id.date_picker, "field 'datePicker'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        ImportPhotoFragment importPhotoFragment = this.target;
        if (importPhotoFragment != null) {
            this.target = null;
            importPhotoFragment.imageView = null;
            importPhotoFragment.datePicker = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
