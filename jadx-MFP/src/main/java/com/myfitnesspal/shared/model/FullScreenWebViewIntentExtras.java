package com.myfitnesspal.shared.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.uacf.core.util.ParcelableUtil;

public class FullScreenWebViewIntentExtras implements Parcelable {
    public static final Creator<FullScreenWebViewIntentExtras> CREATOR = new Creator<FullScreenWebViewIntentExtras>() {
        public FullScreenWebViewIntentExtras createFromParcel(Parcel parcel) {
            return new FullScreenWebViewIntentExtras(parcel);
        }

        public FullScreenWebViewIntentExtras[] newArray(int i) {
            return new FullScreenWebViewIntentExtras[i];
        }
    };
    private boolean addLanguageHeader;
    private boolean handleAllClicksExternally;
    private boolean loadUrlWithAuthHeader;
    private boolean showCloseAsBackButton;
    private String title;
    private String url;
    private String webviewKeyToLaunch;

    public int describeContents() {
        return 0;
    }

    public FullScreenWebViewIntentExtras() {
    }

    private FullScreenWebViewIntentExtras(Parcel parcel) {
        this.url = parcel.readString();
        this.title = parcel.readString();
        this.handleAllClicksExternally = ParcelableUtil.readBoolean(parcel);
        this.addLanguageHeader = ParcelableUtil.readBoolean(parcel);
        this.loadUrlWithAuthHeader = ParcelableUtil.readBoolean(parcel);
        this.showCloseAsBackButton = ParcelableUtil.readBoolean(parcel);
        this.webviewKeyToLaunch = parcel.readString();
    }

    public FullScreenWebViewIntentExtras setUrl(String str) {
        this.url = str;
        return this;
    }

    public FullScreenWebViewIntentExtras setTitle(String str) {
        this.title = str;
        return this;
    }

    public FullScreenWebViewIntentExtras setHandleAllClicksExternally(boolean z) {
        this.handleAllClicksExternally = z;
        return this;
    }

    public FullScreenWebViewIntentExtras setAddLanguageHeader(boolean z) {
        this.addLanguageHeader = z;
        return this;
    }

    public FullScreenWebViewIntentExtras setLoadUrlWithAuthHeader(boolean z) {
        this.loadUrlWithAuthHeader = z;
        return this;
    }

    public FullScreenWebViewIntentExtras setShowCloseAsBackButton(boolean z) {
        this.showCloseAsBackButton = z;
        return this;
    }

    public FullScreenWebViewIntentExtras setWebViewKeyToLaunch(String str) {
        this.webviewKeyToLaunch = str;
        return this;
    }

    public String getUrl() {
        return this.url;
    }

    public String getTitle() {
        return this.title;
    }

    public boolean handleAllClicksExternally() {
        return this.handleAllClicksExternally;
    }

    public boolean addLanguageHeader() {
        return this.addLanguageHeader;
    }

    public boolean loadUrlWithAuthHeader() {
        return this.loadUrlWithAuthHeader;
    }

    public boolean showCloseAsBackButton() {
        return this.showCloseAsBackButton;
    }

    public String getWebviewKeyToLaunch() {
        return this.webviewKeyToLaunch;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.url);
        parcel.writeString(this.title);
        ParcelableUtil.writeBoolean(parcel, this.handleAllClicksExternally);
        ParcelableUtil.writeBoolean(parcel, this.addLanguageHeader);
        ParcelableUtil.writeBoolean(parcel, this.loadUrlWithAuthHeader);
        ParcelableUtil.writeBoolean(parcel, this.showCloseAsBackButton);
        parcel.writeString(this.webviewKeyToLaunch);
    }
}
