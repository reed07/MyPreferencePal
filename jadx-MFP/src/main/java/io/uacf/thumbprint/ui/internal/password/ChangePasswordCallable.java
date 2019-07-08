package io.uacf.thumbprint.ui.internal.password;

import com.uacf.identity.sdk.UacfIdentitySdk;
import io.uacf.thumbprint.ui.sdk.UacfThumbprintUiSdkImpl;
import io.uacf.thumbprint.ui.sdk.UacfThumbprintUiSdkManager;
import java.util.concurrent.Callable;

class ChangePasswordCallable implements Callable<Boolean> {
    private UacfIdentitySdk identitySdk = ((UacfThumbprintUiSdkImpl) UacfThumbprintUiSdkManager.getInstance()).getIdentitySdk();
    private String newPassword;

    public ChangePasswordCallable(String str) {
        this.newPassword = str;
    }

    public Boolean call() throws Exception {
        this.identitySdk.changePassword(this.newPassword);
        return Boolean.valueOf(true);
    }
}
