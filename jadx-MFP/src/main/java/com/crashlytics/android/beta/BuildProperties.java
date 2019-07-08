package com.crashlytics.android.beta;

import com.google.ads.mediation.inmobi.InMobiNetworkValues;
import com.myfitnesspal.shared.service.device.UserAgentProviderImpl.Params;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

class BuildProperties {
    public final String buildId;
    public final String packageName;
    public final String versionCode;
    public final String versionName;

    BuildProperties(String str, String str2, String str3, String str4) {
        this.versionCode = str;
        this.versionName = str2;
        this.buildId = str3;
        this.packageName = str4;
    }

    public static BuildProperties fromProperties(Properties properties) {
        return new BuildProperties(properties.getProperty("version_code"), properties.getProperty(Params.VERSION_NAME), properties.getProperty("build_id"), properties.getProperty(InMobiNetworkValues.PACKAGE_NAME));
    }

    public static BuildProperties fromPropertiesStream(InputStream inputStream) throws IOException {
        Properties properties = new Properties();
        properties.load(inputStream);
        return fromProperties(properties);
    }
}
