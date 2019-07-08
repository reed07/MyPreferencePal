package com.myfitnesspal.feature.registration.task;

import android.content.Context;
import com.myfitnesspal.feature.registration.ui.activity.SignUpActivity.Mode;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.api.auth.SSO;
import com.uacf.core.util.CollectionUtils;
import com.uacf.identity.sdk.model.UacfUser;
import io.uacf.core.api.UacfApiException;
import java.util.Collection;
import java.util.List;

public class FetchUacfIdTask extends EventedTaskBase<String, ApiException> {
    final String email;

    public static class CompletedEvent extends TaskEventBase<String, ApiException> {
        private final Mode mode;

        public CompletedEvent(Mode mode2) {
            this.mode = mode2;
        }

        public Mode getMode() {
            return this.mode;
        }
    }

    public FetchUacfIdTask(String str, Mode mode) {
        super((TaskEventBase) new CompletedEvent(mode));
        this.email = str;
    }

    /* access modifiers changed from: protected */
    public String exec(Context context) throws ApiException {
        try {
            List findUserByEmailAddress = SSO.getSdk().findUserByEmailAddress(this.email);
            if (CollectionUtils.notEmpty((Collection<?>) findUserByEmailAddress)) {
                return ((UacfUser) findUserByEmailAddress.get(0)).getUserId();
            }
            return null;
        } catch (UacfApiException e) {
            throw new ApiException(e);
        }
    }
}
