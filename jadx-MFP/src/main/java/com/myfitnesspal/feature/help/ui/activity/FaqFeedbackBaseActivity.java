package com.myfitnesspal.feature.help.ui.activity;

import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import com.myfitnesspal.shared.api.ApiUrlProvider;
import com.myfitnesspal.shared.constants.SharedConstants.Http;
import com.myfitnesspal.shared.service.install.CountryService;
import com.myfitnesspal.shared.ui.activity.impl.FullScreenWebView;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import java.util.UUID;
import javax.inject.Inject;
import javax.inject.Named;

public abstract class FaqFeedbackBaseActivity extends FullScreenWebView {
    @Inject
    ApiUrlProvider apiUrlProvider;
    @Inject
    CountryService countryService;
    @Inject
    @Named("deviceUUID")
    UUID deviceId;
    @Inject
    @Named("appVersionCode")
    long versionCode;
    @Inject
    @Named("appVersionName")
    String versionName;

    public abstract String getBaseUrlRelativePath();

    public abstract String getUrl();

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
    }

    /* access modifiers changed from: protected */
    public void reloadPage() {
        String url = getUrl();
        if (Strings.notEmpty(url)) {
            ViewUtils.setVisible(true, this.browser);
            ViewUtils.setVisible(false, this.offlineMessage);
            loadUrlWithAuthHeaders(url);
            return;
        }
        ViewUtils.setVisible(false, this.browser);
        ViewUtils.setVisible(true, this.offlineMessage);
    }

    /* access modifiers changed from: protected */
    public Builder getBaseUrlBuilder() {
        Builder appendQueryParameter = Uri.parse(this.apiUrlProvider.getBaseUrlForWebsite(getBaseUrlRelativePath())).buildUpon().appendQueryParameter("locale", this.countryService.getCurrentLocaleIdentifier()).appendQueryParameter("device_id", Strings.toString(this.deviceId));
        String str = Http.HARDWARE;
        StringBuilder sb = new StringBuilder();
        sb.append(Build.MANUFACTURER);
        sb.append(" ");
        sb.append(Build.MODEL);
        return appendQueryParameter.appendQueryParameter(str, sb.toString()).appendQueryParameter(Http.OS, "android").appendQueryParameter("version", VERSION.RELEASE).appendQueryParameter("app_version", Strings.toString(this.versionName)).appendQueryParameter(Http.BUILD_NUMBER, Strings.toString(Long.valueOf(this.versionCode)));
    }
}
