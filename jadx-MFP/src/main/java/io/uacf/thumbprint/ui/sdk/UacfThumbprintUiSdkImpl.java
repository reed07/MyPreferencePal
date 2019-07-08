package io.uacf.thumbprint.ui.sdk;

import android.content.Context;
import android.support.annotation.RestrictTo;
import com.uacf.identity.sdk.UacfIdentitySdk;
import io.uacf.core.interfaces.UacfClientEventsCallback;
import io.uacf.thumbprint.ui.R;
import io.uacf.thumbprint.ui.internal.email.UacfEmailVerificationActivity;
import io.uacf.thumbprint.ui.internal.email.UacfEmailVerificationActivity.Actions;
import io.uacf.thumbprint.ui.internal.email.UacfEmailVerificationActivity.ExportType;
import io.uacf.thumbprint.ui.sdk.uiconfig.UacfThumbprintAppBarConfig;
import io.uacf.thumbprint.ui.sdk.uiconfig.UacfThumbprintTypefaceConfig;
import io.uacf.thumbprint.ui.sdk.uiconfig.UacfThumbprintUiConfig;
import io.uacf.thumbprint.ui.sdk.uiconfig.UacfThumbprintUiConfig.Builder;

public final class UacfThumbprintUiSdkImpl implements UacfThumbprintUiSdk {
    private ClientEmailVerificationStatus clientEmailVerificationStatus;
    private UacfClientEventsCallback clientEventsCallback;
    private Actions emailVerificationActions;
    private UacfIdentitySdk identitySdk;
    private UacfThumbprintUiConfig uiConfig;

    UacfThumbprintUiSdkImpl(UacfIdentitySdk uacfIdentitySdk, UacfClientEventsCallback uacfClientEventsCallback, UacfThumbprintUiConfig uacfThumbprintUiConfig) {
        this.identitySdk = uacfIdentitySdk;
        this.clientEventsCallback = uacfClientEventsCallback;
        this.uiConfig = uacfThumbprintUiConfig;
        fillUiConfigWithDefaultsIfNecessary();
    }

    public void showEmailVerificationOnFileExport(Context context, ExportType exportType, ClientEmailVerificationStatus clientEmailVerificationStatus2, Actions actions) {
        this.clientEmailVerificationStatus = clientEmailVerificationStatus2;
        this.emailVerificationActions = actions;
        UacfEmailVerificationActivity.showOnFileExport(context, exportType);
    }

    public void showEmailVerificationOnAppLaunch(Context context, ClientEmailVerificationStatus clientEmailVerificationStatus2, Actions actions) {
        this.clientEmailVerificationStatus = clientEmailVerificationStatus2;
        this.emailVerificationActions = actions;
        UacfEmailVerificationActivity.showOnAppLaunch(context);
    }

    @RestrictTo
    public UacfIdentitySdk getIdentitySdk() {
        return this.identitySdk;
    }

    @RestrictTo
    public ClientEmailVerificationStatus getClientEmailVerificationStatus() {
        return this.clientEmailVerificationStatus;
    }

    @RestrictTo
    public Actions getEmailVerificationActions() {
        return this.emailVerificationActions;
    }

    @RestrictTo
    public UacfClientEventsCallback getClientEventsCallback() {
        return this.clientEventsCallback;
    }

    @RestrictTo
    public UacfThumbprintUiConfig getUiConfig() {
        return this.uiConfig;
    }

    private void fillUiConfigWithDefaultsIfNecessary() {
        Builder builder = new Builder();
        UacfThumbprintUiConfig uacfThumbprintUiConfig = this.uiConfig;
        if (uacfThumbprintUiConfig == null || uacfThumbprintUiConfig.getAppBarConfig() == null) {
            builder.setAppBarConfig(new UacfThumbprintAppBarConfig.Builder().setAppBarLayout(R.layout.thumbprint_appbar).setToolbarId(R.id.thumbprint_toolbar).setTitleTextViewId(R.id.thumbprint_toolbar_title).setAppBarShadowEnabled(false).build());
        } else {
            builder.setAppBarConfig(this.uiConfig.getAppBarConfig());
        }
        UacfThumbprintUiConfig uacfThumbprintUiConfig2 = this.uiConfig;
        if (uacfThumbprintUiConfig2 == null || uacfThumbprintUiConfig2.getTypefaceConfig() == null) {
            builder.setTypefaceConfig(new UacfThumbprintTypefaceConfig.Builder().build());
        } else {
            builder.setTypefaceConfig(this.uiConfig.getTypefaceConfig());
        }
        this.uiConfig = builder.build();
    }
}
