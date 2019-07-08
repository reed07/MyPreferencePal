package com.uacf.identity.internal.model;

import com.google.gson.annotations.Expose;
import io.uacf.core.app.UacfUserAccountDomain;
import java.util.List;

public class IdmUserInfoContainer {
    @Expose
    private List<IdmAccountLink> accountLinks;
    @Expose
    private UacfUserAccountDomain domain;
    @Expose
    private List<String> emails;
    @Expose
    private Long mergedUserId;
    @Expose
    private IdmUserInfo profile;
    @Expose
    private List<IdmSocialMediaLink> socialMediaLinks;

    public UacfUserAccountDomain getDomain() {
        return this.domain;
    }

    public Long getMergedUserId() {
        return this.mergedUserId;
    }

    public IdmUserInfo getProfile() {
        return this.profile;
    }

    public List<String> getEmails() {
        return this.emails;
    }

    public List<IdmAccountLink> getAccountLinks() {
        return this.accountLinks;
    }

    public List<IdmSocialMediaLink> getSocialMediaLinks() {
        return this.socialMediaLinks;
    }
}
