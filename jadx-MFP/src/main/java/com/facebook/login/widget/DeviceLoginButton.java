package com.facebook.login.widget;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import com.facebook.login.DeviceLoginManager;
import com.facebook.login.LoginBehavior;
import com.facebook.login.LoginManager;

public class DeviceLoginButton extends LoginButton {
    private Uri deviceRedirectUri;

    private class DeviceLoginClickListener extends LoginClickListener {
        private DeviceLoginClickListener() {
            super();
        }

        /* access modifiers changed from: protected */
        public LoginManager getLoginManager() {
            DeviceLoginManager instance = DeviceLoginManager.getInstance();
            instance.setDefaultAudience(DeviceLoginButton.this.getDefaultAudience());
            instance.setLoginBehavior(LoginBehavior.DEVICE_AUTH);
            instance.setDeviceRedirectUri(DeviceLoginButton.this.getDeviceRedirectUri());
            return instance;
        }
    }

    public DeviceLoginButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public DeviceLoginButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public DeviceLoginButton(Context context) {
        super(context);
    }

    public void setDeviceRedirectUri(Uri uri) {
        this.deviceRedirectUri = uri;
    }

    public Uri getDeviceRedirectUri() {
        return this.deviceRedirectUri;
    }

    /* access modifiers changed from: protected */
    public LoginClickListener getNewLoginClickListener() {
        return new DeviceLoginClickListener();
    }
}
