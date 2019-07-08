package com.myfitnesspal.shared.service.syncv2.ops;

import com.uacf.identity.sdk.UacfIdentitySdk;
import com.uacf.sync.engine.UacfScheduleContext;
import com.uacf.sync.engine.UacfScheduleException;
import com.uacf.sync.engine.UacfScheduleOp.Progress;
import com.uacf.sync.engine.UacfScheduleOp.Result;
import com.uacf.sync.engine.UacfScheduleOpBase;
import dagger.Lazy;

public class UacfUserOp extends UacfScheduleOpBase {
    private Lazy<UacfIdentitySdk> identitySdk;

    public UacfUserOp(Lazy<UacfIdentitySdk> lazy) {
        this.identitySdk = lazy;
    }

    public Result sync(UacfScheduleContext uacfScheduleContext, Progress progress) throws UacfScheduleException {
        try {
            ((UacfIdentitySdk) this.identitySdk.get()).refreshCurrentUser();
            return Result.completed();
        } catch (Exception e) {
            return Result.retry(new UacfScheduleException(e));
        }
    }
}
