package io.uacf.clientevents.sdk;

import com.uacf.sync.engine.UacfScheduleContext;
import com.uacf.sync.engine.UacfScheduleException;
import com.uacf.sync.engine.UacfScheduleOp.Progress;
import com.uacf.sync.engine.UacfScheduleOp.Result;
import com.uacf.sync.engine.UacfScheduleOpBase;
import io.uacf.core.api.UacfApiException;

public class ClientEventsUacfScheduleOp extends UacfScheduleOpBase {
    public Result sync(UacfScheduleContext uacfScheduleContext, Progress progress) throws UacfScheduleException {
        try {
            return new UacfClientEventsSdkFactory().newAnalyticsServiceInstance().sendEventsToBackend() ? Result.yield() : Result.completed();
        } catch (UacfApiException e) {
            return Result.retry(new UacfScheduleException(e));
        }
    }
}
