package com.myfitnesspal.shared.api.v2;

import com.myfitnesspal.shared.api.ApiConstructorArgs;
import com.myfitnesspal.shared.api.ApiUrlProvider;

public class MfpV2ConfigApiImpl extends MfpV2ApiImplBase<MfpV2ConfigApi> implements MfpV2ConfigApi {
    public MfpV2ConfigApiImpl(ApiConstructorArgs apiConstructorArgs) {
        super(apiConstructorArgs);
    }

    /* access modifiers changed from: protected */
    public String getBaseUrl() {
        return ((ApiUrlProvider) this.apiUrlProvider.get()).getBaseConfigUrl();
    }
}
