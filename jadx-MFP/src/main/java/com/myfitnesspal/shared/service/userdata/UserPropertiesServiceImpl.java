package com.myfitnesspal.shared.service.userdata;

import com.myfitnesspal.shared.api.ApiErrorCallback;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.api.ApiResponse;
import com.myfitnesspal.shared.api.v1.MfpInformationApi;
import com.myfitnesspal.shared.api.v2.MfpV2Api;
import com.myfitnesspal.shared.constants.Constants.Uri;
import com.myfitnesspal.shared.model.v15.PendingItemTalliesObject;
import com.myfitnesspal.shared.model.v2.MfpUserProperties;
import com.myfitnesspal.shared.model.v2.MfpUserProperties.API_RESPONSE_MAPPER;
import com.myfitnesspal.shared.service.syncv1.packets.PacketPayloadExtractor;
import com.myfitnesspal.shared.service.syncv1.packets.request.PendingItemTalliesRequestPacket;
import com.myfitnesspal.shared.service.syncv1.packets.request.ResendEmailVerificationToUserRequestPacket;
import com.myfitnesspal.shared.service.syncv1.packets.request.UserPropertyUpdateRequestPacket;
import com.uacf.core.util.Function0;
import com.uacf.core.util.Function1;
import com.uacf.core.util.FunctionUtils;
import com.uacf.core.util.Ln;
import com.uacf.core.util.ReturningFunction1;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import java.util.Map;
import javax.inject.Provider;

public class UserPropertiesServiceImpl implements UserPropertiesService {
    private final Provider<MfpInformationApi> api;
    private final Provider<MfpV2Api> userPropertiesV2Api;

    public UserPropertiesServiceImpl(Provider<MfpInformationApi> provider, Provider<MfpV2Api> provider2) {
        this.api = provider;
        this.userPropertiesV2Api = provider2;
    }

    public void updateProperties(Map<String, String> map, Function0 function0, ApiErrorCallback apiErrorCallback) {
        ((MfpInformationApi) ((MfpInformationApi) this.api.get()).addPacket(new UserPropertyUpdateRequestPacket(map))).postAsync((Function1<T>) new Function1() {
            public final void execute(Object obj) {
                FunctionUtils.invokeIfValid(Function0.this);
            }
        }, apiErrorCallback, new Object[0]);
    }

    public Single<PendingItemTalliesObject> getPendingItemTalliesSync() {
        return Single.create(new SingleOnSubscribe() {
            public final void subscribe(SingleEmitter singleEmitter) {
                UserPropertiesServiceImpl.lambda$getPendingItemTalliesSync$1(UserPropertiesServiceImpl.this, singleEmitter);
            }
        });
    }

    public static /* synthetic */ void lambda$getPendingItemTalliesSync$1(UserPropertiesServiceImpl userPropertiesServiceImpl, SingleEmitter singleEmitter) throws Exception {
        PendingItemTalliesObject pendingItemTalliesObject = (PendingItemTalliesObject) ((MfpInformationApi) ((MfpInformationApi) userPropertiesServiceImpl.api.get()).addPacket(new PendingItemTalliesRequestPacket())).post((ReturningFunction1<TTransform, T>) new PacketPayloadExtractor<TTransform,T>(129), new Object[0]);
        if (pendingItemTalliesObject == null) {
            singleEmitter.onError(new IllegalArgumentException("API returned null value"));
        } else {
            singleEmitter.onSuccess(pendingItemTalliesObject);
        }
    }

    public void resendEmailVerification() throws ApiException {
        getEmailVerificationApi().post(new Object[0]);
    }

    public void resendEmailVerificationAsync(Function0 function0, ApiErrorCallback apiErrorCallback) {
        getEmailVerificationApi().postAsync(function0, apiErrorCallback, new Object[0]);
    }

    public void getUpdatedUserInfo(String str, final Function1<MfpUserProperties> function1) {
        Object[] objArr = {"fields[]", "account"};
        StringBuilder sb = new StringBuilder();
        sb.append("Uri = ");
        sb.append(String.format(Uri.USER_PROPERTIES, new Object[]{str}));
        Ln.d(sb.toString(), new Object[0]);
        ((MfpV2Api) ((MfpV2Api) this.userPropertiesV2Api.get()).withOutputType(API_RESPONSE_MAPPER.class)).getAsync(String.format(Uri.USER_PROPERTIES, new Object[]{str}), (Function1<T>) new Function1<ApiResponse<MfpUserProperties>>() {
            public void execute(ApiResponse<MfpUserProperties> apiResponse) {
                FunctionUtils.invokeIfValid(function1, (MfpUserProperties) apiResponse.getItem());
            }
        }, (ApiErrorCallback) new ApiErrorCallback() {
            public void execute(ApiException apiException) {
                Ln.e(apiException);
                FunctionUtils.invokeIfValid(function1, null);
            }
        }, objArr);
    }

    private MfpInformationApi getEmailVerificationApi() {
        return (MfpInformationApi) ((MfpInformationApi) this.api.get()).addPacket(new ResendEmailVerificationToUserRequestPacket());
    }
}
