package com.myfitnesspal.feature.settings.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.CheckedTextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class FacebookSettings_ViewBinding implements Unbinder {
    private FacebookSettings target;

    @UiThread
    public FacebookSettings_ViewBinding(FacebookSettings facebookSettings) {
        this(facebookSettings, facebookSettings.getWindow().getDecorView());
    }

    @UiThread
    public FacebookSettings_ViewBinding(FacebookSettings facebookSettings, View view) {
        this.target = facebookSettings;
        facebookSettings.chkFindMe = (CheckedTextView) Utils.findRequiredViewAsType(view, R.id.chk_find_me, "field 'chkFindMe'", CheckedTextView.class);
        facebookSettings.progress = Utils.findRequiredView(view, R.id.progress, "field 'progress'");
        facebookSettings.disconnectFacebookButton = Utils.findRequiredView(view, R.id.disconnect_facebook, "field 'disconnectFacebookButton'");
        facebookSettings.connectFacebookButton = Utils.findRequiredView(view, R.id.connect_facebook, "field 'connectFacebookButton'");
    }

    @CallSuper
    public void unbind() {
        FacebookSettings facebookSettings = this.target;
        if (facebookSettings != null) {
            this.target = null;
            facebookSettings.chkFindMe = null;
            facebookSettings.progress = null;
            facebookSettings.disconnectFacebookButton = null;
            facebookSettings.connectFacebookButton = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
