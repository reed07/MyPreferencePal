package com.myfitnesspal.shared.service.userdata;

import com.myfitnesspal.shared.api.ApiErrorCallback;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.api.v1.MfpInformationApi;
import com.myfitnesspal.shared.model.v15.UserSummaryObject;
import com.myfitnesspal.shared.service.syncv1.packets.PacketPayloadExtractor;
import com.myfitnesspal.shared.service.syncv1.packets.request.RetrieveUserSummaryRequestPacket;
import com.uacf.core.util.Function1;
import com.uacf.core.util.FunctionUtils;
import com.uacf.core.util.ReturningFunction1;
import javax.inject.Provider;

public class UserSummaryServiceImpl implements UserSummaryService {
    protected static UserSummaryObject cached;
    protected final Provider<MfpInformationApi> api;

    public UserSummaryServiceImpl(Provider<MfpInformationApi> provider) {
        this.api = provider;
    }

    public void fetchUserSummaryAsync(String str, String str2, final Function1<UserSummaryObject> function1, ApiErrorCallback apiErrorCallback) {
        ((MfpInformationApi) ((MfpInformationApi) this.api.get()).addPacket(new RetrieveUserSummaryRequestPacket(str, str2))).postAsync((Function1<TTransform>) new Function1<UserSummaryObject>() {
            public void execute(UserSummaryObject userSummaryObject) {
                UserSummaryServiceImpl.this.setCache(userSummaryObject);
                FunctionUtils.invokeIfValid(function1, userSummaryObject);
            }
        }, apiErrorCallback, (ReturningFunction1<TTransform, T>) new PacketPayloadExtractor<TTransform,T>(120), new Object[0]);
    }

    public UserSummaryObject fetchUserSummary(String str, String str2) throws ApiException {
        setCache((UserSummaryObject) ((MfpInformationApi) ((MfpInformationApi) this.api.get()).addPacket(new RetrieveUserSummaryRequestPacket(str, str2))).post((ReturningFunction1<TTransform, T>) new PacketPayloadExtractor<TTransform,T>(120), new Object[0]));
        return getCache();
    }

    public UserSummaryObject fetchUserSummaryWithCachedFallback(String str, String str2) {
        try {
            return fetchUserSummary(str, str2);
        } catch (Exception unused) {
            return getCache();
        }
    }

    public void clearCache() {
        synchronized (UserSummaryServiceImpl.class) {
            cached = null;
        }
    }

    public UserSummaryObject getCachedUserSummary(String str) {
        UserSummaryObject userSummaryObject = cached;
        if (userSummaryObject == null || !userSummaryObject.getUsername().toLowerCase().equals(str)) {
            return null;
        }
        return userSummaryObject;
    }

    /* access modifiers changed from: protected */
    public void setCache(UserSummaryObject userSummaryObject) {
        synchronized (UserSummaryServiceImpl.class) {
            cached = userSummaryObject;
        }
    }

    /* access modifiers changed from: protected */
    public UserSummaryObject getCache() {
        UserSummaryObject userSummaryObject;
        synchronized (UserSummaryServiceImpl.class) {
            userSummaryObject = cached;
        }
        return userSummaryObject;
    }
}
