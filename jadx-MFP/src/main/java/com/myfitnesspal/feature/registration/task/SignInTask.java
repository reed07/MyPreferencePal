package com.myfitnesspal.feature.registration.task;

import android.content.Context;
import com.myfitnesspal.feature.registration.exception.RegistrationError;
import com.myfitnesspal.feature.registration.exception.RegistrationException;
import com.myfitnesspal.feature.registration.model.LoginModel.FacebookData;
import com.myfitnesspal.feature.registration.service.SignInService;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.provider.MPFAppWidgetProvider;
import com.myfitnesspal.shared.service.session.Session;
import com.uacf.core.util.Ln;
import dagger.Lazy;

public class SignInTask extends EventedTaskBase<Boolean, RegistrationException> {
    private final FacebookData facebookData;
    private final String password;
    private final Session session;
    private final Lazy<SignInService> signInService;
    private final String username;

    public static class CompletedEvent extends TaskEventBase<Boolean, RegistrationException> {
        private final String password;
        private final Type type;
        private final String username;

        public CompletedEvent(Type type2, String str, String str2) {
            this.type = type2;
            this.username = str;
            this.password = str2;
        }

        public Type getType() {
            return this.type;
        }

        public String getUsername() {
            return this.username;
        }

        public String getPassword() {
            return this.password;
        }
    }

    public enum Type {
        Standard,
        Facebook
    }

    public SignInTask(Session session2, Lazy<SignInService> lazy, String str, String str2) {
        super((TaskEventBase) new CompletedEvent(Type.Standard, str, str2));
        this.session = session2;
        this.signInService = lazy;
        this.username = str;
        this.facebookData = null;
        this.password = str2;
    }

    public SignInTask(Session session2, Lazy<SignInService> lazy, String str, FacebookData facebookData2) {
        super((TaskEventBase) new CompletedEvent(Type.Facebook, str, null));
        this.session = session2;
        this.signInService = lazy;
        this.username = str;
        this.facebookData = facebookData2;
        this.password = null;
    }

    /* access modifiers changed from: protected */
    public Boolean exec(Context context) throws RegistrationException {
        try {
            if (this.facebookData != null) {
                ((SignInService) this.signInService.get()).signInToFacebook(this.username, this.facebookData);
            } else {
                ((SignInService) this.signInService.get()).signIn(this.username, this.password);
                MPFAppWidgetProvider.update(context);
            }
            return Boolean.valueOf(true);
        } catch (RegistrationException e) {
            Ln.e(e);
            if (e.getReason() != RegistrationError.NoVerticalAccount) {
                this.session.logoutAndKeepFacebookData();
            }
            throw e;
        }
    }
}
