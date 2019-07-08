package com.myfitnesspal.feature.settings.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.view.ViewGroup;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class SettingsActivity_ViewBinding implements Unbinder {
    private SettingsActivity target;

    @UiThread
    public SettingsActivity_ViewBinding(SettingsActivity settingsActivity) {
        this(settingsActivity, settingsActivity.getWindow().getDecorView());
    }

    @UiThread
    public SettingsActivity_ViewBinding(SettingsActivity settingsActivity, View view) {
        this.target = settingsActivity;
        settingsActivity.accountCard = (ViewGroup) Utils.findRequiredViewAsType(view, R.id.myAccountCard, "field 'accountCard'", ViewGroup.class);
        settingsActivity.settingsCard = (ViewGroup) Utils.findRequiredViewAsType(view, R.id.settingsCard, "field 'settingsCard'", ViewGroup.class);
        settingsActivity.helpCard = (ViewGroup) Utils.findRequiredViewAsType(view, R.id.helpCard, "field 'helpCard'", ViewGroup.class);
        settingsActivity.syncButton = Utils.findRequiredView(view, R.id.syncButton, "field 'syncButton'");
        settingsActivity.syncIcon = Utils.findRequiredView(view, R.id.syncIcon, "field 'syncIcon'");
        settingsActivity.shareCard = Utils.findRequiredView(view, R.id.shareCard, "field 'shareCard'");
    }

    @CallSuper
    public void unbind() {
        SettingsActivity settingsActivity = this.target;
        if (settingsActivity != null) {
            this.target = null;
            settingsActivity.accountCard = null;
            settingsActivity.settingsCard = null;
            settingsActivity.helpCard = null;
            settingsActivity.syncButton = null;
            settingsActivity.syncIcon = null;
            settingsActivity.shareCard = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
