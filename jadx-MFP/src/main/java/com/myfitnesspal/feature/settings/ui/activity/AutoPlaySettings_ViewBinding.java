package com.myfitnesspal.feature.settings.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Switch;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class AutoPlaySettings_ViewBinding implements Unbinder {
    private AutoPlaySettings target;

    @UiThread
    public AutoPlaySettings_ViewBinding(AutoPlaySettings autoPlaySettings) {
        this(autoPlaySettings, autoPlaySettings.getWindow().getDecorView());
    }

    @UiThread
    public AutoPlaySettings_ViewBinding(AutoPlaySettings autoPlaySettings, View view) {
        this.target = autoPlaySettings;
        autoPlaySettings.newsfeedAdAutoPlaySwitch = (Switch) Utils.findRequiredViewAsType(view, R.id.newsfeed_ad_auto_play, "field 'newsfeedAdAutoPlaySwitch'", Switch.class);
        autoPlaySettings.newsfeedVideoAutoPlaySwitch = (Switch) Utils.findRequiredViewAsType(view, R.id.newsfeed_video_auto_play, "field 'newsfeedVideoAutoPlaySwitch'", Switch.class);
    }

    @CallSuper
    public void unbind() {
        AutoPlaySettings autoPlaySettings = this.target;
        if (autoPlaySettings != null) {
            this.target = null;
            autoPlaySettings.newsfeedAdAutoPlaySwitch = null;
            autoPlaySettings.newsfeedVideoAutoPlaySwitch = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
