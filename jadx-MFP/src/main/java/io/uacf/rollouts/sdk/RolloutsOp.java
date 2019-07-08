package io.uacf.rollouts.sdk;

import com.uacf.sync.engine.UacfScheduleContext;
import com.uacf.sync.engine.UacfScheduleException;
import com.uacf.sync.engine.UacfScheduleOp.Progress;
import com.uacf.sync.engine.UacfScheduleOp.Result;
import com.uacf.sync.engine.UacfScheduleOpBase;

public class RolloutsOp extends UacfScheduleOpBase {
    public Result sync(UacfScheduleContext uacfScheduleContext, Progress progress) throws UacfScheduleException {
        try {
            new UacfVariantSdkFactory().newSdkInstance().fetchVariants();
            return Result.completed();
        } catch (Exception e) {
            return Result.retry(new UacfScheduleException(e));
        }
    }
}
