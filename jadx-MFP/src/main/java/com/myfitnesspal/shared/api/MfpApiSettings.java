package com.myfitnesspal.shared.api;

import com.uacf.core.mock.interceptor.legacy.MockProvider;

public interface MfpApiSettings extends MockProvider {
    String getAPIToken();

    String getBlogEndpoint();

    String getConfigEndpoint();

    String getConsentsEndpoint();

    String getDeviceActivationEnvironment();

    String getIdmEndpoint();

    String getNISEndpoint();

    String getSyncV2Endpoint();

    String getV1Endpoint();

    String getV2Endpoint();

    String getWebsiteEndpoint();

    void setBlogEndpoint(String str);

    void setConfigEndpoint(String str);

    void setConsentsEndpoint(String str);

    void setCurrentMock(String str);

    void setDeviceActivationEnvironment(String str);

    void setIdmEndpoint(String str);

    void setNISEndpoint(String str);

    void setSyncV2Endpoint(String str);

    void setV1Endpoint(String str);

    void setV2Endpoint(String str);

    void setWebsiteEndpoint(String str);
}
