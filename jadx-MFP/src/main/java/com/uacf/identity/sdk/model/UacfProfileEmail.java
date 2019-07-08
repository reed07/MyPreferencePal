package com.uacf.identity.sdk.model;

import com.google.gson.annotations.Expose;
import com.uacf.core.mapping.GsonMappableIso8601Date;

public class UacfProfileEmail {
    /* access modifiers changed from: private */
    @Expose
    public String email;
    /* access modifiers changed from: private */
    @Expose
    public String pendingEmail;
    /* access modifiers changed from: private */
    @Expose
    public boolean primary;
    /* access modifiers changed from: private */
    @Expose
    public String scopes;
    /* access modifiers changed from: private */
    @Expose
    public String type;
    /* access modifiers changed from: private */
    @Expose
    public long userId;
    /* access modifiers changed from: private */
    @Expose
    public GsonMappableIso8601Date verificationConfirmedAt;
    /* access modifiers changed from: private */
    @Expose
    public GsonMappableIso8601Date verificationRemovedAt;
    /* access modifiers changed from: private */
    @Expose
    public GsonMappableIso8601Date verificationRequestedAt;
    /* access modifiers changed from: private */
    @Expose
    public String verificationState;
    /* access modifiers changed from: private */
    @Expose
    public boolean verified;

    public static final class Builder {
        private String email;
        private String pendingEmail;
        private boolean primary;
        private String scopes;
        private String type;
        private long userId;
        private GsonMappableIso8601Date verificationConfirmedAt;
        private GsonMappableIso8601Date verificationRemovedAt;
        private GsonMappableIso8601Date verificationRequestedAt;
        private String verificationState;
        private boolean verified;

        public Builder withVerificationState(String str) {
            this.verificationState = str;
            return this;
        }

        public Builder withVerificationRequestedAt(GsonMappableIso8601Date gsonMappableIso8601Date) {
            this.verificationRequestedAt = gsonMappableIso8601Date;
            return this;
        }

        public Builder withVerificationConfirmedAt(GsonMappableIso8601Date gsonMappableIso8601Date) {
            this.verificationConfirmedAt = gsonMappableIso8601Date;
            return this;
        }

        public Builder withVerificationRemovedAt(GsonMappableIso8601Date gsonMappableIso8601Date) {
            this.verificationRemovedAt = gsonMappableIso8601Date;
            return this;
        }

        public Builder withPendingEmail(String str) {
            this.pendingEmail = str;
            return this;
        }

        public Builder withIsVerified(boolean z) {
            this.verified = z;
            return this;
        }

        public Builder withIsPrimary(boolean z) {
            this.primary = z;
            return this;
        }

        public Builder withType(String str) {
            this.type = str;
            return this;
        }

        public Builder withScopes(String str) {
            this.scopes = str;
            return this;
        }

        public Builder withUserId(long j) {
            this.userId = j;
            return this;
        }

        public Builder withEmail(String str) {
            this.email = str;
            return this;
        }

        public UacfProfileEmail build() {
            UacfProfileEmail uacfProfileEmail = new UacfProfileEmail();
            uacfProfileEmail.verificationState = this.verificationState;
            uacfProfileEmail.verificationRequestedAt = this.verificationRequestedAt;
            uacfProfileEmail.verificationConfirmedAt = this.verificationConfirmedAt;
            uacfProfileEmail.verificationRemovedAt = this.verificationRemovedAt;
            uacfProfileEmail.pendingEmail = this.pendingEmail;
            uacfProfileEmail.verified = this.verified;
            uacfProfileEmail.primary = this.primary;
            uacfProfileEmail.type = this.type;
            uacfProfileEmail.scopes = this.scopes;
            uacfProfileEmail.userId = this.userId;
            uacfProfileEmail.email = this.email;
            return uacfProfileEmail;
        }
    }

    public boolean isPrimary() {
        return this.primary;
    }

    public String getEmail() {
        return this.email;
    }
}
