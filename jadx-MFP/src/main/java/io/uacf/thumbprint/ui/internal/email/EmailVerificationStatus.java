package io.uacf.thumbprint.ui.internal.email;

import com.uacf.identity.sdk.model.UacfUser;

final class EmailVerificationStatus {
    private boolean isVerified;
    private UacfUser user;

    EmailVerificationStatus(boolean z, UacfUser uacfUser) {
        this.isVerified = z;
        this.user = uacfUser;
    }

    public boolean isVerified() {
        return this.isVerified;
    }

    public UacfUser getUser() {
        return this.user;
    }
}
