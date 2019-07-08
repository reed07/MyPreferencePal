package com.myfitnesspal.shared.api.auth;

import com.myfitnesspal.build.BuildConfiguration;
import com.myfitnesspal.shared.api.MfpApiSettings;
import com.uacf.core.util.Strings;
import com.uacf.identity.sdk.UacfIdentitySdk;
import com.uacf.identity.sdk.UacfIdentitySdkFactory;
import com.uacf.identity.sdk.model.UacfAccountLink;
import com.uacf.identity.sdk.model.UacfIdentityApiEnvironment;
import com.uacf.identity.sdk.model.UacfVerticalAccountInfo;
import io.uacf.core.app.UacfUserAccountDomain;

public class SSO {
    private static final String CALLBACK_URI = "mfp://identity/callback";
    public static final String DEV = "dev";
    public static final String DEV_SHORT_TTL = "dev-short-ttl";
    static final UacfIdentityApiEnvironment IDM_DEV_ENVIRONMENT;
    static final UacfIdentityApiEnvironment IDM_DEV_SHORT_TTL_ENVIRONMENT;
    static final UacfIdentityApiEnvironment IDM_INTEG_ENVIRONMENT;
    static final UacfIdentityApiEnvironment IDM_PROD_ENVIRONMENT;
    public static final String INTEG = "integ";
    public static final String PROD = "prod";

    static {
        UacfIdentityApiEnvironment uacfIdentityApiEnvironment = new UacfIdentityApiEnvironment("idm-dev", "https://integ-identity.api.ua.com/", "8b28ae46-f8c8-447b-9772-6ffb6b46fe89", "pxxjz3wmbb2n6kugfyetfowuro2kosx7liwckfsa5b4sivd63jcq", CALLBACK_URI);
        IDM_DEV_SHORT_TTL_ENVIRONMENT = uacfIdentityApiEnvironment;
        UacfIdentityApiEnvironment uacfIdentityApiEnvironment2 = new UacfIdentityApiEnvironment("idm-dev", "https://integ-identity.api.ua.com/", "616feb6e-4bb1-4718-bb8c-8c88bd978a9c", "eibf7dewweqeg7x6c7docwccm45bthoqnn5n3rciloh4j6cq4v4a", CALLBACK_URI);
        IDM_DEV_ENVIRONMENT = uacfIdentityApiEnvironment2;
        UacfIdentityApiEnvironment uacfIdentityApiEnvironment3 = new UacfIdentityApiEnvironment("idm-integ", "https://integ-identity.api.ua.com/", "b11efcc8-da04-47c4-8feb-3ed98d6503ef", "2bhtdevgots776gizbmvjmw6vsawr4r4y3gs7nmqjgpbwcfv3x2q", CALLBACK_URI);
        IDM_INTEG_ENVIRONMENT = uacfIdentityApiEnvironment3;
        UacfIdentityApiEnvironment uacfIdentityApiEnvironment4 = new UacfIdentityApiEnvironment("idm-prod", "https://identity.api.ua.com/", "1c70aed5-15c7-40a2-b4f0-a55ed1a5c43c", "7xilqzoa2lqngjgi7vilqaqygq64cgbmc7pmsf4onvfelatb6vla", CALLBACK_URI);
        IDM_PROD_ENVIRONMENT = uacfIdentityApiEnvironment4;
    }

    public static UacfIdentityApiEnvironment getEnvironment(MfpApiSettings mfpApiSettings) {
        BuildConfiguration buildConfiguration = BuildConfiguration.getBuildConfiguration();
        String idmEndpoint = mfpApiSettings.getIdmEndpoint();
        if ((!buildConfiguration.isDebug() && !buildConfiguration.isQABuild()) || Strings.isEmpty(idmEndpoint)) {
            return IDM_PROD_ENVIRONMENT;
        }
        char c = 65535;
        int hashCode = idmEndpoint.hashCode();
        if (hashCode != 99349) {
            if (hashCode != 100361425) {
                if (hashCode == 1302670691 && idmEndpoint.equals(DEV_SHORT_TTL)) {
                    c = 1;
                }
            } else if (idmEndpoint.equals("integ")) {
                c = 2;
            }
        } else if (idmEndpoint.equals("dev")) {
            c = 0;
        }
        switch (c) {
            case 0:
                return IDM_DEV_ENVIRONMENT;
            case 1:
                return IDM_DEV_SHORT_TTL_ENVIRONMENT;
            case 2:
                return IDM_INTEG_ENVIRONMENT;
            default:
                return IDM_PROD_ENVIRONMENT;
        }
    }

    public static UacfIdentitySdk getSdk() {
        return new UacfIdentitySdkFactory().newSdkInstance();
    }

    public static String getDomainUserId(UacfIdentitySdk uacfIdentitySdk) {
        UacfVerticalAccountInfo currentUserAccount = uacfIdentitySdk.getCurrentUserAccount();
        if (currentUserAccount != null) {
            for (UacfAccountLink uacfAccountLink : currentUserAccount.getAccountLinks()) {
                if (uacfAccountLink.getDomain() == UacfUserAccountDomain.MFP) {
                    return uacfAccountLink.getDomainUserId();
                }
            }
        }
        return "";
    }

    public static String getAccountEmail(UacfIdentitySdk uacfIdentitySdk) {
        UacfVerticalAccountInfo currentUserAccount = uacfIdentitySdk.getCurrentUserAccount();
        return currentUserAccount != null ? currentUserAccount.getEmailAddress() : "";
    }
}
