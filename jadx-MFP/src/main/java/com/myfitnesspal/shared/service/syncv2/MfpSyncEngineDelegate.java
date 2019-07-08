package com.myfitnesspal.shared.service.syncv2;

import com.myfitnesspal.app.MyFitnessPalApp;
import com.myfitnesspal.feature.externalsync.service.ops.ExternalSyncOp;
import com.myfitnesspal.feature.notificationinbox.util.NotificationInboxUtil;
import com.myfitnesspal.feature.tizen.service.MfpGearMessageBridge;
import com.myfitnesspal.shared.constants.Constants.ConfigParam;
import com.myfitnesspal.shared.event.SyncServiceIncrementalFailedEvent;
import com.myfitnesspal.shared.model.User;
import com.myfitnesspal.shared.model.v1.UserV1;
import com.myfitnesspal.shared.provider.MPFAppWidgetProvider;
import com.myfitnesspal.shared.service.analytics.AnalyticsSyncMode;
import com.myfitnesspal.shared.service.appindexer.AppIndexerBot;
import com.myfitnesspal.shared.service.imagesync.ImageSyncMode;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.syncv2.ops.AuthTokenOp;
import com.myfitnesspal.shared.service.syncv2.ops.ConfigOp;
import com.myfitnesspal.shared.service.syncv2.ops.FindAndCorrectFoodsWithMissingInfoOp;
import com.myfitnesspal.shared.service.syncv2.ops.GeoLocationOp;
import com.myfitnesspal.shared.service.syncv2.ops.MigrateTokenOp;
import com.myfitnesspal.shared.service.syncv2.ops.ProductServiceOp;
import com.myfitnesspal.shared.service.syncv2.ops.ReceiptsOp;
import com.myfitnesspal.shared.service.syncv2.ops.SyncV1Op;
import com.myfitnesspal.shared.service.syncv2.ops.UacfUserOp;
import com.myfitnesspal.shared.service.syncv2.ops.UserV2Op;
import com.myfitnesspal.shared.uacf.UacfConfigurationUtil;
import com.myfitnesspal.shared.uacf.UacfRolloutUtil;
import com.squareup.otto.Bus;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import com.uacf.sync.engine.UacfScheduleEnqueuedInfo;
import com.uacf.sync.engine.UacfScheduleException.UacfScheduleFailedException;
import com.uacf.sync.engine.UacfScheduleFailedInfo;
import com.uacf.sync.engine.UacfScheduleFinishedInfo;
import com.uacf.sync.engine.UacfScheduleOp;
import com.uacf.sync.engine.UacfScheduleProgressInfo;
import com.uacf.sync.engine.UacfScheduleStartedInfo;
import com.uacf.sync.engine.UacfScheduler;
import com.uacf.sync.engine.UacfSchedulerEngine;
import com.uacf.sync.engine.UacfSchedulerEngineDelegate;
import com.uacf.sync.provider.internal.model.SyncOpBase;
import dagger.Lazy;
import io.uacf.configuration.sdk.ConfigurationOp;
import io.uacf.rollouts.sdk.RolloutsOp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.inject.Provider;

public class MfpSyncEngineDelegate implements UacfSchedulerEngineDelegate<SyncType> {
    private final Lazy<UacfScheduler<AnalyticsSyncMode>> analyticsSyncScheduler;
    private final Lazy<AppIndexerBot> appIndexerBot;
    private final Provider<AuthTokenOp> authTokenOpProvider;
    private final Provider<ConfigOp> configOpProvider;
    private final Provider<ConfigurationOp> configurationOpProvider;
    private final Provider<FindAndCorrectFoodsWithMissingInfoOp> correctV2FoodsOpProvider;
    private final Provider<ExternalSyncOp> externalSyncOpProvider;
    private final Provider<GeoLocationOp> geoLocationOpProvider;
    private final Lazy<UacfSchedulerEngine<ImageSyncMode>> imageSyncService;
    private Date lastSyncDate = new Date();
    private Lazy<Bus> messageBus;
    private final Provider<MigrateTokenOp> migrateTokenOpProvider;
    private final Provider<ProductServiceOp> productServiceOpProvider;
    private final Provider<ReceiptsOp> receiptsOpProvider;
    private final Provider<RolloutsOp> rolloutsOpProvider;
    private final Lazy<Session> session;
    private final Lazy<SyncService> syncService;
    private final Provider<SyncV1Op> syncV1OpProvider;
    private final Provider<SyncOpBase> syncV2OpProvider;
    private final Lazy<UacfConfigurationUtil> uacfConfigurationUtil;
    private final Lazy<UacfRolloutUtil> uacfRolloutUtil;
    private final Provider<UacfUserOp> uacfUserOpProvider;
    private final Provider<UserV2Op> userV2OpProvider;

    public void onSyncProgress(UacfScheduleProgressInfo<SyncType> uacfScheduleProgressInfo) {
    }

    public MfpSyncEngineDelegate(Lazy<Bus> lazy, Lazy<Session> lazy2, Lazy<AppIndexerBot> lazy3, Provider<SyncV1Op> provider, Provider<SyncOpBase> provider2, Provider<UserV2Op> provider3, Provider<AuthTokenOp> provider4, Provider<ConfigOp> provider5, Provider<ConfigurationOp> provider6, Provider<RolloutsOp> provider7, Provider<UacfUserOp> provider8, Provider<GeoLocationOp> provider9, Provider<ReceiptsOp> provider10, Provider<ExternalSyncOp> provider11, Provider<FindAndCorrectFoodsWithMissingInfoOp> provider12, Provider<MigrateTokenOp> provider13, Provider<ProductServiceOp> provider14, Lazy<SyncService> lazy4, Lazy<UacfSchedulerEngine<ImageSyncMode>> lazy5, Lazy<UacfScheduler<AnalyticsSyncMode>> lazy6, Lazy<UacfConfigurationUtil> lazy7, Lazy<UacfRolloutUtil> lazy8) {
        this.messageBus = lazy;
        this.session = lazy2;
        this.appIndexerBot = lazy3;
        this.syncV1OpProvider = provider;
        this.syncV2OpProvider = provider2;
        this.userV2OpProvider = provider3;
        this.authTokenOpProvider = provider4;
        this.configOpProvider = provider5;
        this.configurationOpProvider = provider6;
        this.rolloutsOpProvider = provider7;
        this.uacfUserOpProvider = provider8;
        this.geoLocationOpProvider = provider9;
        this.receiptsOpProvider = provider10;
        this.externalSyncOpProvider = provider11;
        this.correctV2FoodsOpProvider = provider12;
        this.migrateTokenOpProvider = provider13;
        this.productServiceOpProvider = provider14;
        this.syncService = lazy4;
        this.imageSyncService = lazy5;
        this.analyticsSyncScheduler = lazy6;
        this.uacfConfigurationUtil = lazy7;
        this.uacfRolloutUtil = lazy8;
    }

    public synchronized Date getLastSyncDate() {
        return this.lastSyncDate;
    }

    public void onSyncEnqueued(UacfScheduleEnqueuedInfo<SyncType> uacfScheduleEnqueuedInfo) {
        this.lastSyncDate = new Date();
    }

    public void onSyncStarted(UacfScheduleStartedInfo<SyncType> uacfScheduleStartedInfo) {
        if (uacfScheduleStartedInfo.getScheduleType() == SyncType.Incremental) {
            ((UacfScheduler) this.analyticsSyncScheduler.get()).debounceDefaultSync();
        }
    }

    public void onSyncFinished(UacfScheduleFinishedInfo<SyncType> uacfScheduleFinishedInfo) {
        ((Bus) this.messageBus.get()).post(uacfScheduleFinishedInfo);
        switch ((SyncType) uacfScheduleFinishedInfo.getScheduleGroup()) {
            case Incremental:
                notifyWearablesDiaryChanged();
                ((UacfSchedulerEngine) this.imageSyncService.get()).enqueue(ImageSyncMode.All);
                MPFAppWidgetProvider.update(MyFitnessPalApp.getInstance());
                return;
            case External:
                notifyWearablesDiaryChanged();
                return;
            case Config:
                if (((UacfRolloutUtil) this.uacfRolloutUtil.get()).shouldConfigureNiSdkWithConfigurationValues()) {
                    int intValue = ((UacfConfigurationUtil) this.uacfConfigurationUtil.get()).getInteger(ConfigParam.NI_SDK_MAX_PRIORITY_SLOTS_AVAILABLE).intValue();
                    boolean booleanValue = ((UacfConfigurationUtil) this.uacfConfigurationUtil.get()).getBoolean(ConfigParam.NI_SDK_LIMIT_PRIORITY_TO_ONE_PER_CATEGORY).booleanValue();
                    NotificationInboxUtil.setMaxPriorityCountSetting(intValue);
                    NotificationInboxUtil.setLimitPriorityToOnePerCategorySetting(booleanValue);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void onSyncFailed(UacfScheduleFailedInfo<SyncType> uacfScheduleFailedInfo) {
        String scheduleId = uacfScheduleFailedInfo.getScheduleId();
        Exception exception = uacfScheduleFailedInfo.getException();
        if (exception instanceof UacfScheduleFailedException) {
            UacfScheduleFailedException uacfScheduleFailedException = (UacfScheduleFailedException) exception;
            ((Bus) this.messageBus.get()).post(new SyncServiceIncrementalFailedEvent(scheduleId, uacfScheduleFailedException.getStatusCode(), uacfScheduleFailedException.getMessage()));
        } else {
            ((Bus) this.messageBus.get()).post(new SyncServiceIncrementalFailedEvent(scheduleId, 557, null));
        }
        if (uacfScheduleFailedInfo.getScheduleGroup() == SyncType.Incremental) {
            notifyWearablesDiaryChanged();
            ((SyncService) this.syncService.get()).enqueue(SyncType.External);
        }
    }

    private void notifyWearablesDiaryChanged() {
        MfpGearMessageBridge.notifyDiaryContentsUpdated(MyFitnessPalApp.getInstance());
    }

    public List<UacfScheduleOp> getSyncOpsForType(SyncType syncType) {
        ArrayList arrayList = new ArrayList();
        if (((AppIndexerBot) this.appIndexerBot.get()).isActive()) {
            return arrayList;
        }
        switch (syncType) {
            case Incremental:
            case BackgroundIncremental:
                arrayList.add(this.migrateTokenOpProvider.get());
                arrayList.add(this.configOpProvider.get());
                arrayList.add(createSyncV1Op(syncType));
                arrayList.add(this.geoLocationOpProvider.get());
                arrayList.add(this.receiptsOpProvider.get());
                arrayList.add(this.syncV2OpProvider.get());
                arrayList.add(this.externalSyncOpProvider.get());
                arrayList.add(this.productServiceOpProvider.get());
                break;
            case External:
                arrayList.add(this.externalSyncOpProvider.get());
                break;
            case Config:
                arrayList.add(this.configurationOpProvider.get());
                arrayList.add(this.rolloutsOpProvider.get());
                break;
            case SignUp:
            case SignIn:
                arrayList.add(this.configOpProvider.get());
                arrayList.add(createSyncV1Op(syncType));
                arrayList.add(this.authTokenOpProvider.get());
                arrayList.add(this.configOpProvider.get());
                arrayList.add(this.geoLocationOpProvider.get());
                arrayList.add(this.userV2OpProvider.get());
                arrayList.add(this.uacfUserOpProvider.get());
                arrayList.add(this.migrateTokenOpProvider.get());
                arrayList.add(this.syncV2OpProvider.get());
                arrayList.add(this.productServiceOpProvider.get());
                arrayList.add(this.configurationOpProvider.get());
                arrayList.add(this.rolloutsOpProvider.get());
                break;
            case User:
                arrayList.add(this.userV2OpProvider.get());
                break;
            case UacfUser:
                arrayList.add(this.uacfUserOpProvider.get());
                break;
            case FindAndCorrectFoodsWithMissingInfo:
                arrayList.add(this.correctV2FoodsOpProvider.get());
                break;
            case NoOp:
                break;
            default:
                StringBuilder sb = new StringBuilder();
                sb.append("unsupported SyncType: ");
                sb.append(Strings.toString(syncType));
                throw new IllegalArgumentException(sb.toString());
        }
        return arrayList;
    }

    private UacfScheduleOp createSyncV1Op(SyncType syncType) {
        User user = ((Session) this.session.get()).getUser();
        if (user.getUserV1() == null) {
            if (syncType == SyncType.SignIn || syncType == SyncType.SignUp) {
                user.setUserV1(new UserV1());
            } else {
                Ln.e("cannot perform sync until SignIn/SignUp has finished!", new Object[0]);
                throw new IllegalStateException("trying to perform a normal sync, but no user is currently signed in!");
            }
        }
        SyncV1Op syncV1Op = (SyncV1Op) this.syncV1OpProvider.get();
        syncV1Op.setSyncType(syncType);
        return syncV1Op;
    }
}
