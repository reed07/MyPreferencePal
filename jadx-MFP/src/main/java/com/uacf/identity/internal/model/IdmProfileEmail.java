package com.uacf.identity.internal.model;

import com.google.gson.annotations.Expose;
import com.uacf.core.mapping.GsonMappableIso8601Date;

public class IdmProfileEmail {
    @Expose
    private String email;
    @Expose
    private String pendingEmail;
    @Expose
    private boolean primary;
    @Expose
    private String scopes;
    @Expose
    private String type;
    @Expose
    private long userId;
    @Expose
    private GsonMappableIso8601Date verificationConfirmedAt;
    @Expose
    private GsonMappableIso8601Date verificationRemovedAt;
    @Expose
    private GsonMappableIso8601Date verificationRequestedAt;
    @Expose
    private String verificationState;
    @Expose
    private boolean verified;

    public String getVerificationState() {
        return this.verificationState;
    }

    public GsonMappableIso8601Date getVerificationRequestedAt() {
        return this.verificationRequestedAt;
    }

    public GsonMappableIso8601Date getVerificationConfirmedAt() {
        return this.verificationConfirmedAt;
    }

    public GsonMappableIso8601Date getVerificationRemovedAt() {
        return this.verificationRemovedAt;
    }

    public String getPendingEmail() {
        return this.pendingEmail;
    }

    public boolean isVerified() {
        return this.verified;
    }

    public boolean isPrimary() {
        return this.primary;
    }

    public String getType() {
        return this.type;
    }

    public String getScopes() {
        return this.scopes;
    }

    public long getUserId() {
        return this.userId;
    }

    public String getEmail() {
        return this.email;
    }
}
