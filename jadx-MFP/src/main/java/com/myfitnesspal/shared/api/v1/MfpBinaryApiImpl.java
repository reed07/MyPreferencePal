package com.myfitnesspal.shared.api.v1;

import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.api.v1.MfpBinaryApi;
import com.myfitnesspal.shared.service.syncv1.BinaryEncoder;
import com.myfitnesspal.shared.service.syncv1.packets.request.ApiRequestPacket;
import com.myfitnesspal.shared.service.syncv1.packets.response.ApiResponsePacket;
import com.uacf.core.util.CollectionUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.inject.Provider;
import okhttp3.OkHttpClient;

public abstract class MfpBinaryApiImpl<TApi extends MfpBinaryApi> extends MfpBinaryApiImplBase<TApi> {
    private final Provider<BinaryEncoder> encoderProvider;
    private final Object packetSyncObject = new Object();
    private List<ApiRequestPacket> packets;

    protected MfpBinaryApiImpl(ApiBinaryConstructorArgs apiBinaryConstructorArgs) {
        super(apiBinaryConstructorArgs);
        this.encoderProvider = apiBinaryConstructorArgs.getEncoderProvider();
    }

    /* access modifiers changed from: protected */
    public OkHttpClient client() {
        List<ApiRequestPacket> list;
        BinaryEncoder binaryEncoder = (BinaryEncoder) this.encoderProvider.get();
        binaryEncoder.clear();
        synchronized (this.packetSyncObject) {
            list = this.packets;
            this.packets = null;
        }
        if (!CollectionUtils.isEmpty((Collection<?>) list)) {
            for (ApiRequestPacket writeData : list) {
                writeData.writeData(binaryEncoder);
            }
            withBinaryData(binaryEncoder.getBuffer());
        }
        return super.client();
    }

    public TApi insertPacket(int i, ApiRequestPacket apiRequestPacket) {
        synchronized (this.packetSyncObject) {
            if (this.packets == null) {
                this.packets = new ArrayList();
            }
            if (i >= 0) {
                if (i <= this.packets.size()) {
                    this.packets.add(i, apiRequestPacket);
                }
            }
            this.packets.add(apiRequestPacket);
        }
        return this;
    }

    public TApi addPacket(ApiRequestPacket apiRequestPacket) {
        insertPacket(-1, apiRequestPacket);
        return this;
    }

    public List<ApiResponsePacket> get(String str, Object... objArr) throws ApiException {
        throw new UnsupportedOperationException("GET is not supported for binary API");
    }

    public List<ApiResponsePacket> put(String str) throws ApiException {
        throw new UnsupportedOperationException("PUT is not supported for binary API");
    }

    public List<ApiResponsePacket> delete(String str) throws ApiException {
        throw new UnsupportedOperationException("DELETE is not supported for binary API");
    }

    public List<ApiResponsePacket> post(String str, Object... objArr) throws ApiException {
        List<ApiResponsePacket> list = (List) super.post(str, new Object[0]);
        validateResponsePackets(list);
        return list;
    }

    /* access modifiers changed from: protected */
    public void validateResponsePackets(List<ApiResponsePacket> list) throws ApiException {
        if (CollectionUtils.isEmpty((Collection<?>) list)) {
            throw new ApiException("No packets received", 0);
        }
    }
}
