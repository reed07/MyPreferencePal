package com.myfitnesspal.shared.service.syncv2.ops;

import com.myfitnesspal.feature.payments.service.SubscriptionService;
import com.uacf.sync.engine.UacfScheduleContext;
import com.uacf.sync.engine.UacfScheduleException;
import com.uacf.sync.engine.UacfScheduleOp.Progress;
import com.uacf.sync.engine.UacfScheduleOp.Result;
import com.uacf.sync.engine.UacfScheduleOpBase;
import dagger.Lazy;

public class ReceiptsOp extends UacfScheduleOpBase {
    final Lazy<SubscriptionService> subscriptionService;

    public ReceiptsOp(Lazy<SubscriptionService> lazy) {
        this.subscriptionService = lazy;
    }

    public Result sync(UacfScheduleContext uacfScheduleContext, Progress progress) throws UacfScheduleException {
        ((SubscriptionService) this.subscriptionService.get()).pushPendingReceiptsOnCurrentThread();
        return Result.completed();
    }
}
