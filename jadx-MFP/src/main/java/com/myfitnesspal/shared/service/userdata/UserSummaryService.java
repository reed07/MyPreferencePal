package com.myfitnesspal.shared.service.userdata;

import com.myfitnesspal.shared.api.ApiErrorCallback;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.model.v15.UserSummaryObject;
import com.uacf.core.util.Function1;

public interface UserSummaryService {
    void clearCache();

    UserSummaryObject fetchUserSummary(String str, String str2) throws ApiException;

    void fetchUserSummaryAsync(String str, String str2, Function1<UserSummaryObject> function1, ApiErrorCallback apiErrorCallback);

    UserSummaryObject fetchUserSummaryWithCachedFallback(String str, String str2);

    UserSummaryObject getCachedUserSummary(String str);
}
