package com.integralads.avid.library.inmobi;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Handler;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.integralads.avid.library.inmobi.DownloadAvidTask.DownloadAvidTaskListener;
import com.integralads.avid.library.inmobi.utils.NetworkUtils;

public class AvidLoader implements DownloadAvidTaskListener {
    private static AvidLoader avidLoader = new AvidLoader();
    /* access modifiers changed from: private */
    public DownloadAvidTask activeTask;
    /* access modifiers changed from: private */
    public final Runnable avidDownloadRunnable = new Runnable() {
        public void run() {
            if (AvidLoader.this.context == null || !NetworkUtils.isNetworkAvailable(AvidLoader.this.context)) {
                AvidLoader.this.repeatLoading();
            } else {
                AvidLoader.this.loadAvidJSFromUrl();
            }
        }
    };
    /* access modifiers changed from: private */
    public Context context;
    private AvidLoaderListener listener;
    private TaskExecutor taskExecutor = new TaskExecutor();
    private TaskRepeater taskRepeater;

    public interface AvidLoaderListener {
        void onAvidLoaded();
    }

    public class TaskExecutor {
        public TaskExecutor() {
        }

        public void executeTask(DownloadAvidTask downloadAvidTask) {
            if (VERSION.SDK_INT >= 11) {
                AvidLoader.this.activeTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{"https://mobile-static.adsafeprotected.com/avid-v2.js"});
            } else {
                AvidLoader.this.activeTask.execute(new String[]{"https://mobile-static.adsafeprotected.com/avid-v2.js"});
            }
        }
    }

    public class TaskRepeater {
        private Handler handler = new Handler();

        public TaskRepeater() {
        }

        public void repeatLoading() {
            this.handler.postDelayed(AvidLoader.this.avidDownloadRunnable, AdaptiveTrackSelection.DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS);
        }

        public void cleanup() {
            this.handler.removeCallbacks(AvidLoader.this.avidDownloadRunnable);
        }
    }

    public static AvidLoader getInstance() {
        return avidLoader;
    }

    public void registerAvidLoader(Context context2) {
        this.context = context2;
        this.taskRepeater = new TaskRepeater();
        loadAvidJSFromUrl();
    }

    public void unregisterAvidLoader() {
        TaskRepeater taskRepeater2 = this.taskRepeater;
        if (taskRepeater2 != null) {
            taskRepeater2.cleanup();
            this.taskRepeater = null;
        }
        this.listener = null;
        this.context = null;
    }

    public void setListener(AvidLoaderListener avidLoaderListener) {
        this.listener = avidLoaderListener;
    }

    /* access modifiers changed from: private */
    public void loadAvidJSFromUrl() {
        if (!AvidBridge.isAvidJsReady() && this.activeTask == null) {
            this.activeTask = new DownloadAvidTask();
            this.activeTask.setListener(this);
            this.taskExecutor.executeTask(this.activeTask);
        }
    }

    /* access modifiers changed from: private */
    public void repeatLoading() {
        TaskRepeater taskRepeater2 = this.taskRepeater;
        if (taskRepeater2 != null) {
            taskRepeater2.repeatLoading();
        }
    }

    public void onLoadAvid(String str) {
        this.activeTask = null;
        AvidBridge.setAvidJs(str);
        AvidLoaderListener avidLoaderListener = this.listener;
        if (avidLoaderListener != null) {
            avidLoaderListener.onAvidLoaded();
        }
    }

    public void failedToLoadAvid() {
        this.activeTask = null;
        repeatLoading();
    }
}
