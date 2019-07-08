package com.uacf.identity.internal.analytics;

import com.google.gson.annotations.Expose;
import io.uacf.core.app.UacfAppId;
import io.uacf.core.app.UacfSocialNetworkProvider;

public final class IdmAnalyticsAttributes {

    public static final class AccountMergeFinalize {
    }

    public static final class AccountMergeFind {
    }

    public static final class EmailSearch {
        @Expose
        private int count;

        public EmailSearch(int i) {
            this.count = i;
        }
    }

    public static class Error {
        @Expose
        private String error;

        public Error(String str) {
            this.error = str;
        }
    }

    public static class FetchUserAccounts {
    }

    public static final class Login extends Error {
        @Expose
        private UacfAppId otherAppId;
        @Expose
        private LoginType type;
        @Expose
        private String uacfSocialAppId;
        @Expose
        private UacfSocialNetworkProvider uacfSocialProvider;

        public Login(String str, LoginType loginType, UacfSocialNetworkProvider uacfSocialNetworkProvider, String str2, UacfAppId uacfAppId) {
            super(str);
            this.type = loginType;
            this.uacfSocialProvider = uacfSocialNetworkProvider;
            this.uacfSocialAppId = str2;
            this.otherAppId = uacfAppId;
        }
    }

    public enum LoginType {
        PASSWORD,
        LEGACY,
        SOCIAL,
        SSO
    }

    public static final class TokenRefresh {
        @Expose
        private long expiresIn;
        @Expose
        private Type type;

        public enum Type {
            Initial,
            Explicit,
            Implicit,
            Async
        }

        public TokenRefresh(Type type2, long j) {
            this.type = type2;
            this.expiresIn = j;
        }
    }
}
