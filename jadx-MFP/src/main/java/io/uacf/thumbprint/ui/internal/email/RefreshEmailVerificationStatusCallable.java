package io.uacf.thumbprint.ui.internal.email;

import com.uacf.core.util.Ln;
import com.uacf.identity.sdk.UacfIdentitySdk;
import com.uacf.identity.sdk.model.UacfUser;
import io.uacf.thumbprint.ui.sdk.ClientEmailVerificationStatus;
import io.uacf.thumbprint.ui.sdk.UacfThumbprintUiSdkImpl;
import io.uacf.thumbprint.ui.sdk.UacfThumbprintUiSdkManager;
import java.util.concurrent.Callable;

class RefreshEmailVerificationStatusCallable implements Callable<EmailVerificationStatus> {
    private ClientEmailVerificationStatus clientEmailVerificationStatus = ((UacfThumbprintUiSdkImpl) UacfThumbprintUiSdkManager.getInstance()).getClientEmailVerificationStatus();
    private UacfIdentitySdk identitySdk = ((UacfThumbprintUiSdkImpl) UacfThumbprintUiSdkManager.getInstance()).getIdentitySdk();

    RefreshEmailVerificationStatusCallable() {
    }

    public EmailVerificationStatus call() throws Exception {
        boolean z;
        UacfUser refreshCurrentUser = this.identitySdk.refreshCurrentUser();
        boolean isEmailVerified = refreshCurrentUser.getProfileEmails().isEmailVerified();
        boolean z2 = true;
        try {
            z = this.clientEmailVerificationStatus != null && this.clientEmailVerificationStatus.get();
        } catch (Exception e) {
            Ln.e(e, "Failed to get client verification status.", new Object[0]);
            z = false;
        }
        if (!isEmailVerified && !z) {
            z2 = false;
        }
        return new EmailVerificationStatus(z2, refreshCurrentUser);
    }
}
