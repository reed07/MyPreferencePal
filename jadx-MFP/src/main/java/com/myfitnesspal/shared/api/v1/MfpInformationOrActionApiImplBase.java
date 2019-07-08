package com.myfitnesspal.shared.api.v1;

import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.api.ApiUrlProvider;
import com.myfitnesspal.shared.api.v1.MfpBinaryApi;
import com.myfitnesspal.shared.service.syncv1.packets.request.ApiRequestPacket;
import com.myfitnesspal.shared.service.syncv1.packets.response.ApiResponsePacket;
import com.myfitnesspal.shared.service.syncv1.packets.response.InformationOrActionResponsePacket;
import com.uacf.core.util.Strings;
import java.util.ArrayList;
import java.util.List;
import okhttp3.OkHttpClient;

public abstract class MfpInformationOrActionApiImplBase<T extends MfpBinaryApi<T>> extends MfpBinaryApiImpl<T> {
    List<Integer> errorCodesToBeTreatedAsSuccess;

    /* access modifiers changed from: protected */
    public abstract ApiRequestPacket getBasePacket();

    protected MfpInformationOrActionApiImplBase(ApiBinaryConstructorArgs apiBinaryConstructorArgs) {
        super(apiBinaryConstructorArgs);
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: protected */
    public void validateResponsePackets(List<ApiResponsePacket> list) throws ApiException {
        super.validateResponsePackets(list);
        ApiResponsePacket apiResponsePacket = (ApiResponsePacket) list.get(0);
        if (apiResponsePacket instanceof InformationOrActionResponsePacket) {
            InformationOrActionResponsePacket informationOrActionResponsePacket = (InformationOrActionResponsePacket) apiResponsePacket;
            int resultCode = informationOrActionResponsePacket.getResultCode();
            if (resultCode != 2 && resultCode >= 256) {
                try {
                    if (this.errorCodesToBeTreatedAsSuccess == null || !this.errorCodesToBeTreatedAsSuccess.contains(Integer.valueOf(resultCode))) {
                        this.errorCodesToBeTreatedAsSuccess = null;
                        throw new ApiException("", resultCode, Strings.toString(informationOrActionResponsePacket.getErrorMessage()));
                    }
                } catch (Throwable th) {
                    this.errorCodesToBeTreatedAsSuccess = null;
                    throw th;
                }
            }
            this.errorCodesToBeTreatedAsSuccess = null;
        }
    }

    /* access modifiers changed from: protected */
    public OkHttpClient client() {
        insertPacket(0, getBasePacket());
        return super.client();
    }

    public T treatErrorCodeAsSuccess(int... iArr) {
        if (iArr != null && iArr.length > 0) {
            if (this.errorCodesToBeTreatedAsSuccess == null) {
                this.errorCodesToBeTreatedAsSuccess = new ArrayList();
            }
            for (int valueOf : iArr) {
                this.errorCodesToBeTreatedAsSuccess.add(Integer.valueOf(valueOf));
            }
        }
        return this;
    }

    /* access modifiers changed from: protected */
    public String getBaseUrl() {
        return ((ApiUrlProvider) this.apiUrlProvider.get()).getWebServiceBaseUrl();
    }
}
