package com.myfitnesspal.shared.api.v1;

import com.myfitnesspal.shared.api.MfpApi;
import com.myfitnesspal.shared.api.v1.MfpBinaryApi;
import com.myfitnesspal.shared.service.syncv1.packets.request.ApiRequestPacket;

public interface MfpBinaryApi<TApi extends MfpBinaryApi> extends MfpApi<TApi, byte[]> {
    TApi addPacket(ApiRequestPacket apiRequestPacket);

    TApi insertPacket(int i, ApiRequestPacket apiRequestPacket);

    TApi treatErrorCodeAsSuccess(int... iArr);
}
