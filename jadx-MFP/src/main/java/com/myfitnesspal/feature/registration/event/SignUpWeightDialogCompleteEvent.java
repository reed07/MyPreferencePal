package com.myfitnesspal.feature.registration.event;

import com.myfitnesspal.feature.registration.constants.SignUpBmi;
import com.myfitnesspal.shared.event.MfpEventBase;

public class SignUpWeightDialogCompleteEvent extends MfpEventBase {
    private String result;
    private SignUpBmi type;

    public SignUpWeightDialogCompleteEvent(String str, SignUpBmi signUpBmi) {
        this.result = str;
        this.type = signUpBmi;
    }

    public String getString() {
        return this.result;
    }

    public SignUpBmi getBmiType() {
        SignUpBmi signUpBmi = this.type;
        return signUpBmi != null ? signUpBmi : SignUpBmi.Normal;
    }
}
