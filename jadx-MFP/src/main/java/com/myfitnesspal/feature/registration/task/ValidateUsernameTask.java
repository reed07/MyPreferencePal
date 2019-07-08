package com.myfitnesspal.feature.registration.task;

import android.content.Context;
import com.myfitnesspal.feature.registration.service.SignUpService;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.model.v15.UsernameValidationObject;

public class ValidateUsernameTask<CC> extends EventedTaskBase<UsernameValidationObject, ApiException> {
    private final SignUpService signUpService;
    private final String username;

    public static class CompletedEvent<CC> extends TaskEventBase<UsernameValidationObject, ApiException> {
        private CC callContext;
        private String username;

        public CompletedEvent(CC cc, String str) {
            this.callContext = cc;
            this.username = str;
        }

        public CC getCallContext() {
            return this.callContext;
        }

        public String getUsername() {
            return this.username;
        }
    }

    public ValidateUsernameTask(CC cc, SignUpService signUpService2, String str) {
        super((TaskEventBase) new CompletedEvent(cc, str));
        this.signUpService = signUpService2;
        this.username = str;
    }

    /* access modifiers changed from: protected */
    public UsernameValidationObject exec(Context context) throws ApiException {
        return this.signUpService.validateUsername(this.username);
    }
}
