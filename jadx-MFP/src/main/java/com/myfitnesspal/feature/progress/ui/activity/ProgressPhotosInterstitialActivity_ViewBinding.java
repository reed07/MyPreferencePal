package com.myfitnesspal.feature.progress.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class ProgressPhotosInterstitialActivity_ViewBinding implements Unbinder {
    private ProgressPhotosInterstitialActivity target;

    @UiThread
    public ProgressPhotosInterstitialActivity_ViewBinding(ProgressPhotosInterstitialActivity progressPhotosInterstitialActivity) {
        this(progressPhotosInterstitialActivity, progressPhotosInterstitialActivity.getWindow().getDecorView());
    }

    @UiThread
    public ProgressPhotosInterstitialActivity_ViewBinding(ProgressPhotosInterstitialActivity progressPhotosInterstitialActivity, View view) {
        this.target = progressPhotosInterstitialActivity;
        progressPhotosInterstitialActivity.okButton = Utils.findRequiredView(view, R.id.ok_button, "field 'okButton'");
    }

    @CallSuper
    public void unbind() {
        ProgressPhotosInterstitialActivity progressPhotosInterstitialActivity = this.target;
        if (progressPhotosInterstitialActivity != null) {
            this.target = null;
            progressPhotosInterstitialActivity.okButton = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
