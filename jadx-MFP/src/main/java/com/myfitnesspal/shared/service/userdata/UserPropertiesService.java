package com.myfitnesspal.shared.service.userdata;

import com.myfitnesspal.shared.api.ApiErrorCallback;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.model.v15.PendingItemTalliesObject;
import com.myfitnesspal.shared.model.v2.MfpUserProperties;
import com.uacf.core.util.Function0;
import com.uacf.core.util.Function1;
import io.reactivex.Single;
import java.util.Map;

public interface UserPropertiesService {
    Single<PendingItemTalliesObject> getPendingItemTalliesSync();

    void getUpdatedUserInfo(String str, Function1<MfpUserProperties> function1);

    void resendEmailVerification() throws ApiException;

    void resendEmailVerificationAsync(Function0 function0, ApiErrorCallback apiErrorCallback);

    void updateProperties(Map<String, String> map, Function0 function0, ApiErrorCallback apiErrorCallback);
}
