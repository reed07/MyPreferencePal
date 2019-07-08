package com.myfitnesspal.shared.api.v1;

import com.myfitnesspal.shared.api.ApiUrlProvider;
import com.myfitnesspal.shared.service.syncv1.packets.request.ApiRequestPacket;
import com.uacf.core.exception.UacfNotImplementedException;

public class MfpSyncApiImpl extends MfpBinaryApiImplBase<MfpSyncApi> implements MfpSyncApi {
    public MfpSyncApi addPacket(ApiRequestPacket apiRequestPacket) {
        return this;
    }

    public MfpSyncApi insertPacket(int i, ApiRequestPacket apiRequestPacket) {
        return this;
    }

    public MfpSyncApiImpl(ApiBinaryConstructorArgs apiBinaryConstructorArgs) {
        super(apiBinaryConstructorArgs);
    }

    /* access modifiers changed from: protected */
    public String getBaseUrl() {
        return ((ApiUrlProvider) this.apiUrlProvider.get()).getSyncUrl();
    }

    public MfpSyncApi treatErrorCodeAsSuccess(int... iArr) {
        throw new UacfNotImplementedException();
    }
}
