package com.myfitnesspal.feature.externalsync.impl.shealth.service;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import com.facebook.internal.ServerProtocol;
import com.myfitnesspal.app.MyFitnessPalApp;
import com.myfitnesspal.feature.externalsync.impl.shealth.constants.SHealthConstants.StepDailyTrend;
import com.myfitnesspal.feature.externalsync.impl.shealth.event.SHealthConnectionErrorEvent;
import com.myfitnesspal.feature.externalsync.impl.shealth.event.SHealthConnectionEvent;
import com.myfitnesspal.feature.externalsync.impl.shealth.event.SHealthNoPermissionEvent;
import com.myfitnesspal.feature.externalsync.impl.shealth.event.SHealthNoPermissionEvent.Error;
import com.myfitnesspal.feature.externalsync.impl.shealth.util.SHealthUtil;
import com.myfitnesspal.feature.externalsync.service.WaitCondition;
import com.myfitnesspal.feature.externalsync.task.RegisterStepSourceTask;
import com.myfitnesspal.shared.constants.Constants.Analytics;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.steps.StepService;
import com.myfitnesspal.shared.util.ConfigUtils;
import com.samsung.android.sdk.healthdata.HealthConnectionErrorResult;
import com.samsung.android.sdk.healthdata.HealthConstants.Exercise;
import com.samsung.android.sdk.healthdata.HealthConstants.FoodInfo;
import com.samsung.android.sdk.healthdata.HealthConstants.FoodIntake;
import com.samsung.android.sdk.healthdata.HealthConstants.StepCount;
import com.samsung.android.sdk.healthdata.HealthConstants.Weight;
import com.samsung.android.sdk.healthdata.HealthDataService;
import com.samsung.android.sdk.healthdata.HealthDataStore;
import com.samsung.android.sdk.healthdata.HealthPermissionManager;
import com.samsung.android.sdk.healthdata.HealthPermissionManager.PermissionKey;
import com.samsung.android.sdk.healthdata.HealthPermissionManager.PermissionResult;
import com.samsung.android.sdk.healthdata.HealthPermissionManager.PermissionType;
import com.samsung.android.sdk.healthdata.HealthResultHolder.ResultListener;
import com.squareup.otto.Bus;
import com.uacf.core.preferences.KeyedSharedPreferences;
import com.uacf.core.util.Ln;
import com.uacf.core.util.MapUtil;
import com.uacf.core.util.Strings;
import com.uacf.taskrunner.Runner;
import com.uacf.taskrunner.Runner.CacheMode;
import com.uacf.taskrunner.Runner.DedupeMode;
import dagger.Lazy;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SHealthConnection {
    private static final String EVENT_SHEALTH_CANCELLED = "samsung_shealth_cancelled";
    private static final String EVENT_SHEALTH_CONNECTED = "samsung_shealth_connected";
    private static final String EVENT_SHEALTH_CONNECT_ERROR = "samsung_shealth_connect_error";
    private static final int MESSAGE_CONNECT = 1024;
    private static final String PAIRING_STATE = "pairing_state";
    /* access modifiers changed from: private */
    public static final PermissionKey READ_EXERCISE_PERMISSION = new PermissionKey(Exercise.HEALTH_DATA_TYPE, PermissionType.READ);
    /* access modifiers changed from: private */
    public static final PermissionKey READ_FOOD_INFO_PERMISSION = new PermissionKey(FoodInfo.HEALTH_DATA_TYPE, PermissionType.READ);
    /* access modifiers changed from: private */
    public static final PermissionKey READ_FOOD_PERMISSION = new PermissionKey(FoodIntake.HEALTH_DATA_TYPE, PermissionType.READ);
    /* access modifiers changed from: private */
    public static final PermissionKey READ_STEPS_PERMISSION = new PermissionKey(StepCount.HEALTH_DATA_TYPE, PermissionType.READ);
    /* access modifiers changed from: private */
    public static final PermissionKey READ_STEP_TREND_PERMISSION = new PermissionKey(StepDailyTrend.HEALTH_DATA_TYPE, PermissionType.READ);
    /* access modifiers changed from: private */
    public static final PermissionKey READ_WEIGHT_PERMISSION = new PermissionKey(Weight.HEALTH_DATA_TYPE, PermissionType.READ);
    private static final Map<Integer, String> SHEALTH_ERROR_CODES = new HashMap();
    private static final Set<PermissionKey> S_HEALTH_PERMISSIONS = new HashSet();
    /* access modifiers changed from: private */
    public static final PermissionKey WRITE_EXERCISE_PERMISSION = new PermissionKey(Exercise.HEALTH_DATA_TYPE, PermissionType.WRITE);
    /* access modifiers changed from: private */
    public static final PermissionKey WRITE_FOOD_INFO_PERMISSION = new PermissionKey(FoodInfo.HEALTH_DATA_TYPE, PermissionType.WRITE);
    /* access modifiers changed from: private */
    public static final PermissionKey WRITE_FOOD_PERMISSION = new PermissionKey(FoodIntake.HEALTH_DATA_TYPE, PermissionType.WRITE);
    /* access modifiers changed from: private */
    public static final PermissionKey WRITE_STEPS_PERMISSION = new PermissionKey(StepCount.HEALTH_DATA_TYPE, PermissionType.WRITE);
    /* access modifiers changed from: private */
    public static final PermissionKey WRITE_WEIGHT_PERMISSION = new PermissionKey(Weight.HEALTH_DATA_TYPE, PermissionType.WRITE);
    /* access modifiers changed from: private */
    public ConnectionListener activeListener;
    private final Lazy<AnalyticsService> analytics;
    /* access modifiers changed from: private */
    public final Bus bus;
    private final Lazy<ConfigService> configService;
    private Handler connectHandler;
    private final Context context;
    private HealthDataService healthDataService;
    /* access modifiers changed from: private */
    public HealthDataStore healthDataStore;
    /* access modifiers changed from: private */
    public HealthPermissionManager healthPermissionManager;
    private final Lazy<KeyedSharedPreferences> prefs;
    private final Lazy<Session> session;
    private State state;
    private final Lazy<StepService> stepService;
    /* access modifiers changed from: private */
    public final WaitCondition waitForConnect = new WaitCondition();

    private class ConnectionListener implements com.samsung.android.sdk.healthdata.HealthDataStore.ConnectionListener {
        private ConnectionListener() {
        }

        public void onConnected() {
            if (SHealthConnection.this.activeListener == this) {
                SHealthConnection sHealthConnection = SHealthConnection.this;
                sHealthConnection.healthPermissionManager = new HealthPermissionManager(sHealthConnection.healthDataStore);
                SHealthConnection.this.setState(State.Connected);
                boolean z = false;
                if (SHealthConnection.this.getPairState() == 1) {
                    SHealthConnection.this.setPairState(2);
                    z = true;
                }
                SHealthConnection.this.bus.post(new SHealthConnectionEvent(true, z));
            }
            SHealthConnection.this.waitForConnect.complete();
        }

        public void onConnectionFailed(HealthConnectionErrorResult healthConnectionErrorResult) {
            if (SHealthConnection.this.activeListener == this) {
                SHealthConnection.this.unpairIfPairing();
                SHealthConnection.this.disconnect();
                SHealthConnection.this.reportConnetionError(healthConnectionErrorResult);
                SHealthConnection.this.bus.post(new SHealthConnectionErrorEvent(healthConnectionErrorResult));
            }
            SHealthConnection.this.waitForConnect.complete();
        }

        public void onDisconnected() {
            if (SHealthConnection.this.activeListener == this) {
                SHealthConnection.this.unpairIfPairing();
                SHealthConnection.this.disconnect();
            }
            SHealthConnection.this.waitForConnect.complete();
        }
    }

    private interface PairState {
        public static final int Paired = 2;
        public static final int Pairing = 1;
        public static final int Unpaired = 0;
    }

    public enum Permission {
        ReadSteps(SHealthConnection.READ_STEPS_PERMISSION),
        WriteSteps(SHealthConnection.WRITE_STEPS_PERMISSION),
        ReadStepsTrend(SHealthConnection.READ_STEP_TREND_PERMISSION),
        ReadFood(SHealthConnection.READ_FOOD_PERMISSION),
        WriteFood(SHealthConnection.WRITE_FOOD_PERMISSION),
        ReadFoodEntry(SHealthConnection.READ_FOOD_INFO_PERMISSION),
        WriteFoodEntry(SHealthConnection.WRITE_FOOD_INFO_PERMISSION),
        ReadExercise(SHealthConnection.READ_EXERCISE_PERMISSION),
        WriteExercise(SHealthConnection.WRITE_EXERCISE_PERMISSION),
        ReadWeight(SHealthConnection.READ_WEIGHT_PERMISSION),
        WriteWeight(SHealthConnection.WRITE_WEIGHT_PERMISSION);
        
        final String dataType;
        final Set<PermissionKey> permissionKeySet;
        final PermissionType permissionType;

        private Permission(PermissionKey permissionKey) {
            this.dataType = permissionKey.getDataType();
            this.permissionType = permissionKey.getPermissionType();
            this.permissionKeySet = new HashSet();
            this.permissionKeySet.add(permissionKey);
        }

        public static Permission findByName(PermissionKey permissionKey) {
            Permission[] values;
            if (permissionKey == null) {
                return null;
            }
            for (Permission permission : values()) {
                if (permission.dataType.equals(permissionKey.getDataType()) && permission.permissionType == permissionKey.getPermissionType()) {
                    return permission;
                }
            }
            return null;
        }

        /* access modifiers changed from: private */
        public Set<PermissionKey> getPermissionKeySet() {
            return this.permissionKeySet;
        }

        /* access modifiers changed from: private */
        public String getDataType() {
            return this.dataType;
        }
    }

    public enum PermissionState {
        Unknown,
        Granted,
        Denied
    }

    public enum State {
        Disconnected,
        Connecting,
        Connected,
        Disconnecting
    }

    static {
        S_HEALTH_PERMISSIONS.add(READ_STEP_TREND_PERMISSION);
        S_HEALTH_PERMISSIONS.add(READ_FOOD_PERMISSION);
        S_HEALTH_PERMISSIONS.add(WRITE_FOOD_PERMISSION);
        S_HEALTH_PERMISSIONS.add(READ_FOOD_INFO_PERMISSION);
        S_HEALTH_PERMISSIONS.add(WRITE_FOOD_INFO_PERMISSION);
        S_HEALTH_PERMISSIONS.add(READ_EXERCISE_PERMISSION);
        S_HEALTH_PERMISSIONS.add(WRITE_EXERCISE_PERMISSION);
        S_HEALTH_PERMISSIONS.add(READ_WEIGHT_PERMISSION);
        S_HEALTH_PERMISSIONS.add(WRITE_WEIGHT_PERMISSION);
        SHEALTH_ERROR_CODES.put(Integer.valueOf(0), Analytics.UNKNOWN);
        SHEALTH_ERROR_CODES.put(Integer.valueOf(1), ServerProtocol.errorConnectionFailure);
        SHEALTH_ERROR_CODES.put(Integer.valueOf(2), "PLATFORM_NOT_INSTALLED");
        SHEALTH_ERROR_CODES.put(Integer.valueOf(3), "OLD_VERSION_SDK");
        SHEALTH_ERROR_CODES.put(Integer.valueOf(4), "OLD_VERSION_PLATFORM");
        SHEALTH_ERROR_CODES.put(Integer.valueOf(5), "TIMEOUT");
        SHEALTH_ERROR_CODES.put(Integer.valueOf(6), "PLATFORM_DISABLED");
        SHEALTH_ERROR_CODES.put(Integer.valueOf(7), "USER_PASSWORD_NEEDED");
        SHEALTH_ERROR_CODES.put(Integer.valueOf(8), "PLATFORM_SIGNATURE_FAILURE");
        SHEALTH_ERROR_CODES.put(Integer.valueOf(9), "USER_AGREEMENT_NEEDED");
        SHEALTH_ERROR_CODES.put(Integer.valueOf(-1), "SUCCESS");
        SHEALTH_ERROR_CODES.put(Integer.valueOf(-2), "PLATFORM_INITIALIZING");
        SHEALTH_ERROR_CODES.put(Integer.valueOf(-3), "USER_PASSWORD_POPUP");
    }

    public SHealthConnection(Lazy<Session> lazy, Lazy<AnalyticsService> lazy2, Lazy<KeyedSharedPreferences> lazy3, Lazy<StepService> lazy4, Lazy<ConfigService> lazy5, Bus bus2) {
        this.session = lazy;
        this.analytics = lazy2;
        this.prefs = lazy3;
        this.stepService = lazy4;
        this.configService = lazy5;
        this.bus = bus2;
        this.context = MyFitnessPalApp.getInstance();
        this.state = State.Disconnected;
        HandlerThread handlerThread = new HandlerThread("SHealthConnection");
        handlerThread.start();
        this.connectHandler = new Handler(handlerThread.getLooper()) {
            public void handleMessage(Message message) {
                if (message.what == SHealthConnection.MESSAGE_CONNECT) {
                    SHealthConnection.this.connectInternal();
                }
            }
        };
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0055, code lost:
        return ((java.lang.Boolean) r1.getValue()).booleanValue() ? com.myfitnesspal.feature.externalsync.impl.shealth.service.SHealthConnection.PermissionState.Granted : com.myfitnesspal.feature.externalsync.impl.shealth.service.SHealthConnection.PermissionState.Denied;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized com.myfitnesspal.feature.externalsync.impl.shealth.service.SHealthConnection.PermissionState getPermissionState(com.myfitnesspal.feature.externalsync.impl.shealth.service.SHealthConnection.Permission r5) {
        /*
            r4 = this;
            monitor-enter(r4)
            if (r5 == 0) goto L_0x0056
            com.myfitnesspal.feature.externalsync.impl.shealth.service.SHealthConnection$State r0 = r4.getState()     // Catch:{ all -> 0x005a }
            com.myfitnesspal.feature.externalsync.impl.shealth.service.SHealthConnection$State r1 = com.myfitnesspal.feature.externalsync.impl.shealth.service.SHealthConnection.State.Connected     // Catch:{ all -> 0x005a }
            if (r0 != r1) goto L_0x0056
            com.samsung.android.sdk.healthdata.HealthPermissionManager r0 = r4.healthPermissionManager     // Catch:{ all -> 0x005a }
            if (r0 == 0) goto L_0x0056
            com.samsung.android.sdk.healthdata.HealthPermissionManager r0 = r4.healthPermissionManager     // Catch:{ all -> 0x005a }
            java.util.Set r1 = r5.getPermissionKeySet()     // Catch:{ all -> 0x005a }
            java.util.Map r0 = r0.isPermissionAcquired(r1)     // Catch:{ all -> 0x005a }
            if (r0 == 0) goto L_0x0056
            java.util.Set r0 = r0.entrySet()     // Catch:{ all -> 0x005a }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ all -> 0x005a }
        L_0x0023:
            boolean r1 = r0.hasNext()     // Catch:{ all -> 0x005a }
            if (r1 == 0) goto L_0x0056
            java.lang.Object r1 = r0.next()     // Catch:{ all -> 0x005a }
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1     // Catch:{ all -> 0x005a }
            java.lang.Object r2 = r1.getKey()     // Catch:{ all -> 0x005a }
            com.samsung.android.sdk.healthdata.HealthPermissionManager$PermissionKey r2 = (com.samsung.android.sdk.healthdata.HealthPermissionManager.PermissionKey) r2     // Catch:{ all -> 0x005a }
            java.lang.String r2 = r2.getDataType()     // Catch:{ all -> 0x005a }
            java.lang.String r3 = r5.getDataType()     // Catch:{ all -> 0x005a }
            boolean r2 = r2.equals(r3)     // Catch:{ all -> 0x005a }
            if (r2 == 0) goto L_0x0023
            java.lang.Object r5 = r1.getValue()     // Catch:{ all -> 0x005a }
            java.lang.Boolean r5 = (java.lang.Boolean) r5     // Catch:{ all -> 0x005a }
            boolean r5 = r5.booleanValue()     // Catch:{ all -> 0x005a }
            if (r5 == 0) goto L_0x0052
            com.myfitnesspal.feature.externalsync.impl.shealth.service.SHealthConnection$PermissionState r5 = com.myfitnesspal.feature.externalsync.impl.shealth.service.SHealthConnection.PermissionState.Granted     // Catch:{ all -> 0x005a }
            goto L_0x0054
        L_0x0052:
            com.myfitnesspal.feature.externalsync.impl.shealth.service.SHealthConnection$PermissionState r5 = com.myfitnesspal.feature.externalsync.impl.shealth.service.SHealthConnection.PermissionState.Denied     // Catch:{ all -> 0x005a }
        L_0x0054:
            monitor-exit(r4)
            return r5
        L_0x0056:
            com.myfitnesspal.feature.externalsync.impl.shealth.service.SHealthConnection$PermissionState r5 = com.myfitnesspal.feature.externalsync.impl.shealth.service.SHealthConnection.PermissionState.Unknown     // Catch:{ all -> 0x005a }
            monitor-exit(r4)
            return r5
        L_0x005a:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.externalsync.impl.shealth.service.SHealthConnection.getPermissionState(com.myfitnesspal.feature.externalsync.impl.shealth.service.SHealthConnection$Permission):com.myfitnesspal.feature.externalsync.impl.shealth.service.SHealthConnection$PermissionState");
    }

    public synchronized void pair() {
        setPairState(1);
        disconnect();
        connect();
    }

    public synchronized void unpair() {
        setPairState(0);
        disconnect();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0023, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0025, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean isPaired() {
        /*
            r4 = this;
            monitor-enter(r4)
            int r0 = r4.getPairState()     // Catch:{ all -> 0x0026 }
            com.myfitnesspal.feature.externalsync.impl.shealth.service.SHealthConnection$State r1 = r4.getState()     // Catch:{ all -> 0x0026 }
            com.myfitnesspal.feature.externalsync.impl.shealth.service.SHealthConnection$State r2 = com.myfitnesspal.feature.externalsync.impl.shealth.service.SHealthConnection.State.Connected     // Catch:{ all -> 0x0026 }
            r3 = 0
            if (r1 == r2) goto L_0x0013
            r4.connect()     // Catch:{ all -> 0x0026 }
            monitor-exit(r4)
            return r3
        L_0x0013:
            if (r0 == 0) goto L_0x0024
            boolean r1 = r4.checkPermissions()     // Catch:{ all -> 0x0026 }
            if (r1 == 0) goto L_0x0024
            r1 = 1
            if (r0 != r1) goto L_0x0022
            r0 = 2
            r4.setPairState(r0)     // Catch:{ all -> 0x0026 }
        L_0x0022:
            monitor-exit(r4)
            return r1
        L_0x0024:
            monitor-exit(r4)
            return r3
        L_0x0026:
            r0 = move-exception
            monitor-exit(r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.externalsync.impl.shealth.service.SHealthConnection.isPaired():boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0018, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void connect() {
        /*
            r2 = this;
            monitor-enter(r2)
            boolean r0 = r2.allowedToConnect()     // Catch:{ all -> 0x0019 }
            if (r0 != 0) goto L_0x000c
            r2.disconnect()     // Catch:{ all -> 0x0019 }
            monitor-exit(r2)
            return
        L_0x000c:
            com.myfitnesspal.feature.externalsync.impl.shealth.service.SHealthConnection$State r0 = r2.getState()     // Catch:{ all -> 0x0019 }
            com.myfitnesspal.feature.externalsync.impl.shealth.service.SHealthConnection$State r1 = com.myfitnesspal.feature.externalsync.impl.shealth.service.SHealthConnection.State.Disconnected     // Catch:{ all -> 0x0019 }
            if (r0 != r1) goto L_0x0017
            r2.startConnecting()     // Catch:{ all -> 0x0019 }
        L_0x0017:
            monitor-exit(r2)
            return
        L_0x0019:
            r0 = move-exception
            monitor-exit(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.externalsync.impl.shealth.service.SHealthConnection.connect():void");
    }

    public boolean connectAndWait() {
        boolean z = false;
        if (!allowedToConnect()) {
            disconnect();
            return false;
        }
        switch (getState()) {
            case Connected:
                return true;
            case Connecting:
                this.waitForConnect.await();
                break;
            case Disconnected:
            case Disconnecting:
                startConnecting();
                this.waitForConnect.await();
                break;
        }
        if (getState() == State.Connected) {
            z = true;
        }
        return z;
    }

    public synchronized void disconnect() {
        State state2 = getState();
        if (state2 == State.Connected || state2 == State.Connecting) {
            if (this.healthDataStore != null) {
                this.healthDataStore.disconnectService();
            }
            this.healthPermissionManager = null;
            setState(State.Disconnected);
            this.bus.post(new SHealthConnectionEvent(false));
        }
    }

    public synchronized void startPermissionRequest(Activity activity) {
        if (this.healthPermissionManager != null) {
            this.healthPermissionManager.requestPermissions(S_HEALTH_PERMISSIONS, activity).setResultListener(new ResultListener<PermissionResult>() {
                public void onResult(PermissionResult permissionResult) {
                    SHealthConnection.this.reportConnectionResult(permissionResult);
                    for (Boolean booleanValue : permissionResult.getResultMap().values()) {
                        if (booleanValue.booleanValue()) {
                            Ln.e("user granted at least one permission. good to go!", new Object[0]);
                            return;
                        }
                    }
                    SHealthConnection.this.disconnect();
                }
            });
        }
    }

    public void updateStepSourceRegistration(Runner runner) {
        int pairState = getPairState();
        if (pairState == 2 || pairState == 0) {
            new RegisterStepSourceTask(this.stepService, SHealthUtil.createStepSource((Session) this.session.get()), pairState == 2 ? 0 : 1).setCacheMode(CacheMode.None).setDedupeMode(DedupeMode.CancelExisting).run(runner);
        }
    }

    public HealthDataStore getDataStore() {
        return this.healthDataStore;
    }

    private boolean allowedToConnect() {
        if (ConfigUtils.isSHealthEnabled((ConfigService) this.configService.get())) {
            int pairState = getPairState();
            if (pairState == 1 || pairState == 2) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    public synchronized void connectInternal() {
        if (this.healthDataStore != null) {
            setState(State.Disconnecting);
            this.healthDataStore.disconnectService();
            this.healthDataStore = null;
        }
        if (this.healthDataService != null) {
            setState(State.Connecting);
            this.waitForConnect.reset();
            this.activeListener = new ConnectionListener();
            try {
                this.healthDataStore = new HealthDataStore(this.context, this.activeListener);
                this.healthDataStore.connectService();
            } catch (Exception e) {
                this.waitForConnect.complete();
                throw e;
            }
        }
    }

    private synchronized void startConnecting() {
        if (this.healthDataService == null) {
            this.healthDataService = new HealthDataService();
            try {
                this.healthDataService.initialize(this.context);
            } catch (Exception unused) {
                this.healthDataService = null;
                Ln.e("failed to initialize health data service!", new Object[0]);
            }
        }
        this.connectHandler.sendEmptyMessage(MESSAGE_CONNECT);
    }

    private synchronized boolean checkPermissions() {
        if (this.healthPermissionManager != null) {
            try {
                for (Boolean booleanValue : this.healthPermissionManager.isPermissionAcquired(S_HEALTH_PERMISSIONS).values()) {
                    if (booleanValue.booleanValue()) {
                        Ln.e("user granted at least one permission. good to go!", new Object[0]);
                        return true;
                    }
                }
            } catch (Exception e) {
                Ln.e(e, "failed to check S Health permissions", new Object[0]);
                this.bus.post(new SHealthNoPermissionEvent(Error.UnknownFailure));
                return false;
            }
        }
        Ln.e("user has not granted any permissions! broadcasting an event to the mixin", new Object[0]);
        this.bus.post(new SHealthNoPermissionEvent(Error.NoPermission));
        return false;
    }

    /* access modifiers changed from: private */
    public synchronized void setState(State state2) {
        if (state2 != this.state) {
            this.state = state2;
            Ln.d("state changed to %s", state2);
        }
    }

    private synchronized State getState() {
        return this.state;
    }

    /* access modifiers changed from: private */
    public int getPairState() {
        return ((KeyedSharedPreferences) this.prefs.get()).getInt(PAIRING_STATE, 0);
    }

    /* access modifiers changed from: private */
    public void setPairState(int i) {
        ((KeyedSharedPreferences) this.prefs.get()).edit().putInt(PAIRING_STATE, i).apply();
    }

    /* access modifiers changed from: private */
    public void unpairIfPairing() {
        if (getPairState() == 1) {
            setPairState(0);
        }
    }

    /* access modifiers changed from: private */
    public void reportConnetionError(HealthConnectionErrorResult healthConnectionErrorResult) {
        if (healthConnectionErrorResult != null) {
            String strings = Strings.toString(SHEALTH_ERROR_CODES.get(Integer.valueOf(healthConnectionErrorResult.getErrorCode())));
            ((AnalyticsService) this.analytics.get()).reportEvent(EVENT_SHEALTH_CONNECT_ERROR, MapUtil.createMap("reason", strings));
        }
    }

    /* access modifiers changed from: private */
    public void reportConnectionResult(PermissionResult permissionResult) {
        HashMap hashMap = new HashMap();
        boolean z = false;
        for (PermissionKey findByName : S_HEALTH_PERMISSIONS) {
            Permission findByName2 = Permission.findByName(findByName);
            if (findByName2 != null) {
                PermissionState permissionState = getPermissionState(findByName2);
                z |= permissionState == PermissionState.Granted;
                hashMap.put(findByName2.toString(), permissionState.toString());
            }
        }
        if (z) {
            ((AnalyticsService) this.analytics.get()).reportEvent(EVENT_SHEALTH_CONNECTED, (Map<String, String>) hashMap);
        } else {
            ((AnalyticsService) this.analytics.get()).reportEvent(EVENT_SHEALTH_CANCELLED);
        }
    }
}
