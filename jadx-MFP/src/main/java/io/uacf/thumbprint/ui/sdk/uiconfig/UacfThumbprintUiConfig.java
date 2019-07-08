package io.uacf.thumbprint.ui.sdk.uiconfig;

public class UacfThumbprintUiConfig {
    private UacfThumbprintAppBarConfig appBarConfig;
    private UacfThumbprintStatusBarConfig statusBarConfig;
    private UacfThumbprintTypefaceConfig typefaceConfig;

    public static final class Builder {
        /* access modifiers changed from: private */
        public UacfThumbprintAppBarConfig appBarConfig;
        /* access modifiers changed from: private */
        public UacfThumbprintStatusBarConfig statusBarConfig;
        /* access modifiers changed from: private */
        public UacfThumbprintTypefaceConfig typefaceConfig;

        public Builder setAppBarConfig(UacfThumbprintAppBarConfig uacfThumbprintAppBarConfig) {
            this.appBarConfig = uacfThumbprintAppBarConfig;
            return this;
        }

        public Builder setTypefaceConfig(UacfThumbprintTypefaceConfig uacfThumbprintTypefaceConfig) {
            this.typefaceConfig = uacfThumbprintTypefaceConfig;
            return this;
        }

        public UacfThumbprintUiConfig build() {
            return new UacfThumbprintUiConfig(this);
        }
    }

    private UacfThumbprintUiConfig(Builder builder) {
        this.appBarConfig = builder.appBarConfig;
        this.statusBarConfig = builder.statusBarConfig;
        this.typefaceConfig = builder.typefaceConfig;
    }

    public UacfThumbprintAppBarConfig getAppBarConfig() {
        return this.appBarConfig;
    }

    public UacfThumbprintStatusBarConfig getStatusBarConfig() {
        return this.statusBarConfig;
    }

    public UacfThumbprintTypefaceConfig getTypefaceConfig() {
        return this.typefaceConfig;
    }
}
