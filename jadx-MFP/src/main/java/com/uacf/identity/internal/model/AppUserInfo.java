package com.uacf.identity.internal.model;

import com.google.gson.annotations.Expose;
import com.uacf.core.mapping.UacfGsonFactory;
import com.uacf.core.util.Strings;
import com.uacf.identity.internal.util.JWTUtil;
import io.uacf.core.app.UacfUserAccountDomain;
import java.util.ArrayList;
import java.util.List;

public final class AppUserInfo {
    @Expose
    private List<IdmAccountLink> accountLinks;
    @Expose
    private UacfUserAccountDomain domain;
    @Expose
    private List<String> emails;
    @Expose
    private List<IdmSocialMediaLink> socialMediaLinks;
    @Expose
    private IdmOAuthTokenInfo tokenInfo;
    @Expose
    private IdmUser user;
    @Expose
    private String userId;
    @Expose
    private IdmUserInfo userInfo;

    public String getUserId() {
        return this.userId;
    }

    public AppUserInfo setUserId(String str) {
        this.userId = str;
        return this;
    }

    public UacfUserAccountDomain getDomain() {
        return this.domain;
    }

    public AppUserInfo setDomain(UacfUserAccountDomain uacfUserAccountDomain) {
        this.domain = uacfUserAccountDomain;
        return this;
    }

    public IdmOAuthTokenInfo getTokenInfo() {
        return this.tokenInfo;
    }

    public AppUserInfo setTokenInfo(IdmOAuthTokenInfo idmOAuthTokenInfo, IdmKeyDesc idmKeyDesc) {
        this.tokenInfo = idmOAuthTokenInfo;
        String idToken = idmOAuthTokenInfo.getIdToken();
        if (Strings.notEmpty(idToken) && idmKeyDesc != null) {
            setUserId(((IdmDecodedIdToken) UacfGsonFactory.newInstance().fromJson(JWTUtil.decode(idToken, idmKeyDesc), IdmDecodedIdToken.class)).getSub());
        }
        return this;
    }

    public IdmUser getUser() {
        return this.user;
    }

    public AppUserInfo setUser(IdmUser idmUser) {
        this.user = idmUser;
        return this;
    }

    public IdmUserInfo getUserInfo() {
        return this.userInfo;
    }

    public AppUserInfo setUserInfo(IdmUserInfo idmUserInfo) {
        this.userInfo = idmUserInfo;
        return this;
    }

    public List<String> getEmails() {
        return this.emails;
    }

    public AppUserInfo setEmails(List<String> list) {
        this.emails = list != null ? new ArrayList(list) : null;
        return this;
    }

    public List<IdmAccountLink> getAccountLinks() {
        return this.accountLinks;
    }

    public AppUserInfo setAccountLinks(List<IdmAccountLink> list) {
        this.accountLinks = list != null ? new ArrayList(list) : null;
        return this;
    }

    public List<IdmSocialMediaLink> getSocialMediaLinks() {
        return this.socialMediaLinks;
    }

    public AppUserInfo setSocialMediaLinks(List<IdmSocialMediaLink> list) {
        this.socialMediaLinks = list;
        return this;
    }
}
