package com.myfitnesspal.shared.api;

import com.myfitnesspal.shared.api.auth.AuthTokenProvider;
import java.util.Map;

public interface ApiUrlProvider {
    String getAccountRestrictedUrl();

    String getActionRequestUrl();

    String getApiV2BaseUrl();

    String getBaseConfigUrl();

    String getBaseUrlForBlog();

    String getBaseUrlForBlog(String str);

    String getBaseUrlForWebsite(String str);

    String getCheckDiagnosticStatusUrl(String str);

    String getClearDeviceTokenUrl();

    Map<String, String> getCredentials();

    Map<String, String> getCredentials(AuthTokenProvider authTokenProvider);

    String getDiagnosticUploadUrl(String str);

    String getForgotPasswordUrl(String str);

    String getInformationRequestUrl();

    String getOAuth2Url();

    String getOnlineSearchUrl();

    String getSyncUrl();

    String getWebServiceBaseUrl();

    String getWebServiceUrl(String str);

    void setApiV2BaseUrl(String str);

    void setBaseConfigUrl(String str);

    void setBaseUrlForWebsite(String str);

    void setWebServiceBaseUrl(String str);
}
