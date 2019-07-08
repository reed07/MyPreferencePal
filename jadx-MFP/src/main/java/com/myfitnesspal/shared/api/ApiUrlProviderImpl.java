package com.myfitnesspal.shared.api;

import android.net.Uri.Builder;
import com.myfitnesspal.shared.api.auth.AuthTokenProvider;
import com.myfitnesspal.shared.constants.SharedConstants.Http;
import com.myfitnesspal.shared.constants.SharedConstants.Uri;
import com.myfitnesspal.shared.service.install.CountryService;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.util.HashMap;
import java.util.Map;

public class ApiUrlProviderImpl implements ApiUrlProvider {
    Lazy<AuthTokenProvider> authTokenProvider;
    String clientId;
    private final Lazy<CountryService> countryService;
    String guestAccessToken;
    private final Lazy<MfpApiSettings> mfpApiSettings;

    public ApiUrlProviderImpl(Lazy<MfpApiSettings> lazy, Lazy<CountryService> lazy2, Lazy<AuthTokenProvider> lazy3, String str, String str2) {
        this.mfpApiSettings = lazy;
        this.countryService = lazy2;
        this.authTokenProvider = lazy3;
        this.clientId = str;
        this.guestAccessToken = str2;
    }

    public void setWebServiceBaseUrl(String str) {
        ((MfpApiSettings) this.mfpApiSettings.get()).setV1Endpoint(str);
    }

    public String getWebServiceBaseUrl() {
        return getUrl(((MfpApiSettings) this.mfpApiSettings.get()).getV1Endpoint(), null);
    }

    public void setBaseUrlForWebsite(String str) {
        ((MfpApiSettings) this.mfpApiSettings.get()).setWebsiteEndpoint(str);
    }

    public String getBaseUrlForWebsite(String str) {
        return getUrl(((MfpApiSettings) this.mfpApiSettings.get()).getWebsiteEndpoint(), str);
    }

    public String getBaseUrlForBlog() {
        return getUrl(((MfpApiSettings) this.mfpApiSettings.get()).getBlogEndpoint(), (String) null, false);
    }

    public String getBaseUrlForBlog(String str) {
        return getUrl(((MfpApiSettings) this.mfpApiSettings.get()).getBlogEndpoint(), str, false);
    }

    public String getWebServiceUrl(String str) {
        return getUrl(((MfpApiSettings) this.mfpApiSettings.get()).getV1Endpoint(), str);
    }

    public void setApiV2BaseUrl(String str) {
        ((MfpApiSettings) this.mfpApiSettings.get()).setV2Endpoint(str);
    }

    public String getApiV2BaseUrl() {
        return getUrl(((MfpApiSettings) this.mfpApiSettings.get()).getV2Endpoint(), (String) null, false);
    }

    public String getActionRequestUrl() {
        return getWebServiceUrl("iphone_api/synchronize");
    }

    public String getClearDeviceTokenUrl() {
        return getWebServiceUrl(Uri.CLEAR_DEVICE_TOKEN);
    }

    public String getDiagnosticUploadUrl(String str) {
        return getWebServiceUrl("iphone_api/diagnostic_upload", "code", str);
    }

    public String getForgotPasswordUrl(String str) {
        return getWebServiceUrl("iphone_api/forgot_password", Http.USERNAME_OR_EMAIL, str);
    }

    public String getInformationRequestUrl() {
        return getWebServiceUrl("iphone_api/synchronize");
    }

    public String getCheckDiagnosticStatusUrl(String str) {
        return getUrl(((MfpApiSettings) this.mfpApiSettings.get()).getV1Endpoint(), "iphone_api/check_diagnostic_status", "code", str);
    }

    public String getOnlineSearchUrl() {
        return getWebServiceUrl("iphone_api/online_search");
    }

    public String getSyncUrl() {
        return getWebServiceUrl("iphone_api/synchronize");
    }

    public String getOAuth2Url() {
        return getBaseUrlForWebsite("/oauth2/authorize");
    }

    public String getAccountRestrictedUrl() {
        return getBaseUrlForWebsite(Uri.ACCOUNT_RESTRICTED);
    }

    public String getBaseConfigUrl() {
        return getUrl(((MfpApiSettings) this.mfpApiSettings.get()).getConfigEndpoint(), (String) null, false);
    }

    public void setBaseConfigUrl(String str) {
        ((MfpApiSettings) this.mfpApiSettings.get()).setConfigEndpoint(str);
    }

    public Map<String, String> getCredentials(AuthTokenProvider authTokenProvider2) {
        HashMap hashMap = new HashMap();
        hashMap.put(Http.MFP_CLIENT_ID, Strings.toString(this.clientId));
        if (!Strings.notEmpty(authTokenProvider2.getAccessToken()) || !Strings.notEmpty(authTokenProvider2.getDomainUserId())) {
            hashMap.put("Authorization", String.format(Http.BEARER_AUTH_FORMAT, new Object[]{Strings.toString(this.guestAccessToken)}));
        } else {
            String deviceId = authTokenProvider2.getDeviceId();
            StringBuilder sb = new StringBuilder();
            sb.append("Bearer ");
            sb.append(authTokenProvider2.getAccessToken());
            hashMap.put("Authorization", sb.toString());
            hashMap.put(Http.MFP_USER_ID, Strings.toString(authTokenProvider2.getDomainUserId()));
            if (Strings.notEmpty(deviceId)) {
                hashMap.put(Http.MFP_DEVICE_ID, deviceId);
            }
        }
        return hashMap;
    }

    public Map<String, String> getCredentials() {
        return getCredentials((AuthTokenProvider) this.authTokenProvider.get());
    }

    private String getWebServiceUrl(String str, Object... objArr) {
        return getUrl(((MfpApiSettings) this.mfpApiSettings.get()).getV1Endpoint(), str, objArr);
    }

    private Builder getUriBuilder(String str, String str2) {
        return getUriBuilder(str, str2, true);
    }

    private Builder getUriBuilder(String str, String str2, boolean z) {
        return getUriBuilder(str, z).path(Strings.toString(str2));
    }

    private Builder getUriBuilder(String str, boolean z) {
        Builder buildUpon = android.net.Uri.parse(Strings.toString(str)).buildUpon();
        if (z) {
            buildUpon.appendQueryParameter(Http.LANG, ((CountryService) this.countryService.get()).getCurrentLocaleIdentifier());
        }
        return buildUpon;
    }

    private String getUrl(String str, String str2) {
        return getUrl(str, str2, true);
    }

    private String getUrl(String str, String str2, boolean z) {
        return getUriBuilder(str, str2, z).toString();
    }

    private String getUrl(String str, String str2, Object... objArr) {
        Builder uriBuilder = getUriBuilder(str, Strings.toString(str2));
        int length = objArr != null ? objArr.length : 0;
        if (length > 0) {
            for (int i = 0; i < length; i += 2) {
                uriBuilder.appendQueryParameter(Strings.toString(objArr[i]), Strings.toString(objArr[i + 1]));
            }
        }
        return uriBuilder.toString();
    }
}
