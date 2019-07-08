package com.myfitnesspal.shared.constants;

public final class Environment {

    public interface Blog {
        public static final String INTEG = "http://myfitnesspal.staging.wpengine.com";
        public static final String PROD = "http://blog.myfitnesspal.com";
    }

    public interface Config {
        public static final String INTEG = "https://integ.myfitnesspal.com";
        public static final String PROD = "https://androidconfig.myfitnesspal.com";
        public static final String QA = "https://qa.myfitnesspal.com";
    }

    public interface DeviceActivation {
        public static final String INTEG = "https://integ-device-activator.api.ua.com";
        public static final String PROD = "https://device-activator.api.ua.com";
    }

    public interface V1 {
        public static final String DEV1 = "https://dev1.myfitnesspal.com";
        public static final String INTEG = "https://integ.myfitnesspal.com";
        public static final String PREPROD = "https://preprod.myfitnesspal.com";
        public static final String PROD = "https://sync.myfitnesspal.com";
        public static final String QA = "https://qa.myfitnesspal.com";
    }

    public interface V2 {
        public static final String DEV1 = "https://dev1.myfitnesspal.com";
        public static final String INTEG = "https://integ.myfitnesspal.com";
        public static final String PREPROD = "https://preprod.myfitnesspal.com";
        public static final String PROD = "https://api.myfitnesspal.com";
        public static final String QA = "https://qa.myfitnesspal.com";
    }

    public interface Website {
        public static final String INTEG = "https://integ.myfitnesspal.com";
        public static final String PREPROD = "https://preprod.myfitnesspal.com";
        public static final String PROD = "https://www.myfitnesspal.com";
        public static final String QA = "https://qa.myfitnesspal.com";
    }

    private Environment() {
    }
}
