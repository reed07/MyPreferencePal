package com.myfitnesspal.feature.deleteaccount.task;

import android.content.Context;
import com.myfitnesspal.feature.registration.service.SignInService;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase.Unchecked;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import dagger.Lazy;

public class VerifyAccountTask extends Unchecked<Boolean> {
    private final String email;
    private final String facebookToken;
    private final String facebookUserId;
    private final String password;
    private final Lazy<SignInService> signInService;
    private final VerificationType verificationType;

    public static class CompletedEvent extends TaskEventBase.Unchecked<Boolean> {
        private final VerificationType verificationType;

        public CompletedEvent(VerificationType verificationType2) {
            this.verificationType = verificationType2;
        }

        public VerificationType getVerificationType() {
            return this.verificationType;
        }
    }

    public enum VerificationType {
        Regular,
        Facebook
    }

    public static VerifyAccountTask createTaskForRegularVerification(Lazy<SignInService> lazy, String str, String str2) {
        VerifyAccountTask verifyAccountTask = new VerifyAccountTask(lazy, str, str2, null, null, VerificationType.Regular);
        return verifyAccountTask;
    }

    public static VerifyAccountTask createTaskForFacebookVerification(Lazy<SignInService> lazy, String str, String str2) {
        VerifyAccountTask verifyAccountTask = new VerifyAccountTask(lazy, null, null, str, str2, VerificationType.Facebook);
        return verifyAccountTask;
    }

    private VerifyAccountTask(Lazy<SignInService> lazy, String str, String str2, String str3, String str4, VerificationType verificationType2) {
        super((TaskEventBase) new CompletedEvent(verificationType2));
        this.signInService = lazy;
        this.email = str;
        this.password = str2;
        this.facebookUserId = str3;
        this.facebookToken = str4;
        this.verificationType = verificationType2;
    }

    /* access modifiers changed from: protected */
    public Boolean exec(Context context) {
        boolean z;
        if (this.verificationType == VerificationType.Facebook) {
            z = ((SignInService) this.signInService.get()).verifyFacebookSignIn(this.facebookUserId, this.facebookToken);
        } else {
            z = ((SignInService) this.signInService.get()).verifySignIn(this.email, this.password);
        }
        return Boolean.valueOf(z);
    }
}
