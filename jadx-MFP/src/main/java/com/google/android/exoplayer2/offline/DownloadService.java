package com.google.android.exoplayer2.offline;

import android.app.Notification;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import com.google.android.exoplayer2.offline.DownloadManager.Listener;
import com.google.android.exoplayer2.offline.DownloadManager.TaskState;
import com.google.android.exoplayer2.scheduler.Requirements;
import com.google.android.exoplayer2.scheduler.RequirementsWatcher;
import com.google.android.exoplayer2.scheduler.Scheduler;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.NotificationUtil;
import com.google.android.exoplayer2.util.Util;
import java.util.HashMap;

public abstract class DownloadService extends Service {
    public static final String ACTION_ADD = "com.google.android.exoplayer.downloadService.action.ADD";
    public static final String ACTION_INIT = "com.google.android.exoplayer.downloadService.action.INIT";
    public static final String ACTION_RELOAD_REQUIREMENTS = "com.google.android.exoplayer.downloadService.action.RELOAD_REQUIREMENTS";
    private static final String ACTION_RESTART = "com.google.android.exoplayer.downloadService.action.RESTART";
    private static final boolean DEBUG = false;
    public static final long DEFAULT_FOREGROUND_NOTIFICATION_UPDATE_INTERVAL = 1000;
    private static final Requirements DEFAULT_REQUIREMENTS = new Requirements(1, false, false);
    public static final int FOREGROUND_NOTIFICATION_ID_NONE = 0;
    public static final String KEY_DOWNLOAD_ACTION = "download_action";
    public static final String KEY_FOREGROUND = "foreground";
    private static final String TAG = "DownloadService";
    private static final HashMap<Class<? extends DownloadService>, RequirementsHelper> requirementsHelpers = new HashMap<>();
    @Nullable
    private final String channelId;
    @StringRes
    private final int channelName;
    /* access modifiers changed from: private */
    public DownloadManager downloadManager;
    private DownloadManagerListener downloadManagerListener;
    /* access modifiers changed from: private */
    @Nullable
    public final ForegroundNotificationUpdater foregroundNotificationUpdater;
    private int lastStartId;
    private boolean startedInForeground;
    private boolean taskRemoved;

    private final class DownloadManagerListener implements Listener {
        private DownloadManagerListener() {
        }

        public void onInitialized(DownloadManager downloadManager) {
            DownloadService downloadService = DownloadService.this;
            downloadService.maybeStartWatchingRequirements(downloadService.getRequirements());
        }

        public void onTaskStateChanged(DownloadManager downloadManager, TaskState taskState) {
            DownloadService.this.onTaskStateChanged(taskState);
            if (DownloadService.this.foregroundNotificationUpdater == null) {
                return;
            }
            if (taskState.state == 1) {
                DownloadService.this.foregroundNotificationUpdater.startPeriodicUpdates();
            } else {
                DownloadService.this.foregroundNotificationUpdater.update();
            }
        }

        public final void onIdle(DownloadManager downloadManager) {
            DownloadService.this.stop();
        }
    }

    private final class ForegroundNotificationUpdater implements Runnable {
        private final Handler handler = new Handler(Looper.getMainLooper());
        private boolean notificationDisplayed;
        private final int notificationId;
        private boolean periodicUpdatesStarted;
        private final long updateInterval;

        public ForegroundNotificationUpdater(int i, long j) {
            this.notificationId = i;
            this.updateInterval = j;
        }

        public void startPeriodicUpdates() {
            this.periodicUpdatesStarted = true;
            update();
        }

        public void stopPeriodicUpdates() {
            this.periodicUpdatesStarted = false;
            this.handler.removeCallbacks(this);
        }

        public void update() {
            TaskState[] allTaskStates = DownloadService.this.downloadManager.getAllTaskStates();
            DownloadService downloadService = DownloadService.this;
            downloadService.startForeground(this.notificationId, downloadService.getForegroundNotification(allTaskStates));
            this.notificationDisplayed = true;
            if (this.periodicUpdatesStarted) {
                this.handler.removeCallbacks(this);
                this.handler.postDelayed(this, this.updateInterval);
            }
        }

        public void showNotificationIfNotAlready() {
            if (!this.notificationDisplayed) {
                update();
            }
        }

        public void run() {
            update();
        }
    }

    private static final class RequirementsHelper implements RequirementsWatcher.Listener {
        private final Context context;
        private final Requirements requirements;
        private final RequirementsWatcher requirementsWatcher;
        @Nullable
        private final Scheduler scheduler;
        private final Class<? extends DownloadService> serviceClass;

        private RequirementsHelper(Context context2, Requirements requirements2, @Nullable Scheduler scheduler2, Class<? extends DownloadService> cls) {
            this.context = context2;
            this.requirements = requirements2;
            this.scheduler = scheduler2;
            this.serviceClass = cls;
            this.requirementsWatcher = new RequirementsWatcher(context2, this, requirements2);
        }

        public void start() {
            this.requirementsWatcher.start();
        }

        public void stop() {
            this.requirementsWatcher.stop();
            Scheduler scheduler2 = this.scheduler;
            if (scheduler2 != null) {
                scheduler2.cancel();
            }
        }

        public void requirementsMet(RequirementsWatcher requirementsWatcher2) {
            try {
                notifyService();
                Scheduler scheduler2 = this.scheduler;
                if (scheduler2 != null) {
                    scheduler2.cancel();
                }
            } catch (Exception unused) {
            }
        }

        public void requirementsNotMet(RequirementsWatcher requirementsWatcher2) {
            try {
                notifyService();
            } catch (Exception unused) {
            }
            if (this.scheduler != null) {
                if (!this.scheduler.schedule(this.requirements, this.context.getPackageName(), DownloadService.ACTION_RESTART)) {
                    Log.e(DownloadService.TAG, "Scheduling downloads failed.");
                }
            }
        }

        private void notifyService() throws Exception {
            try {
                this.context.startService(DownloadService.getIntent(this.context, this.serviceClass, DownloadService.ACTION_INIT));
            } catch (IllegalStateException e) {
                throw new Exception(e);
            }
        }
    }

    private void logd(String str) {
    }

    /* access modifiers changed from: protected */
    public abstract DownloadManager getDownloadManager();

    /* access modifiers changed from: protected */
    @Nullable
    public abstract Scheduler getScheduler();

    @Nullable
    public IBinder onBind(Intent intent) {
        return null;
    }

    /* access modifiers changed from: protected */
    public void onTaskStateChanged(TaskState taskState) {
    }

    protected DownloadService(int i) {
        this(i, 1000);
    }

    protected DownloadService(int i, long j) {
        this(i, j, null, 0);
    }

    protected DownloadService(int i, long j, @Nullable String str, @StringRes int i2) {
        ForegroundNotificationUpdater foregroundNotificationUpdater2;
        if (i == 0) {
            foregroundNotificationUpdater2 = null;
        } else {
            foregroundNotificationUpdater2 = new ForegroundNotificationUpdater(i, j);
        }
        this.foregroundNotificationUpdater = foregroundNotificationUpdater2;
        this.channelId = str;
        this.channelName = i2;
    }

    public static Intent buildAddActionIntent(Context context, Class<? extends DownloadService> cls, DownloadAction downloadAction, boolean z) {
        return getIntent(context, cls, ACTION_ADD).putExtra(KEY_DOWNLOAD_ACTION, downloadAction.toByteArray()).putExtra(KEY_FOREGROUND, z);
    }

    public static void startWithAction(Context context, Class<? extends DownloadService> cls, DownloadAction downloadAction, boolean z) {
        Intent buildAddActionIntent = buildAddActionIntent(context, cls, downloadAction, z);
        if (z) {
            Util.startForegroundService(context, buildAddActionIntent);
        } else {
            context.startService(buildAddActionIntent);
        }
    }

    public static void start(Context context, Class<? extends DownloadService> cls) {
        context.startService(getIntent(context, cls, ACTION_INIT));
    }

    public static void startForeground(Context context, Class<? extends DownloadService> cls) {
        Util.startForegroundService(context, getIntent(context, cls, ACTION_INIT).putExtra(KEY_FOREGROUND, true));
    }

    public void onCreate() {
        logd("onCreate");
        String str = this.channelId;
        if (str != null) {
            NotificationUtil.createNotificationChannel(this, str, this.channelName, 2);
        }
        this.downloadManager = getDownloadManager();
        this.downloadManagerListener = new DownloadManagerListener();
        this.downloadManager.addListener(this.downloadManagerListener);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0067, code lost:
        if (r1.equals(ACTION_INIT) == false) goto L_0x0088;
     */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x008c  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00a3  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00a7  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00cf  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00d5  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00e5  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int onStartCommand(android.content.Intent r5, int r6, int r7) {
        /*
            r4 = this;
            r4.lastStartId = r7
            r6 = 0
            r4.taskRemoved = r6
            r0 = 1
            if (r5 == 0) goto L_0x0026
            java.lang.String r1 = r5.getAction()
            boolean r2 = r4.startedInForeground
            java.lang.String r3 = "foreground"
            boolean r3 = r5.getBooleanExtra(r3, r6)
            if (r3 != 0) goto L_0x0021
            java.lang.String r3 = "com.google.android.exoplayer.downloadService.action.RESTART"
            boolean r3 = r3.equals(r1)
            if (r3 == 0) goto L_0x001f
            goto L_0x0021
        L_0x001f:
            r3 = 0
            goto L_0x0022
        L_0x0021:
            r3 = 1
        L_0x0022:
            r2 = r2 | r3
            r4.startedInForeground = r2
            goto L_0x0027
        L_0x0026:
            r1 = 0
        L_0x0027:
            if (r1 != 0) goto L_0x002b
            java.lang.String r1 = "com.google.android.exoplayer.downloadService.action.INIT"
        L_0x002b:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "onStartCommand action: "
            r2.append(r3)
            r2.append(r1)
            java.lang.String r3 = " startId: "
            r2.append(r3)
            r2.append(r7)
            java.lang.String r7 = r2.toString()
            r4.logd(r7)
            r7 = -1
            int r2 = r1.hashCode()
            r3 = -871181424(0xffffffffcc12d390, float:-3.8489664E7)
            if (r2 == r3) goto L_0x007e
            r3 = -608867945(0xffffffffdbb56997, float:-1.02126136E17)
            if (r2 == r3) goto L_0x0074
            r3 = -382886238(0xffffffffe92d9ea2, float:-1.311833E25)
            if (r2 == r3) goto L_0x006a
            r3 = 1015676687(0x3c89ff0f, float:0.016845254)
            if (r2 == r3) goto L_0x0061
            goto L_0x0088
        L_0x0061:
            java.lang.String r2 = "com.google.android.exoplayer.downloadService.action.INIT"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L_0x0088
            goto L_0x0089
        L_0x006a:
            java.lang.String r6 = "com.google.android.exoplayer.downloadService.action.ADD"
            boolean r6 = r1.equals(r6)
            if (r6 == 0) goto L_0x0088
            r6 = 2
            goto L_0x0089
        L_0x0074:
            java.lang.String r6 = "com.google.android.exoplayer.downloadService.action.RELOAD_REQUIREMENTS"
            boolean r6 = r1.equals(r6)
            if (r6 == 0) goto L_0x0088
            r6 = 3
            goto L_0x0089
        L_0x007e:
            java.lang.String r6 = "com.google.android.exoplayer.downloadService.action.RESTART"
            boolean r6 = r1.equals(r6)
            if (r6 == 0) goto L_0x0088
            r6 = 1
            goto L_0x0089
        L_0x0088:
            r6 = -1
        L_0x0089:
            switch(r6) {
                case 0: goto L_0x00c5;
                case 1: goto L_0x00c5;
                case 2: goto L_0x00a7;
                case 3: goto L_0x00a3;
                default: goto L_0x008c;
            }
        L_0x008c:
            java.lang.String r5 = "DownloadService"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "Ignoring unrecognized action: "
            r6.append(r7)
            r6.append(r1)
            java.lang.String r6 = r6.toString()
            com.google.android.exoplayer2.util.Log.e(r5, r6)
            goto L_0x00c5
        L_0x00a3:
            r4.stopWatchingRequirements()
            goto L_0x00c5
        L_0x00a7:
            java.lang.String r6 = "download_action"
            byte[] r5 = r5.getByteArrayExtra(r6)
            if (r5 != 0) goto L_0x00b7
            java.lang.String r5 = "DownloadService"
            java.lang.String r6 = "Ignoring ADD action with no action data"
            com.google.android.exoplayer2.util.Log.e(r5, r6)
            goto L_0x00c5
        L_0x00b7:
            com.google.android.exoplayer2.offline.DownloadManager r6 = r4.downloadManager     // Catch:{ IOException -> 0x00bd }
            r6.handleAction(r5)     // Catch:{ IOException -> 0x00bd }
            goto L_0x00c5
        L_0x00bd:
            r5 = move-exception
            java.lang.String r6 = "DownloadService"
            java.lang.String r7 = "Failed to handle ADD action"
            com.google.android.exoplayer2.util.Log.e(r6, r7, r5)
        L_0x00c5:
            com.google.android.exoplayer2.scheduler.Requirements r5 = r4.getRequirements()
            boolean r6 = r5.checkRequirements(r4)
            if (r6 == 0) goto L_0x00d5
            com.google.android.exoplayer2.offline.DownloadManager r6 = r4.downloadManager
            r6.startDownloads()
            goto L_0x00da
        L_0x00d5:
            com.google.android.exoplayer2.offline.DownloadManager r6 = r4.downloadManager
            r6.stopDownloads()
        L_0x00da:
            r4.maybeStartWatchingRequirements(r5)
            com.google.android.exoplayer2.offline.DownloadManager r5 = r4.downloadManager
            boolean r5 = r5.isIdle()
            if (r5 == 0) goto L_0x00e8
            r4.stop()
        L_0x00e8:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.offline.DownloadService.onStartCommand(android.content.Intent, int, int):int");
    }

    public void onTaskRemoved(Intent intent) {
        StringBuilder sb = new StringBuilder();
        sb.append("onTaskRemoved rootIntent: ");
        sb.append(intent);
        logd(sb.toString());
        this.taskRemoved = true;
    }

    public void onDestroy() {
        logd("onDestroy");
        ForegroundNotificationUpdater foregroundNotificationUpdater2 = this.foregroundNotificationUpdater;
        if (foregroundNotificationUpdater2 != null) {
            foregroundNotificationUpdater2.stopPeriodicUpdates();
        }
        this.downloadManager.removeListener(this.downloadManagerListener);
        maybeStopWatchingRequirements();
    }

    /* access modifiers changed from: protected */
    public Requirements getRequirements() {
        return DEFAULT_REQUIREMENTS;
    }

    /* access modifiers changed from: protected */
    public Notification getForegroundNotification(TaskState[] taskStateArr) {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getName());
        sb.append(" is started in the foreground but getForegroundNotification() is not implemented.");
        throw new IllegalStateException(sb.toString());
    }

    /* access modifiers changed from: private */
    public void maybeStartWatchingRequirements(Requirements requirements) {
        if (this.downloadManager.getDownloadCount() != 0) {
            Class cls = getClass();
            if (((RequirementsHelper) requirementsHelpers.get(cls)) == null) {
                RequirementsHelper requirementsHelper = new RequirementsHelper(this, requirements, getScheduler(), cls);
                requirementsHelpers.put(cls, requirementsHelper);
                requirementsHelper.start();
                logd("started watching requirements");
            }
        }
    }

    private void maybeStopWatchingRequirements() {
        if (this.downloadManager.getDownloadCount() <= 0) {
            stopWatchingRequirements();
        }
    }

    private void stopWatchingRequirements() {
        RequirementsHelper requirementsHelper = (RequirementsHelper) requirementsHelpers.remove(getClass());
        if (requirementsHelper != null) {
            requirementsHelper.stop();
            logd("stopped watching requirements");
        }
    }

    /* access modifiers changed from: private */
    public void stop() {
        ForegroundNotificationUpdater foregroundNotificationUpdater2 = this.foregroundNotificationUpdater;
        if (foregroundNotificationUpdater2 != null) {
            foregroundNotificationUpdater2.stopPeriodicUpdates();
            if (this.startedInForeground && Util.SDK_INT >= 26) {
                this.foregroundNotificationUpdater.showNotificationIfNotAlready();
            }
        }
        if (Util.SDK_INT >= 28 || !this.taskRemoved) {
            boolean stopSelfResult = stopSelfResult(this.lastStartId);
            StringBuilder sb = new StringBuilder();
            sb.append("stopSelf(");
            sb.append(this.lastStartId);
            sb.append(") result: ");
            sb.append(stopSelfResult);
            logd(sb.toString());
            return;
        }
        stopSelf();
        logd("stopSelf()");
    }

    /* access modifiers changed from: private */
    public static Intent getIntent(Context context, Class<? extends DownloadService> cls, String str) {
        return new Intent(context, cls).setAction(str);
    }
}
