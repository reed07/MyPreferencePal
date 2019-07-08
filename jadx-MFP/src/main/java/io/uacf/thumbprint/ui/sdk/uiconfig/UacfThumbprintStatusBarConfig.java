package io.uacf.thumbprint.ui.sdk.uiconfig;

public class UacfThumbprintStatusBarConfig {
    private int statusBarColorResId;
    private int systemUiVisibility;

    public static final class Builder {
    }

    public int getSystemUiVisibility() {
        return this.systemUiVisibility;
    }

    public int getStatusBarColorResId() {
        return this.statusBarColorResId;
    }
}
