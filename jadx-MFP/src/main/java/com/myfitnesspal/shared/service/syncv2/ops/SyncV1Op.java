package com.myfitnesspal.shared.service.syncv2.ops;

import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.model.v1.UserV1;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.syncv1.LegacySyncManager;
import com.myfitnesspal.shared.service.syncv1.LegacySyncManager.ProgressHandler;
import com.myfitnesspal.shared.service.syncv1.LegacySyncManager.ResultCode;
import com.myfitnesspal.shared.service.syncv1.LegacySyncManager.SyncMode;
import com.myfitnesspal.shared.service.syncv2.SyncExceptions.ApiSyncException;
import com.myfitnesspal.shared.service.syncv2.SyncExceptions.UnknownLegacySyncFailure;
import com.myfitnesspal.shared.service.syncv2.SyncType;
import com.myfitnesspal.shared.service.syncv2.SyncUtil;
import com.uacf.core.util.Ln;
import com.uacf.sync.engine.UacfScheduleContext;
import com.uacf.sync.engine.UacfScheduleException;
import com.uacf.sync.engine.UacfScheduleOp.Progress;
import com.uacf.sync.engine.UacfScheduleOp.Result;
import com.uacf.sync.engine.UacfScheduleOpBase;
import com.uacf.sync.engine.UacfScheduleProgressInfo;
import dagger.Lazy;

public class SyncV1Op extends UacfScheduleOpBase {
    private static LegacySyncManager SYNC_MANAGER_INSTANCE;
    private final Session session;
    private final Lazy<SyncUtil> syncUtil;
    private SyncType type = SyncType.Incremental;

    private static final class InstanceProgressHandler implements ProgressHandler {
        private Progress progress;

        InstanceProgressHandler(Progress progress2) {
            this.progress = progress2;
        }

        public void onProgress(String str) {
            this.progress.publish(new UacfScheduleProgressInfo(str));
        }
    }

    public SyncV1Op(Session session2, Lazy<SyncUtil> lazy, LegacySyncManager legacySyncManager) {
        this.session = session2;
        this.syncUtil = lazy;
        SYNC_MANAGER_INSTANCE = legacySyncManager;
    }

    public final void setSyncType(SyncType syncType) {
        this.type = syncType;
    }

    public Result sync(UacfScheduleContext uacfScheduleContext, Progress progress) throws UacfScheduleException {
        Ln.d("sync() entered. will be processing type=%s", this.type);
        ResultCode resultCode = ResultCode.Failed;
        InstanceProgressHandler instanceProgressHandler = new InstanceProgressHandler(progress);
        UserV1 userV1 = this.session.getUser().getUserV1();
        synchronized (SyncV1Op.class) {
            try {
                SYNC_MANAGER_INSTANCE.setProgressHandler(instanceProgressHandler);
                Ln.d("sync(): processing type=%s NOW!", this.type);
                switch (this.type) {
                    case SignIn:
                        resultCode = SYNC_MANAGER_INSTANCE.sync(SyncMode.Import, userV1);
                        if (resultCode != ResultCode.Failed) {
                            this.type = SyncType.Incremental;
                            break;
                        }
                        break;
                    case BackgroundIncremental:
                        resultCode = SYNC_MANAGER_INSTANCE.sync(SyncMode.BackgroundNormal, userV1);
                        break;
                    case Incremental:
                        resultCode = SYNC_MANAGER_INSTANCE.sync(SyncMode.Normal, userV1);
                        break;
                    case SignUp:
                        resultCode = SYNC_MANAGER_INSTANCE.sync(SyncMode.Registration, userV1);
                        break;
                }
                SYNC_MANAGER_INSTANCE.setProgressHandler(null);
            } catch (UacfScheduleException e) {
                Ln.e("SyncV1Op: caught an unexpected UacfScheduleException, let's try again...", new Object[0]);
                Ln.e(e);
                Result retry = Result.retry(e);
                SYNC_MANAGER_INSTANCE.setProgressHandler(null);
                return retry;
            } catch (ApiException e2) {
                Ln.e("SyncV1Op: caught ApiException, scheduling a retry...", new Object[0]);
                Result retry2 = Result.retry(new ApiSyncException(e2));
                SYNC_MANAGER_INSTANCE.setProgressHandler(null);
                return retry2;
            } catch (Throwable th) {
                try {
                    Ln.e("SyncV1Op: caught unexpected exception", new Object[0]);
                    Ln.e(th);
                    Result retry3 = Result.retry(new UnknownLegacySyncFailure(th, SYNC_MANAGER_INSTANCE.getResponseStatusCode(), SYNC_MANAGER_INSTANCE.getResponseStatusMessage()));
                    SYNC_MANAGER_INSTANCE.setProgressHandler(null);
                    return retry3;
                } catch (Throwable th2) {
                    SYNC_MANAGER_INSTANCE.setProgressHandler(null);
                    throw th2;
                }
            }
        }
        switch (resultCode) {
            case HasMoreData:
                Ln.d("sync(): type=%s has more data", this.type);
                return Result.pending();
            case Finished:
                Ln.d("sync(): type=%s completed successfully", this.type);
                ((SyncUtil) this.syncUtil.get()).migrateResourceState(1, 2);
                return Result.completed();
            default:
                int responseStatusCode = SYNC_MANAGER_INSTANCE.getResponseStatusCode();
                String responseStatusMessage = SYNC_MANAGER_INSTANCE.getResponseStatusMessage();
                Exception exc = new Exception(String.format("code=%d message=%s", new Object[]{Integer.valueOf(responseStatusCode), responseStatusMessage}));
                exc.fillInStackTrace();
                Ln.e(exc);
                Ln.e("sync(): type=%s failed or fell through. scheduling a retry...", this.type);
                return Result.retry(new UnknownLegacySyncFailure(responseStatusCode, responseStatusMessage));
        }
    }
}
