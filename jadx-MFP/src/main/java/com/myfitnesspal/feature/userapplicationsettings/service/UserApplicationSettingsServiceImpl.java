package com.myfitnesspal.feature.userapplicationsettings.service;

import android.content.ContentValues;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.annotation.NonNull;
import com.myfitnesspal.feature.userapplicationsettings.request.UserApplicationSettingsPatch;
import com.myfitnesspal.feature.userapplicationsettings.response.UserApplicationSetting;
import com.myfitnesspal.feature.userapplicationsettings.response.UserApplicationSettingsResponse;
import com.myfitnesspal.feature.userapplicationsettings.response.UserApplicationSettingsResponse.API_RESPONSE_MAPPER;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.api.ApiRequest;
import com.myfitnesspal.shared.api.ApiResponse;
import com.myfitnesspal.shared.api.v2.MfpV2Api;
import com.myfitnesspal.shared.db.table.UserApplicationSettingsTable;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.uacf.core.database.SQLiteDatabaseWrapper;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Enumerable;
import com.uacf.core.util.Function1;
import com.uacf.core.util.Function2;
import com.uacf.core.util.Strings;
import com.uacf.sync.engine.UacfScheduleException;
import com.uacf.sync.provider.sdk.model.SyncItem;
import com.uacf.sync.provider.sdk.model.SyncItem.Action;
import dagger.Lazy;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import javax.inject.Provider;

public class UserApplicationSettingsServiceImpl implements UserApplicationSettingsService {
    private static final String SETTING_ALEXA_INTERSTITIAL_LOG_WATER_SEEN = "alexa_interstitial_log_water_seen";
    private static final String SETTING_ALEXA_INTERSTITIAL_LOG_WEIGHT_SEEN = "alexa_interstitial_log_weight_seen";
    private static final String SETTING_AUTOPLAY_NEWSFEED_ADS = "autoplay_newsfeed_ads";
    private static final String SETTING_AUTOPLAY_NEWSFEED_VIDEOS = "autoplay_blog_videos";
    private static final String SETTING_BOTTOM_BAR_ONBOARDING_DISMISSED = "bottom_bar_onboarding_dismissed";
    private static final String SETTING_MEAL_GOALS_BUY_UPSELL_DISMISSED = "meal_goals_buy_upsell_dismissed";
    private static final String SETTING_PREMIUM_TRIALS_ENABLED = "premium_trials_enabled";
    private static final int SYNC_FLAG_DO_NOT_UPDATE = 0;
    private static final int SYNC_FLAG_UPDATE = 1;
    private static final String TYPE_BOOLEAN = "boolean";
    private static final String TYPE_DOUBLE = "double";
    private static final String TYPE_INTEGER = "integer";
    private static final String TYPE_STRING = "string";
    private static final String URL_PATCH_USER_APPLICATION_SETTINGS = "/v2/user-application-settings/%s";
    private final int HANDLER_REMOVE = 2;
    private final int HANDLER_SHUTDOWN = 3;
    private final int HANDLER_UPSERT = 1;
    private boolean cacheWarmed;
    /* access modifiers changed from: private */
    public final Map<String, UserApplicationSetting> cachedSettings;
    /* access modifiers changed from: private */
    public HandlerThread handlerThread;
    private final Lazy<LocalSettingsService> localSettingsService;
    private final Session session;
    private final Provider<MfpV2Api> userApi;
    private final UserApplicationSettingsTable userApplicationSettingsTable;
    /* access modifiers changed from: private */
    public Handler workHandler;

    /* renamed from: com.myfitnesspal.feature.userapplicationsettings.service.UserApplicationSettingsServiceImpl$3 reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$uacf$sync$provider$sdk$model$SyncItem$Action = new int[Action.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        static {
            /*
                com.uacf.sync.provider.sdk.model.SyncItem$Action[] r0 = com.uacf.sync.provider.sdk.model.SyncItem.Action.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$uacf$sync$provider$sdk$model$SyncItem$Action = r0
                int[] r0 = $SwitchMap$com$uacf$sync$provider$sdk$model$SyncItem$Action     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.uacf.sync.provider.sdk.model.SyncItem$Action r1 = com.uacf.sync.provider.sdk.model.SyncItem.Action.Delete     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = $SwitchMap$com$uacf$sync$provider$sdk$model$SyncItem$Action     // Catch:{ NoSuchFieldError -> 0x001f }
                com.uacf.sync.provider.sdk.model.SyncItem$Action r1 = com.uacf.sync.provider.sdk.model.SyncItem.Action.Create     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = $SwitchMap$com$uacf$sync$provider$sdk$model$SyncItem$Action     // Catch:{ NoSuchFieldError -> 0x002a }
                com.uacf.sync.provider.sdk.model.SyncItem$Action r1 = com.uacf.sync.provider.sdk.model.SyncItem.Action.Update     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.userapplicationsettings.service.UserApplicationSettingsServiceImpl.AnonymousClass3.<clinit>():void");
        }
    }

    private static class UpsertContext {
        final UserApplicationSetting newValue;
        final UserApplicationSetting oldValue;

        public UpsertContext(UserApplicationSetting userApplicationSetting, UserApplicationSetting userApplicationSetting2) {
            this.newValue = userApplicationSetting;
            this.oldValue = userApplicationSetting2;
        }
    }

    public String getSyncResourceName() {
        return "user_application_settings";
    }

    public UserApplicationSettingsServiceImpl(@NonNull Provider<MfpV2Api> provider, @NonNull Lazy<SQLiteDatabaseWrapper> lazy, @NonNull Lazy<LocalSettingsService> lazy2, @NonNull Session session2) {
        this.userApi = provider;
        this.session = session2;
        this.userApplicationSettingsTable = new UserApplicationSettingsTable((SQLiteDatabaseWrapper) lazy.get());
        this.cachedSettings = new ConcurrentHashMap();
        this.localSettingsService = lazy2;
        ensureWorkQueueRunning();
    }

    public Class<?> getSyncItemClass() {
        return UserApplicationSettingsResponse.class;
    }

    public void consumeSyncItems(List<SyncItem<UserApplicationSettingsResponse>> list) {
        Enumerable.forEach((Collection<T>) list, (Function1<T>) new Function1<SyncItem<UserApplicationSettingsResponse>>() {
            public void execute(SyncItem<UserApplicationSettingsResponse> syncItem) {
                if (syncItem.getAction() != null) {
                    switch (AnonymousClass3.$SwitchMap$com$uacf$sync$provider$sdk$model$SyncItem$Action[syncItem.getAction().ordinal()]) {
                        case 1:
                            UserApplicationSettingsServiceImpl.this.scheduleDelete((UserApplicationSettingsResponse) syncItem.getItem());
                            break;
                        case 2:
                        case 3:
                            UserApplicationSettingsServiceImpl.this.scheduleUpsert((UserApplicationSettingsResponse) syncItem.getItem());
                            break;
                    }
                }
            }
        });
    }

    public void publishUnsyncedItems(Function2<Integer, Integer> function2) throws UacfScheduleException {
        Map fetchSettingsToSync = fetchSettingsToSync();
        if (CollectionUtils.notEmpty(fetchSettingsToSync)) {
            UserApplicationSettingsPatch userApplicationSettingsPatch = new UserApplicationSettingsPatch(this.session.getUser().getUserId(), fetchSettingsToSync);
            try {
                scheduleUpsert((UserApplicationSettingsResponse) ((ApiResponse) ((MfpV2Api) ((MfpV2Api) ((MfpV2Api) this.userApi.get()).withOutputType(API_RESPONSE_MAPPER.class)).withJsonBody(new ApiRequest(userApplicationSettingsPatch))).patch(String.format(URL_PATCH_USER_APPLICATION_SETTINGS, new Object[]{userApplicationSettingsPatch.getUserId()}))).getItem());
            } catch (ApiException e) {
                throw new UacfScheduleException(e);
            }
        }
    }

    public boolean isMealGoalsBuyUpsellDismissed() {
        return getBoolean(SETTING_MEAL_GOALS_BUY_UPSELL_DISMISSED, false);
    }

    public void setMealGoalsBuyUpsellDismissed(boolean z) {
        setBoolean(SETTING_MEAL_GOALS_BUY_UPSELL_DISMISSED, z);
    }

    public boolean arePremiumTrialsEnabled() {
        return getBoolean(SETTING_PREMIUM_TRIALS_ENABLED, false);
    }

    public void setPremiumTrialsEnabled(boolean z) {
        setBoolean(SETTING_PREMIUM_TRIALS_ENABLED, z);
    }

    public boolean isAutoplayNewsfeedAdsEnabled() {
        if (!settingExists(SETTING_AUTOPLAY_NEWSFEED_ADS)) {
            setBoolean(SETTING_AUTOPLAY_NEWSFEED_ADS, ((LocalSettingsService) this.localSettingsService.get()).shouldNativeAdVideoAutoPlay());
        }
        return getBoolean(SETTING_AUTOPLAY_NEWSFEED_ADS, false);
    }

    public boolean isAutoplayNewsfeedVideoSettingExists() {
        return settingExists(SETTING_AUTOPLAY_NEWSFEED_VIDEOS);
    }

    public void setAutoplayNewsfeedAdsEnabled(boolean z) {
        setBoolean(SETTING_AUTOPLAY_NEWSFEED_ADS, z);
    }

    public boolean isAutoplayNewsfeedVideosEnabled() {
        return getBoolean(SETTING_AUTOPLAY_NEWSFEED_VIDEOS, false);
    }

    public void setAutoplayNewsfeedVideosEnabled(boolean z) {
        setBoolean(SETTING_AUTOPLAY_NEWSFEED_VIDEOS, z);
    }

    public boolean isBottomBarOnboardingDismissed() {
        return getBoolean(SETTING_BOTTOM_BAR_ONBOARDING_DISMISSED, false);
    }

    public void setBottomBarOnboardingDismissed(boolean z) {
        setBoolean(SETTING_BOTTOM_BAR_ONBOARDING_DISMISSED, z);
    }

    public boolean didSeeAlexaInterstitialForLogWater() {
        return getBoolean(SETTING_ALEXA_INTERSTITIAL_LOG_WATER_SEEN, false);
    }

    public void setDidSeeAlexaInterstitialForLogWater(boolean z) {
        setBoolean(SETTING_ALEXA_INTERSTITIAL_LOG_WATER_SEEN, z);
    }

    public boolean didSeeAlexaInterstitialForLogWeight() {
        return getBoolean(SETTING_ALEXA_INTERSTITIAL_LOG_WEIGHT_SEEN, false);
    }

    public void setDidSeeAlexaInterstitialForLogWeight(boolean z) {
        setBoolean(SETTING_ALEXA_INTERSTITIAL_LOG_WEIGHT_SEEN, z);
    }

    /* JADX WARNING: type inference failed for: r0v2, types: [java.lang.String[], android.database.Cursor] */
    /* JADX WARNING: Incorrect condition in loop: B:7:0x0028 */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v2, types: [java.lang.String[], android.database.Cursor]
  assigns: [?[int, float, boolean, short, byte, char, OBJECT, ARRAY]]
  uses: [?[int, boolean, OBJECT, ARRAY, byte, short, char], android.database.Cursor, java.lang.String[]]
  mth insns count: 41
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void preCacheValues() {
        /*
            r10 = this;
            monitor-enter(r10)
            java.util.Map<java.lang.String, com.myfitnesspal.feature.userapplicationsettings.response.UserApplicationSetting> r0 = r10.cachedSettings     // Catch:{ all -> 0x0068 }
            r0.clear()     // Catch:{ all -> 0x0068 }
            r0 = 0
            com.myfitnesspal.shared.db.table.UserApplicationSettingsTable r1 = r10.userApplicationSettingsTable     // Catch:{ all -> 0x0061 }
            java.lang.String r2 = "user_id=?"
            r3 = 1
            java.lang.Object[] r4 = new java.lang.Object[r3]     // Catch:{ all -> 0x0061 }
            r5 = 0
            com.myfitnesspal.shared.service.session.Session r6 = r10.session     // Catch:{ all -> 0x0061 }
            com.myfitnesspal.shared.model.User r6 = r6.getUser()     // Catch:{ all -> 0x0061 }
            java.lang.String r6 = r6.getUid()     // Catch:{ all -> 0x0061 }
            r4[r5] = r6     // Catch:{ all -> 0x0061 }
            android.database.Cursor r0 = r1.queryData(r0, r2, r4)     // Catch:{ all -> 0x0061 }
            com.uacf.core.database.CursorMapper r1 = new com.uacf.core.database.CursorMapper     // Catch:{ all -> 0x0061 }
            r1.<init>(r0)     // Catch:{ all -> 0x0061 }
        L_0x0024:
            boolean r2 = r0.moveToNext()     // Catch:{ all -> 0x0061 }
            if (r2 == 0) goto L_0x0058
            com.myfitnesspal.feature.userapplicationsettings.response.UserApplicationSetting r2 = new com.myfitnesspal.feature.userapplicationsettings.response.UserApplicationSetting     // Catch:{ all -> 0x0061 }
            java.lang.String r4 = "name"
            java.lang.String r5 = r1.getString(r4)     // Catch:{ all -> 0x0061 }
            java.lang.String r4 = "user_id"
            java.lang.String r6 = r1.getString(r4)     // Catch:{ all -> 0x0061 }
            java.lang.String r4 = "value"
            java.lang.String r7 = r1.getString(r4)     // Catch:{ all -> 0x0061 }
            java.lang.String r4 = "type"
            java.lang.String r8 = r1.getString(r4)     // Catch:{ all -> 0x0061 }
            java.lang.String r4 = "updated_at"
            java.lang.String r9 = r1.getString(r4)     // Catch:{ all -> 0x0061 }
            r4 = r2
            r4.<init>(r5, r6, r7, r8, r9)     // Catch:{ all -> 0x0061 }
            java.util.Map<java.lang.String, com.myfitnesspal.feature.userapplicationsettings.response.UserApplicationSetting> r4 = r10.cachedSettings     // Catch:{ all -> 0x0061 }
            java.lang.String r5 = r2.getName()     // Catch:{ all -> 0x0061 }
            r4.put(r5, r2)     // Catch:{ all -> 0x0061 }
            goto L_0x0024
        L_0x0058:
            if (r0 == 0) goto L_0x005d
            r0.close()     // Catch:{ all -> 0x0068 }
        L_0x005d:
            r10.cacheWarmed = r3     // Catch:{ all -> 0x0068 }
            monitor-exit(r10)
            return
        L_0x0061:
            r1 = move-exception
            if (r0 == 0) goto L_0x0067
            r0.close()     // Catch:{ all -> 0x0068 }
        L_0x0067:
            throw r1     // Catch:{ all -> 0x0068 }
        L_0x0068:
            r0 = move-exception
            monitor-exit(r10)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.userapplicationsettings.service.UserApplicationSettingsServiceImpl.preCacheValues():void");
    }

    private synchronized void ensureCacheIsWarm() {
        if (!this.cacheWarmed) {
            preCacheValues();
        }
    }

    private synchronized UserApplicationSetting get(String str) {
        ensureCacheIsWarm();
        return (UserApplicationSetting) this.cachedSettings.get(str);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001e, code lost:
        return r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized boolean getBoolean(java.lang.String r3, boolean r4) {
        /*
            r2 = this;
            monitor-enter(r2)
            com.myfitnesspal.feature.userapplicationsettings.response.UserApplicationSetting r3 = r2.get(r3)     // Catch:{ all -> 0x001f }
            if (r3 == 0) goto L_0x001d
            java.lang.String r0 = r3.getType()     // Catch:{ all -> 0x001f }
            java.lang.String r1 = "boolean"
            boolean r0 = com.uacf.core.util.Strings.equals(r0, r1)     // Catch:{ all -> 0x001f }
            if (r0 == 0) goto L_0x001d
            java.lang.String r3 = r3.getValue()     // Catch:{ all -> 0x001f }
            boolean r3 = com.uacf.core.util.Strings.toBoolean(r3)     // Catch:{ all -> 0x001f }
            monitor-exit(r2)
            return r3
        L_0x001d:
            monitor-exit(r2)
            return r4
        L_0x001f:
            r3 = move-exception
            monitor-exit(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.userapplicationsettings.service.UserApplicationSettingsServiceImpl.getBoolean(java.lang.String, boolean):boolean");
    }

    private void setBoolean(String str, boolean z) {
        UserApplicationSetting userApplicationSetting = new UserApplicationSetting(str, this.session.getUser().getUserId(), Strings.toString(Boolean.valueOf(z)), "boolean", DateTimeUtils.getDateStringFromDate(new Date()), Integer.valueOf(1));
        scheduleUpsert(userApplicationSetting);
    }

    /* access modifiers changed from: private */
    public void scheduleUpsert(UserApplicationSettingsResponse userApplicationSettingsResponse) {
        if (userApplicationSettingsResponse != null) {
            Map settings = userApplicationSettingsResponse.getSettings();
            if (CollectionUtils.notEmpty(settings)) {
                for (Entry entry : settings.entrySet()) {
                    UserApplicationSetting userApplicationSetting = (UserApplicationSetting) entry.getValue();
                    userApplicationSetting.setName((String) entry.getKey());
                    userApplicationSetting.setUserId(this.session.getUser().getUserId());
                    userApplicationSetting.setUpdatedAt(userApplicationSettingsResponse.getUpdatedAt());
                    userApplicationSetting.setSyncFlag(Integer.valueOf(0));
                    scheduleUpsert(userApplicationSetting);
                }
            }
        }
    }

    private synchronized void scheduleUpsert(UserApplicationSetting userApplicationSetting) {
        ensureWorkQueueRunning();
        UserApplicationSetting userApplicationSetting2 = (UserApplicationSetting) this.cachedSettings.get(userApplicationSetting.getName());
        this.cachedSettings.put(userApplicationSetting.getName(), userApplicationSetting);
        this.workHandler.sendMessage(Message.obtain(this.workHandler, 1, new UpsertContext(userApplicationSetting, userApplicationSetting2)));
    }

    /* access modifiers changed from: private */
    public boolean upsert(UserApplicationSetting userApplicationSetting) {
        String name = userApplicationSetting.getName();
        ContentValues contentValuesForUpdateOrInsert = UserApplicationSettingsTable.getContentValuesForUpdateOrInsert(userApplicationSetting);
        boolean z = true;
        String format = String.format("%s=?", new Object[]{"name"});
        if (contentValuesForUpdateOrInsert != null) {
            if (this.userApplicationSettingsTable.updateData(contentValuesForUpdateOrInsert, format, name) == 0) {
                if (this.userApplicationSettingsTable.insertData(contentValuesForUpdateOrInsert) == -1) {
                    z = false;
                }
                return z;
            }
        }
        return true;
    }

    /* access modifiers changed from: private */
    public void scheduleDelete(UserApplicationSettingsResponse userApplicationSettingsResponse) {
        Map settings = userApplicationSettingsResponse.getSettings();
        if (CollectionUtils.notEmpty(settings)) {
            for (Entry entry : settings.entrySet()) {
                UserApplicationSetting userApplicationSetting = (UserApplicationSetting) entry.getValue();
                userApplicationSetting.setName((String) entry.getKey());
                scheduleDelete(userApplicationSetting.getName());
            }
        }
    }

    private synchronized void scheduleDelete(String str) {
        ensureWorkQueueRunning();
        this.cachedSettings.remove(str);
        this.workHandler.sendMessage(Message.obtain(this.workHandler, 1, str));
    }

    /* access modifiers changed from: private */
    public void delete(String str) {
        this.userApplicationSettingsTable.deleteData("name=? AND user_id=?", str, this.session.getUser().getUserId());
    }

    private synchronized void ensureWorkQueueRunning() {
        if (this.handlerThread == null) {
            this.handlerThread = new HandlerThread("UserApplicationSettingsService.Writer");
            this.handlerThread.start();
            this.workHandler = new Handler(this.handlerThread.getLooper()) {
                public void handleMessage(Message message) {
                    switch (message.what) {
                        case 1:
                            UpsertContext upsertContext = (UpsertContext) message.obj;
                            if (upsertContext != null && upsertContext.newValue != null && !UserApplicationSettingsServiceImpl.this.upsert(upsertContext.newValue)) {
                                synchronized (UserApplicationSettingsServiceImpl.this) {
                                    UserApplicationSettingsServiceImpl.this.cachedSettings.put(upsertContext.newValue.getName(), upsertContext.oldValue);
                                }
                                return;
                            }
                            return;
                        case 2:
                            UserApplicationSettingsServiceImpl.this.delete((String) message.obj);
                            return;
                        case 3:
                            synchronized (UserApplicationSettingsServiceImpl.this) {
                                UserApplicationSettingsServiceImpl.this.handlerThread.quit();
                                UserApplicationSettingsServiceImpl.this.handlerThread = null;
                                UserApplicationSettingsServiceImpl.this.workHandler = null;
                            }
                            return;
                        default:
                            return;
                    }
                }
            };
        }
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: type inference failed for: r1v0, types: [java.lang.String[], android.database.Cursor] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r1v0, types: [java.lang.String[], android.database.Cursor]
  assigns: [?[int, float, boolean, short, byte, char, OBJECT, ARRAY]]
  uses: [?[int, boolean, OBJECT, ARRAY, byte, short, char], android.database.Cursor, java.lang.String[]]
  mth insns count: 38
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.Map<java.lang.String, com.myfitnesspal.feature.userapplicationsettings.response.UserApplicationSetting> fetchSettingsToSync() {
        /*
            r15 = this;
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            r1 = 0
            com.myfitnesspal.shared.db.table.UserApplicationSettingsTable r2 = r15.userApplicationSettingsTable     // Catch:{ all -> 0x0067 }
            java.lang.String r3 = "user_id = ? AND sync_flag != 0"
            r4 = 1
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ all -> 0x0067 }
            r5 = 0
            com.myfitnesspal.shared.service.session.Session r6 = r15.session     // Catch:{ all -> 0x0067 }
            com.myfitnesspal.shared.model.User r6 = r6.getUser()     // Catch:{ all -> 0x0067 }
            java.lang.String r6 = r6.getUserId()     // Catch:{ all -> 0x0067 }
            r4[r5] = r6     // Catch:{ all -> 0x0067 }
            android.database.Cursor r1 = r2.queryData(r1, r3, r4)     // Catch:{ all -> 0x0067 }
            java.lang.String r2 = "name"
            int r2 = r1.getColumnIndex(r2)     // Catch:{ all -> 0x0067 }
            java.lang.String r3 = "user_id"
            int r3 = r1.getColumnIndex(r3)     // Catch:{ all -> 0x0067 }
            java.lang.String r4 = "value"
            int r4 = r1.getColumnIndex(r4)     // Catch:{ all -> 0x0067 }
            java.lang.String r5 = "type"
            int r5 = r1.getColumnIndex(r5)     // Catch:{ all -> 0x0067 }
            java.lang.String r6 = "updated_at"
            int r6 = r1.getColumnIndex(r6)     // Catch:{ all -> 0x0067 }
        L_0x003c:
            boolean r7 = r1.moveToNext()     // Catch:{ all -> 0x0067 }
            if (r7 == 0) goto L_0x0061
            java.lang.String r7 = r1.getString(r2)     // Catch:{ all -> 0x0067 }
            com.myfitnesspal.feature.userapplicationsettings.response.UserApplicationSetting r14 = new com.myfitnesspal.feature.userapplicationsettings.response.UserApplicationSetting     // Catch:{ all -> 0x0067 }
            java.lang.String r10 = r1.getString(r3)     // Catch:{ all -> 0x0067 }
            java.lang.String r11 = r1.getString(r4)     // Catch:{ all -> 0x0067 }
            java.lang.String r12 = r1.getString(r5)     // Catch:{ all -> 0x0067 }
            java.lang.String r13 = r1.getString(r6)     // Catch:{ all -> 0x0067 }
            r8 = r14
            r9 = r7
            r8.<init>(r9, r10, r11, r12, r13)     // Catch:{ all -> 0x0067 }
            r0.put(r7, r14)     // Catch:{ all -> 0x0067 }
            goto L_0x003c
        L_0x0061:
            if (r1 == 0) goto L_0x0066
            r1.close()
        L_0x0066:
            return r0
        L_0x0067:
            r0 = move-exception
            if (r1 == 0) goto L_0x006d
            r1.close()
        L_0x006d:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.userapplicationsettings.service.UserApplicationSettingsServiceImpl.fetchSettingsToSync():java.util.Map");
    }

    private synchronized boolean settingExists(@NonNull String str) {
        return get(str) != null;
    }
}
