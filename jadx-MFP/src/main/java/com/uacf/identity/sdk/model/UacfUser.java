package com.uacf.identity.sdk.model;

import com.google.gson.annotations.Expose;
import io.uacf.core.app.UacfUserAccountDomain;
import java.util.List;

public class UacfUser {
    /* access modifiers changed from: private */
    @Expose
    public List<UacfAccountLink> accountLinks;
    /* access modifiers changed from: private */
    @Expose
    public UacfAccountStatus accountStatus;
    /* access modifiers changed from: private */
    @Expose
    public UacfUserAccountDomain domain;
    /* access modifiers changed from: private */
    @Expose
    public UacfProfileEmails profileEmails;
    /* access modifiers changed from: private */
    @Expose
    public String region;
    /* access modifiers changed from: private */
    @Expose
    public List<UacfSocialMediaLink> socialMediaLinks;
    /* access modifiers changed from: private */
    @Expose
    public String userId;

    /* renamed from: com.uacf.identity.sdk.model.UacfUser$1 reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$io$uacf$core$app$UacfUserAccountDomain = new int[UacfUserAccountDomain.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(20:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|(3:19|20|22)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(22:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|22) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0040 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x004b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0056 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0062 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x006e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0035 */
        static {
            /*
                io.uacf.core.app.UacfUserAccountDomain[] r0 = io.uacf.core.app.UacfUserAccountDomain.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$io$uacf$core$app$UacfUserAccountDomain = r0
                int[] r0 = $SwitchMap$io$uacf$core$app$UacfUserAccountDomain     // Catch:{ NoSuchFieldError -> 0x0014 }
                io.uacf.core.app.UacfUserAccountDomain r1 = io.uacf.core.app.UacfUserAccountDomain.ECOMM_NA     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = $SwitchMap$io$uacf$core$app$UacfUserAccountDomain     // Catch:{ NoSuchFieldError -> 0x001f }
                io.uacf.core.app.UacfUserAccountDomain r1 = io.uacf.core.app.UacfUserAccountDomain.ECOMM_EU     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = $SwitchMap$io$uacf$core$app$UacfUserAccountDomain     // Catch:{ NoSuchFieldError -> 0x002a }
                io.uacf.core.app.UacfUserAccountDomain r1 = io.uacf.core.app.UacfUserAccountDomain.ECOMM_SEA     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = $SwitchMap$io$uacf$core$app$UacfUserAccountDomain     // Catch:{ NoSuchFieldError -> 0x0035 }
                io.uacf.core.app.UacfUserAccountDomain r1 = io.uacf.core.app.UacfUserAccountDomain.ECOMM_MX     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r0 = $SwitchMap$io$uacf$core$app$UacfUserAccountDomain     // Catch:{ NoSuchFieldError -> 0x0040 }
                io.uacf.core.app.UacfUserAccountDomain r1 = io.uacf.core.app.UacfUserAccountDomain.ECOMM_CL     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                int[] r0 = $SwitchMap$io$uacf$core$app$UacfUserAccountDomain     // Catch:{ NoSuchFieldError -> 0x004b }
                io.uacf.core.app.UacfUserAccountDomain r1 = io.uacf.core.app.UacfUserAccountDomain.ECOMM_AU     // Catch:{ NoSuchFieldError -> 0x004b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x004b }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x004b }
            L_0x004b:
                int[] r0 = $SwitchMap$io$uacf$core$app$UacfUserAccountDomain     // Catch:{ NoSuchFieldError -> 0x0056 }
                io.uacf.core.app.UacfUserAccountDomain r1 = io.uacf.core.app.UacfUserAccountDomain.ECOMM_OC     // Catch:{ NoSuchFieldError -> 0x0056 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0056 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0056 }
            L_0x0056:
                int[] r0 = $SwitchMap$io$uacf$core$app$UacfUserAccountDomain     // Catch:{ NoSuchFieldError -> 0x0062 }
                io.uacf.core.app.UacfUserAccountDomain r1 = io.uacf.core.app.UacfUserAccountDomain.ECOMM_KR     // Catch:{ NoSuchFieldError -> 0x0062 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0062 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0062 }
            L_0x0062:
                int[] r0 = $SwitchMap$io$uacf$core$app$UacfUserAccountDomain     // Catch:{ NoSuchFieldError -> 0x006e }
                io.uacf.core.app.UacfUserAccountDomain r1 = io.uacf.core.app.UacfUserAccountDomain.ECOMM_TR     // Catch:{ NoSuchFieldError -> 0x006e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006e }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006e }
            L_0x006e:
                int[] r0 = $SwitchMap$io$uacf$core$app$UacfUserAccountDomain     // Catch:{ NoSuchFieldError -> 0x007a }
                io.uacf.core.app.UacfUserAccountDomain r1 = io.uacf.core.app.UacfUserAccountDomain.ECOMM_BR     // Catch:{ NoSuchFieldError -> 0x007a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x007a }
                r2 = 10
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x007a }
            L_0x007a:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.uacf.identity.sdk.model.UacfUser.AnonymousClass1.<clinit>():void");
        }
    }

    public static final class Builder {
        private List<UacfAccountLink> accountLinks;
        private UacfAccountStatus accountStatus;
        private UacfUserAccountDomain domain;
        private UacfProfileEmails profileEmails;
        private String region;
        private List<UacfSocialMediaLink> socialMediaLinks;
        private String userId;

        public Builder withUserId(String str) {
            this.userId = str;
            return this;
        }

        public Builder withDomain(UacfUserAccountDomain uacfUserAccountDomain) {
            this.domain = uacfUserAccountDomain;
            return this;
        }

        public Builder withRegion(String str) {
            this.region = str;
            return this;
        }

        public Builder withAccountLinks(List<UacfAccountLink> list) {
            this.accountLinks = list;
            return this;
        }

        public Builder withSocialMediaLinks(List<UacfSocialMediaLink> list) {
            this.socialMediaLinks = list;
            return this;
        }

        public Builder withProfileEmails(UacfProfileEmails uacfProfileEmails) {
            this.profileEmails = uacfProfileEmails;
            return this;
        }

        public Builder withAccountStatus(UacfAccountStatus uacfAccountStatus) {
            this.accountStatus = uacfAccountStatus;
            return this;
        }

        public UacfUser build() {
            UacfUser uacfUser = new UacfUser(null);
            uacfUser.userId = this.userId;
            uacfUser.domain = this.domain;
            uacfUser.region = this.region;
            uacfUser.accountLinks = this.accountLinks;
            uacfUser.socialMediaLinks = this.socialMediaLinks;
            uacfUser.profileEmails = this.profileEmails;
            uacfUser.accountStatus = this.accountStatus;
            return uacfUser;
        }
    }

    /* synthetic */ UacfUser(AnonymousClass1 r1) {
        this();
    }

    private UacfUser() {
    }

    public String getUserId() {
        return this.userId;
    }

    public UacfUserAccountDomain getDomain() {
        return this.domain;
    }

    public String getRegion() {
        return this.region;
    }

    public List<UacfAccountLink> getAccountLinks() {
        return this.accountLinks;
    }

    public UacfProfileEmails getProfileEmails() {
        return this.profileEmails;
    }

    public UacfAccountStatus getAccountStatus() {
        return this.accountStatus;
    }

    public boolean isVerified() {
        UacfProfileEmails uacfProfileEmails = this.profileEmails;
        if (uacfProfileEmails != null) {
            return uacfProfileEmails.isEmailVerified();
        }
        return false;
    }
}
