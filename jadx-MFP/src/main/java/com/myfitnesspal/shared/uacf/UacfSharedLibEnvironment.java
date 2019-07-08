package com.myfitnesspal.shared.uacf;

import com.myfitnesspal.build.BuildConfiguration;
import com.myfitnesspal.shared.api.MfpApiSettings;
import com.myfitnesspal.shared.constants.Constants.URLs.Prod;
import com.uacf.core.util.Strings;
import io.uacf.core.api.UacfApiEnvironment;

public class UacfSharedLibEnvironment {
    static final UacfApiEnvironment CONFIGURATION_DEV_ENVIRONMENT = new UacfApiEnvironment("configuration-integ", "https://integ-configuration.api.ua.com/", "", "");
    static final UacfApiEnvironment CONFIGURATION_PROD_ENVIRONMENT = new UacfApiEnvironment("configuration-prod", "https://prod-configuration.api.ua.com/", "", "");
    static final UacfApiEnvironment CONSENTS_DEV_ENVIRONMENT = new UacfApiEnvironment("consents-integ", "https://integ-consent.api.ua.com", "", "");
    static final UacfApiEnvironment CONSENTS_PROD_ENVIRONMENT = new UacfApiEnvironment("consents-prod", "https://consent.api.ua.com", "", "");
    public static final String DEV = "dev";
    public static final String INTEG = "integ";
    static final UacfApiEnvironment NIS_DEV_ENVIRONMENT = new UacfApiEnvironment("inbox-dev", "https://dev-inbox.api.ua.com", "", "");
    static final UacfApiEnvironment NIS_PROD_ENVIRONMENT = new UacfApiEnvironment("inbox-prod", "https://inbox.api.ua.com", "", "");
    public static final String PROD = "prod";
    static final UacfApiEnvironment ROLLOUTS_DEV_ENVIRONMENT = new UacfApiEnvironment("rollouts-integ", "https://integ-rollouts.api.ua.com/", "", "");
    static final UacfApiEnvironment ROLLOUTS_PROD_ENVIRONMENT = new UacfApiEnvironment("rollouts-prod", "https://rollouts.api.ua.com/", "", "");
    static final UacfApiEnvironment SYNCV2_INTEG_ENVIRONMENT = new UacfApiEnvironment("sync-integ", "https://integ-mobilesync.api.ua.com", "", "");
    static final UacfApiEnvironment SYNCV2_PROD_ENVIRONMENT = new UacfApiEnvironment("sync-prod", Prod.SYNCV2, "", "");

    public static UacfApiEnvironment getNISEnvironment(MfpApiSettings mfpApiSettings) {
        BuildConfiguration buildConfiguration = BuildConfiguration.getBuildConfiguration();
        String nISEndpoint = mfpApiSettings.getNISEndpoint();
        if ((!buildConfiguration.isDebug() && !buildConfiguration.isQABuild()) || Strings.isEmpty(nISEndpoint)) {
            return NIS_PROD_ENVIRONMENT;
        }
        char c = 65535;
        if (nISEndpoint.hashCode() == 99349 && nISEndpoint.equals("dev")) {
            c = 0;
        }
        if (c != 0) {
            return NIS_PROD_ENVIRONMENT;
        }
        return NIS_DEV_ENVIRONMENT;
    }

    public static UacfApiEnvironment getSyncV2Environment(MfpApiSettings mfpApiSettings) {
        BuildConfiguration buildConfiguration = BuildConfiguration.getBuildConfiguration();
        String syncV2Endpoint = mfpApiSettings.getSyncV2Endpoint();
        if ((!buildConfiguration.isDebug() && !buildConfiguration.isQABuild()) || Strings.isEmpty(syncV2Endpoint)) {
            return SYNCV2_PROD_ENVIRONMENT;
        }
        char c = 65535;
        if (syncV2Endpoint.hashCode() == 100361425 && syncV2Endpoint.equals("integ")) {
            c = 0;
        }
        if (c != 0) {
            return SYNCV2_PROD_ENVIRONMENT;
        }
        return SYNCV2_INTEG_ENVIRONMENT;
    }

    public static UacfApiEnvironment getConsentsEnvironment(MfpApiSettings mfpApiSettings) {
        BuildConfiguration buildConfiguration = BuildConfiguration.getBuildConfiguration();
        String consentsEndpoint = mfpApiSettings.getConsentsEndpoint();
        if ((!buildConfiguration.isDebug() && !buildConfiguration.isQABuild()) || Strings.isEmpty(consentsEndpoint)) {
            return CONSENTS_PROD_ENVIRONMENT;
        }
        char c = 65535;
        if (consentsEndpoint.hashCode() == 99349 && consentsEndpoint.equals("dev")) {
            c = 0;
        }
        if (c != 0) {
            return CONSENTS_PROD_ENVIRONMENT;
        }
        return CONSENTS_DEV_ENVIRONMENT;
    }

    public static UacfApiEnvironment getRolloutsEnvironment(MfpApiSettings mfpApiSettings) {
        BuildConfiguration buildConfiguration = BuildConfiguration.getBuildConfiguration();
        String consentsEndpoint = mfpApiSettings.getConsentsEndpoint();
        if ((!buildConfiguration.isDebug() && !buildConfiguration.isQABuild()) || Strings.isEmpty(consentsEndpoint)) {
            return ROLLOUTS_PROD_ENVIRONMENT;
        }
        char c = 65535;
        if (consentsEndpoint.hashCode() == 99349 && consentsEndpoint.equals("dev")) {
            c = 0;
        }
        if (c != 0) {
            return ROLLOUTS_PROD_ENVIRONMENT;
        }
        return ROLLOUTS_DEV_ENVIRONMENT;
    }

    public static UacfApiEnvironment getConfigurationEnvironment(MfpApiSettings mfpApiSettings) {
        BuildConfiguration buildConfiguration = BuildConfiguration.getBuildConfiguration();
        String consentsEndpoint = mfpApiSettings.getConsentsEndpoint();
        if ((!buildConfiguration.isDebug() && !buildConfiguration.isQABuild()) || Strings.isEmpty(consentsEndpoint)) {
            return CONFIGURATION_PROD_ENVIRONMENT;
        }
        char c = 65535;
        if (consentsEndpoint.hashCode() == 99349 && consentsEndpoint.equals("dev")) {
            c = 0;
        }
        if (c != 0) {
            return CONFIGURATION_PROD_ENVIRONMENT;
        }
        return CONFIGURATION_DEV_ENVIRONMENT;
    }
}
