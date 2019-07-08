package com.myfitnesspal.feature.community.service;

import com.myfitnesspal.shared.api.ApiResponse;
import com.myfitnesspal.shared.api.v2.MfpV2ApiErrorCallback;
import com.myfitnesspal.shared.model.v2.MfpUrl;
import com.uacf.core.util.Function2;
import java.util.List;

public interface CommunityService {
    boolean isCommunityEnabled();

    void singleSignOn(String str, Function2<ApiResponse<MfpUrl>, List<String>> function2, MfpV2ApiErrorCallback mfpV2ApiErrorCallback);
}
