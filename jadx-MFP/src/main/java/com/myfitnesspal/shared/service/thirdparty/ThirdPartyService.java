package com.myfitnesspal.shared.service.thirdparty;

import com.myfitnesspal.shared.api.ApiErrorCallback;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.api.exception.MfpApiInvalidArgumentException;
import com.myfitnesspal.shared.api.v1.MfpInformationApi;
import com.myfitnesspal.shared.model.v15.DissociateThirdPartyObject;
import com.myfitnesspal.shared.model.v15.VerifyThirdPartyObject;
import com.myfitnesspal.shared.service.syncv1.PacketTypes;
import com.myfitnesspal.shared.service.syncv1.packets.PacketPayloadExtractor;
import com.myfitnesspal.shared.service.syncv1.packets.request.AssociateThirdPartyRequestPacket;
import com.myfitnesspal.shared.service.syncv1.packets.request.ChangePasswordRequestPacket;
import com.myfitnesspal.shared.service.syncv1.packets.request.DissociateThirdPartyRequestPacket;
import com.myfitnesspal.shared.service.syncv1.packets.request.VerifyThirdPartyRequestPacket;
import com.uacf.core.util.Function0;
import com.uacf.core.util.Function1;
import com.uacf.core.util.FunctionUtils;
import com.uacf.core.util.ReturningFunction1;
import com.uacf.core.util.Strings;
import javax.inject.Inject;
import javax.inject.Provider;

public class ThirdPartyService {
    private final Provider<MfpInformationApi> api;

    @Inject
    public ThirdPartyService(Provider<MfpInformationApi> provider) {
        this.api = provider;
    }

    public void verifyThirdPartyUser(boolean z, int i, String str, String str2, Function1<VerifyThirdPartyObject> function1, ApiErrorCallback apiErrorCallback) {
        if (i < 0 || i == 0) {
            FunctionUtils.invokeIfValid(apiErrorCallback, new MfpApiInvalidArgumentException("ThirdPartyService.verifyThirdPartyUser", "serviceId", Integer.valueOf(i)));
        } else if (Strings.isEmpty(str)) {
            FunctionUtils.invokeIfValid(apiErrorCallback, new MfpApiInvalidArgumentException("ThirdPartyService.verifyThirdPartyUser", "userId", str));
        } else if (Strings.isEmpty(str2)) {
            FunctionUtils.invokeIfValid(apiErrorCallback, new MfpApiInvalidArgumentException("ThirdPartyService.verifyThirdPartyUser", "accessToken", str2));
        } else {
            ((MfpInformationApi) ((MfpInformationApi) this.api.get()).addPacket(new VerifyThirdPartyRequestPacket(z, i, str, str2))).postAsync(function1, apiErrorCallback, (ReturningFunction1<TTransform, T>) new PacketPayloadExtractor<TTransform,T>(134), new Object[0]);
        }
    }

    public void associate(int i, String str, String str2) throws ApiException {
        ((MfpInformationApi) ((MfpInformationApi) this.api.get()).addPacket(new AssociateThirdPartyRequestPacket(i, str, str2))).post(new Object[0]);
    }

    public void associate(int i, String str, String str2, Function0 function0, ApiErrorCallback apiErrorCallback) {
        if (i < 0 || i == 0) {
            FunctionUtils.invokeIfValid(apiErrorCallback, new MfpApiInvalidArgumentException("ThirdPartyService.associate", "serviceId", Integer.valueOf(i)));
        } else if (Strings.isEmpty(str)) {
            FunctionUtils.invokeIfValid(apiErrorCallback, new MfpApiInvalidArgumentException("ThirdPartyService.associate", "userId", str));
        } else if (Strings.isEmpty(str2)) {
            FunctionUtils.invokeIfValid(apiErrorCallback, new MfpApiInvalidArgumentException("ThirdPartyService.associate", "accessToken", str2));
        } else {
            ((MfpInformationApi) ((MfpInformationApi) this.api.get()).addPacket(new AssociateThirdPartyRequestPacket(i, str, str2))).postAsync(function0, apiErrorCallback, new Object[0]);
        }
    }

    public void dissociate(int i, Function1<DissociateThirdPartyObject> function1, ApiErrorCallback apiErrorCallback) {
        if (i < 0 || i == 0) {
            FunctionUtils.invokeIfValid(apiErrorCallback, new MfpApiInvalidArgumentException("ThirdPartyService.dissociate", "serviceId", Integer.valueOf(i)));
        } else {
            ((MfpInformationApi) ((MfpInformationApi) this.api.get()).addPacket(new DissociateThirdPartyRequestPacket(i))).postAsync(function1, apiErrorCallback, (ReturningFunction1<TTransform, T>) new PacketPayloadExtractor<TTransform,T>(PacketTypes.ThirdPartyDissociateResponse), new Object[0]);
        }
    }

    public void changePassword(String str, Function0 function0, ApiErrorCallback apiErrorCallback) {
        if (Strings.isEmpty(str)) {
            FunctionUtils.invokeIfValid(apiErrorCallback, new MfpApiInvalidArgumentException("ThirdPartyService.changePassword", "newPassword", "*********"));
        } else {
            ((MfpInformationApi) ((MfpInformationApi) this.api.get()).addPacket(new ChangePasswordRequestPacket(str))).postAsync(function0, apiErrorCallback, new Object[0]);
        }
    }
}
