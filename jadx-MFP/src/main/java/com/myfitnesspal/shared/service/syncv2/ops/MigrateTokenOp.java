package com.myfitnesspal.shared.service.syncv2.ops;

import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.api.auth.AuthTokenProvider;
import com.myfitnesspal.shared.api.auth.SSOAuthTokenProvider;
import com.uacf.sync.engine.UacfScheduleContext;
import com.uacf.sync.engine.UacfScheduleException;
import com.uacf.sync.engine.UacfScheduleOp.Progress;
import com.uacf.sync.engine.UacfScheduleOp.Result;
import com.uacf.sync.engine.UacfScheduleOpBase;
import dagger.Lazy;

public class MigrateTokenOp extends UacfScheduleOpBase {
    private final Lazy<AuthTokenProvider> provider;

    public MigrateTokenOp(Lazy<AuthTokenProvider> lazy) {
        this.provider = lazy;
    }

    public void onRetriesExhausted() {
        super.onRetriesExhausted();
    }

    public Result sync(UacfScheduleContext uacfScheduleContext, Progress progress) throws UacfScheduleException {
        try {
            AuthTokenProvider authTokenProvider = (AuthTokenProvider) this.provider.get();
            if (authTokenProvider instanceof SSOAuthTokenProvider) {
                ((SSOAuthTokenProvider) authTokenProvider).migrateLegacyCredentialsIfRequired();
            }
            return Result.completed();
        } catch (ApiException e) {
            return Result.retry(new UacfScheduleException(e));
        }
    }
}
