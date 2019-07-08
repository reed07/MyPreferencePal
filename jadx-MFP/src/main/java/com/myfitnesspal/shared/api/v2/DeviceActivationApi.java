package com.myfitnesspal.shared.api.v2;

import com.myfitnesspal.shared.api.ApiConstructorArgs;
import com.myfitnesspal.shared.api.MfpApiSettings;
import com.uacf.core.mapping.Mapper2;
import dagger.Lazy;

public class DeviceActivationApi extends MfpV2ApiImpl {
    private Lazy<MfpApiSettings> apiSettings;

    public DeviceActivationApi(ApiConstructorArgs apiConstructorArgs, Lazy<MfpApiSettings> lazy) {
        super(apiConstructorArgs);
        this.apiSettings = lazy;
    }

    /* access modifiers changed from: protected */
    public String getBaseUrl() {
        return ((MfpApiSettings) this.apiSettings.get()).getDeviceActivationEnvironment();
    }

    public DeviceActivationApi withMapper(Mapper2<?, String> mapper2) {
        super.withMapper((Mapper2) mapper2);
        return this;
    }
}
