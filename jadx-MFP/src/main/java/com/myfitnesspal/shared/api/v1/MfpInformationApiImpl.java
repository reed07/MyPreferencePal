package com.myfitnesspal.shared.api.v1;

import com.myfitnesspal.shared.api.ApiUrlProvider;
import com.myfitnesspal.shared.service.syncv1.packets.request.ApiRequestPacket;
import com.myfitnesspal.shared.service.syncv1.packets.request.InformationRequestPacket;

public class MfpInformationApiImpl extends MfpInformationOrActionApiImplBase<MfpInformationApi> implements MfpInformationApi {
    public MfpInformationApiImpl(ApiBinaryConstructorArgs apiBinaryConstructorArgs) {
        super(apiBinaryConstructorArgs);
    }

    /* access modifiers changed from: protected */
    public ApiRequestPacket getBasePacket() {
        return new InformationRequestPacket();
    }

    /* access modifiers changed from: protected */
    public String getBaseUrl() {
        return ((ApiUrlProvider) this.apiUrlProvider.get()).getInformationRequestUrl();
    }
}
