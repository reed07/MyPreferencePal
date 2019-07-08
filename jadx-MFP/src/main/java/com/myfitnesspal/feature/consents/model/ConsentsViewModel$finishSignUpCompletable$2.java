package com.myfitnesspal.feature.consents.model;

import com.myfitnesspal.feature.registration.exception.RegistrationError;
import com.myfitnesspal.feature.registration.exception.RegistrationException;
import com.myfitnesspal.feature.registration.service.SignUpService;
import java.util.concurrent.Callable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "call"}, k = 3, mv = {1, 1, 13})
/* compiled from: ConsentsViewModel.kt */
final class ConsentsViewModel$finishSignUpCompletable$2<V> implements Callable<Object> {
    final /* synthetic */ ConsentsViewModel this$0;

    ConsentsViewModel$finishSignUpCompletable$2(ConsentsViewModel consentsViewModel) {
        this.this$0 = consentsViewModel;
    }

    public final void call() {
        if (this.this$0.signUpService != null) {
            SignUpService access$getSignUpService$p = this.this$0.signUpService;
            if (access$getSignUpService$p == null) {
                Intrinsics.throwNpe();
            }
            access$getSignUpService$p.signUp(this.this$0.signUpModel, this.this$0.loginModel);
            return;
        }
        throw new RegistrationException(RegistrationError.Unknown);
    }
}
