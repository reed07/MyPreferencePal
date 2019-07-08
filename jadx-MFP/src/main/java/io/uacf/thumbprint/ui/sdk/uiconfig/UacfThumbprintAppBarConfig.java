package io.uacf.thumbprint.ui.sdk.uiconfig;

import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;

public class UacfThumbprintAppBarConfig {
    private int appBarLayout;
    private boolean appBarShadowEnabled;
    private int titleTextViewId;
    private int toolbarId;

    public static final class Builder {
        /* access modifiers changed from: private */
        public int appBarLayout;
        /* access modifiers changed from: private */
        public boolean appBarShadowEnabled;
        /* access modifiers changed from: private */
        public int titleTextViewId;
        /* access modifiers changed from: private */
        public int toolbarId;

        public Builder setAppBarLayout(@LayoutRes int i) {
            this.appBarLayout = i;
            return this;
        }

        public Builder setToolbarId(@IdRes int i) {
            this.toolbarId = i;
            return this;
        }

        public Builder setTitleTextViewId(@IdRes int i) {
            this.titleTextViewId = i;
            return this;
        }

        public Builder setAppBarShadowEnabled(boolean z) {
            this.appBarShadowEnabled = z;
            return this;
        }

        public UacfThumbprintAppBarConfig build() {
            return new UacfThumbprintAppBarConfig(this);
        }
    }

    private UacfThumbprintAppBarConfig(Builder builder) {
        this.appBarLayout = builder.appBarLayout;
        this.toolbarId = builder.toolbarId;
        this.titleTextViewId = builder.titleTextViewId;
        this.appBarShadowEnabled = builder.appBarShadowEnabled;
    }

    public int getAppBarLayout() {
        return this.appBarLayout;
    }

    public int getToolbarId() {
        return this.toolbarId;
    }

    public int getTitleTextViewId() {
        return this.titleTextViewId;
    }

    public boolean isAppBarShadowEnabled() {
        return this.appBarShadowEnabled;
    }
}
