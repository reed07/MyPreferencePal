package com.brightcove.player.management;

import android.util.Log;
import com.brightcove.player.BuildConfig;
import com.brightcove.player.event.AbstractComponent;
import com.brightcove.player.event.AbstractEvent;
import com.brightcove.player.event.Default;
import com.brightcove.player.event.Emits;
import com.brightcove.player.event.Event;
import com.brightcove.player.event.EventEmitter;
import com.brightcove.player.event.EventListener;
import com.brightcove.player.event.EventType;
import com.brightcove.player.event.ListensFor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@ListensFor(events = {"registerPlugin"})
@Emits(events = {"version"})
public class BrightcovePluginManager extends AbstractComponent {
    private static final String CRASHLYTICS_LOG = "log";
    /* access modifiers changed from: private */
    public static final String TAG = "BrightcovePluginManager";
    private Class<?> crashlyticsClass;
    private Method crashlyticsLogMethod;
    private boolean isCrashlyticsAvailable;
    OnRegisterPluginListener onRegisterPluginListener;
    /* access modifiers changed from: private */
    public List<String> pluginsInUse = new ArrayList();

    protected class OnRegisterPluginListener implements EventListener {
        protected OnRegisterPluginListener() {
        }

        @Default
        public void processEvent(Event event) {
            if (event.properties.containsKey(AbstractEvent.PLUGIN_NAME)) {
                String str = (String) event.properties.get(AbstractEvent.PLUGIN_NAME);
                String access$000 = BrightcovePluginManager.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("OnRegisterPluginListener: plugin: ");
                sb.append(str);
                Log.v(access$000, sb.toString());
                if (!BrightcovePluginManager.this.pluginsInUse.contains(str)) {
                    BrightcovePluginManager.this.pluginsInUse.add(str);
                }
            }
        }
    }

    public String getCommitIdentifier() {
        return BuildConfig.COMMIT_ID;
    }

    public String getReleaseIdentifier() {
        return BuildConfig.RELEASE_ID;
    }

    public BrightcovePluginManager(EventEmitter eventEmitter) {
        super(eventEmitter, BrightcovePluginManager.class);
        initializeListeners();
        checkForCrashlytics();
        generateCrashlyticsMethods();
        crashlyticsLog("Git Commit SHA: 9c14743d2387ca2184dc24f622eff3893bdb910a");
        crashlyticsLog("Release Number: 6.8.0");
        HashMap hashMap = new HashMap();
        hashMap.put(AbstractEvent.BUILD_VERSION, BuildConfig.RELEASE_ID);
        getEventEmitter().emit("version", hashMap);
    }

    /* access modifiers changed from: protected */
    public void initializeListeners() {
        this.onRegisterPluginListener = new OnRegisterPluginListener();
        addListener(EventType.REGISTER_PLUGIN, this.onRegisterPluginListener);
    }

    /* access modifiers changed from: protected */
    public void checkForCrashlytics() {
        try {
            this.crashlyticsClass = Class.forName("com.crashlytics.android.Crashlytics");
            this.isCrashlyticsAvailable = true;
        } catch (ClassNotFoundException unused) {
            Log.i(TAG, "Crashlytics was not found. Logging to console only.");
            this.isCrashlyticsAvailable = false;
        }
    }

    /* access modifiers changed from: protected */
    public void generateCrashlyticsMethods() {
        if (this.isCrashlyticsAvailable) {
            try {
                this.crashlyticsLogMethod = this.crashlyticsClass.getMethod(CRASHLYTICS_LOG, new Class[]{String.class});
            } catch (NoSuchMethodException unused) {
                Log.i(TAG, "Failed to wrap crashlytics methods.");
            }
        }
    }

    public void crashlyticsLog(String str) {
        String str2 = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("crashlyticsLog: ");
        sb.append(str);
        Log.d(str2, sb.toString());
        if (this.isCrashlyticsAvailable) {
            Method method = this.crashlyticsLogMethod;
            if (method != null) {
                try {
                    method.invoke(null, new Object[]{str});
                } catch (IllegalArgumentException unused) {
                    Log.i(TAG, "crashlyticsLog: Illegal argument exception occurred.");
                } catch (IllegalAccessException unused2) {
                    Log.i(TAG, "crashlyticsLog: Illegal access exception occurred.");
                } catch (InvocationTargetException unused3) {
                    Log.i(TAG, "crashlyticsLog: Invocation target exception occurred.");
                }
            }
        }
    }

    public List<String> getPluginsInUse() {
        return this.pluginsInUse;
    }
}
