package io.uacf.thumbprint.ui.internal.email;

import com.uacf.identity.sdk.UacfIdentitySdk;
import io.uacf.thumbprint.ui.sdk.UacfThumbprintUiSdkImpl;
import io.uacf.thumbprint.ui.sdk.UacfThumbprintUiSdkManager;
import java.util.concurrent.Callable;

class SendVerificationEmailCallable implements Callable<Boolean> {
    private String email;
    private UacfIdentitySdk identitySdk = ((UacfThumbprintUiSdkImpl) UacfThumbprintUiSdkManager.getInstance()).getIdentitySdk();

    SendVerificationEmailCallable(String str) {
        this.email = str;
    }

    public Boolean call() throws Exception {
        this.identitySdk.sendVerificationEmail(this.email);
        return Boolean.valueOf(true);
    }
}
