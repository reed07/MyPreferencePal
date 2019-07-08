package com.myfitnesspal.shared.api.v1;

import com.myfitnesspal.shared.api.ApiUrlProvider;
import com.myfitnesspal.shared.service.syncv1.packets.request.ActionRequestPacket;
import com.myfitnesspal.shared.service.syncv1.packets.request.ApiRequestPacket;

public class MfpActionApiImpl extends MfpInformationOrActionApiImplBase<MfpActionApi> implements MfpActionApi {
    public MfpActionApiImpl(ApiBinaryConstructorArgs apiBinaryConstructorArgs) {
        super(apiBinaryConstructorArgs);
    }

    /* access modifiers changed from: protected */
    public ApiRequestPacket getBasePacket() {
        return new ActionRequestPacket();
    }

    /* access modifiers changed from: protected */
    public String getBaseUrl() {
        return ((ApiUrlProvider) this.apiUrlProvider.get()).getActionRequestUrl();
    }
}
