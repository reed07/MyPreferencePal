package com.myfitnesspal.feature.registration.task;

import android.content.Context;
import com.myfitnesspal.feature.registration.model.IdmEmailUniquenessCheck;
import com.myfitnesspal.feature.registration.service.SignUpService;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.api.ApiException;
import com.uacf.identity.sdk.model.UacfUser;
import dagger.Lazy;
import java.util.List;

public class ValidateEmailAddressTask extends EventedTaskBase<List<UacfUser>, ApiException> {
    private final String emailAddress;
    private final Lazy<SignUpService> signUpService;

    public static class CompletedEvent extends TaskEventBase<List<UacfUser>, ApiException> {
        private String emailAddress;
        private boolean mfpAccountExists;
        private String password;
        private boolean uacfAccountExists;

        public CompletedEvent(String str, String str2) {
            this.emailAddress = str;
            this.password = str2;
        }

        public String getEmailAddress() {
            return this.emailAddress;
        }

        public String getPassword() {
            return this.password;
        }

        public boolean uacfAccountExists() {
            return this.uacfAccountExists;
        }

        public boolean mfpAccountExists() {
            return this.mfpAccountExists;
        }

        /* access modifiers changed from: 0000 */
        public void setUacfAccountExists(boolean z) {
            this.uacfAccountExists = z;
        }

        /* access modifiers changed from: 0000 */
        public void setMfpAccountExists(boolean z) {
            this.mfpAccountExists = z;
        }
    }

    public ValidateEmailAddressTask(Lazy<SignUpService> lazy, String str, String str2) {
        super((TaskEventBase) new CompletedEvent(str, str2));
        this.signUpService = lazy;
        this.emailAddress = str;
    }

    /* access modifiers changed from: protected */
    public List<UacfUser> exec(Context context) throws ApiException {
        IdmEmailUniquenessCheck validateEmailAddressIdm = ((SignUpService) this.signUpService.get()).validateEmailAddressIdm(this.emailAddress);
        CompletedEvent completedEvent = (CompletedEvent) getEvent();
        completedEvent.setMfpAccountExists(validateEmailAddressIdm.isMfpEmailTaken());
        completedEvent.setUacfAccountExists(validateEmailAddressIdm.isUacfEmailTaken());
        return validateEmailAddressIdm.getUsers();
    }
}
