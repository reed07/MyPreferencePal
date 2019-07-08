package com.myfitnesspal.feature.addfriends.service;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.api.ApiErrorCallback;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.api.v1.MfpInformationApi;
import com.myfitnesspal.shared.constants.Constants;
import com.myfitnesspal.shared.model.v1.Friend;
import com.myfitnesspal.shared.model.v15.AssociatedMfpFriend;
import com.myfitnesspal.shared.model.v15.FriendCheckResponseObject;
import com.myfitnesspal.shared.service.friends.FriendService;
import com.myfitnesspal.shared.service.syncv1.packets.PacketPayloadExtractor;
import com.myfitnesspal.shared.service.syncv1.packets.request.ApiRequestPacket;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.myfitnesspal.shared.util.RequiresActivityContext;
import com.uacf.core.caching.Cache;
import com.uacf.core.util.Function0;
import com.uacf.core.util.Function2;
import com.uacf.core.util.FunctionUtils;
import com.uacf.core.util.ReturningFunction1;
import com.uacf.core.util.SafeAsyncTask;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.inject.Provider;

public abstract class FriendOnMfpService<TFriend extends Friend> implements RequiresActivityContext {
    protected Activity activityContext;
    /* access modifiers changed from: private */
    public final Provider<MfpInformationApi> api;
    /* access modifiers changed from: private */
    public final Cache<FriendCheckResponseObject> cache;
    protected final Context context;
    private final FriendService friendService;
    protected final Handler handler;
    private MfpCheckListener<TFriend> listener;
    protected final NavigationHelper navigationHelper;
    private final SharedPreferences prefs;

    public interface MfpCheckListener<TFriend> {
        void onMfpCheckComplete(List<TFriend> list, List<TFriend> list2);

        void onMfpCheckFailed(int i, String str);
    }

    /* access modifiers changed from: protected */
    public abstract List<TFriend> getFriends();

    /* access modifiers changed from: protected */
    public abstract int getResponsePacketType();

    /* access modifiers changed from: protected */
    public abstract boolean matches(AssociatedMfpFriend associatedMfpFriend, TFriend tfriend);

    public abstract void sendExternalInvites(List<TFriend> list, String str, InviteSucceeded inviteSucceeded);

    /* access modifiers changed from: protected */
    public abstract ApiRequestPacket supplyPacketForRequest(List<TFriend> list);

    protected FriendOnMfpService(FriendOnMfpConstructorArgs friendOnMfpConstructorArgs) {
        this.context = friendOnMfpConstructorArgs.getContext();
        this.prefs = friendOnMfpConstructorArgs.getPrefs();
        this.friendService = friendOnMfpConstructorArgs.getFriendService();
        this.handler = friendOnMfpConstructorArgs.getHandler();
        this.navigationHelper = friendOnMfpConstructorArgs.getNavigationHelper();
        this.api = friendOnMfpConstructorArgs.getApi();
        this.cache = friendOnMfpConstructorArgs.getCache();
    }

    public void setActivityContext(Activity activity) {
        this.activityContext = activity;
        this.navigationHelper.setContext(activity);
    }

    public MfpCheckListener<TFriend> getListener() {
        return this.listener;
    }

    public void setListener(MfpCheckListener<TFriend> mfpCheckListener) {
        this.listener = mfpCheckListener;
    }

    public void clearState() {
        this.prefs.edit().clear().apply();
    }

    /* access modifiers changed from: protected */
    public void onMfpCheckComplete(List<TFriend> list, List<TFriend> list2) {
        this.handler.post(new Runnable(list, list2) {
            private final /* synthetic */ List f$1;
            private final /* synthetic */ List f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void run() {
                FriendOnMfpService.lambda$onMfpCheckComplete$0(FriendOnMfpService.this, this.f$1, this.f$2);
            }
        });
    }

    public static /* synthetic */ void lambda$onMfpCheckComplete$0(FriendOnMfpService friendOnMfpService, List list, List list2) {
        MfpCheckListener<TFriend> mfpCheckListener = friendOnMfpService.listener;
        if (mfpCheckListener != null) {
            mfpCheckListener.onMfpCheckComplete(list, list2);
        }
    }

    /* access modifiers changed from: protected */
    public void onMfpCheckFailed(int i, String str) {
        this.handler.post(new Runnable(i, str) {
            private final /* synthetic */ int f$1;
            private final /* synthetic */ String f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void run() {
                FriendOnMfpService.lambda$onMfpCheckFailed$1(FriendOnMfpService.this, this.f$1, this.f$2);
            }
        });
    }

    public static /* synthetic */ void lambda$onMfpCheckFailed$1(FriendOnMfpService friendOnMfpService, int i, String str) {
        MfpCheckListener<TFriend> mfpCheckListener = friendOnMfpService.listener;
        if (mfpCheckListener != null) {
            mfpCheckListener.onMfpCheckFailed(i, str);
        }
    }

    public void loadMfpFriends() {
        new SafeAsyncTask<FriendCheckResponseObject>() {
            private List<TFriend> allFriends;

            public FriendCheckResponseObject call() throws Exception {
                this.allFriends = FriendOnMfpService.this.getFriends();
                if (this.allFriends.size() > 0) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(Constants.Cache.FRIENDS_ON_MFP_KEY);
                    sb.append(FriendOnMfpService.this.getResponsePacketType());
                    String sb2 = sb.toString();
                    FriendCheckResponseObject friendCheckResponseObject = (FriendCheckResponseObject) FriendOnMfpService.this.cache.get(sb2);
                    if (friendCheckResponseObject == null) {
                        friendCheckResponseObject = (FriendCheckResponseObject) ((MfpInformationApi) ((MfpInformationApi) FriendOnMfpService.this.api.get()).addPacket(FriendOnMfpService.this.supplyPacketForRequest(this.allFriends))).post((ReturningFunction1<TTransform, T>) new PacketPayloadExtractor<TTransform,T>(FriendOnMfpService.this.getResponsePacketType()), new Object[0]);
                        FriendOnMfpService.this.cache.put(sb2, friendCheckResponseObject);
                    }
                    return friendCheckResponseObject;
                }
                FriendOnMfpService.this.onMfpCheckComplete(null, null);
                return null;
            }

            /* access modifiers changed from: protected */
            public void onSuccess(FriendCheckResponseObject friendCheckResponseObject) throws Exception {
                super.onSuccess(friendCheckResponseObject);
                FriendOnMfpService.this.handleCheckFriendsSucceeded(friendCheckResponseObject, this.allFriends);
            }

            /* access modifiers changed from: protected */
            public void onException(Exception exc) {
                super.onException(exc);
                FriendOnMfpService.this.onMfpCheckFailed(0, exc.getMessage());
            }
        }.execute();
    }

    public void addFriend(List<TFriend> list, String str, Function2<Integer, String> function2) {
        ArrayList arrayList = new ArrayList();
        for (TFriend mfpUsername : list) {
            arrayList.add(mfpUsername.getMfpUsername());
        }
        this.friendService.sendInvitations(arrayList, str, this.context.getString(R.string.addfriends_invite_message), new Function0() {
            public final void execute() {
                FunctionUtils.invokeIfValid(Function2.this, Integer.valueOf(0), null);
            }
        }, new ApiErrorCallback() {
            public final void execute(Object obj) {
                FunctionUtils.invokeIfValid(Function2.this, Integer.valueOf(((ApiException) obj).getStatusCode()), ((ApiException) obj).getBody());
            }
        });
    }

    /* access modifiers changed from: private */
    public void handleCheckFriendsSucceeded(FriendCheckResponseObject friendCheckResponseObject, List<TFriend> list) {
        boolean z;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        if (friendCheckResponseObject != null) {
            List matches = friendCheckResponseObject.getMatches();
            List nonMatches = friendCheckResponseObject.getNonMatches();
            if (list == null) {
                onMfpCheckFailed(0, null);
                return;
            }
            for (TFriend tfriend : list) {
                Iterator it = matches.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        z = false;
                        break;
                    }
                    AssociatedMfpFriend associatedMfpFriend = (AssociatedMfpFriend) it.next();
                    if (matches(associatedMfpFriend, tfriend)) {
                        tfriend.setMfpUsername(associatedMfpFriend.getMfpUsername());
                        arrayList.add(tfriend);
                        z = true;
                        break;
                    }
                }
                if (!z) {
                    Iterator it2 = nonMatches.iterator();
                    while (true) {
                        if (it2.hasNext()) {
                            if (matches((AssociatedMfpFriend) it2.next(), tfriend)) {
                                arrayList2.add(tfriend);
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                }
            }
        }
        onMfpCheckComplete(arrayList, arrayList2);
    }
}
