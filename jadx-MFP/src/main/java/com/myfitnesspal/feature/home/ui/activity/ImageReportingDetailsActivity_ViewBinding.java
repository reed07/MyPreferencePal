package com.myfitnesspal.feature.home.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class ImageReportingDetailsActivity_ViewBinding implements Unbinder {
    private ImageReportingDetailsActivity target;

    @UiThread
    public ImageReportingDetailsActivity_ViewBinding(ImageReportingDetailsActivity imageReportingDetailsActivity) {
        this(imageReportingDetailsActivity, imageReportingDetailsActivity.getWindow().getDecorView());
    }

    @UiThread
    public ImageReportingDetailsActivity_ViewBinding(ImageReportingDetailsActivity imageReportingDetailsActivity, View view) {
        this.target = imageReportingDetailsActivity;
        imageReportingDetailsActivity.tvReasonType = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_reason_type, "field 'tvReasonType'", TextView.class);
        imageReportingDetailsActivity.tvReasons = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_reasons, "field 'tvReasons'", TextView.class);
        imageReportingDetailsActivity.tvDontAllow = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_dont_allow, "field 'tvDontAllow'", TextView.class);
        imageReportingDetailsActivity.btnReportImage = (Button) Utils.findRequiredViewAsType(view, R.id.btn_report_image, "field 'btnReportImage'", Button.class);
    }

    @CallSuper
    public void unbind() {
        ImageReportingDetailsActivity imageReportingDetailsActivity = this.target;
        if (imageReportingDetailsActivity != null) {
            this.target = null;
            imageReportingDetailsActivity.tvReasonType = null;
            imageReportingDetailsActivity.tvReasons = null;
            imageReportingDetailsActivity.tvDontAllow = null;
            imageReportingDetailsActivity.btnReportImage = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
