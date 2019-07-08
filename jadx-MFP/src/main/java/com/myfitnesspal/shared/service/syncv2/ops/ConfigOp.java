package com.myfitnesspal.shared.service.syncv2.ops;

import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.syncv2.SyncExceptions.ApiSyncException;
import com.uacf.core.util.Ln;
import com.uacf.sync.engine.UacfScheduleContext;
import com.uacf.sync.engine.UacfScheduleException;
import com.uacf.sync.engine.UacfScheduleOp.Progress;
import com.uacf.sync.engine.UacfScheduleOp.Result;
import com.uacf.sync.engine.UacfScheduleOpBase;
import dagger.Lazy;

public class ConfigOp extends UacfScheduleOpBase {
    private Lazy<ConfigService> configService;

    public ConfigOp(Lazy<ConfigService> lazy) {
        this.configService = lazy;
    }

    public Result sync(UacfScheduleContext uacfScheduleContext, Progress progress) throws UacfScheduleException {
        UacfScheduleException uacfScheduleException;
        try {
            if (((ConfigService) this.configService.get()).refresh()) {
                return Result.completed();
            }
            uacfScheduleException = null;
            return ((ConfigService) this.configService.get()).hasValidConfiguration() ? Result.completed() : Result.retry(uacfScheduleException);
        } catch (ApiException e) {
            Ln.e(e);
            uacfScheduleException = new ApiSyncException(e);
        }
    }
}
