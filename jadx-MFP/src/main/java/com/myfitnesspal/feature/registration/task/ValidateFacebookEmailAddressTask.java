package com.myfitnesspal.feature.registration.task;

import android.content.Context;
import com.myfitnesspal.feature.registration.service.SignUpService;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.model.v15.EmailUniquenessCheckObject;

public class ValidateFacebookEmailAddressTask extends EventedTaskBase<EmailUniquenessCheckObject, ApiException> {
    private final String emailAddress;
    private final SignUpService signUpService;

    public static class CompletedEvent extends TaskEventBase<EmailUniquenessCheckObject, ApiException> {
        private String emailAddress;

        public CompletedEvent(String str) {
            this.emailAddress = str;
        }

        public String getEmailAddress() {
            return this.emailAddress;
        }
    }

    public ValidateFacebookEmailAddressTask(SignUpService signUpService2, String str) {
        super((TaskEventBase) new CompletedEvent(str));
        this.signUpService = signUpService2;
        this.emailAddress = str;
    }

    /* access modifiers changed from: protected */
    public EmailUniquenessCheckObject exec(Context context) throws ApiException {
        return this.signUpService.validateEmailAddressLegacy(this.emailAddress);
    }
}
