package com.myfitnesspal.feature.registration.task;

import android.content.Context;
import com.myfitnesspal.feature.registration.model.IdmEmailUniquenessCheck;
import com.myfitnesspal.feature.registration.service.SignUpService;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.model.v15.UsernameValidationObject;

public class ValidateUsernameAndEmailTask<CC> extends EventedTaskBase<Boolean, ApiException> {
    private final String email;
    private final SignUpService signUpService;
    private final String username;

    public static class CompletedEvent<CC> extends TaskEventBase<Boolean, ApiException> {
        private CC callContext;
        private IdmEmailUniquenessCheck emailCheckResult;
        private UsernameValidationObject usernameCheckResult;

        public CompletedEvent(CC cc) {
            this.callContext = cc;
        }

        /* access modifiers changed from: private */
        public void setEmailCheckResult(IdmEmailUniquenessCheck idmEmailUniquenessCheck) {
            this.emailCheckResult = idmEmailUniquenessCheck;
        }

        /* access modifiers changed from: private */
        public void setUsernameCheckResult(UsernameValidationObject usernameValidationObject) {
            this.usernameCheckResult = usernameValidationObject;
        }

        public IdmEmailUniquenessCheck getEmailCheckResult() {
            return this.emailCheckResult;
        }

        public UsernameValidationObject getUsernameCheckResult() {
            return this.usernameCheckResult;
        }

        public CC getCallContext() {
            return this.callContext;
        }
    }

    public ValidateUsernameAndEmailTask(CC cc, SignUpService signUpService2, String str, String str2) {
        super((TaskEventBase) new CompletedEvent(cc));
        this.signUpService = signUpService2;
        this.username = str;
        this.email = str2;
    }

    /* access modifiers changed from: protected */
    public Boolean exec(Context context) throws ApiException {
        CompletedEvent completedEvent = (CompletedEvent) getEvent();
        completedEvent.setEmailCheckResult(this.signUpService.validateEmailAddressIdm(this.email));
        completedEvent.setUsernameCheckResult(this.signUpService.validateUsername(this.username));
        return Boolean.valueOf(true);
    }
}
