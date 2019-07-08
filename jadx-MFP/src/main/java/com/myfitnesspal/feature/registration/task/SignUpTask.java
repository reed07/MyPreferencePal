package com.myfitnesspal.feature.registration.task;

import android.content.Context;
import com.myfitnesspal.feature.registration.exception.RegistrationException;
import com.myfitnesspal.feature.registration.model.LoginModel;
import com.myfitnesspal.feature.registration.model.SignUpModel;
import com.myfitnesspal.feature.registration.service.SignUpService;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;

public class SignUpTask extends EventedTaskBase<Boolean, RegistrationException> {
    private final LoginModel loginModel;
    private final SignUpModel signUpModel;
    private final SignUpService signUpService;

    public static class CompletedEvent extends TaskEventBase<Boolean, RegistrationException> {
    }

    public SignUpTask(SignUpService signUpService2, SignUpModel signUpModel2, LoginModel loginModel2) {
        super(CompletedEvent.class);
        this.signUpService = signUpService2;
        this.signUpModel = signUpModel2;
        this.loginModel = loginModel2;
    }

    /* access modifiers changed from: protected */
    public Boolean exec(Context context) throws RegistrationException {
        this.signUpService.signUp(this.signUpModel, this.loginModel);
        return Boolean.valueOf(true);
    }
}
