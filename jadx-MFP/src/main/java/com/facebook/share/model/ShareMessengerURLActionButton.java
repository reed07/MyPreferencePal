package com.facebook.share.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;

public final class ShareMessengerURLActionButton extends ShareMessengerActionButton {
    public static final Creator<ShareMessengerURLActionButton> CREATOR = new Creator<ShareMessengerURLActionButton>() {
        public ShareMessengerURLActionButton createFromParcel(Parcel parcel) {
            return new ShareMessengerURLActionButton(parcel);
        }

        public ShareMessengerURLActionButton[] newArray(int i) {
            return new ShareMessengerURLActionButton[i];
        }
    };
    private final Uri fallbackUrl;
    private final boolean isMessengerExtensionURL;
    private final boolean shouldHideWebviewShareButton;
    private final Uri url;
    private final WebviewHeightRatio webviewHeightRatio;

    public static final class Builder extends com.facebook.share.model.ShareMessengerActionButton.Builder<ShareMessengerURLActionButton, Builder> {
        /* access modifiers changed from: private */
        public Uri fallbackUrl;
        /* access modifiers changed from: private */
        public boolean isMessengerExtensionURL;
        /* access modifiers changed from: private */
        public boolean shouldHideWebviewShareButton;
        /* access modifiers changed from: private */
        public Uri url;
        /* access modifiers changed from: private */
        public WebviewHeightRatio webviewHeightRatio;

        public Builder setUrl(@Nullable Uri uri) {
            this.url = uri;
            return this;
        }

        public Builder setIsMessengerExtensionURL(boolean z) {
            this.isMessengerExtensionURL = z;
            return this;
        }

        public Builder setFallbackUrl(@Nullable Uri uri) {
            this.fallbackUrl = uri;
            return this;
        }

        public Builder setWebviewHeightRatio(WebviewHeightRatio webviewHeightRatio2) {
            this.webviewHeightRatio = webviewHeightRatio2;
            return this;
        }

        public Builder setShouldHideWebviewShareButton(boolean z) {
            this.shouldHideWebviewShareButton = z;
            return this;
        }

        public Builder readFrom(ShareMessengerURLActionButton shareMessengerURLActionButton) {
            if (shareMessengerURLActionButton == null) {
                return this;
            }
            return setUrl(shareMessengerURLActionButton.getUrl()).setIsMessengerExtensionURL(shareMessengerURLActionButton.getIsMessengerExtensionURL()).setFallbackUrl(shareMessengerURLActionButton.getFallbackUrl()).setWebviewHeightRatio(shareMessengerURLActionButton.getWebviewHeightRatio()).setShouldHideWebviewShareButton(shareMessengerURLActionButton.getShouldHideWebviewShareButton());
        }

        public ShareMessengerURLActionButton build() {
            return new ShareMessengerURLActionButton(this);
        }
    }

    public enum WebviewHeightRatio {
        WebviewHeightRatioFull,
        WebviewHeightRatioTall,
        WebviewHeightRatioCompact
    }

    private ShareMessengerURLActionButton(Builder builder) {
        super((com.facebook.share.model.ShareMessengerActionButton.Builder) builder);
        this.url = builder.url;
        this.isMessengerExtensionURL = builder.isMessengerExtensionURL;
        this.fallbackUrl = builder.fallbackUrl;
        this.webviewHeightRatio = builder.webviewHeightRatio;
        this.shouldHideWebviewShareButton = builder.shouldHideWebviewShareButton;
    }

    ShareMessengerURLActionButton(Parcel parcel) {
        super(parcel);
        this.url = (Uri) parcel.readParcelable(Uri.class.getClassLoader());
        boolean z = true;
        this.isMessengerExtensionURL = parcel.readByte() != 0;
        this.fallbackUrl = (Uri) parcel.readParcelable(Uri.class.getClassLoader());
        this.webviewHeightRatio = (WebviewHeightRatio) parcel.readSerializable();
        if (parcel.readByte() == 0) {
            z = false;
        }
        this.shouldHideWebviewShareButton = z;
    }

    public Uri getUrl() {
        return this.url;
    }

    public boolean getIsMessengerExtensionURL() {
        return this.isMessengerExtensionURL;
    }

    @Nullable
    public Uri getFallbackUrl() {
        return this.fallbackUrl;
    }

    @Nullable
    public WebviewHeightRatio getWebviewHeightRatio() {
        return this.webviewHeightRatio;
    }

    public boolean getShouldHideWebviewShareButton() {
        return this.shouldHideWebviewShareButton;
    }
}
