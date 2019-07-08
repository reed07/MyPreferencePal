package com.brightcove.player.analytics;

import android.content.Context;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.brightcove.player.analytics.IAnalyticsHandler.ProcessListener;
import com.brightcove.player.logging.Log;
import com.brightcove.player.network.ConnectivityMonitor;
import com.brightcove.player.network.ConnectivityMonitor.Listener;
import com.brightcove.player.network.HttpService;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import java.net.UnknownServiceException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class DefaultAnalyticsHandler implements IAnalyticsHandler {
    private static final String BASE_URL = "https://metrics.brightcove.com/v2/tracker";
    private static final int CONNECTION_TIMEOUT_MS = 60000;
    private static volatile DefaultAnalyticsHandler INSTANCE = null;
    private static final int READ_TIMEOUT_MS = 60000;
    /* access modifiers changed from: private */
    public static final String TAG = "DefaultAnalyticsHandler";
    /* access modifiers changed from: private */
    public final AnalyticsStore analyticsStore;
    private final Listener connectivityListener = new Listener() {
        public void onConnectivityChanged(boolean z, @Nullable NetworkInfo networkInfo) {
            if (z) {
                Log.d(DefaultAnalyticsHandler.TAG, "Host is connected to a network. Analytics transmission will resume", new Object[0]);
                DefaultAnalyticsHandler.this.processBacklog();
                return;
            }
            Log.d(DefaultAnalyticsHandler.TAG, "Host is not connected to a network. Analytics events will be save and transmitted later", new Object[0]);
        }
    };
    /* access modifiers changed from: private */
    public final ConnectivityMonitor connectivityMonitor;
    private final HttpService httpService;
    @Nullable
    private AtomicReference<ProcessListener> processListener = new AtomicReference<>();
    /* access modifiers changed from: private */
    public final AtomicBoolean sendingBacklog = new AtomicBoolean();

    public DefaultAnalyticsHandler setProcessListener(@Nullable ProcessListener processListener2) {
        this.processListener.set(processListener2);
        return this;
    }

    @Nullable
    public ProcessListener getProcessListener() {
        return (ProcessListener) this.processListener.get();
    }

    private DefaultAnalyticsHandler(@NonNull Context context) {
        this.connectivityMonitor = ConnectivityMonitor.getInstance(context);
        this.analyticsStore = AnalyticsStore.getInstance(context);
        this.httpService = new HttpService(60000, 60000);
        processBacklog();
    }

    public static DefaultAnalyticsHandler getInstance(@NonNull Context context) {
        if (INSTANCE == null) {
            synchronized (DefaultAnalyticsHandler.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DefaultAnalyticsHandler(context.getApplicationContext());
                }
            }
        }
        return INSTANCE;
    }

    private static Exception notifyRetryLimitError(@Nullable IAnalyticsErrorListener iAnalyticsErrorListener, @NonNull AnalyticsEvent analyticsEvent, long j, @Nullable Exception exc) {
        AnalyticsRetryLimitException analyticsRetryLimitException = new AnalyticsRetryLimitException(analyticsEvent, j, exc);
        if (iAnalyticsErrorListener == null) {
            Log.w(TAG, "Retry limit reached: retryLimit = %d, event = %s", exc, Long.valueOf(j), analyticsEvent);
        } else {
            iAnalyticsErrorListener.onAnalyticsError(analyticsRetryLimitException);
        }
        return analyticsRetryLimitException;
    }

    private static Exception notifyOutOfSpaceError(@Nullable IAnalyticsErrorListener iAnalyticsErrorListener, @NonNull AnalyticsEvent analyticsEvent, long j, long j2, @Nullable Exception exc) {
        AnalyticsOutOfSpaceException analyticsOutOfSpaceException = new AnalyticsOutOfSpaceException(analyticsEvent, j, j2, exc);
        if (iAnalyticsErrorListener == null) {
            Log.w(TAG, "Event dropped because analytics backlog is full: backlogSize = %d bytes,  backlogLimit = %d bytes, event = %s", Long.valueOf(j), Long.valueOf(j2), analyticsEvent);
        } else {
            iAnalyticsErrorListener.onAnalyticsError(analyticsOutOfSpaceException);
        }
        return analyticsOutOfSpaceException;
    }

    public void onAttached() {
        this.connectivityMonitor.addListener(this.connectivityListener);
    }

    public void onRemoved() {
        this.connectivityMonitor.removeListener(this.connectivityListener);
    }

    public void onAnalyticsEvent(@NonNull final AnalyticsEvent analyticsEvent, @Nullable final IAnalyticsErrorListener iAnalyticsErrorListener) {
        Observable.fromCallable(new Callable<Boolean>() {
            public Boolean call() throws Exception {
                DefaultAnalyticsHandler.this.submitEvents(true, iAnalyticsErrorListener, analyticsEvent);
                return Boolean.valueOf(true);
            }
        }).subscribeOn(Schedulers.io()).subscribe();
    }

    public void onNetworkEntitlementChanged(int i, boolean z) {
        if (z) {
            if (this.connectivityMonitor.isConnected(i)) {
                processBacklog();
            }
        }
    }

    /* access modifiers changed from: private */
    public void processBacklog() {
        if (!this.sendingBacklog.get()) {
            this.sendingBacklog.set(true);
            Observable.fromCallable(new Callable<Boolean>() {
                public Boolean call() throws Exception {
                    List list;
                    int size;
                    do {
                        if (DefaultAnalyticsHandler.this.connectivityMonitor.isConnected()) {
                            list = DefaultAnalyticsHandler.this.analyticsStore.getCriticalEvents(AnalyticsClient.getBatchSize());
                            if (list.size() == 0 && DefaultAnalyticsHandler.this.connectivityMonitor.isConnected(AnalyticsClient.getNetworkEntitlements())) {
                                list = DefaultAnalyticsHandler.this.analyticsStore.getBacklog(AnalyticsClient.getBatchSize());
                            }
                        } else {
                            list = Collections.emptyList();
                        }
                        size = list.size();
                        if (size > 0) {
                            DefaultAnalyticsHandler.this.submitEvents(false, null, (AnalyticsEvent[]) list.toArray(new AnalyticsEvent[0]));
                            continue;
                        }
                    } while (size > 0);
                    DefaultAnalyticsHandler.this.sendingBacklog.set(false);
                    return Boolean.valueOf(true);
                }
            }).subscribeOn(Schedulers.io()).subscribe();
        }
    }

    /* access modifiers changed from: private */
    public void submitEvents(boolean z, @Nullable IAnalyticsErrorListener iAnalyticsErrorListener, @NonNull AnalyticsEvent... analyticsEventArr) {
        Exception exc;
        int i;
        boolean z2;
        int i2;
        for (AnalyticsEvent analyticsEvent : analyticsEventArr) {
            int priority = analyticsEvent.getPriority();
            int attemptsMade = analyticsEvent.getAttemptsMade();
            if (!this.connectivityMonitor.isConnected()) {
                exc = new Exception("No Network");
                i = attemptsMade;
                z2 = false;
            } else if (z || priority > 1 || this.connectivityMonitor.isConnected(AnalyticsClient.getNetworkEntitlements())) {
                try {
                    trySubmit(analyticsEvent);
                    int i3 = attemptsMade + 1;
                    analyticsEvent.setAttemptsMade(i3);
                    exc = null;
                    i = i3;
                    z2 = true;
                } catch (Exception e) {
                    int i4 = attemptsMade + 1;
                    analyticsEvent.setAttemptsMade(i4);
                    exc = e;
                    i = i4;
                    z2 = false;
                } catch (Throwable th) {
                    analyticsEvent.setAttemptsMade(attemptsMade + 1);
                    throw th;
                }
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("Network Not Allowed: ");
                sb.append(this.connectivityMonitor.getActiveNetworkName());
                exc = new Exception(sb.toString());
                i = attemptsMade;
                z2 = false;
            }
            if (z2) {
                if (analyticsEvent.getKey() != null) {
                    this.analyticsStore.deleteEntity(analyticsEvent);
                }
                i2 = 1;
            } else if (priority > -1) {
                long retryLimit = (long) AnalyticsClient.getRetryLimit();
                if (priority > 0 || ((long) i) < retryLimit) {
                    Exception saveEvent = saveEvent(analyticsEvent, iAnalyticsErrorListener, exc);
                    if (saveEvent == null) {
                        saveEvent = exc;
                        i2 = 2;
                    } else {
                        i2 = 0;
                    }
                    exc = saveEvent;
                } else {
                    this.analyticsStore.deleteEntity(analyticsEvent);
                    Exception notifyRetryLimitError = notifyRetryLimitError(iAnalyticsErrorListener, analyticsEvent, retryLimit, exc);
                    if (notifyRetryLimitError != null) {
                        exc = notifyRetryLimitError;
                        i2 = 0;
                    } else {
                        i2 = 0;
                    }
                }
            } else {
                i2 = 0;
            }
            notifyProcessListener(analyticsEvent, i2, exc);
        }
    }

    private void notifyProcessListener(@NonNull AnalyticsEvent analyticsEvent, int i, @Nullable Exception exc) {
        final ProcessListener processListener2 = getProcessListener();
        if (processListener2 != null) {
            final AnalyticsEvent analyticsEvent2 = analyticsEvent;
            final int i2 = i;
            final Exception exc2 = exc;
            AnonymousClass4 r0 = new Callable<Boolean>() {
                public Boolean call() throws Exception {
                    processListener2.onProcessed(analyticsEvent2, i2, exc2);
                    return Boolean.valueOf(true);
                }
            };
            Observable.fromCallable(r0).subscribeOn(Schedulers.computation()).subscribe();
        }
    }

    private void trySubmit(AnalyticsEvent analyticsEvent) throws Exception {
        HashMap hashMap = new HashMap();
        hashMap.put("event", analyticsEvent.type);
        for (Entry entry : analyticsEvent.parameters.entrySet()) {
            hashMap.put(entry.getKey(), entry.getValue());
        }
        String doGetRequest = this.httpService.doGetRequest(HttpService.buildURIWithQueryParameters(BASE_URL, hashMap));
        if (TextUtils.isEmpty(doGetRequest) || !doGetRequest.startsWith("GIF89a")) {
            throw new UnknownServiceException(doGetRequest);
        }
    }

    private Exception saveEvent(@NonNull AnalyticsEvent analyticsEvent, @Nullable IAnalyticsErrorListener iAnalyticsErrorListener, @Nullable Exception exc) {
        if (analyticsEvent.getKey() == null) {
            long fileSize = this.analyticsStore.getFileSize();
            long backlogLimit = AnalyticsClient.getBacklogLimit();
            int i = (backlogLimit > 0 ? 1 : (backlogLimit == 0 ? 0 : -1));
            if (i > 0 && fileSize >= backlogLimit) {
                this.analyticsStore.deleteNonEssentialEvents();
                if (this.analyticsStore.compact()) {
                    fileSize = this.analyticsStore.getFileSize();
                }
            }
            if (i > 0 && fileSize >= backlogLimit) {
                return notifyOutOfSpaceError(iAnalyticsErrorListener, analyticsEvent, fileSize, backlogLimit, exc);
            }
        }
        this.analyticsStore.saveEntity(analyticsEvent);
        return null;
    }
}
