package com.uacf.identity.sdk.model;

import com.google.gson.annotations.Expose;
import java.util.List;

public class UacfProfileEmails {
    /* access modifiers changed from: private */
    @Expose
    public List<UacfProfileEmail> emails;
    /* access modifiers changed from: private */
    @Expose
    public boolean isEmailVerified;

    public static final class Builder {
        private List<UacfProfileEmail> emails;
        private boolean isEmailVerified;

        public Builder withIsEmailVerified(boolean z) {
            this.isEmailVerified = z;
            return this;
        }

        public Builder withEmails(List<UacfProfileEmail> list) {
            this.emails = list;
            return this;
        }

        public UacfProfileEmails build() {
            UacfProfileEmails uacfProfileEmails = new UacfProfileEmails();
            uacfProfileEmails.isEmailVerified = this.isEmailVerified;
            uacfProfileEmails.emails = this.emails;
            return uacfProfileEmails;
        }
    }

    public boolean isEmailVerified() {
        return this.isEmailVerified;
    }

    public List<UacfProfileEmail> getEmails() {
        return this.emails;
    }
}
