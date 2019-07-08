package com.myfitnesspal.shared.service.analytics;

import com.uacf.core.util.Strings;
import com.uacf.sync.engine.UacfScheduleEnqueuedInfo;
import com.uacf.sync.engine.UacfScheduleFailedInfo;
import com.uacf.sync.engine.UacfScheduleFinishedInfo;
import com.uacf.sync.engine.UacfScheduleOp;
import com.uacf.sync.engine.UacfScheduleProgressInfo;
import com.uacf.sync.engine.UacfScheduleStartedInfo;
import com.uacf.sync.engine.UacfSchedulerDelegate;
import com.uacf.sync.engine.UacfSchedulerEngine;
import com.uacf.sync.engine.UacfSchedulerEngineDelegate;
import io.uacf.clientevents.sdk.ClientEventsUacfScheduleOp;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Provider;

public class AnalyticsSyncDelegate implements UacfSchedulerEngineDelegate<AnalyticsSyncMode> {
    private final Provider<AnalyticsSyncOp> analyticsOpProvider;

    /* renamed from: com.myfitnesspal.shared.service.analytics.AnalyticsSyncDelegate$1 reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$myfitnesspal$shared$service$analytics$AnalyticsSyncMode = new int[AnalyticsSyncMode.values().length];

        static {
            try {
                $SwitchMap$com$myfitnesspal$shared$service$analytics$AnalyticsSyncMode[AnalyticsSyncMode.Analytics.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public static class SchedulerDelegate implements UacfSchedulerDelegate<AnalyticsSyncMode> {
        private static final int SYNC_DEBOUNCE_TIMEOUT_MILLIS = 5000;
        private static final long SYNC_INTERVAL_MILLIS = 900000;
        private UacfSchedulerEngine<AnalyticsSyncMode> engine;

        public long getPeriodicSyncTime() {
            return SYNC_INTERVAL_MILLIS;
        }

        public int getSyncDebounceTimeout() {
            return 5000;
        }

        public boolean requiresReschedule(AnalyticsSyncMode analyticsSyncMode) {
            return false;
        }

        public boolean shouldResetLastPeriodicSyncTime(AnalyticsSyncMode analyticsSyncMode) {
            return false;
        }

        public SchedulerDelegate(UacfSchedulerEngine<AnalyticsSyncMode> uacfSchedulerEngine) {
            this.engine = uacfSchedulerEngine;
        }

        public UacfSchedulerEngine<AnalyticsSyncMode> getSyncEngine() {
            return this.engine;
        }

        public AnalyticsSyncMode[] getDefaultSyncTypes() {
            return new AnalyticsSyncMode[]{AnalyticsSyncMode.Analytics};
        }
    }

    public void onSyncEnqueued(UacfScheduleEnqueuedInfo<AnalyticsSyncMode> uacfScheduleEnqueuedInfo) {
    }

    public void onSyncFailed(UacfScheduleFailedInfo<AnalyticsSyncMode> uacfScheduleFailedInfo) {
    }

    public void onSyncFinished(UacfScheduleFinishedInfo<AnalyticsSyncMode> uacfScheduleFinishedInfo) {
    }

    public void onSyncProgress(UacfScheduleProgressInfo<AnalyticsSyncMode> uacfScheduleProgressInfo) {
    }

    public void onSyncStarted(UacfScheduleStartedInfo<AnalyticsSyncMode> uacfScheduleStartedInfo) {
    }

    public AnalyticsSyncDelegate(Provider<AnalyticsSyncOp> provider) {
        this.analyticsOpProvider = provider;
    }

    public List<UacfScheduleOp> getSyncOpsForType(AnalyticsSyncMode analyticsSyncMode) {
        if (AnonymousClass1.$SwitchMap$com$myfitnesspal$shared$service$analytics$AnalyticsSyncMode[analyticsSyncMode.ordinal()] == 1) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(this.analyticsOpProvider.get());
            arrayList.add(new ClientEventsUacfScheduleOp());
            return arrayList;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("invalid sync mode: ");
        sb.append(Strings.toString(analyticsSyncMode));
        throw new IllegalArgumentException(sb.toString());
    }
}
