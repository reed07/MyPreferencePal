package com.uacf.identity.internal.model;

import com.google.gson.annotations.Expose;
import com.uacf.identity.sdk.model.UacfAccountStatus;
import java.util.List;

public final class IdmUser {
    @Expose
    private List<IdmAccountLink> accountLinks;
    @Expose
    private String domain;
    @Expose
    private IdmProfileEmails profileEmails;
    @Expose
    private String region;
    @Expose
    private List<IdmSocialMediaLink> socialMediaLinks;
    @Expose
    private UacfAccountStatus status;
    @Expose
    private Long userId;

    public Long getUserId() {
        return this.userId;
    }

    public String getDomain() {
        return this.domain;
    }

    public String getRegion() {
        return this.region;
    }

    public UacfAccountStatus getStatus() {
        return this.status;
    }

    public List<IdmAccountLink> getAccountLinks() {
        return this.accountLinks;
    }

    public List<IdmSocialMediaLink> getSocialMediaLinks() {
        return this.socialMediaLinks;
    }

    public IdmProfileEmails getProfileEmails() {
        return this.profileEmails;
    }
}
