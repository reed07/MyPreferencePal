package io.uacf.configuration.internal.analytics;

import com.google.gson.annotations.Expose;

public final class Attributes {

    public static final class ConfigAccessed {
        @Expose
        private final String configName;
        @Expose
        private final String configUsedDefault;
        @Expose
        private final String configValue;
        @Expose
        private final String configVersion;

        public ConfigAccessed(String str, String str2, String str3, String str4) {
            this.configName = str;
            this.configValue = str2;
            this.configVersion = str3;
            this.configUsedDefault = str4;
        }
    }
}
