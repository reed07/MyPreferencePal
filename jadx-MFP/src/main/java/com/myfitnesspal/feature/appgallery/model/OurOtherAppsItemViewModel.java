package com.myfitnesspal.feature.appgallery.model;

import android.content.Context;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.model.v2.MfpPlatformApp;
import com.uacf.core.util.Strings;
import com.uacf.taskrunner.Runner;

public class OurOtherAppsItemViewModel extends ViewModelWithCtaButton {
    private MfpPlatformApp app;
    private Context context;

    public static int getPlaceholderColor() {
        return R.color.white;
    }

    public static int getPlaceholderDrawable() {
        return R.drawable.app_gallery_placeholder;
    }

    public OurOtherAppsItemViewModel(Context context2, MfpPlatformApp mfpPlatformApp, Runner runner) {
        super(runner);
        this.context = context2.getApplicationContext();
        this.app = mfpPlatformApp;
        refreshCtaMode();
    }

    public void refresh() {
        refreshCtaMode();
    }

    public String getIconUri() {
        if (this.app.getIconImage() != null) {
            return this.app.getIconImage().getFilename();
        }
        return null;
    }

    public String getName() {
        return Strings.toString(this.app.getName());
    }

    public MfpPlatformApp getApp() {
        return this.app;
    }

    /* access modifiers changed from: protected */
    public Context getContext() {
        return this.context;
    }
}
