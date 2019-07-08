package io.uacf.configuration.sdk;

import com.uacf.sync.engine.UacfScheduleContext;
import com.uacf.sync.engine.UacfScheduleException;
import com.uacf.sync.engine.UacfScheduleOp.Progress;
import com.uacf.sync.engine.UacfScheduleOp.Result;
import com.uacf.sync.engine.UacfScheduleOpBase;

public class ConfigurationOp extends UacfScheduleOpBase {
    public Result sync(UacfScheduleContext uacfScheduleContext, Progress progress) throws UacfScheduleException {
        try {
            new UacfConfigurationSdkFactory().newSdkInstance().fetchConfiguration();
            return Result.completed();
        } catch (Exception e) {
            return Result.retry(new UacfScheduleException(e));
        }
    }
}
