package com.brightcove.player.analytics;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Handler;
import android.provider.Settings.Secure;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import com.brightcove.player.event.AbstractComponent;
import com.brightcove.player.event.AbstractEvent;
import com.brightcove.player.event.Component;
import com.brightcove.player.event.Default;
import com.brightcove.player.event.Emits;
import com.brightcove.player.event.Event;
import com.brightcove.player.event.EventEmitter;
import com.brightcove.player.event.EventListener;
import com.brightcove.player.event.EventType;
import com.brightcove.player.event.ListensFor;
import com.brightcove.player.mediacontroller.BrightcoveMediaController;
import com.brightcove.player.model.Video;
import com.brightcove.player.util.Convert;
import com.brightcove.player.util.LayoutUtil;
import com.facebook.internal.NativeProtocol;
import com.myfitnesspal.feature.timestamp.service.TimestampAnalyticsHelper;
import com.myfitnesspal.shared.constants.SharedConstants.Http;
import java.net.URI;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

@ListensFor(events = {"account", "activityStarted", "activityStopped", "activityDestroyed", "adBreakStarted", "adBreakCompleted", "adProgress", "addAnalyticsBaseParams", "analyticsCatalogRequest", "analyticsCatalogResponse", "analyticsVideoEngagement", "bufferingCompleted", "bufferingStarted", "completed", "didPause", "didPlay", "didSeekTo", "didSetVideo", "error", "fragmentStarted", "fragmentStopped", "fragmentDestroyed", "play", "progress", "setAnalyticsBaseParams", "version", "videoDurationChanged", "videoSizeKnown", "willChangeVideo", "videoDownloadStarted", "videoDownloadCompleted", "videoDownloadCancelled", "videoDownloadFailed"})
@Emits(events = {"account", "didSetAnalyticsBaseParams", "error"})
public class Analytics extends AbstractComponent {
    private static final String DEVICE_OS = "android";
    private static final String DOMAIN = "videocloud";
    private static final int ENGAGEMENT_PERIOD_MS = 10000;
    public static final String TAG = "Analytics";
    /* access modifiers changed from: private */
    public String account;
    /* access modifiers changed from: private */
    public long adEndTime;
    /* access modifiers changed from: private */
    public long adProgressTime;
    /* access modifiers changed from: private */
    public long adStartTime;
    private final IAnalyticsErrorListener analyticsErrorListener;
    /* access modifiers changed from: private */
    public final Map<String, String> baseParams;
    private Context context;
    public String destination;
    private String deviceType;
    /* access modifiers changed from: private */
    public int duration;
    /* access modifiers changed from: private */
    public long elapsedRebufferingMs;
    /* access modifiers changed from: private */
    public int endTime;
    /* access modifiers changed from: private */
    public int forwardBufferSeconds;
    /* access modifiers changed from: private */
    public boolean hasStarted;
    private boolean hasTimerStarted;
    /* access modifiers changed from: private */
    public long initiateDataLoadTime;
    /* access modifiers changed from: private */
    public boolean isAdPlaying;
    /* access modifiers changed from: private */
    public boolean isBuffering;
    /* access modifiers changed from: private */
    public boolean isPaused;
    /* access modifiers changed from: private */
    public int measuredBps;
    private String platform;
    /* access modifiers changed from: private */
    public String platformVersion;
    /* access modifiers changed from: private */
    public int playerHeight;
    /* access modifiers changed from: private */
    public int playerWidth;
    /* access modifiers changed from: private */
    public int rebufferingCount;
    /* access modifiers changed from: private */
    public long rebufferingMs;
    /* access modifiers changed from: private */
    public int renditionHeight;
    /* access modifiers changed from: private */
    public int renditionIndicatedBps;
    /* access modifiers changed from: private */
    public String renditionMimeType;
    /* access modifiers changed from: private */
    public String renditionUrl;
    /* access modifiers changed from: private */
    public int renditionWidth;
    private String source;
    /* access modifiers changed from: private */
    public int startTime;
    /* access modifiers changed from: private */
    public Handler timerHandler;
    private Runnable timerRunnable;
    /* access modifiers changed from: private */
    @Nullable
    public Long totalMediaBytesTransferred;
    private boolean uniqueIdentifierEnabled;
    /* access modifiers changed from: private */
    public boolean videoCompleted;
    /* access modifiers changed from: private */
    public Event videoEngagementDataEvent;
    /* access modifiers changed from: private */
    public long videoViewPlayableTime;
    /* access modifiers changed from: private */
    public long videoViewStartTime;
    /* access modifiers changed from: private */
    public long videoViewTime;

    public static class Fields {
        protected static final String ACCOUNT = "account";
        public static final String BASE_PARAMS = "baseParams";
        protected static final String CATALOG_URL = "catalog_url";
        protected static final String DESTINATION = "destination";
        protected static final String DEVICE = "device";
        protected static final String DEVICE_CPU = "device_cpu";
        protected static final String DEVICE_HARDWARE = "device_hardware";
        protected static final String DEVICE_MANUFACTURER = "device_manufacturer";
        protected static final String DEVICE_MODEL = "device_model";
        protected static final String DEVICE_OS = "device_os";
        protected static final String DEVICE_OS_VERSION = "device_os_version";
        protected static final String DEVICE_PRODUCT = "device_product";
        protected static final String DEVICE_TYPE = "device_type";
        protected static final String DOMAIN = "domain";
        public static final String DOWNLOAD_CANCEL_TIME = "download_cancel_time";
        public static final String DOWNLOAD_COMPLETION_TIME = "download_completion_time";
        public static final String DOWNLOAD_FAILURE_TIME = "download_failure_time";
        public static final String DOWNLOAD_ID = "download_id";
        public static final String DOWNLOAD_REQUEST_TIME = "download_request_time";
        public static final String DOWNLOAD_SIZE = "file_size_bytes";
        protected static final String ERROR_CODE = "error_code";
        protected static final String ERROR_DESCRIPTION = "error_description";
        protected static final String EVENT = "event";
        protected static final String EVENT_AD_END = "ad_end";
        protected static final String EVENT_AD_START = "ad_start";
        protected static final String EVENT_CATALOG_REQUEST = "catalog_request";
        protected static final String EVENT_CATALOG_RESPONSE = "catalog_response";
        public static final String EVENT_DOWNLOAD_CANCEL = "video_download_cancellation";
        public static final String EVENT_DOWNLOAD_COMPLETE = "video_download_complete";
        public static final String EVENT_DOWNLOAD_FAIL = "video_download_error";
        protected static final String EVENT_DOWNLOAD_PREFIX = "video_download_";
        public static final String EVENT_DOWNLOAD_REQUESTED = "video_download_request";
        public static final String EVENT_DOWNLOAD_START = "video_download_start";
        protected static final String EVENT_ERROR = "error";
        protected static final String EVENT_PLAY_REQUEST = "play_request";
        protected static final String EVENT_VIDEO_COMPLETE = "video_complete";
        protected static final String EVENT_VIDEO_ENGAGEMENT = "video_engagement";
        protected static final String EVENT_VIDEO_IMPRESSION = "video_impression";
        protected static final String EVENT_VIDEO_VIEW = "video_view";
        protected static final String FORWARD_BUFFER_SECONDS = "forward_buffer_seconds";
        protected static final String LOAD_TIME_MS = "load_time_ms";
        protected static final String MEASURED_BPS = "measured_bps";
        protected static final String MEDIA_BYTES_TRANSFERRED = "media_bytes_transferred";
        protected static final String PLATFORM = "platform";
        protected static final String PLATFORM_VERSION = "platform_version";
        protected static final String PLAYER_HEIGHT = "player_height";
        protected static final String PLAYER_WIDTH = "player_width";
        protected static final String RANGE = "range";
        protected static final String REBUFFERING_COUNT = "rebuffering_count";
        protected static final String REBUFFERING_SECONDS = "rebuffering_seconds";
        protected static final String RENDITION_HEIGHT = "rendition_height";
        protected static final String RENDITION_INDICATED_BPS = "rendition_indicated_bps";
        protected static final String RENDITION_MIME_TYPE = "rendition_mime_type";
        protected static final String RENDITION_URL = "rendition_url";
        protected static final String RENDITION_WIDTH = "rendition_width";
        protected static final String RESPONSE_TIME_MS = "response_time_ms";
        protected static final String SESSION = "session";
        protected static final String SOURCE = "source";
        protected static final String START_TIME_MS = "start_time_ms";
        protected static final String TIME = "time";
        protected static final String USER = "user";
        protected static final String VIDEO_DURATION = "video_duration";
        protected static final String VIDEO_ID = "video";
        protected static final String VIDEO_NAME = "video_name";
        protected static final String VIDEO_SECONDS_VIEWED = "video_seconds_viewed";
    }

    private static class Lazy {
        @NonNull
        public static final String SESSION_KEY = UUID.randomUUID().toString();

        private Lazy() {
        }
    }

    @NonNull
    public static String getSessionKey() {
        return Lazy.SESSION_KEY;
    }

    public Analytics(EventEmitter eventEmitter, Context context2) {
        this(eventEmitter, context2, Analytics.class);
    }

    protected Analytics(EventEmitter eventEmitter, Context context2, Class<? extends Component> cls) {
        super(eventEmitter, cls);
        this.baseParams = new HashMap();
        this.platform = "android-native-sdk";
        this.uniqueIdentifierEnabled = true;
        this.analyticsErrorListener = new IAnalyticsErrorListener() {
            public void onAnalyticsError(@NonNull Throwable th) {
                Analytics.this.eventEmitter.emit("error", Collections.singletonMap("error", th));
            }
        };
        this.context = context2;
        setDestination(context2);
        initializeEvents();
        this.deviceType = getDeviceType(context2);
    }

    public String getAccount() {
        return this.account;
    }

    public void setAccount(String str) {
        this.account = str;
        HashMap hashMap = new HashMap();
        hashMap.put("value", str);
        this.eventEmitter.emit("account", hashMap);
    }

    public void setDestination(String str) {
        this.destination = str;
        Uri parse = Uri.parse(str);
        if (parse.getScheme() == null) {
            String str2 = TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("Invalid destination (a scheme is required): ");
            sb.append(str);
            Log.e(str2, sb.toString());
        }
        if (parse.getAuthority() == null) {
            String str3 = TAG;
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Invalid destination (an authority is required): ");
            sb2.append(str);
            Log.e(str3, sb2.toString());
        }
    }

    private void setDestination(Context context2) {
        if (context2 != null) {
            Context applicationContext = context2.getApplicationContext();
            if (applicationContext != null) {
                PackageManager packageManager = applicationContext.getPackageManager();
                if (packageManager != null) {
                    try {
                        String str = (String) packageManager.getApplicationLabel(packageManager.getApplicationInfo(context2.getPackageName(), 0));
                        StringBuilder sb = new StringBuilder();
                        sb.append("bcsdk://");
                        sb.append(str);
                        sb.append("/");
                        sb.append(context2.getClass().getPackage().getName());
                        setDestination(sb.toString());
                    } catch (NameNotFoundException unused) {
                        Log.w(TAG, "No application name found.");
                    }
                }
            }
        }
    }

    public void setSource(String str) {
        this.source = str;
        Uri parse = Uri.parse(str);
        if (parse.getScheme() == null) {
            String str2 = TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("Invalid source (a scheme is required): ");
            sb.append(str);
            Log.e(str2, sb.toString());
        }
        if (parse.getAuthority() == null) {
            String str3 = TAG;
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Invalid source (an authority is required): ");
            sb2.append(str);
            Log.e(str3, sb2.toString());
        }
    }

    private void initializeEvents() {
        this.eventEmitter.on(EventType.ADD_ANALYTICS_BASE_PARAMS, new EventListener() {
            @Default
            public void processEvent(Event event) {
                Map map = (Map) event.properties.get(Fields.BASE_PARAMS);
                if (map != null) {
                    Analytics.this.baseParams.putAll(map);
                }
                Analytics.this.emitDidSetEvent();
            }
        });
        this.eventEmitter.on(EventType.SET_ANALYTICS_BASE_PARAMS, new EventListener() {
            @Default
            public void processEvent(Event event) {
                Map map = (Map) event.properties.get(Fields.BASE_PARAMS);
                if (map != null) {
                    Analytics.this.baseParams.clear();
                    Analytics.this.baseParams.putAll(map);
                }
                Analytics.this.emitDidSetEvent();
            }
        });
        this.eventEmitter.on(EventType.PLAY, new EventListener() {
            @Default
            public void processEvent(Event event) {
                if (!Analytics.this.hasStarted) {
                    Analytics.this.videoViewStartTime = System.currentTimeMillis();
                    Analytics.this.sendVideoAnalyticsRequest(event, "play_request", null);
                }
            }
        });
        this.eventEmitter.on(EventType.DID_PLAY, new EventListener() {
            public void processEvent(Event event) {
                if (Analytics.this.isPaused) {
                    Analytics.this.startVideoEngagementTimer();
                    Analytics.this.isPaused = false;
                }
            }
        });
        this.eventEmitter.on(EventType.DID_PAUSE, new EventListener() {
            public void processEvent(Event event) {
                if (!Analytics.this.isAdPlaying) {
                    Analytics.this.stopVideoEngagementTimer();
                    Analytics analytics = Analytics.this;
                    analytics.sendVideoEngagementRequest(event, analytics.startTime, Analytics.this.endTime);
                    Analytics analytics2 = Analytics.this;
                    analytics2.startTime = analytics2.endTime;
                    Analytics.this.isPaused = true;
                }
            }
        });
        this.eventEmitter.on(EventType.DID_SET_VIDEO, new EventListener() {
            public void processEvent(Event event) {
                Analytics.this.videoViewPlayableTime = System.currentTimeMillis();
            }
        });
        this.eventEmitter.on(EventType.ANALYTICS_CATALOG_REQUEST, new EventListener() {
            public void processEvent(Event event) {
                URI uri = (URI) event.properties.get(AbstractEvent.CATALOG_URL);
                HashMap hashMap = new HashMap();
                hashMap.put("catalog_url", uri.toString());
                Analytics.this.sendVideoAnalyticsRequest(event, "catalog_request", hashMap);
            }
        });
        this.eventEmitter.on(EventType.ANALYTICS_CATALOG_RESPONSE, new EventListener() {
            public void processEvent(Event event) {
                URI uri = (URI) event.properties.get(AbstractEvent.CATALOG_URL);
                long longValue = ((Long) event.properties.get(AbstractEvent.RESPONSE_TIME_MS)).longValue();
                HashMap hashMap = new HashMap();
                hashMap.put("catalog_url", uri.toString());
                hashMap.put("response_time_ms", Long.toString(longValue));
                Analytics.this.sendVideoAnalyticsRequest(event, "catalog_response", hashMap);
                Analytics.this.initiateDataLoadTime = System.currentTimeMillis();
            }
        });
        this.eventEmitter.on(EventType.WILL_CHANGE_VIDEO, new EventListener() {
            @Default
            public void processEvent(Event event) {
                Video video = (Video) event.properties.get(AbstractEvent.CURRENT_VIDEO);
                if (video != null && Analytics.this.endTime - Analytics.this.startTime > 0) {
                    event.properties.put("video", video);
                    Analytics analytics = Analytics.this;
                    analytics.sendVideoEngagementRequest(event, analytics.startTime, Analytics.this.endTime);
                }
                Video video2 = (Video) event.properties.get(AbstractEvent.NEXT_VIDEO);
                if (video2 != null) {
                    event.properties.put("video", video2);
                    Analytics.this.sendVideoAnalyticsRequest(event, "video_impression", null);
                    event.properties.remove("video");
                }
                Analytics analytics2 = Analytics.this;
                analytics2.duration = analytics2.startTime = analytics2.endTime = 0;
                Analytics.this.hasStarted = false;
                Analytics.this.videoCompleted = false;
                Analytics analytics3 = Analytics.this;
                analytics3.videoViewTime = analytics3.videoViewStartTime = analytics3.videoViewPlayableTime = 0;
                Analytics analytics4 = Analytics.this;
                analytics4.adStartTime = analytics4.adProgressTime = analytics4.adEndTime = analytics4.initiateDataLoadTime = 0;
                Analytics analytics5 = Analytics.this;
                analytics5.renditionUrl = analytics5.renditionMimeType = "";
                Analytics analytics6 = Analytics.this;
                analytics6.renditionIndicatedBps = analytics6.measuredBps = 0;
                Analytics analytics7 = Analytics.this;
                analytics7.renditionHeight = analytics7.renditionWidth = 0;
                Analytics.this.totalMediaBytesTransferred = null;
                Analytics analytics8 = Analytics.this;
                analytics8.rebufferingMs = analytics8.elapsedRebufferingMs = (long) analytics8.rebufferingCount = 0;
                Analytics analytics9 = Analytics.this;
                analytics9.playerHeight = analytics9.playerWidth = 0;
                Analytics.this.forwardBufferSeconds = 0;
            }
        });
        this.eventEmitter.on(EventType.DID_SEEK_TO, new EventListener() {
            @Default
            public void processEvent(Event event) {
                int integerProperty = event.getIntegerProperty(AbstractEvent.SEEK_POSITION);
                if (integerProperty > 0 && integerProperty - Analytics.this.endTime > 1000) {
                    Analytics analytics = Analytics.this;
                    analytics.sendVideoEngagementRequest(event, analytics.startTime, Analytics.this.endTime);
                    Analytics analytics2 = Analytics.this;
                    analytics2.startTime = analytics2.endTime = integerProperty;
                    Analytics analytics3 = Analytics.this;
                    analytics3.rebufferingMs = analytics3.elapsedRebufferingMs = (long) analytics3.rebufferingCount = 0;
                }
            }
        });
        this.eventEmitter.on("progress", new EventListener() {
            @Default
            public void processEvent(Event event) {
                Video video = (Video) event.properties.get("video");
                int integerProperty = event.getIntegerProperty(AbstractEvent.PLAYHEAD_POSITION);
                Analytics.this.duration = event.getIntegerProperty("duration");
                if (event.properties.containsKey(AbstractEvent.FORWARD_BUFFER_SECONDS)) {
                    Analytics.this.forwardBufferSeconds = event.getIntegerProperty(AbstractEvent.FORWARD_BUFFER_SECONDS);
                }
                if (integerProperty >= 0) {
                    if (Math.abs(Analytics.this.endTime - integerProperty) > 5000) {
                        Analytics analytics = Analytics.this;
                        analytics.startTime = analytics.endTime = integerProperty;
                        return;
                    }
                    Analytics.this.videoEngagementDataEvent = event;
                    if (Analytics.this.endTime - Analytics.this.startTime >= 10000) {
                        Analytics analytics2 = Analytics.this;
                        analytics2.startTime = analytics2.endTime;
                    }
                    Analytics.this.endTime = integerProperty;
                    if (!(Analytics.this.hasStarted || video == null || Analytics.this.endTime == 0)) {
                        Analytics.this.videoViewTime = System.currentTimeMillis();
                        long access$1200 = Analytics.this.initiateDataLoadTime != 0 ? Analytics.this.videoViewPlayableTime - Analytics.this.initiateDataLoadTime : 0;
                        Analytics.this.startVideoEngagementTimer();
                        Analytics.this.hasStarted = true;
                        if (Analytics.this.adEndTime == 0) {
                            Analytics analytics3 = Analytics.this;
                            analytics3.adEndTime = analytics3.adProgressTime;
                        }
                        if (Analytics.this.videoViewStartTime == 0) {
                            Log.e(Analytics.TAG, "videoViewStartTime is 0");
                        } else if (Analytics.this.adStartTime == 0 || Analytics.this.adEndTime != 0) {
                            Analytics.this.sendVideoViewRequest(event, access$1200, (Analytics.this.videoViewTime - Analytics.this.videoViewStartTime) - (Analytics.this.adEndTime - Analytics.this.adStartTime));
                        } else {
                            Log.e(Analytics.TAG, "adEndTime and adProgressTime are 0.");
                        }
                    }
                }
            }
        });
        this.eventEmitter.on("completed", new EventListener() {
            @Default
            public void processEvent(Event event) {
                int integerProperty = event.getIntegerProperty(AbstractEvent.PLAYHEAD_POSITION);
                if (integerProperty >= 0) {
                    Analytics.this.endTime = integerProperty;
                    Analytics analytics = Analytics.this;
                    analytics.sendVideoEngagementRequest(event, analytics.startTime, Analytics.this.endTime);
                    Analytics analytics2 = Analytics.this;
                    analytics2.startTime = analytics2.endTime;
                    Analytics.this.sendVideoAnalyticsRequest(event, "video_complete", null);
                    Analytics.this.stopVideoEngagementTimer();
                    Analytics.this.videoCompleted = true;
                }
            }
        });
        this.eventEmitter.on(EventType.VIDEO_DURATION_CHANGED, new EventListener() {
            @Default
            public void processEvent(Event event) {
                int intValue = ((Integer) event.properties.get("duration")).intValue();
                if (intValue > 0) {
                    Analytics.this.duration = intValue;
                }
            }
        });
        this.eventEmitter.on(EventType.AD_BREAK_STARTED, new EventListener() {
            public void processEvent(Event event) {
                Analytics.this.adStartTime = System.currentTimeMillis();
                Analytics.this.sendVideoAnalyticsRequest(event, "ad_start", null);
                Analytics.this.stopVideoEngagementTimer();
                Analytics analytics = Analytics.this;
                analytics.sendVideoEngagementRequest(event, analytics.startTime, Analytics.this.endTime);
                Analytics.this.isAdPlaying = true;
                Analytics analytics2 = Analytics.this;
                analytics2.startTime = analytics2.endTime;
            }
        });
        this.eventEmitter.on(EventType.AD_PROGRESS, new EventListener() {
            public void processEvent(Event event) {
                Analytics.this.adProgressTime = System.currentTimeMillis();
            }
        });
        this.eventEmitter.on(EventType.AD_BREAK_COMPLETED, new EventListener() {
            public void processEvent(Event event) {
                Analytics.this.adEndTime = System.currentTimeMillis();
                Analytics.this.sendVideoAnalyticsRequest(event, "ad_end", null);
                Analytics.this.startVideoEngagementTimer();
                Analytics.this.isAdPlaying = false;
                if (Analytics.this.videoCompleted) {
                    Analytics.this.stopVideoEngagementTimer();
                    Analytics.this.videoCompleted = false;
                }
            }
        });
        this.eventEmitter.on("version", new EventListener() {
            @Default
            public void processEvent(Event event) {
                Analytics.this.platformVersion = (String) event.properties.get(AbstractEvent.BUILD_VERSION);
            }
        });
        this.eventEmitter.on(EventType.ANALYTICS_VIDEO_ENGAGEMENT, new EventListener() {
            public void processEvent(Event event) {
                if (event.properties.containsKey(AbstractEvent.RENDITION_URL)) {
                    Analytics.this.renditionUrl = (String) event.properties.get(AbstractEvent.RENDITION_URL);
                }
                if (event.properties.containsKey(AbstractEvent.RENDITION_INDICATED_BPS)) {
                    Analytics.this.renditionIndicatedBps = event.getIntegerProperty(AbstractEvent.RENDITION_INDICATED_BPS);
                }
                if (event.properties.containsKey(AbstractEvent.RENDITION_MIME_TYPE)) {
                    Analytics.this.renditionMimeType = (String) event.properties.get(AbstractEvent.RENDITION_MIME_TYPE);
                }
                if (event.properties.containsKey(AbstractEvent.RENDITION_HEIGHT)) {
                    Analytics.this.renditionHeight = event.getIntegerProperty(AbstractEvent.RENDITION_HEIGHT);
                }
                if (event.properties.containsKey(AbstractEvent.RENDITION_WIDTH)) {
                    Analytics.this.renditionWidth = event.getIntegerProperty(AbstractEvent.RENDITION_WIDTH);
                }
                if (event.properties.containsKey(AbstractEvent.FORWARD_BUFFER_SECONDS)) {
                    Analytics.this.forwardBufferSeconds = event.getIntegerProperty(AbstractEvent.FORWARD_BUFFER_SECONDS);
                }
                if (event.properties.containsKey(AbstractEvent.MEASURED_BPS)) {
                    Analytics.this.measuredBps = event.getIntegerProperty(AbstractEvent.MEASURED_BPS);
                }
                if (event.properties.containsKey(AbstractEvent.MEDIA_BYTES_TRANSFERRED)) {
                    Analytics.this.totalMediaBytesTransferred = Long.valueOf(Convert.toLong(event.properties.get(AbstractEvent.MEDIA_BYTES_TRANSFERRED)));
                } else {
                    Analytics.this.totalMediaBytesTransferred = null;
                }
            }
        });
        this.eventEmitter.on(EventType.BUFFERING_STARTED, new EventListener() {
            public void processEvent(Event event) {
                
                /*  JADX ERROR: Method code generation error
                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0002: INVOKE  (wrap: com.brightcove.player.analytics.Analytics
                      0x0000: IGET  (r3v1 com.brightcove.player.analytics.Analytics) = (r2v0 'this' com.brightcove.player.analytics.Analytics$19 A[THIS]) com.brightcove.player.analytics.Analytics.19.this$0 com.brightcove.player.analytics.Analytics) com.brightcove.player.analytics.Analytics.access$2908(com.brightcove.player.analytics.Analytics):int type: STATIC in method: com.brightcove.player.analytics.Analytics.19.processEvent(com.brightcove.player.event.Event):void, dex: classes.dex
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:245)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:213)
                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                    	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:210)
                    	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:203)
                    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:316)
                    	at jadx.core.codegen.ClassGen.addMethods(ClassGen.java:262)
                    	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:225)
                    	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:661)
                    	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:595)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:353)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:223)
                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:105)
                    	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:773)
                    	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:713)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:357)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:239)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:213)
                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                    	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:210)
                    	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:203)
                    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:316)
                    	at jadx.core.codegen.ClassGen.addMethods(ClassGen.java:262)
                    	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:225)
                    	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:110)
                    	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:76)
                    	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:44)
                    	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:32)
                    	at jadx.core.codegen.CodeGen.generate(CodeGen.java:20)
                    	at jadx.core.ProcessClass.process(ProcessClass.java:36)
                    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
                    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
                    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
                    Caused by: org.objenesis.ObjenesisException: java.lang.ClassNotFoundException: sun.reflect.ReflectionFactory
                    	at org.objenesis.instantiator.sun.SunReflectionFactoryHelper.getReflectionFactoryClass(SunReflectionFactoryHelper.java:57)
                    	at org.objenesis.instantiator.sun.SunReflectionFactoryHelper.newConstructorForSerialization(SunReflectionFactoryHelper.java:37)
                    	at org.objenesis.instantiator.sun.SunReflectionFactoryInstantiator.<init>(SunReflectionFactoryInstantiator.java:41)
                    	at org.objenesis.strategy.StdInstantiatorStrategy.newInstantiatorOf(StdInstantiatorStrategy.java:68)
                    	at org.objenesis.ObjenesisBase.getInstantiatorOf(ObjenesisBase.java:94)
                    	at org.objenesis.ObjenesisBase.newInstance(ObjenesisBase.java:73)
                    	at com.rits.cloning.ObjenesisInstantiationStrategy.newInstance(ObjenesisInstantiationStrategy.java:17)
                    	at com.rits.cloning.Cloner.newInstance(Cloner.java:300)
                    	at com.rits.cloning.Cloner.cloneObject(Cloner.java:461)
                    	at com.rits.cloning.Cloner.cloneInternal(Cloner.java:456)
                    	at com.rits.cloning.Cloner.deepClone(Cloner.java:326)
                    	at jadx.core.dex.nodes.InsnNode.copy(InsnNode.java:352)
                    	at jadx.core.dex.nodes.InsnNode.copyCommonParams(InsnNode.java:333)
                    	at jadx.core.dex.instructions.IndexInsnNode.copy(IndexInsnNode.java:24)
                    	at jadx.core.dex.instructions.IndexInsnNode.copy(IndexInsnNode.java:9)
                    	at jadx.core.codegen.InsnGen.inlineMethod(InsnGen.java:880)
                    	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:669)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:357)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:239)
                    	... 38 more
                    Caused by: java.lang.ClassNotFoundException: sun.reflect.ReflectionFactory
                    	at java.base/jdk.internal.loader.BuiltinClassLoader.loadClass(Unknown Source)
                    	at java.base/jdk.internal.loader.ClassLoaders$AppClassLoader.loadClass(Unknown Source)
                    	at java.base/java.lang.ClassLoader.loadClass(Unknown Source)
                    	at java.base/java.lang.Class.forName0(Native Method)
                    	at java.base/java.lang.Class.forName(Unknown Source)
                    	at org.objenesis.instantiator.sun.SunReflectionFactoryHelper.getReflectionFactoryClass(SunReflectionFactoryHelper.java:54)
                    	... 56 more
                    */
                /*
                    this = this;
                    com.brightcove.player.analytics.Analytics r3 = com.brightcove.player.analytics.Analytics.this
                    
                    // error: 0x0002: INVOKE  (r3 I:com.brightcove.player.analytics.Analytics) com.brightcove.player.analytics.Analytics.access$2908(com.brightcove.player.analytics.Analytics):int type: STATIC
                    com.brightcove.player.analytics.Analytics r3 = com.brightcove.player.analytics.Analytics.this
                    long r0 = java.lang.System.currentTimeMillis()
                    r3.rebufferingMs = r0
                    com.brightcove.player.analytics.Analytics r3 = com.brightcove.player.analytics.Analytics.this
                    r0 = 1
                    r3.isBuffering = r0
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.brightcove.player.analytics.Analytics.AnonymousClass19.processEvent(com.brightcove.player.event.Event):void");
            }
        });
        this.eventEmitter.on(EventType.BUFFERING_COMPLETED, new EventListener() {
            public void processEvent(Event event) {
                if (Analytics.this.rebufferingMs == 0) {
                    Log.e(Analytics.TAG, "Buffer complete without buffer start time!");
                } else {
                    Analytics analytics = Analytics.this;
                    analytics.elapsedRebufferingMs = analytics.elapsedRebufferingMs + (System.currentTimeMillis() - Analytics.this.rebufferingMs);
                }
                if (Analytics.this.elapsedRebufferingMs < 1000 && Analytics.this.rebufferingCount > 0) {
                    Analytics analytics2 = Analytics.this;
                    analytics2.rebufferingCount = analytics2.rebufferingCount - 1;
                }
                Analytics.this.isBuffering = false;
            }
        });
        this.eventEmitter.on("account", new EventListener() {
            public void processEvent(Event event) {
                Analytics.this.account = (String) event.properties.get("value");
            }
        });
        this.eventEmitter.on(EventType.VIDEO_SIZE_KNOWN, new EventListener() {
            public void processEvent(Event event) {
                Analytics.this.playerWidth = event.getIntegerProperty("width");
                Analytics.this.playerHeight = event.getIntegerProperty("height");
            }
        });
        this.eventEmitter.on(EventType.ACTIVITY_STOPPED, new EventListener() {
            public void processEvent(Event event) {
                Analytics.this.stopVideoEngagementTimer();
            }
        });
        this.eventEmitter.on(EventType.ACTIVITY_STARTED, new EventListener() {
            public void processEvent(Event event) {
                Analytics.this.startVideoEngagementTimer();
            }
        });
        this.eventEmitter.on(EventType.FRAGMENT_STOPPED, new EventListener() {
            public void processEvent(Event event) {
                Analytics.this.stopVideoEngagementTimer();
            }
        });
        this.eventEmitter.on(EventType.FRAGMENT_STARTED, new EventListener() {
            public void processEvent(Event event) {
                Analytics.this.startVideoEngagementTimer();
            }
        });
        this.eventEmitter.on("error", new EventListener() {
            public void processEvent(Event event) {
                Analytics.this.sendErrorRequest(event, (String) event.properties.get("errorCode"), (String) event.properties.get(AbstractEvent.ERROR_MESSAGE));
            }
        });
        this.eventEmitter.on(EventType.VIDEO_DOWNLOAD_STARTED, new EventListener() {
            public void processEvent(Event event) {
                Analytics.this.sendVideoDownloadAnalyticsRequest(event, Fields.EVENT_DOWNLOAD_REQUESTED);
            }
        });
        this.eventEmitter.on(EventType.VIDEO_DOWNLOAD_COMPLETED, new EventListener() {
            public void processEvent(Event event) {
                Analytics.this.sendVideoDownloadAnalyticsRequest(event, Fields.EVENT_DOWNLOAD_COMPLETE);
            }
        });
        this.eventEmitter.on(EventType.VIDEO_DOWNLOAD_CANCELLED, new EventListener() {
            public void processEvent(Event event) {
                Analytics.this.sendVideoDownloadAnalyticsRequest(event, Fields.EVENT_DOWNLOAD_CANCEL);
            }
        });
        this.eventEmitter.on(EventType.VIDEO_DOWNLOAD_FAILED, new EventListener() {
            public void processEvent(Event event) {
                Analytics.this.sendVideoDownloadAnalyticsRequest(event, Fields.EVENT_DOWNLOAD_FAIL);
            }
        });
    }

    /* access modifiers changed from: private */
    public void sendVideoEngagementRequest(Event event, int i, int i2) {
        long j;
        if (i != 0) {
            i = Double.valueOf(Math.ceil(((double) i) / 1000.0d)).intValue();
        }
        int intValue = Double.valueOf(Math.floor(((double) i2) / 1000.0d)).intValue();
        if (intValue >= i && intValue > 0) {
            int i3 = this.duration;
            if (i3 <= 0 || intValue <= i3) {
                int i4 = this.rebufferingCount;
                if (this.isBuffering) {
                    j = (System.currentTimeMillis() - this.rebufferingMs) / 1000;
                } else {
                    j = this.elapsedRebufferingMs / 1000;
                }
                HashMap hashMap = new HashMap();
                if (j < 10) {
                    if (this.duration > 0) {
                        hashMap.put("range", String.format(Locale.getDefault(), "%d..%d", new Object[]{Integer.valueOf(i), Integer.valueOf(intValue)}));
                    } else {
                        hashMap.put("video_seconds_viewed", String.format(Locale.getDefault(), "%d", new Object[]{Integer.valueOf(intValue - i)}));
                    }
                }
                hashMap.put("rendition_url", this.renditionUrl);
                hashMap.put("rendition_indicated_bps", Integer.toString(this.renditionIndicatedBps));
                hashMap.put("rendition_mime_type", this.renditionMimeType);
                hashMap.put("rendition_height", Integer.toString(this.renditionHeight));
                hashMap.put("rendition_width", Integer.toString(this.renditionWidth));
                hashMap.put("rebuffering_seconds", Long.toString(j));
                hashMap.put("rebuffering_count", Integer.toString(i4));
                hashMap.put("forward_buffer_seconds", Integer.toString(this.forwardBufferSeconds));
                hashMap.put("measured_bps", Integer.toString(this.measuredBps));
                hashMap.put("player_width", Integer.toString(this.playerWidth));
                hashMap.put("player_height", Integer.toString(this.playerHeight));
                Long l = this.totalMediaBytesTransferred;
                if (l != null) {
                    hashMap.put("media_bytes_transferred", Convert.toString(l));
                }
                sendVideoAnalyticsRequest(event, "video_engagement", hashMap);
                this.startTime = this.endTime;
            }
        }
    }

    /* access modifiers changed from: private */
    public void sendVideoViewRequest(Event event, long j, long j2) {
        HashMap hashMap = new HashMap();
        hashMap.put("load_time_ms", String.format(Locale.getDefault(), "%d", new Object[]{Long.valueOf(j)}));
        hashMap.put("start_time_ms", String.format(Locale.getDefault(), "%d", new Object[]{Long.valueOf(j2)}));
        sendVideoAnalyticsRequest(event, "video_view", hashMap);
    }

    /* access modifiers changed from: private */
    public void sendErrorRequest(Event event, String str, String str2) {
        HashMap hashMap = new HashMap();
        if (str != null && !str.isEmpty()) {
            hashMap.put(NativeProtocol.BRIDGE_ARG_ERROR_CODE, str);
        }
        if (str2 != null && !str2.isEmpty()) {
            hashMap.put("error_description", str2);
        }
        sendVideoAnalyticsRequest(event, "error", hashMap);
    }

    /* access modifiers changed from: private */
    public void sendVideoDownloadAnalyticsRequest(Event event, String str) {
        HashMap hashMap = new HashMap();
        if (((Video) event.properties.get("video")) != null) {
            HashMap hashMap2 = new HashMap(event.properties);
            if (hashMap2.containsKey("errorCode")) {
                hashMap.put(NativeProtocol.BRIDGE_ARG_ERROR_CODE, Convert.toString(hashMap2.remove("errorCode")));
            }
            if (hashMap2.containsKey(AbstractEvent.RENDITION_URL)) {
                hashMap.put("rendition_url", Convert.toString(hashMap2.remove(AbstractEvent.RENDITION_URL)));
            }
            if (hashMap2.containsKey(AbstractEvent.RENDITION_WIDTH)) {
                hashMap.put("rendition_width", Convert.toString(hashMap2.remove(AbstractEvent.RENDITION_WIDTH)));
            }
            if (hashMap2.containsKey(AbstractEvent.RENDITION_HEIGHT)) {
                hashMap.put("rendition_height", Convert.toString(hashMap2.remove(AbstractEvent.RENDITION_HEIGHT)));
            }
            if (hashMap2.containsKey(AbstractEvent.RENDITION_MIME_TYPE)) {
                hashMap.put("rendition_mime_type", Convert.toString(hashMap2.remove(AbstractEvent.RENDITION_MIME_TYPE)));
            }
            if (hashMap2.containsKey(AbstractEvent.RENDITION_INDICATED_BPS)) {
                hashMap.put("rendition_indicated_bps", Convert.toString(hashMap2.remove(AbstractEvent.RENDITION_INDICATED_BPS)));
            }
            for (Entry entry : hashMap2.entrySet()) {
                hashMap.put(entry.getKey(), Convert.toString(entry.getValue()));
            }
        }
        sendVideoAnalyticsRequest(event, str, hashMap);
    }

    /* access modifiers changed from: private */
    public void sendVideoAnalyticsRequest(Event event, String str, Map<String, String> map) {
        if (map == null) {
            map = new HashMap<>();
        }
        Video video = (Video) event.properties.get("video");
        if (video != null) {
            String id = video.getId();
            if (!id.isEmpty()) {
                map.put("video", id);
            }
            if (this.account == null) {
                String stringProperty = video.getStringProperty(com.brightcove.player.model.Video.Fields.PUBLISHER_ID);
                if (stringProperty != null && !stringProperty.isEmpty()) {
                    setAccount(stringProperty);
                }
            }
        }
        String str2 = this.account;
        if (str2 != null) {
            map.put("account", str2);
            sendAnalyticsRequest(event, str, map);
            return;
        }
        Log.e(TAG, "The account id must be sent with every analytics request.");
    }

    /* access modifiers changed from: protected */
    @SuppressLint({"NewApi"})
    public Map<String, String> sendAnalyticsRequest(Event event, String str, Map<String, String> map) {
        HashMap hashMap = new HashMap();
        hashMap.putAll(this.baseParams);
        if (map != null) {
            hashMap.putAll(map);
        }
        Video video = (Video) event.properties.get("video");
        if (video != null) {
            if (video.getId() != null) {
                String stringProperty = video.getStringProperty("name");
                if (!TextUtils.isEmpty(stringProperty)) {
                    hashMap.put("video_name", stringProperty);
                }
            }
            UUID downloadId = video.getDownloadId();
            if (downloadId != null && !hashMap.containsKey(Fields.DOWNLOAD_ID)) {
                hashMap.put(Fields.DOWNLOAD_ID, downloadId.toString());
            }
        }
        if (this.duration > 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("");
            sb.append(this.duration / 1000);
            hashMap.put("video_duration", sb.toString());
        }
        hashMap.put("session", getSessionKey());
        hashMap.put("domain", DOMAIN);
        hashMap.put("device_os", "android");
        hashMap.put("device_type", this.deviceType);
        hashMap.put("device_os_version", VERSION.RELEASE);
        if (VERSION.SDK_INT >= 21) {
            hashMap.put("device_cpu", getFirstSupportedAbi());
        } else {
            hashMap.put("device_cpu", getCpu());
        }
        hashMap.put("device", Build.DEVICE);
        hashMap.put("device_hardware", Build.HARDWARE);
        hashMap.put("device_manufacturer", Build.MANUFACTURER);
        hashMap.put("device_model", Build.MODEL);
        hashMap.put("device_product", Build.PRODUCT);
        String str2 = TimestampAnalyticsHelper.ATTR_TIME;
        StringBuilder sb2 = new StringBuilder();
        sb2.append("");
        sb2.append(new Date().getTime());
        hashMap.put(str2, sb2.toString());
        hashMap.put(Http.PLATFORM, this.platform);
        hashMap.put("platform_version", this.platformVersion);
        String str3 = this.destination;
        if (str3 != null) {
            hashMap.put("destination", str3);
        }
        String str4 = this.source;
        if (str4 != null) {
            hashMap.put("source", str4);
        }
        if (this.uniqueIdentifierEnabled) {
            hashMap.put("user", Secure.getString(this.context.getContentResolver(), "android_id"));
        }
        AnalyticsClient.getInstance(this.context).publish(AnalyticsEvent.create("video_view".equals(str) ? 1 : 0, str, hashMap), this.analyticsErrorListener);
        return hashMap;
    }

    /* access modifiers changed from: private */
    public void startVideoEngagementTimer() {
        if (!this.hasTimerStarted) {
            this.timerHandler = new Handler();
            this.timerRunnable = new Runnable() {
                public void run() {
                    if (Analytics.this.videoEngagementDataEvent != null) {
                        Analytics analytics = Analytics.this;
                        analytics.sendVideoEngagementRequest(analytics.videoEngagementDataEvent, Analytics.this.startTime, Analytics.this.endTime);
                        Analytics.this.rebufferingCount = 0;
                        if (Analytics.this.isBuffering) {
                            Analytics.this.rebufferingMs = System.currentTimeMillis();
                        } else {
                            Analytics.this.rebufferingMs = 0;
                        }
                        Analytics.this.elapsedRebufferingMs = 0;
                    }
                    Analytics.this.timerHandler.postDelayed(this, 10000);
                }
            };
            this.timerHandler.postDelayed(this.timerRunnable, 10000);
            this.hasTimerStarted = true;
        }
    }

    /* access modifiers changed from: private */
    public void stopVideoEngagementTimer() {
        Handler handler = this.timerHandler;
        if (handler != null) {
            handler.removeCallbacks(this.timerRunnable);
            this.hasTimerStarted = false;
        }
    }

    private String getCpu() {
        return Build.CPU_ABI;
    }

    @TargetApi(21)
    private String getFirstSupportedAbi() {
        return Build.SUPPORTED_ABIS[0];
    }

    /* access modifiers changed from: private */
    public void emitDidSetEvent() {
        HashMap hashMap = new HashMap();
        hashMap.put(Fields.BASE_PARAMS, this.baseParams);
        this.eventEmitter.emit(EventType.DID_SET_ANALYTICS_BASE_PARAMS, hashMap);
    }

    private String getDeviceType(Context context2) {
        String str = "tablet";
        if (context2 == null) {
            return str;
        }
        try {
            if (BrightcoveMediaController.checkTvMode(context2)) {
                return "tv";
            }
            return !LayoutUtil.isLargeScreen(context2) ? "mobile" : str;
        } catch (UnsupportedOperationException unused) {
            return "tablet";
        } catch (IllegalStateException unused2) {
            return "tablet";
        }
    }

    public boolean getUniqueIdentifierEnabled() {
        return this.uniqueIdentifierEnabled;
    }

    public void setUniqueIdentifierEnabled(boolean z) {
        this.uniqueIdentifierEnabled = z;
    }
}
