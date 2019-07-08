package com.myfitnesspal.shared.service.friends;

import com.myfitnesspal.shared.api.ApiErrorCallback;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.api.v1.MfpActionApi;
import com.myfitnesspal.shared.constants.Constants;
import com.myfitnesspal.shared.model.v15.FriendRequestObject;
import com.myfitnesspal.shared.model.v15.UserSummaryObject;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.syncv1.packets.PacketPayloadListExtractor;
import com.myfitnesspal.shared.service.syncv1.packets.request.InvitationRequestPacket;
import com.myfitnesspal.shared.service.syncv1.packets.request.RetrieveFriendListRequestPacket;
import com.myfitnesspal.shared.service.syncv1.packets.request.RetrieveFriendRequestsPacket;
import com.myfitnesspal.shared.service.syncv1.packets.request.TakeActionOnInvitationRequestPacket;
import com.myfitnesspal.shared.service.syncv1.packets.request.UnfriendUserRequestPacket;
import com.uacf.core.asyncservice.SimpleAsyncServiceBase;
import com.uacf.core.caching.Cache;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Function0;
import com.uacf.core.util.Function1;
import com.uacf.core.util.FunctionUtils;
import com.uacf.core.util.ReturningFunction1;
import dagger.Lazy;
import java.util.Collection;
import java.util.List;
import javax.inject.Provider;

public class FriendServiceImpl extends SimpleAsyncServiceBase implements FriendService {
    private final Provider<MfpActionApi> api;
    private final Lazy<BackgroundJobHelper> backgroundServiceHelper;
    private Cache<List<UserSummaryObject>> cache;
    private final Lazy<LocalSettingsService> localSettingsService;
    private final Lazy<Session> session;

    /* access modifiers changed from: protected */
    public int getMaxThreads() {
        return 5;
    }

    /* access modifiers changed from: protected */
    public String getThreadName() {
        return "FriendServiceImpl";
    }

    public FriendServiceImpl(Provider<MfpActionApi> provider, Lazy<BackgroundJobHelper> lazy, Cache<List<UserSummaryObject>> cache2, Lazy<LocalSettingsService> lazy2, Lazy<Session> lazy3) {
        this.api = provider;
        this.backgroundServiceHelper = lazy;
        this.session = lazy3;
        this.cache = cache2;
        this.localSettingsService = lazy2;
    }

    public void sendInvitations(List<String> list, String str, String str2, Function0 function0, ApiErrorCallback apiErrorCallback) {
        ((MfpActionApi) ((MfpActionApi) this.api.get()).addPacket(new InvitationRequestPacket().addInvitees(list).setFullName(str).setMessage(str2))).postAsync(function0, apiErrorCallback, new Object[0]);
    }

    public List<FriendRequestObject> fetchRequests(int i, int i2, int i3) throws ApiException {
        return (List) ((MfpActionApi) ((MfpActionApi) this.api.get()).addPacket(new RetrieveFriendRequestsPacket(i, i2, i3))).post((ReturningFunction1<TTransform, T>) new PacketPayloadListExtractor<TTransform,T>(118), new Object[0]);
    }

    public void unfriendUserId(long j, String str, Function0 function0, ApiErrorCallback apiErrorCallback) {
        ((MfpActionApi) ((MfpActionApi) this.api.get()).addPacket(new UnfriendUserRequestPacket(j, str))).postAsync(function0, apiErrorCallback, new Object[0]);
        clearCache();
    }

    public void acceptFriendRequest(long j, String str) throws ApiException {
        TakeActionOnInvitationRequestPacket takeActionOnInvitationRequestPacket = new TakeActionOnInvitationRequestPacket();
        takeActionOnInvitationRequestPacket.setAction(1);
        takeActionOnInvitationRequestPacket.setRequestMasterId(j);
        takeActionOnInvitationRequestPacket.setRequestUid(str);
        ((MfpActionApi) ((MfpActionApi) this.api.get()).addPacket(takeActionOnInvitationRequestPacket)).post(new Object[0]);
        ((BackgroundJobHelper) this.backgroundServiceHelper.get()).updateInAppNotifications();
        clearCache();
    }

    public void rejectFriendRequest(long j, String str) throws ApiException {
        TakeActionOnInvitationRequestPacket takeActionOnInvitationRequestPacket = new TakeActionOnInvitationRequestPacket();
        takeActionOnInvitationRequestPacket.setAction(2);
        takeActionOnInvitationRequestPacket.setRequestMasterId(j);
        takeActionOnInvitationRequestPacket.setRequestUid(str);
        ((MfpActionApi) ((MfpActionApi) this.api.get()).addPacket(takeActionOnInvitationRequestPacket)).post(new Object[0]);
        ((BackgroundJobHelper) this.backgroundServiceHelper.get()).updateInAppNotifications();
    }

    public void getFriendCount(final Function1<Integer> function1) {
        if (!((Session) this.session.get()).getUser().isLoggedIn()) {
            FunctionUtils.invokeIfValid(function1, Integer.valueOf(-1));
        } else {
            fetchFriends(new Function1<List<UserSummaryObject>>() {
                public void execute(List<UserSummaryObject> list) {
                    FunctionUtils.invokeIfValid(function1, Integer.valueOf(CollectionUtils.size((Collection<?>) list)));
                }
            }, new ApiErrorCallback() {
                public void execute(ApiException apiException) {
                    FunctionUtils.invokeIfValid(function1, Integer.valueOf(0));
                }
            });
        }
    }

    public void fetchFriends(final Function1<List<UserSummaryObject>> function1, final ApiErrorCallback apiErrorCallback) {
        async(new Runnable() {
            public void run() {
                try {
                    FriendServiceImpl.this.postToMainThread(function1, FriendServiceImpl.this.fetchFriends());
                } catch (ApiException e) {
                    FriendServiceImpl.this.postToMainThread(apiErrorCallback, e);
                }
            }
        });
    }

    public List<UserSummaryObject> fetchFriends() throws ApiException {
        try {
            List<UserSummaryObject> list = (List) ((MfpActionApi) ((MfpActionApi) this.api.get()).addPacket(new RetrieveFriendListRequestPacket(1000, 0))).post((ReturningFunction1<TTransform, T>) new PacketPayloadListExtractor<TTransform,T>(120), new Object[0]);
            this.cache.put(getCacheKey(), list);
            setHasFriends(CollectionUtils.notEmpty((Collection<?>) list));
            return list;
        } catch (ApiException e) {
            List<UserSummaryObject> friendsListFromCache = getFriendsListFromCache();
            if (CollectionUtils.notEmpty((Collection<?>) friendsListFromCache)) {
                return friendsListFromCache;
            }
            throw e;
        }
    }

    private void setHasFriends(boolean z) {
        ((LocalSettingsService) this.localSettingsService.get()).setHasFriends(z);
    }

    private List<UserSummaryObject> getFriendsListFromCache() {
        if (this.cache.contains(getCacheKey())) {
            return (List) this.cache.get(getCacheKey());
        }
        return null;
    }

    private String getCacheKey() {
        return String.format("%s::%s", new Object[]{Constants.Cache.FRIENDS_CACHE_KEY, ((Session) this.session.get()).getUser().getUserId()});
    }

    private void clearCache() {
        this.cache.remove(getCacheKey());
    }
}
