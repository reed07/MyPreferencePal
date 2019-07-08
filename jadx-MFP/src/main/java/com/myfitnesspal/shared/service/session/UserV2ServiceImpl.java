package com.myfitnesspal.shared.service.session;

import android.content.Context;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.api.ApiRequest;
import com.myfitnesspal.shared.api.ApiResponse;
import com.myfitnesspal.shared.api.v2.MfpV2Api;
import com.myfitnesspal.shared.constants.SharedConstants.Http;
import com.myfitnesspal.shared.constants.SharedConstants.Uri;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.db.adapter.UserV2ServiceDbAdapter;
import com.myfitnesspal.shared.model.v2.MfpGoalDisplay;
import com.myfitnesspal.shared.model.v2.MfpGoalPreferences;
import com.myfitnesspal.shared.model.v2.MfpProfile;
import com.myfitnesspal.shared.model.v2.MfpStepSource;
import com.myfitnesspal.shared.model.v2.UserV2;
import com.myfitnesspal.shared.model.v2.UserV2.API_RESPONSE_MAPPER;
import com.myfitnesspal.shared.service.syncv2.SyncV2ServiceBase;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Function1;
import com.uacf.core.util.Function2;
import com.uacf.core.util.Ln;
import com.uacf.core.util.ParcelableUtil;
import com.uacf.sync.engine.UacfScheduleException;
import com.uacf.sync.provider.sdk.model.SyncItem;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.inject.Provider;

public class UserV2ServiceImpl extends SyncV2ServiceBase<UserV2> implements UserV2Service {
    private static final int MAX_THREADS = 2;
    private static final String TAG = "UserServiceImpl";
    private Object[] ALL_USER_REQUEST_PARAMS = {"fields[]", Http.GOAL_PREFERENCES, "fields[]", Http.PROFILES, "fields[]", "account", "fields[]", Http.UNIT_PREFERENCES, "fields[]", Http.LOCATION_PREFERENCES, "fields[]", Http.DAIRY_PREFERENCES, "fields[]", Http.SOCIAL_PREFERENCES, "fields[]", Http.SYSTEM_DATA, "fields[]", Http.PRIVACY_PREFERENCES, "fields[]", Http.NOTIFICATION_PREFERENCES, "fields[]", Http.APP_PREFERENCES, "fields[]", Http.GOAL_DISPLAYS, "fields[]", Http.STEPS_SOURCES};
    /* access modifiers changed from: private */
    public UserV2ServiceDbAdapter dbAdapter;
    private Session session;
    private Lazy<UserImpl> user;
    private Provider<MfpV2Api> userApi;

    private static class UpdateCustomGoalsRequest {
        @Expose
        private MfpGoalDisplay customGoalDisplay = new MfpGoalDisplay();

        public UpdateCustomGoalsRequest(MfpGoalDisplay mfpGoalDisplay) {
            this.customGoalDisplay.setDisplayType(mfpGoalDisplay.getDisplayType());
            this.customGoalDisplay.setNutrients(new ArrayList(mfpGoalDisplay.getNutrients()));
        }
    }

    /* access modifiers changed from: protected */
    public int getMaxThreads() {
        return 2;
    }

    public String getSyncResourceName() {
        return "user";
    }

    /* access modifiers changed from: protected */
    public String getThreadName() {
        return TAG;
    }

    public void publishUnsyncedItems(Function2<Integer, Integer> function2) throws UacfScheduleException {
    }

    public UserV2ServiceImpl(Context context, Lazy<UserImpl> lazy, DbConnectionManager dbConnectionManager, Provider<MfpV2Api> provider, Session session2) {
        this.user = lazy;
        this.userApi = provider;
        this.session = session2;
        this.dbAdapter = new UserV2ServiceDbAdapter(context, dbConnectionManager, this.session);
    }

    public Class<? extends Parcelable> getSyncItemClass() {
        return UserV2.class;
    }

    public void consumeSyncItems(List<SyncItem<UserV2>> list) {
        if (CollectionUtils.notEmpty((Collection<?>) list)) {
            UserV2 userV2 = (UserV2) ((SyncItem) list.get(list.size() - 1)).getItem();
            if (userV2 != null && this.dbAdapter.setUser(userV2)) {
                ((UserImpl) this.user.get()).setUserV2(userV2);
            }
        }
    }

    public void updateStepsSourcesAsync(final Function1<List<MfpStepSource>> function1, Function1<List<Exception>> function12, List<MfpStepSource> list) {
        UserV2 userV2 = new UserV2();
        userV2.setStepSources(list);
        updateUserAsync(new Function1<UserV2>() {
            public void execute(UserV2 userV2) {
                UserV2ServiceImpl.this.postToMainThread(function1, userV2.getStepSources());
            }
        }, function12, userV2);
    }

    public List<MfpStepSource> updateStepsSources(List<MfpStepSource> list) throws ApiException {
        UserV2 userV2 = new UserV2();
        userV2.setStepSources(list);
        return updateUser(userV2).getStepSources();
    }

    public void updateGoalPreferencesAsync(final Function1<MfpGoalPreferences> function1, Function1<List<Exception>> function12, MfpGoalPreferences mfpGoalPreferences) {
        UserV2 userV2 = new UserV2();
        MfpGoalPreferences mfpGoalPreferences2 = (MfpGoalPreferences) ParcelableUtil.clone(mfpGoalPreferences, MfpGoalPreferences.CREATOR);
        mfpGoalPreferences2.setDailyEnergyGoal(null);
        userV2.setGoalPreferences(mfpGoalPreferences2);
        updateUserAsync(new Function1<UserV2>() {
            public void execute(UserV2 userV2) throws RuntimeException {
                UserV2ServiceImpl.this.postToMainThread(function1, userV2.getGoalPreferences());
            }
        }, function12, userV2);
    }

    public void updateGoalDisplaysAsync(final Function1<List<MfpGoalDisplay>> function1, Function1<List<Exception>> function12, MfpGoalDisplay mfpGoalDisplay) {
        updateUserAsync(new Function1<UserV2>() {
            public void execute(UserV2 userV2) throws RuntimeException {
                UserV2ServiceImpl.this.postToMainThread(function1, userV2.getGoalDisplays());
            }
        }, function12, new UpdateCustomGoalsRequest(mfpGoalDisplay));
    }

    public UserV2 patchMfpProfile(MfpProfile mfpProfile) throws ApiException {
        UserV2 userV2 = new UserV2();
        ArrayList arrayList = new ArrayList();
        arrayList.add(mfpProfile);
        userV2.setProfiles(arrayList);
        return updateUser(userV2);
    }

    public UserV2 fetchFromDiskSync() {
        return this.dbAdapter.getUser();
    }

    public void logout() {
        this.dbAdapter.deleteAllUsers();
    }

    public UserV2 fetchFromBackend() throws ApiException {
        UserV2 userOnCurrentThread = getUserOnCurrentThread(this.ALL_USER_REQUEST_PARAMS);
        if (userOnCurrentThread != null) {
            ((UserImpl) this.user.get()).setUserV2(userOnCurrentThread);
            writeToDisk(userOnCurrentThread, null);
        }
        return userOnCurrentThread;
    }

    public void writeToDisk(final UserV2 userV2, final Function1<Boolean> function1) {
        auto(new Runnable() {
            public void run() {
                UserV2ServiceImpl.this.postToMainThread(function1, Boolean.valueOf(UserV2ServiceImpl.this.dbAdapter.setUser(userV2)));
            }
        });
    }

    private UserV2 getUserOnCurrentThread(Object... objArr) throws ApiException {
        ApiResponse apiResponse = (ApiResponse) ((MfpV2Api) ((MfpV2Api) this.userApi.get()).withOutputType(API_RESPONSE_MAPPER.class)).get(Uri.CURRENT_USER, objArr);
        if (apiResponse != null) {
            return (UserV2) apiResponse.getItem();
        }
        return null;
    }

    /* access modifiers changed from: private */
    public <RequestType> UserV2 updateUser(RequestType requesttype) throws ApiException {
        UserV2 userV2 = (UserV2) ((ApiResponse) ((MfpV2Api) ((MfpV2Api) ((MfpV2Api) this.userApi.get()).withOutputType(API_RESPONSE_MAPPER.class)).withJsonBody(new ApiRequest(requesttype))).patch(Uri.CURRENT_USER)).getItem();
        ((UserImpl) this.user.get()).setUserV2(userV2);
        writeToDisk(userV2, null);
        return userV2;
    }

    private <RequestType> void updateUserAsync(Function1<UserV2> function1, Function1<List<Exception>> function12, RequestType requesttype) {
        final ArrayList arrayList = new ArrayList();
        final RequestType requesttype2 = requesttype;
        final Function1<UserV2> function13 = function1;
        final Function1<List<Exception>> function14 = function12;
        AnonymousClass5 r0 = new Runnable() {
            public void run() {
                try {
                    UserV2ServiceImpl.this.postToMainThread(function13, UserV2ServiceImpl.this.updateUser(requesttype2));
                } catch (ApiException e) {
                    Ln.e("failed to patch users! %s", e);
                    arrayList.add(e);
                    UserV2ServiceImpl.this.postToMainThread(function14, arrayList);
                }
            }
        };
        async(r0);
    }
}
