package com.amplitude.api;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.location.Location;
import android.os.Build.VERSION;
import android.util.Pair;
import com.brightcove.player.event.AbstractEvent;
import com.google.common.base.Ascii;
import com.myfitnesspal.shared.constants.Constants.Ads.Keywords;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.constants.SharedConstants.Http;
import com.myfitnesspal.shared.service.device.UserAgentProviderImpl.Params;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import okhttp3.OkHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AmplitudeClient {
    /* access modifiers changed from: private */
    public static final AmplitudeLog logger = AmplitudeLog.getLogger();
    protected String apiKey;
    /* access modifiers changed from: private */
    public boolean backoffUpload;
    /* access modifiers changed from: private */
    public int backoffUploadBatchSize;
    protected Context context;
    protected DatabaseHelper dbHelper;
    protected String deviceId;
    private DeviceInfo deviceInfo;
    private int eventMaxCount;
    /* access modifiers changed from: private */
    public int eventUploadMaxBatchSize;
    private long eventUploadPeriodMillis;
    /* access modifiers changed from: private */
    public int eventUploadThreshold;
    /* access modifiers changed from: private */
    public boolean flushEventsOnClose;
    protected OkHttpClient httpClient;
    WorkerThread httpThread;
    /* access modifiers changed from: private */
    public boolean inForeground;
    protected boolean initialized;
    protected String instanceName;
    Throwable lastError;
    long lastEventId;
    long lastEventTime;
    long lastIdentifyId;
    WorkerThread logThread;
    private long minTimeBetweenSessionsMillis;
    private boolean newDeviceIdPerInstall;
    private boolean offline;
    /* access modifiers changed from: private */
    public boolean optOut;
    protected String platform;
    long previousSessionId;
    long sequenceNumber;
    long sessionId;
    private long sessionTimeoutMillis;
    /* access modifiers changed from: private */
    public boolean trackingSessionEvents;
    /* access modifiers changed from: private */
    public AtomicBoolean updateScheduled;
    AtomicBoolean uploadingCurrently;
    String url;
    private boolean useAdvertisingIdForDeviceId;
    protected String userId;
    private boolean usingForegroundTracking;

    public AmplitudeClient() {
        this(null);
    }

    public AmplitudeClient(String str) {
        this.newDeviceIdPerInstall = false;
        this.useAdvertisingIdForDeviceId = false;
        this.initialized = false;
        this.optOut = false;
        this.offline = false;
        this.sessionId = -1;
        this.sequenceNumber = 0;
        this.lastEventId = -1;
        this.lastIdentifyId = -1;
        this.lastEventTime = -1;
        this.previousSessionId = -1;
        this.eventUploadThreshold = 30;
        this.eventUploadMaxBatchSize = 50;
        this.eventMaxCount = 1000;
        this.eventUploadPeriodMillis = 30000;
        this.minTimeBetweenSessionsMillis = 300000;
        this.sessionTimeoutMillis = 1800000;
        this.backoffUpload = false;
        this.backoffUploadBatchSize = this.eventUploadMaxBatchSize;
        this.usingForegroundTracking = false;
        this.trackingSessionEvents = false;
        this.inForeground = false;
        this.flushEventsOnClose = true;
        this.updateScheduled = new AtomicBoolean(false);
        this.uploadingCurrently = new AtomicBoolean(false);
        this.url = "https://api.amplitude.com/";
        this.logThread = new WorkerThread("logThread");
        this.httpThread = new WorkerThread("httpThread");
        this.instanceName = Utils.normalizeInstanceName(str);
        this.logThread.start();
        this.httpThread.start();
    }

    public AmplitudeClient initialize(Context context2, String str) {
        return initialize(context2, str, null);
    }

    public AmplitudeClient initialize(Context context2, String str, String str2) {
        return initialize(context2, str, str2, null);
    }

    public synchronized AmplitudeClient initialize(final Context context2, String str, final String str2, String str3) {
        if (context2 == null) {
            logger.e("com.amplitude.api.AmplitudeClient", "Argument context cannot be null in initialize()");
            return this;
        } else if (Utils.isEmptyString(str)) {
            logger.e("com.amplitude.api.AmplitudeClient", "Argument apiKey cannot be null or blank in initialize()");
            return this;
        } else {
            this.context = context2.getApplicationContext();
            this.apiKey = str;
            this.dbHelper = DatabaseHelper.getDatabaseHelper(this.context, this.instanceName);
            if (Utils.isEmptyString(str3)) {
                str3 = "Android";
            }
            this.platform = str3;
            runOnLogThread(new Runnable() {
                public void run() {
                    if (!AmplitudeClient.this.initialized) {
                        try {
                            if (AmplitudeClient.this.instanceName.equals("$default_instance")) {
                                AmplitudeClient.upgradePrefs(context2);
                                AmplitudeClient.upgradeSharedPrefsToDB(context2);
                            }
                            AmplitudeClient.this.httpClient = new OkHttpClient();
                            AmplitudeClient.this.initializeDeviceInfo();
                            if (str2 != null) {
                                this.userId = str2;
                                AmplitudeClient.this.dbHelper.insertOrReplaceKeyValue("user_id", str2);
                            } else {
                                this.userId = AmplitudeClient.this.dbHelper.getValue("user_id");
                            }
                            Long longValue = AmplitudeClient.this.dbHelper.getLongValue("opt_out");
                            AmplitudeClient.this.optOut = longValue != null && longValue.longValue() == 1;
                            AmplitudeClient.this.previousSessionId = AmplitudeClient.this.getLongvalue("previous_session_id", -1);
                            if (AmplitudeClient.this.previousSessionId >= 0) {
                                AmplitudeClient.this.sessionId = AmplitudeClient.this.previousSessionId;
                            }
                            AmplitudeClient.this.sequenceNumber = AmplitudeClient.this.getLongvalue("sequence_number", 0);
                            AmplitudeClient.this.lastEventId = AmplitudeClient.this.getLongvalue("last_event_id", -1);
                            AmplitudeClient.this.lastIdentifyId = AmplitudeClient.this.getLongvalue("last_identify_id", -1);
                            AmplitudeClient.this.lastEventTime = AmplitudeClient.this.getLongvalue("last_event_time", -1);
                            AmplitudeClient.this.initialized = true;
                        } catch (CursorWindowAllocationException e) {
                            AmplitudeClient.logger.e("com.amplitude.api.AmplitudeClient", String.format("Failed to initialize Amplitude SDK due to: %s", new Object[]{e.getMessage()}));
                            this.apiKey = null;
                        }
                    }
                }
            });
            return this;
        }
    }

    public AmplitudeClient enableForegroundTracking(Application application) {
        if (!this.usingForegroundTracking && contextAndApiKeySet("enableForegroundTracking()") && VERSION.SDK_INT >= 14) {
            application.registerActivityLifecycleCallbacks(new AmplitudeCallbacks(this));
        }
        return this;
    }

    /* access modifiers changed from: private */
    public void initializeDeviceInfo() {
        this.deviceInfo = new DeviceInfo(this.context);
        this.deviceId = initializeDeviceId();
        this.deviceInfo.prefetch();
    }

    /* access modifiers changed from: 0000 */
    public void useForegroundTracking() {
        this.usingForegroundTracking = true;
    }

    public void logEvent(String str, JSONObject jSONObject) {
        logEvent(str, jSONObject, false);
    }

    public void logEvent(String str, JSONObject jSONObject, boolean z) {
        logEvent(str, jSONObject, null, z);
    }

    public void logEvent(String str, JSONObject jSONObject, JSONObject jSONObject2, boolean z) {
        logEvent(str, jSONObject, jSONObject2, getCurrentTimeMillis(), z);
    }

    public void logEvent(String str, JSONObject jSONObject, JSONObject jSONObject2, long j, boolean z) {
        if (validateLogEvent(str)) {
            logEventAsync(str, jSONObject, null, null, jSONObject2, j, z);
        }
    }

    /* access modifiers changed from: protected */
    public boolean validateLogEvent(String str) {
        if (!Utils.isEmptyString(str)) {
            return contextAndApiKeySet("logEvent()");
        }
        logger.e("com.amplitude.api.AmplitudeClient", "Argument eventType cannot be null or blank in logEvent()");
        return false;
    }

    /* access modifiers changed from: protected */
    public void logEventAsync(String str, JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3, JSONObject jSONObject4, long j, boolean z) {
        final JSONObject cloneJSONObject = jSONObject != null ? Utils.cloneJSONObject(jSONObject) : jSONObject;
        final JSONObject cloneJSONObject2 = jSONObject3 != null ? Utils.cloneJSONObject(jSONObject3) : jSONObject3;
        final JSONObject cloneJSONObject3 = jSONObject4 != null ? Utils.cloneJSONObject(jSONObject4) : jSONObject4;
        final String str2 = str;
        final JSONObject jSONObject5 = jSONObject2;
        final long j2 = j;
        final boolean z2 = z;
        AnonymousClass5 r0 = new Runnable() {
            public void run() {
                if (!Utils.isEmptyString(AmplitudeClient.this.apiKey)) {
                    AmplitudeClient.this.logEvent(str2, cloneJSONObject, jSONObject5, cloneJSONObject2, cloneJSONObject3, j2, z2);
                }
            }
        };
        runOnLogThread(r0);
    }

    /* access modifiers changed from: protected */
    public long logEvent(String str, JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3, JSONObject jSONObject4, long j, boolean z) {
        long j2;
        JSONObject jSONObject5;
        JSONObject jSONObject6;
        StringBuilder sb = new StringBuilder();
        sb.append("Logged event to Amplitude: ");
        sb.append(str);
        logger.d("com.amplitude.api.AmplitudeClient", sb.toString());
        long j3 = -1;
        if (this.optOut) {
            return -1;
        }
        if (!(this.trackingSessionEvents && (str.equals(Events.SESSION_START) || str.equals("session_end"))) && !z) {
            if (!this.inForeground) {
                startNewSessionIfNeeded(j);
            } else {
                refreshSessionTime(j);
            }
        }
        JSONObject jSONObject7 = new JSONObject();
        try {
            jSONObject7.put("event_type", replaceWithJSONNull(str));
            jSONObject7.put("timestamp", j);
            jSONObject7.put("user_id", replaceWithJSONNull(this.userId));
            jSONObject7.put("device_id", replaceWithJSONNull(this.deviceId));
            String str2 = Attributes.SESSION_ID;
            if (z) {
                j2 = -1;
            } else {
                j2 = this.sessionId;
            }
            jSONObject7.put(str2, j2);
            jSONObject7.put(Params.VERSION_NAME, replaceWithJSONNull(this.deviceInfo.getVersionName()));
            jSONObject7.put("os_name", replaceWithJSONNull(this.deviceInfo.getOsName()));
            jSONObject7.put(Attributes.OS_VERSION, replaceWithJSONNull(this.deviceInfo.getOsVersion()));
            jSONObject7.put("device_brand", replaceWithJSONNull(this.deviceInfo.getBrand()));
            jSONObject7.put("device_manufacturer", replaceWithJSONNull(this.deviceInfo.getManufacturer()));
            jSONObject7.put("device_model", replaceWithJSONNull(this.deviceInfo.getModel()));
            jSONObject7.put(Attributes.CARRIER, replaceWithJSONNull(this.deviceInfo.getCarrier()));
            jSONObject7.put("country", replaceWithJSONNull(this.deviceInfo.getCountry()));
            jSONObject7.put("language", replaceWithJSONNull(this.deviceInfo.getLanguage()));
            jSONObject7.put(Http.PLATFORM, this.platform);
            jSONObject7.put(AbstractEvent.UUID, UUID.randomUUID().toString());
            jSONObject7.put("sequence_number", getNextSequenceNumber());
            JSONObject jSONObject8 = new JSONObject();
            jSONObject8.put("name", "amplitude-android");
            jSONObject8.put("version", "2.18.1");
            jSONObject7.put("library", jSONObject8);
            if (jSONObject2 == null) {
                jSONObject2 = new JSONObject();
            }
            Location mostRecentLocation = this.deviceInfo.getMostRecentLocation();
            if (mostRecentLocation != null) {
                JSONObject jSONObject9 = new JSONObject();
                jSONObject9.put(Keywords.LOCATION_LAT, mostRecentLocation.getLatitude());
                jSONObject9.put(Keywords.LOCATION_LONG, mostRecentLocation.getLongitude());
                jSONObject2.put("location", jSONObject9);
            }
            if (this.deviceInfo.getAdvertisingId() != null) {
                jSONObject2.put("androidADID", this.deviceInfo.getAdvertisingId());
            }
            jSONObject2.put(Events.LIMIT_AD_TRACKING, this.deviceInfo.isLimitAdTrackingEnabled());
            jSONObject2.put("gps_enabled", this.deviceInfo.isGooglePlayServicesEnabled());
            jSONObject7.put("api_properties", jSONObject2);
            String str3 = "event_properties";
            if (jSONObject == null) {
                jSONObject5 = new JSONObject();
            } else {
                jSONObject5 = truncate(jSONObject);
            }
            jSONObject7.put(str3, jSONObject5);
            String str4 = "user_properties";
            if (jSONObject3 == null) {
                jSONObject6 = new JSONObject();
            } else {
                jSONObject6 = truncate(jSONObject3);
            }
            jSONObject7.put(str4, jSONObject6);
            jSONObject7.put("groups", jSONObject4 == null ? new JSONObject() : truncate(jSONObject4));
            j3 = saveEvent(str, jSONObject7);
        } catch (JSONException e) {
            logger.e("com.amplitude.api.AmplitudeClient", String.format("JSON Serialization of event type %s failed, skipping: %s", new Object[]{str, e.toString()}));
        }
        return j3;
    }

    /* access modifiers changed from: protected */
    public long saveEvent(String str, JSONObject jSONObject) {
        String jSONObject2 = jSONObject.toString();
        if (Utils.isEmptyString(jSONObject2)) {
            logger.e("com.amplitude.api.AmplitudeClient", String.format("Detected empty event string for event type %s, skipping", new Object[]{str}));
            return -1;
        }
        if (str.equals("$identify")) {
            this.lastIdentifyId = this.dbHelper.addIdentify(jSONObject2);
            setLastIdentifyId(this.lastIdentifyId);
        } else {
            this.lastEventId = this.dbHelper.addEvent(jSONObject2);
            setLastEventId(this.lastEventId);
        }
        int min = Math.min(Math.max(1, this.eventMaxCount / 10), 20);
        if (this.dbHelper.getEventCount() > ((long) this.eventMaxCount)) {
            DatabaseHelper databaseHelper = this.dbHelper;
            databaseHelper.removeEvents(databaseHelper.getNthEventId((long) min));
        }
        if (this.dbHelper.getIdentifyCount() > ((long) this.eventMaxCount)) {
            DatabaseHelper databaseHelper2 = this.dbHelper;
            databaseHelper2.removeIdentifys(databaseHelper2.getNthIdentifyId((long) min));
        }
        long totalEventCount = this.dbHelper.getTotalEventCount();
        int i = this.eventUploadThreshold;
        if (totalEventCount % ((long) i) != 0 || totalEventCount < ((long) i)) {
            updateServerLater(this.eventUploadPeriodMillis);
        } else {
            updateServer();
        }
        return str.equals("$identify") ? this.lastIdentifyId : this.lastEventId;
    }

    /* access modifiers changed from: private */
    public long getLongvalue(String str, long j) {
        Long longValue = this.dbHelper.getLongValue(str);
        return longValue == null ? j : longValue.longValue();
    }

    /* access modifiers changed from: 0000 */
    public long getNextSequenceNumber() {
        this.sequenceNumber++;
        this.dbHelper.insertOrReplaceKeyLongValue("sequence_number", Long.valueOf(this.sequenceNumber));
        return this.sequenceNumber;
    }

    /* access modifiers changed from: 0000 */
    public void setLastEventTime(long j) {
        this.lastEventTime = j;
        this.dbHelper.insertOrReplaceKeyLongValue("last_event_time", Long.valueOf(j));
    }

    /* access modifiers changed from: 0000 */
    public void setLastEventId(long j) {
        this.lastEventId = j;
        this.dbHelper.insertOrReplaceKeyLongValue("last_event_id", Long.valueOf(j));
    }

    /* access modifiers changed from: 0000 */
    public void setLastIdentifyId(long j) {
        this.lastIdentifyId = j;
        this.dbHelper.insertOrReplaceKeyLongValue("last_identify_id", Long.valueOf(j));
    }

    /* access modifiers changed from: 0000 */
    public void setPreviousSessionId(long j) {
        this.previousSessionId = j;
        this.dbHelper.insertOrReplaceKeyLongValue("previous_session_id", Long.valueOf(j));
    }

    /* access modifiers changed from: 0000 */
    public boolean startNewSessionIfNeeded(long j) {
        if (inSession()) {
            if (isWithinMinTimeBetweenSessions(j)) {
                refreshSessionTime(j);
                return false;
            }
            startNewSession(j);
            return true;
        } else if (isWithinMinTimeBetweenSessions(j)) {
            long j2 = this.previousSessionId;
            if (j2 == -1) {
                startNewSession(j);
                return true;
            }
            setSessionId(j2);
            refreshSessionTime(j);
            return false;
        } else {
            startNewSession(j);
            return true;
        }
    }

    private void startNewSession(long j) {
        if (this.trackingSessionEvents) {
            sendSessionEvent("session_end");
        }
        setSessionId(j);
        refreshSessionTime(j);
        if (this.trackingSessionEvents) {
            sendSessionEvent(Events.SESSION_START);
        }
    }

    private boolean inSession() {
        return this.sessionId >= 0;
    }

    private boolean isWithinMinTimeBetweenSessions(long j) {
        return j - this.lastEventTime < (this.usingForegroundTracking ? this.minTimeBetweenSessionsMillis : this.sessionTimeoutMillis);
    }

    /* access modifiers changed from: private */
    public void setSessionId(long j) {
        this.sessionId = j;
        setPreviousSessionId(j);
    }

    /* access modifiers changed from: 0000 */
    public void refreshSessionTime(long j) {
        if (inSession()) {
            setLastEventTime(j);
        }
    }

    /* access modifiers changed from: private */
    public void sendSessionEvent(String str) {
        if (contextAndApiKeySet(String.format("sendSessionEvent('%s')", new Object[]{str})) && inSession()) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("special", str);
                logEvent(str, null, jSONObject, null, null, this.lastEventTime, false);
            } catch (JSONException unused) {
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void onExitForeground(final long j) {
        runOnLogThread(new Runnable() {
            public void run() {
                if (!Utils.isEmptyString(AmplitudeClient.this.apiKey)) {
                    AmplitudeClient.this.refreshSessionTime(j);
                    AmplitudeClient.this.inForeground = false;
                    if (AmplitudeClient.this.flushEventsOnClose) {
                        AmplitudeClient.this.updateServer();
                    }
                }
            }
        });
    }

    /* access modifiers changed from: 0000 */
    public void onEnterForeground(final long j) {
        runOnLogThread(new Runnable() {
            public void run() {
                if (!Utils.isEmptyString(AmplitudeClient.this.apiKey)) {
                    AmplitudeClient.this.startNewSessionIfNeeded(j);
                    AmplitudeClient.this.inForeground = true;
                }
            }
        });
    }

    public void identify(Identify identify) {
        identify(identify, false);
    }

    public void identify(Identify identify, boolean z) {
        if (identify != null && identify.userPropertiesOperations.length() != 0 && contextAndApiKeySet("identify()")) {
            logEventAsync("$identify", null, null, identify.userPropertiesOperations, null, getCurrentTimeMillis(), z);
        }
    }

    public JSONObject truncate(JSONObject jSONObject) {
        if (jSONObject == null) {
            return new JSONObject();
        }
        if (jSONObject.length() > 1000) {
            logger.w("com.amplitude.api.AmplitudeClient", "Warning: too many properties (more than 1000), ignoring");
            return new JSONObject();
        }
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            try {
                Object obj = jSONObject.get(str);
                if (!str.equals("$receipt")) {
                    if (!str.equals("$receiptSig")) {
                        if (obj.getClass().equals(String.class)) {
                            jSONObject.put(str, truncate((String) obj));
                        } else if (obj.getClass().equals(JSONObject.class)) {
                            jSONObject.put(str, truncate((JSONObject) obj));
                        } else if (obj.getClass().equals(JSONArray.class)) {
                            jSONObject.put(str, truncate((JSONArray) obj));
                        }
                    }
                }
                jSONObject.put(str, obj);
            } catch (JSONException e) {
                logger.e("com.amplitude.api.AmplitudeClient", e.toString());
            }
        }
        return jSONObject;
    }

    public JSONArray truncate(JSONArray jSONArray) throws JSONException {
        if (jSONArray == null) {
            return new JSONArray();
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            Object obj = jSONArray.get(i);
            if (obj.getClass().equals(String.class)) {
                jSONArray.put(i, truncate((String) obj));
            } else if (obj.getClass().equals(JSONObject.class)) {
                jSONArray.put(i, truncate((JSONObject) obj));
            } else if (obj.getClass().equals(JSONArray.class)) {
                jSONArray.put(i, truncate((JSONArray) obj));
            }
        }
        return jSONArray;
    }

    public String truncate(String str) {
        return str.length() <= 1024 ? str : str.substring(0, 1024);
    }

    public AmplitudeClient setUserId(String str) {
        return setUserId(str, false);
    }

    public AmplitudeClient setUserId(final String str, final boolean z) {
        if (!contextAndApiKeySet("setUserId()")) {
            return this;
        }
        runOnLogThread(new Runnable() {
            public void run() {
                if (!Utils.isEmptyString(this.apiKey)) {
                    if (z && AmplitudeClient.this.trackingSessionEvents) {
                        AmplitudeClient.this.sendSessionEvent("session_end");
                    }
                    this.userId = str;
                    AmplitudeClient.this.dbHelper.insertOrReplaceKeyValue("user_id", str);
                    if (z) {
                        long currentTimeMillis = AmplitudeClient.this.getCurrentTimeMillis();
                        AmplitudeClient.this.setSessionId(currentTimeMillis);
                        AmplitudeClient.this.refreshSessionTime(currentTimeMillis);
                        if (AmplitudeClient.this.trackingSessionEvents) {
                            AmplitudeClient.this.sendSessionEvent(Events.SESSION_START);
                        }
                    }
                }
            }
        });
        return this;
    }

    private void updateServerLater(long j) {
        if (!this.updateScheduled.getAndSet(true)) {
            this.logThread.postDelayed(new Runnable() {
                public void run() {
                    AmplitudeClient.this.updateScheduled.set(false);
                    AmplitudeClient.this.updateServer();
                }
            }, j);
        }
    }

    /* access modifiers changed from: protected */
    public void updateServer() {
        updateServer(false);
    }

    /* access modifiers changed from: protected */
    public void updateServer(boolean z) {
        if (!this.optOut && !this.offline && !this.uploadingCurrently.getAndSet(true)) {
            long min = Math.min((long) (z ? this.backoffUploadBatchSize : this.eventUploadMaxBatchSize), this.dbHelper.getTotalEventCount());
            if (min <= 0) {
                this.uploadingCurrently.set(false);
                return;
            }
            try {
                Pair mergeEventsAndIdentifys = mergeEventsAndIdentifys(this.dbHelper.getEvents(this.lastEventId, min), this.dbHelper.getIdentifys(this.lastIdentifyId, min), min);
                if (((JSONArray) mergeEventsAndIdentifys.second).length() == 0) {
                    this.uploadingCurrently.set(false);
                    return;
                }
                final long longValue = ((Long) ((Pair) mergeEventsAndIdentifys.first).first).longValue();
                final long longValue2 = ((Long) ((Pair) mergeEventsAndIdentifys.first).second).longValue();
                final String jSONArray = ((JSONArray) mergeEventsAndIdentifys.second).toString();
                WorkerThread workerThread = this.httpThread;
                AnonymousClass13 r3 = new Runnable() {
                    public void run() {
                        AmplitudeClient amplitudeClient = AmplitudeClient.this;
                        amplitudeClient.makeEventUploadPostRequest(amplitudeClient.httpClient, jSONArray, longValue, longValue2);
                    }
                };
                workerThread.post(r3);
            } catch (JSONException e) {
                this.uploadingCurrently.set(false);
                logger.e("com.amplitude.api.AmplitudeClient", e.toString());
            } catch (CursorWindowAllocationException e2) {
                this.uploadingCurrently.set(false);
                logger.e("com.amplitude.api.AmplitudeClient", String.format("Caught Cursor window exception during event upload, deferring upload: %s", new Object[]{e2.getMessage()}));
            }
        }
    }

    /* access modifiers changed from: protected */
    public Pair<Pair<Long, Long>, JSONArray> mergeEventsAndIdentifys(List<JSONObject> list, List<JSONObject> list2, long j) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        long j2 = -1;
        long j3 = -1;
        while (true) {
            if (((long) jSONArray.length()) >= j) {
                break;
            }
            boolean isEmpty = list.isEmpty();
            boolean isEmpty2 = list2.isEmpty();
            if (isEmpty && isEmpty2) {
                logger.w("com.amplitude.api.AmplitudeClient", String.format("mergeEventsAndIdentifys: number of events and identifys less than expected by %d", new Object[]{Long.valueOf(j - ((long) jSONArray.length()))}));
                break;
            } else if (isEmpty2) {
                JSONObject jSONObject = (JSONObject) list.remove(0);
                long j4 = jSONObject.getLong(Extras.EVENT_ID);
                jSONArray.put(jSONObject);
                j2 = j4;
            } else if (isEmpty) {
                JSONObject jSONObject2 = (JSONObject) list2.remove(0);
                long j5 = jSONObject2.getLong(Extras.EVENT_ID);
                jSONArray.put(jSONObject2);
                j3 = j5;
            } else if (!((JSONObject) list.get(0)).has("sequence_number") || ((JSONObject) list.get(0)).getLong("sequence_number") < ((JSONObject) list2.get(0)).getLong("sequence_number")) {
                JSONObject jSONObject3 = (JSONObject) list.remove(0);
                long j6 = jSONObject3.getLong(Extras.EVENT_ID);
                jSONArray.put(jSONObject3);
                j2 = j6;
            } else {
                JSONObject jSONObject4 = (JSONObject) list2.remove(0);
                long j7 = jSONObject4.getLong(Extras.EVENT_ID);
                jSONArray.put(jSONObject4);
                j3 = j7;
            }
        }
        return new Pair<>(new Pair(Long.valueOf(j2), Long.valueOf(j3)), jSONArray);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x01a5  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void makeEventUploadPostRequest(okhttp3.OkHttpClient r9, java.lang.String r10, long r11, long r13) {
        /*
            r8 = this;
            java.lang.String r0 = "2"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = ""
            r1.append(r2)
            long r2 = r8.getCurrentTimeMillis()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            java.lang.String r2 = ""
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ UnsupportedEncodingException -> 0x0044 }
            r3.<init>()     // Catch:{ UnsupportedEncodingException -> 0x0044 }
            r3.append(r0)     // Catch:{ UnsupportedEncodingException -> 0x0044 }
            java.lang.String r4 = r8.apiKey     // Catch:{ UnsupportedEncodingException -> 0x0044 }
            r3.append(r4)     // Catch:{ UnsupportedEncodingException -> 0x0044 }
            r3.append(r10)     // Catch:{ UnsupportedEncodingException -> 0x0044 }
            r3.append(r1)     // Catch:{ UnsupportedEncodingException -> 0x0044 }
            java.lang.String r3 = r3.toString()     // Catch:{ UnsupportedEncodingException -> 0x0044 }
            com.amplitude.security.MD5 r4 = new com.amplitude.security.MD5     // Catch:{ UnsupportedEncodingException -> 0x0044 }
            r4.<init>()     // Catch:{ UnsupportedEncodingException -> 0x0044 }
            java.lang.String r5 = "UTF-8"
            byte[] r3 = r3.getBytes(r5)     // Catch:{ UnsupportedEncodingException -> 0x0044 }
            byte[] r3 = r4.digest(r3)     // Catch:{ UnsupportedEncodingException -> 0x0044 }
            java.lang.String r2 = r8.bytesToHexString(r3)     // Catch:{ UnsupportedEncodingException -> 0x0044 }
            goto L_0x0050
        L_0x0044:
            r3 = move-exception
            com.amplitude.api.AmplitudeLog r4 = logger
            java.lang.String r5 = "com.amplitude.api.AmplitudeClient"
            java.lang.String r3 = r3.toString()
            r4.e(r5, r3)
        L_0x0050:
            okhttp3.FormBody$Builder r3 = new okhttp3.FormBody$Builder
            r3.<init>()
            java.lang.String r4 = "v"
            okhttp3.FormBody$Builder r0 = r3.add(r4, r0)
            java.lang.String r3 = "client"
            java.lang.String r4 = r8.apiKey
            okhttp3.FormBody$Builder r0 = r0.add(r3, r4)
            java.lang.String r3 = "e"
            okhttp3.FormBody$Builder r10 = r0.add(r3, r10)
            java.lang.String r0 = "upload_time"
            okhttp3.FormBody$Builder r10 = r10.add(r0, r1)
            java.lang.String r0 = "checksum"
            okhttp3.FormBody$Builder r10 = r10.add(r0, r2)
            okhttp3.FormBody r10 = r10.build()
            r0 = 0
            okhttp3.Request$Builder r1 = new okhttp3.Request$Builder     // Catch:{ IllegalArgumentException -> 0x01ab }
            r1.<init>()     // Catch:{ IllegalArgumentException -> 0x01ab }
            java.lang.String r2 = r8.url     // Catch:{ IllegalArgumentException -> 0x01ab }
            okhttp3.Request$Builder r1 = r1.url(r2)     // Catch:{ IllegalArgumentException -> 0x01ab }
            okhttp3.Request$Builder r10 = r1.post(r10)     // Catch:{ IllegalArgumentException -> 0x01ab }
            okhttp3.Request r10 = r10.build()     // Catch:{ IllegalArgumentException -> 0x01ab }
            r1 = 1
            okhttp3.Call r9 = r9.newCall(r10)     // Catch:{ ConnectException -> 0x019f, UnknownHostException -> 0x019a, IOException -> 0x018a, AssertionError -> 0x017c, Exception -> 0x016e }
            okhttp3.Response r9 = r9.execute()     // Catch:{ ConnectException -> 0x019f, UnknownHostException -> 0x019a, IOException -> 0x018a, AssertionError -> 0x017c, Exception -> 0x016e }
            okhttp3.ResponseBody r10 = r9.body()     // Catch:{ ConnectException -> 0x019f, UnknownHostException -> 0x019a, IOException -> 0x018a, AssertionError -> 0x017c, Exception -> 0x016e }
            java.lang.String r10 = r10.string()     // Catch:{ ConnectException -> 0x019f, UnknownHostException -> 0x019a, IOException -> 0x018a, AssertionError -> 0x017c, Exception -> 0x016e }
            java.lang.String r2 = "success"
            boolean r2 = r10.equals(r2)     // Catch:{ ConnectException -> 0x019f, UnknownHostException -> 0x019a, IOException -> 0x018a, AssertionError -> 0x017c, Exception -> 0x016e }
            if (r2 == 0) goto L_0x00c5
            com.amplitude.api.WorkerThread r9 = r8.logThread     // Catch:{ ConnectException -> 0x00c2, UnknownHostException -> 0x00bf, IOException -> 0x00bc, AssertionError -> 0x00b9, Exception -> 0x00b6 }
            com.amplitude.api.AmplitudeClient$14 r10 = new com.amplitude.api.AmplitudeClient$14     // Catch:{ ConnectException -> 0x00c2, UnknownHostException -> 0x00bf, IOException -> 0x00bc, AssertionError -> 0x00b9, Exception -> 0x00b6 }
            r2 = r10
            r3 = r8
            r4 = r11
            r6 = r13
            r2.<init>(r4, r6)     // Catch:{ ConnectException -> 0x00c2, UnknownHostException -> 0x00bf, IOException -> 0x00bc, AssertionError -> 0x00b9, Exception -> 0x00b6 }
            r9.post(r10)     // Catch:{ ConnectException -> 0x00c2, UnknownHostException -> 0x00bf, IOException -> 0x00bc, AssertionError -> 0x00b9, Exception -> 0x00b6 }
            goto L_0x01a3
        L_0x00b6:
            r9 = move-exception
            goto L_0x0170
        L_0x00b9:
            r9 = move-exception
            goto L_0x017e
        L_0x00bc:
            r9 = move-exception
            goto L_0x018c
        L_0x00bf:
            r9 = move-exception
            goto L_0x019c
        L_0x00c2:
            r9 = move-exception
            goto L_0x01a1
        L_0x00c5:
            java.lang.String r2 = "invalid_api_key"
            boolean r2 = r10.equals(r2)     // Catch:{ ConnectException -> 0x019f, UnknownHostException -> 0x019a, IOException -> 0x018a, AssertionError -> 0x017c, Exception -> 0x016e }
            if (r2 == 0) goto L_0x00d8
            com.amplitude.api.AmplitudeLog r9 = logger     // Catch:{ ConnectException -> 0x019f, UnknownHostException -> 0x019a, IOException -> 0x018a, AssertionError -> 0x017c, Exception -> 0x016e }
            java.lang.String r10 = "com.amplitude.api.AmplitudeClient"
            java.lang.String r11 = "Invalid API key, make sure your API key is correct in initialize()"
            r9.e(r10, r11)     // Catch:{ ConnectException -> 0x019f, UnknownHostException -> 0x019a, IOException -> 0x018a, AssertionError -> 0x017c, Exception -> 0x016e }
            goto L_0x016c
        L_0x00d8:
            java.lang.String r2 = "bad_checksum"
            boolean r2 = r10.equals(r2)     // Catch:{ ConnectException -> 0x019f, UnknownHostException -> 0x019a, IOException -> 0x018a, AssertionError -> 0x017c, Exception -> 0x016e }
            if (r2 == 0) goto L_0x00eb
            com.amplitude.api.AmplitudeLog r9 = logger     // Catch:{ ConnectException -> 0x019f, UnknownHostException -> 0x019a, IOException -> 0x018a, AssertionError -> 0x017c, Exception -> 0x016e }
            java.lang.String r10 = "com.amplitude.api.AmplitudeClient"
            java.lang.String r11 = "Bad checksum, post request was mangled in transit, will attempt to reupload later"
            r9.w(r10, r11)     // Catch:{ ConnectException -> 0x019f, UnknownHostException -> 0x019a, IOException -> 0x018a, AssertionError -> 0x017c, Exception -> 0x016e }
            goto L_0x016c
        L_0x00eb:
            java.lang.String r2 = "request_db_write_failed"
            boolean r2 = r10.equals(r2)     // Catch:{ ConnectException -> 0x019f, UnknownHostException -> 0x019a, IOException -> 0x018a, AssertionError -> 0x017c, Exception -> 0x016e }
            if (r2 == 0) goto L_0x00fd
            com.amplitude.api.AmplitudeLog r9 = logger     // Catch:{ ConnectException -> 0x019f, UnknownHostException -> 0x019a, IOException -> 0x018a, AssertionError -> 0x017c, Exception -> 0x016e }
            java.lang.String r10 = "com.amplitude.api.AmplitudeClient"
            java.lang.String r11 = "Couldn't write to request database on server, will attempt to reupload later"
            r9.w(r10, r11)     // Catch:{ ConnectException -> 0x019f, UnknownHostException -> 0x019a, IOException -> 0x018a, AssertionError -> 0x017c, Exception -> 0x016e }
            goto L_0x016c
        L_0x00fd:
            int r9 = r9.code()     // Catch:{ ConnectException -> 0x019f, UnknownHostException -> 0x019a, IOException -> 0x018a, AssertionError -> 0x017c, Exception -> 0x016e }
            r2 = 413(0x19d, float:5.79E-43)
            if (r9 != r2) goto L_0x014f
            boolean r9 = r8.backoffUpload     // Catch:{ ConnectException -> 0x019f, UnknownHostException -> 0x019a, IOException -> 0x018a, AssertionError -> 0x017c, Exception -> 0x016e }
            if (r9 == 0) goto L_0x0121
            int r9 = r8.backoffUploadBatchSize     // Catch:{ ConnectException -> 0x019f, UnknownHostException -> 0x019a, IOException -> 0x018a, AssertionError -> 0x017c, Exception -> 0x016e }
            if (r9 != r1) goto L_0x0121
            r9 = 0
            int r2 = (r11 > r9 ? 1 : (r11 == r9 ? 0 : -1))
            if (r2 < 0) goto L_0x0118
            com.amplitude.api.DatabaseHelper r2 = r8.dbHelper     // Catch:{ ConnectException -> 0x019f, UnknownHostException -> 0x019a, IOException -> 0x018a, AssertionError -> 0x017c, Exception -> 0x016e }
            r2.removeEvent(r11)     // Catch:{ ConnectException -> 0x019f, UnknownHostException -> 0x019a, IOException -> 0x018a, AssertionError -> 0x017c, Exception -> 0x016e }
        L_0x0118:
            int r11 = (r13 > r9 ? 1 : (r13 == r9 ? 0 : -1))
            if (r11 < 0) goto L_0x0121
            com.amplitude.api.DatabaseHelper r9 = r8.dbHelper     // Catch:{ ConnectException -> 0x019f, UnknownHostException -> 0x019a, IOException -> 0x018a, AssertionError -> 0x017c, Exception -> 0x016e }
            r9.removeIdentify(r13)     // Catch:{ ConnectException -> 0x019f, UnknownHostException -> 0x019a, IOException -> 0x018a, AssertionError -> 0x017c, Exception -> 0x016e }
        L_0x0121:
            r8.backoffUpload = r1     // Catch:{ ConnectException -> 0x019f, UnknownHostException -> 0x019a, IOException -> 0x018a, AssertionError -> 0x017c, Exception -> 0x016e }
            com.amplitude.api.DatabaseHelper r9 = r8.dbHelper     // Catch:{ ConnectException -> 0x019f, UnknownHostException -> 0x019a, IOException -> 0x018a, AssertionError -> 0x017c, Exception -> 0x016e }
            long r9 = r9.getEventCount()     // Catch:{ ConnectException -> 0x019f, UnknownHostException -> 0x019a, IOException -> 0x018a, AssertionError -> 0x017c, Exception -> 0x016e }
            int r10 = (int) r9     // Catch:{ ConnectException -> 0x019f, UnknownHostException -> 0x019a, IOException -> 0x018a, AssertionError -> 0x017c, Exception -> 0x016e }
            int r9 = r8.backoffUploadBatchSize     // Catch:{ ConnectException -> 0x019f, UnknownHostException -> 0x019a, IOException -> 0x018a, AssertionError -> 0x017c, Exception -> 0x016e }
            int r9 = java.lang.Math.min(r10, r9)     // Catch:{ ConnectException -> 0x019f, UnknownHostException -> 0x019a, IOException -> 0x018a, AssertionError -> 0x017c, Exception -> 0x016e }
            double r9 = (double) r9     // Catch:{ ConnectException -> 0x019f, UnknownHostException -> 0x019a, IOException -> 0x018a, AssertionError -> 0x017c, Exception -> 0x016e }
            r11 = 4611686018427387904(0x4000000000000000, double:2.0)
            double r9 = r9 / r11
            double r9 = java.lang.Math.ceil(r9)     // Catch:{ ConnectException -> 0x019f, UnknownHostException -> 0x019a, IOException -> 0x018a, AssertionError -> 0x017c, Exception -> 0x016e }
            int r9 = (int) r9     // Catch:{ ConnectException -> 0x019f, UnknownHostException -> 0x019a, IOException -> 0x018a, AssertionError -> 0x017c, Exception -> 0x016e }
            r8.backoffUploadBatchSize = r9     // Catch:{ ConnectException -> 0x019f, UnknownHostException -> 0x019a, IOException -> 0x018a, AssertionError -> 0x017c, Exception -> 0x016e }
            com.amplitude.api.AmplitudeLog r9 = logger     // Catch:{ ConnectException -> 0x019f, UnknownHostException -> 0x019a, IOException -> 0x018a, AssertionError -> 0x017c, Exception -> 0x016e }
            java.lang.String r10 = "com.amplitude.api.AmplitudeClient"
            java.lang.String r11 = "Request too large, will decrease size and attempt to reupload"
            r9.w(r10, r11)     // Catch:{ ConnectException -> 0x019f, UnknownHostException -> 0x019a, IOException -> 0x018a, AssertionError -> 0x017c, Exception -> 0x016e }
            com.amplitude.api.WorkerThread r9 = r8.logThread     // Catch:{ ConnectException -> 0x019f, UnknownHostException -> 0x019a, IOException -> 0x018a, AssertionError -> 0x017c, Exception -> 0x016e }
            com.amplitude.api.AmplitudeClient$15 r10 = new com.amplitude.api.AmplitudeClient$15     // Catch:{ ConnectException -> 0x019f, UnknownHostException -> 0x019a, IOException -> 0x018a, AssertionError -> 0x017c, Exception -> 0x016e }
            r10.<init>()     // Catch:{ ConnectException -> 0x019f, UnknownHostException -> 0x019a, IOException -> 0x018a, AssertionError -> 0x017c, Exception -> 0x016e }
            r9.post(r10)     // Catch:{ ConnectException -> 0x019f, UnknownHostException -> 0x019a, IOException -> 0x018a, AssertionError -> 0x017c, Exception -> 0x016e }
            goto L_0x016c
        L_0x014f:
            com.amplitude.api.AmplitudeLog r9 = logger     // Catch:{ ConnectException -> 0x019f, UnknownHostException -> 0x019a, IOException -> 0x018a, AssertionError -> 0x017c, Exception -> 0x016e }
            java.lang.String r11 = "com.amplitude.api.AmplitudeClient"
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ ConnectException -> 0x019f, UnknownHostException -> 0x019a, IOException -> 0x018a, AssertionError -> 0x017c, Exception -> 0x016e }
            r12.<init>()     // Catch:{ ConnectException -> 0x019f, UnknownHostException -> 0x019a, IOException -> 0x018a, AssertionError -> 0x017c, Exception -> 0x016e }
            java.lang.String r13 = "Upload failed, "
            r12.append(r13)     // Catch:{ ConnectException -> 0x019f, UnknownHostException -> 0x019a, IOException -> 0x018a, AssertionError -> 0x017c, Exception -> 0x016e }
            r12.append(r10)     // Catch:{ ConnectException -> 0x019f, UnknownHostException -> 0x019a, IOException -> 0x018a, AssertionError -> 0x017c, Exception -> 0x016e }
            java.lang.String r10 = ", will attempt to reupload later"
            r12.append(r10)     // Catch:{ ConnectException -> 0x019f, UnknownHostException -> 0x019a, IOException -> 0x018a, AssertionError -> 0x017c, Exception -> 0x016e }
            java.lang.String r10 = r12.toString()     // Catch:{ ConnectException -> 0x019f, UnknownHostException -> 0x019a, IOException -> 0x018a, AssertionError -> 0x017c, Exception -> 0x016e }
            r9.w(r11, r10)     // Catch:{ ConnectException -> 0x019f, UnknownHostException -> 0x019a, IOException -> 0x018a, AssertionError -> 0x017c, Exception -> 0x016e }
        L_0x016c:
            r1 = 0
            goto L_0x01a3
        L_0x016e:
            r9 = move-exception
            r1 = 0
        L_0x0170:
            com.amplitude.api.AmplitudeLog r10 = logger
            java.lang.String r11 = "com.amplitude.api.AmplitudeClient"
            java.lang.String r12 = "Exception:"
            r10.e(r11, r12, r9)
            r8.lastError = r9
            goto L_0x01a3
        L_0x017c:
            r9 = move-exception
            r1 = 0
        L_0x017e:
            com.amplitude.api.AmplitudeLog r10 = logger
            java.lang.String r11 = "com.amplitude.api.AmplitudeClient"
            java.lang.String r12 = "Exception:"
            r10.e(r11, r12, r9)
            r8.lastError = r9
            goto L_0x01a3
        L_0x018a:
            r9 = move-exception
            r1 = 0
        L_0x018c:
            com.amplitude.api.AmplitudeLog r10 = logger
            java.lang.String r11 = "com.amplitude.api.AmplitudeClient"
            java.lang.String r12 = r9.toString()
            r10.e(r11, r12)
            r8.lastError = r9
            goto L_0x01a3
        L_0x019a:
            r9 = move-exception
            r1 = 0
        L_0x019c:
            r8.lastError = r9
            goto L_0x01a3
        L_0x019f:
            r9 = move-exception
            r1 = 0
        L_0x01a1:
            r8.lastError = r9
        L_0x01a3:
            if (r1 != 0) goto L_0x01aa
            java.util.concurrent.atomic.AtomicBoolean r9 = r8.uploadingCurrently
            r9.set(r0)
        L_0x01aa:
            return
        L_0x01ab:
            r9 = move-exception
            com.amplitude.api.AmplitudeLog r10 = logger
            java.lang.String r11 = "com.amplitude.api.AmplitudeClient"
            java.lang.String r9 = r9.toString()
            r10.e(r11, r9)
            java.util.concurrent.atomic.AtomicBoolean r9 = r8.uploadingCurrently
            r9.set(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amplitude.api.AmplitudeClient.makeEventUploadPostRequest(okhttp3.OkHttpClient, java.lang.String, long, long):void");
    }

    private Set<String> getInvalidDeviceIds() {
        HashSet hashSet = new HashSet();
        hashSet.add("");
        hashSet.add("9774d56d682e549c");
        hashSet.add("unknown");
        hashSet.add("000000000000000");
        hashSet.add("Android");
        hashSet.add("DEFACE");
        hashSet.add("00000000-0000-0000-0000-000000000000");
        return hashSet;
    }

    private String initializeDeviceId() {
        Set invalidDeviceIds = getInvalidDeviceIds();
        String value = this.dbHelper.getValue("device_id");
        if (!Utils.isEmptyString(value) && !invalidDeviceIds.contains(value)) {
            return value;
        }
        if (!this.newDeviceIdPerInstall && this.useAdvertisingIdForDeviceId) {
            String advertisingId = this.deviceInfo.getAdvertisingId();
            if (!Utils.isEmptyString(advertisingId) && !invalidDeviceIds.contains(advertisingId)) {
                this.dbHelper.insertOrReplaceKeyValue("device_id", advertisingId);
                return advertisingId;
            }
        }
        StringBuilder sb = new StringBuilder();
        DeviceInfo deviceInfo2 = this.deviceInfo;
        sb.append(DeviceInfo.generateUUID());
        sb.append("R");
        String sb2 = sb.toString();
        this.dbHelper.insertOrReplaceKeyValue("device_id", sb2);
        return sb2;
    }

    /* access modifiers changed from: protected */
    public void runOnLogThread(Runnable runnable) {
        Thread currentThread = Thread.currentThread();
        WorkerThread workerThread = this.logThread;
        if (currentThread != workerThread) {
            workerThread.post(runnable);
        } else {
            runnable.run();
        }
    }

    /* access modifiers changed from: protected */
    public Object replaceWithJSONNull(Object obj) {
        return obj == null ? JSONObject.NULL : obj;
    }

    /* access modifiers changed from: protected */
    public synchronized boolean contextAndApiKeySet(String str) {
        if (this.context == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("context cannot be null, set context with initialize() before calling ");
            sb.append(str);
            logger.e("com.amplitude.api.AmplitudeClient", sb.toString());
            return false;
        } else if (!Utils.isEmptyString(this.apiKey)) {
            return true;
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("apiKey cannot be null or empty, set apiKey with initialize() before calling ");
            sb2.append(str);
            logger.e("com.amplitude.api.AmplitudeClient", sb2.toString());
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public String bytesToHexString(byte[] bArr) {
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        char[] cArr2 = new char[(bArr.length * 2)];
        for (int i = 0; i < bArr.length; i++) {
            byte b = bArr[i] & 255;
            int i2 = i * 2;
            cArr2[i2] = cArr[b >>> 4];
            cArr2[i2 + 1] = cArr[b & Ascii.SI];
        }
        return new String(cArr2);
    }

    static boolean upgradePrefs(Context context2) {
        return upgradePrefs(context2, null, null);
    }

    static boolean upgradePrefs(Context context2, String str, String str2) {
        if (str == null) {
            str = "com.amplitude.api";
            try {
                str = Constants.class.getPackage().getName();
            } catch (Exception unused) {
            }
        }
        if (str2 == null) {
            str2 = "com.amplitude.api";
        }
        try {
            if (str2.equals(str)) {
                return false;
            }
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(".");
            sb.append(context2.getPackageName());
            String sb2 = sb.toString();
            SharedPreferences sharedPreferences = context2.getSharedPreferences(sb2, 0);
            if (sharedPreferences.getAll().size() == 0) {
                return false;
            }
            StringBuilder sb3 = new StringBuilder();
            sb3.append(str2);
            sb3.append(".");
            sb3.append(context2.getPackageName());
            String sb4 = sb3.toString();
            Editor edit = context2.getSharedPreferences(sb4, 0).edit();
            StringBuilder sb5 = new StringBuilder();
            sb5.append(str);
            sb5.append(".previousSessionId");
            if (sharedPreferences.contains(sb5.toString())) {
                StringBuilder sb6 = new StringBuilder();
                sb6.append(str);
                sb6.append(".previousSessionId");
                edit.putLong("com.amplitude.api.previousSessionId", sharedPreferences.getLong(sb6.toString(), -1));
            }
            StringBuilder sb7 = new StringBuilder();
            sb7.append(str);
            sb7.append(".deviceId");
            if (sharedPreferences.contains(sb7.toString())) {
                StringBuilder sb8 = new StringBuilder();
                sb8.append(str);
                sb8.append(".deviceId");
                edit.putString("com.amplitude.api.deviceId", sharedPreferences.getString(sb8.toString(), null));
            }
            StringBuilder sb9 = new StringBuilder();
            sb9.append(str);
            sb9.append(".userId");
            if (sharedPreferences.contains(sb9.toString())) {
                StringBuilder sb10 = new StringBuilder();
                sb10.append(str);
                sb10.append(".userId");
                edit.putString("com.amplitude.api.userId", sharedPreferences.getString(sb10.toString(), null));
            }
            StringBuilder sb11 = new StringBuilder();
            sb11.append(str);
            sb11.append(".optOut");
            if (sharedPreferences.contains(sb11.toString())) {
                StringBuilder sb12 = new StringBuilder();
                sb12.append(str);
                sb12.append(".optOut");
                edit.putBoolean("com.amplitude.api.optOut", sharedPreferences.getBoolean(sb12.toString(), false));
            }
            edit.apply();
            sharedPreferences.edit().clear().apply();
            StringBuilder sb13 = new StringBuilder();
            sb13.append("Upgraded shared preferences from ");
            sb13.append(sb2);
            sb13.append(" to ");
            sb13.append(sb4);
            logger.i("com.amplitude.api.AmplitudeClient", sb13.toString());
            return true;
        } catch (Exception e) {
            logger.e("com.amplitude.api.AmplitudeClient", "Error upgrading shared preferences", e);
            return false;
        }
    }

    static boolean upgradeSharedPrefsToDB(Context context2) {
        return upgradeSharedPrefsToDB(context2, null);
    }

    static boolean upgradeSharedPrefsToDB(Context context2, String str) {
        if (str == null) {
            str = "com.amplitude.api";
        }
        DatabaseHelper databaseHelper = DatabaseHelper.getDatabaseHelper(context2);
        String value = databaseHelper.getValue("device_id");
        Long longValue = databaseHelper.getLongValue("previous_session_id");
        Long longValue2 = databaseHelper.getLongValue("last_event_time");
        if (!Utils.isEmptyString(value) && longValue != null && longValue2 != null) {
            return true;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(".");
        sb.append(context2.getPackageName());
        SharedPreferences sharedPreferences = context2.getSharedPreferences(sb.toString(), 0);
        migrateStringValue(sharedPreferences, "com.amplitude.api.deviceId", null, databaseHelper, "device_id");
        SharedPreferences sharedPreferences2 = sharedPreferences;
        DatabaseHelper databaseHelper2 = databaseHelper;
        migrateLongValue(sharedPreferences2, "com.amplitude.api.lastEventTime", -1, databaseHelper2, "last_event_time");
        migrateLongValue(sharedPreferences2, "com.amplitude.api.lastEventId", -1, databaseHelper2, "last_event_id");
        migrateLongValue(sharedPreferences2, "com.amplitude.api.lastIdentifyId", -1, databaseHelper2, "last_identify_id");
        migrateLongValue(sharedPreferences2, "com.amplitude.api.previousSessionId", -1, databaseHelper2, "previous_session_id");
        migrateStringValue(sharedPreferences, "com.amplitude.api.userId", null, databaseHelper, "user_id");
        migrateBooleanValue(sharedPreferences, "com.amplitude.api.optOut", false, databaseHelper, "opt_out");
        return true;
    }

    private static void migrateLongValue(SharedPreferences sharedPreferences, String str, long j, DatabaseHelper databaseHelper, String str2) {
        if (databaseHelper.getLongValue(str2) == null) {
            databaseHelper.insertOrReplaceKeyLongValue(str2, Long.valueOf(sharedPreferences.getLong(str, j)));
            sharedPreferences.edit().remove(str).apply();
        }
    }

    private static void migrateStringValue(SharedPreferences sharedPreferences, String str, String str2, DatabaseHelper databaseHelper, String str3) {
        if (Utils.isEmptyString(databaseHelper.getValue(str3))) {
            String string = sharedPreferences.getString(str, str2);
            if (!Utils.isEmptyString(string)) {
                databaseHelper.insertOrReplaceKeyValue(str3, string);
                sharedPreferences.edit().remove(str).apply();
            }
        }
    }

    private static void migrateBooleanValue(SharedPreferences sharedPreferences, String str, boolean z, DatabaseHelper databaseHelper, String str2) {
        if (databaseHelper.getLongValue(str2) == null) {
            databaseHelper.insertOrReplaceKeyLongValue(str2, Long.valueOf(sharedPreferences.getBoolean(str, z) ? 1 : 0));
            sharedPreferences.edit().remove(str).apply();
        }
    }

    /* access modifiers changed from: protected */
    public long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }
}
