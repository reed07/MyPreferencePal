package com.myfitnesspal.shared.api.v1;

import com.myfitnesspal.shared.api.ApiUrlProvider;
import com.myfitnesspal.shared.service.syncv1.packets.request.SyncFirstRequestPacket;
import com.uacf.core.exception.UacfNotImplementedException;
import okhttp3.OkHttpClient;

public class MfpSearchApiImpl extends MfpBinaryApiImpl<MfpSearchApi> implements MfpSearchApi {
    public MfpSearchApiImpl(ApiBinaryConstructorArgs apiBinaryConstructorArgs) {
        super(apiBinaryConstructorArgs);
    }

    /* access modifiers changed from: protected */
    public String getBaseUrl() {
        return ((ApiUrlProvider) this.apiUrlProvider.get()).getOnlineSearchUrl();
    }

    /* access modifiers changed from: protected */
    public OkHttpClient client() {
        insertPacket(0, new SyncFirstRequestPacket(4));
        return super.client();
    }

    public MfpSearchApi treatErrorCodeAsSuccess(int... iArr) {
        throw new UacfNotImplementedException();
    }
}
