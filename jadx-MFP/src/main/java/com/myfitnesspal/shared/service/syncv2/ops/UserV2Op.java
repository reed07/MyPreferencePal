package com.myfitnesspal.shared.service.syncv2.ops;

import com.myfitnesspal.shared.service.session.UserV2Service;
import com.uacf.core.preferences.KeyedSharedPreferences;
import com.uacf.core.util.Ln;
import com.uacf.sync.engine.UacfScheduleContext;
import com.uacf.sync.engine.UacfScheduleException;
import com.uacf.sync.engine.UacfScheduleOp.Progress;
import com.uacf.sync.engine.UacfScheduleOp.Result;
import com.uacf.sync.engine.UacfScheduleOpBase;
import dagger.Lazy;

public class UserV2Op extends UacfScheduleOpBase {
    public static final String SHARED_PREFS_USER_V2_COMPLETED = "user-v2-completed";
    private final KeyedSharedPreferences prefs;
    private final Lazy<UserV2Service> userService;

    public UserV2Op(Lazy<UserV2Service> lazy, KeyedSharedPreferences keyedSharedPreferences) {
        this.userService = lazy;
        this.prefs = keyedSharedPreferences;
    }

    public Result sync(UacfScheduleContext uacfScheduleContext, Progress progress) throws UacfScheduleException {
        try {
            if (((UserV2Service) this.userService.get()).fetchFromBackend() == null) {
                return Result.retry(new UacfScheduleException(999, "failed to fetch user from backend"));
            }
            setInitialUserV2SyncCompleted();
            return Result.completed();
        } catch (Exception e) {
            Ln.e(e);
            return Result.retry(new UacfScheduleException(e));
        }
    }

    private void setInitialUserV2SyncCompleted() {
        this.prefs.edit().putBoolean(SHARED_PREFS_USER_V2_COMPLETED, true).apply();
    }
}
