package com.uacf.identity.internal.model;

import com.google.gson.annotations.Expose;
import com.uacf.core.util.Enumerable;
import com.uacf.core.util.ReturningFunction1;
import com.uacf.core.util.Strings;
import com.uacf.identity.sdk.model.UacfAccountLink;
import com.uacf.identity.sdk.model.UacfLocation;
import com.uacf.identity.sdk.model.UacfSocialMediaLink;
import com.uacf.identity.sdk.model.UacfVerticalAccountInfo;
import com.uacf.identity.sdk.model.UacfVerticalAccountInfo.Builder;
import io.uacf.core.app.UacfAppId;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public final class AppSessionInfo {
    @Expose
    private final UacfAppId appId;
    @Expose
    private IdmKeyInfo clientKeyInfo;
    @Expose
    private IdmOAuthTokenInfo clientTokenInfo;
    @Expose
    private String currentUserId;
    @Expose
    private IdmKeyDesc serverKeyInfo;
    @Expose
    private Map<String, AppUserInfo> userInfoMap;

    public AppSessionInfo(UacfAppId uacfAppId) {
        this.appId = uacfAppId;
    }

    public String getCurrentUserId() {
        return this.currentUserId;
    }

    public AppSessionInfo setCurrentUserId(String str) {
        this.currentUserId = str;
        return this;
    }

    public IdmKeyInfo getClientKeyInfo() {
        return this.clientKeyInfo;
    }

    public AppSessionInfo setClientKeyInfo(IdmKeyInfo idmKeyInfo) {
        this.clientKeyInfo = idmKeyInfo;
        return this;
    }

    public IdmKeyDesc getServerKeyInfo() {
        return this.serverKeyInfo;
    }

    public AppSessionInfo setServerKeyInfo(IdmKeyDesc idmKeyDesc) {
        this.serverKeyInfo = idmKeyDesc;
        return this;
    }

    public AppUserInfo getCurrentUserInfo() {
        return getUserInfo(this.currentUserId);
    }

    public Map<String, AppUserInfo> getUserInfoMap() {
        return this.userInfoMap;
    }

    public AppUserInfo getUserInfo(String str) {
        ensureMap();
        return (AppUserInfo) this.userInfoMap.get(str);
    }

    public AppSessionInfo setUserInfo(String str, AppUserInfo appUserInfo) {
        ensureMap();
        this.userInfoMap.put(str, appUserInfo);
        return this;
    }

    public AppSessionInfo setCurrentUserInfo(AppUserInfo appUserInfo) {
        return setUserInfo(this.currentUserId, appUserInfo);
    }

    public IdmOAuthTokenInfo getClientTokenInfo() {
        return this.clientTokenInfo;
    }

    public AppSessionInfo setClientTokenInfo(IdmOAuthTokenInfo idmOAuthTokenInfo) {
        this.clientTokenInfo = idmOAuthTokenInfo;
        return this;
    }

    private void ensureMap() {
        if (this.userInfoMap == null) {
            this.userInfoMap = new HashMap();
        }
    }

    public UacfVerticalAccountInfo toVerticalAccountInfo() {
        String currentUserId2 = getCurrentUserId();
        if (Strings.isEmpty(currentUserId2)) {
            return null;
        }
        ensureMap();
        AppUserInfo appUserInfo = (AppUserInfo) this.userInfoMap.get(currentUserId2);
        if (appUserInfo == null) {
            return null;
        }
        Builder withSocialMediaLinks = new Builder().withUacfUserId(currentUserId2).withAppId(this.appId).withEmailAddress((String) Enumerable.firstOrDefault(appUserInfo.getEmails())).withDomain(appUserInfo.getDomain()).withAccountLinks(Enumerable.select((Collection<T>) appUserInfo.getAccountLinks(), (ReturningFunction1<U, T>) new ReturningFunction1<UacfAccountLink, IdmAccountLink>() {
            public UacfAccountLink execute(IdmAccountLink idmAccountLink) {
                return new UacfAccountLink().setDomain(idmAccountLink.getDomain()).setDomainUserId(idmAccountLink.getDomainUserId());
            }
        })).withSocialMediaLinks(Enumerable.select((Collection<T>) appUserInfo.getSocialMediaLinks(), (ReturningFunction1<U, T>) new ReturningFunction1<UacfSocialMediaLink, IdmSocialMediaLink>() {
            public UacfSocialMediaLink execute(IdmSocialMediaLink idmSocialMediaLink) {
                return new UacfSocialMediaLink.Builder().withAppId(idmSocialMediaLink.getAppId()).withAuthToken(idmSocialMediaLink.getAuthToken()).withExpiry(idmSocialMediaLink.getExpiry()).withProvider(idmSocialMediaLink.getProvider()).withRefreshToken(idmSocialMediaLink.getRefreshToken()).withUserId(idmSocialMediaLink.getUserId()).withUsername(idmSocialMediaLink.getUsername()).build();
            }
        }));
        IdmOAuthTokenInfo tokenInfo = appUserInfo.getTokenInfo();
        if (tokenInfo != null) {
            withSocialMediaLinks.withAccessToken(tokenInfo.getAccessToken()).withRefreshToken(tokenInfo.getRefreshToken()).withExpirationTime(tokenInfo.getExpirationTimeInMillis());
        }
        IdmUserInfo userInfo = appUserInfo.getUserInfo();
        if (userInfo != null) {
            withSocialMediaLinks.withUserLocale(userInfo.getLocale()).withFullName(userInfo.getFullName()).withDisplayName(userInfo.getDisplayName()).withLastName(userInfo.getLastName()).withFirstName(userInfo.getFirstName()).withProfilePictureUri(userInfo.getProfilePictureUri()).withGender(userInfo.getGender()).withHeight(userInfo.getHeight()).withWeight(userInfo.getWeight()).withBirthdate(userInfo.getBirthdate());
            IdmLocation location = userInfo.getLocation();
            if (location != null) {
                withSocialMediaLinks.withLocation(new UacfLocation().setCountry(location.getCountry()).setPostalCode(location.getPostalCode()).setRegion(location.getRegion()));
            }
        }
        return withSocialMediaLinks.build();
    }
}
