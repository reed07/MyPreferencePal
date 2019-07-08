package com.myfitnesspal.shared.model.v2;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.myfitnesspal.shared.api.ApiResponse;
import com.uacf.core.util.ParcelableUtil;
import java.util.ArrayList;

public class MfpPlatformApp implements Parcelable {
    public static Creator<MfpPlatformApp> CREATOR = new Creator<MfpPlatformApp>() {
        public MfpPlatformApp createFromParcel(Parcel parcel) {
            return new MfpPlatformApp(parcel);
        }

        public MfpPlatformApp[] newArray(int i) {
            return new MfpPlatformApp[i];
        }
    };
    @Expose
    private ArrayList<String> appCategories;
    @Expose
    private String appId;
    @Expose
    private String appLongDescription;
    @Expose
    private String appShortDescription;
    @Expose
    private String clientId;
    @Expose
    private String clientName;
    @Expose
    private String connectUri;
    @Expose
    private String deepConnectUri;
    @Expose
    private MfpAppImage iconImage;
    @Expose
    private int id;
    @Expose
    private String landingPageUri;
    @Expose
    private String name;
    @Expose
    private String platformSubtype;
    @Expose
    private String platformType;
    @Expose
    private MfpAppImage promoImage;
    @Expose
    private boolean purchasable;
    @Expose
    private String purchaseUrl;
    @Expose
    private ArrayList<MfpAppImage> screenshotImages;
    @Expose
    private String storeLink;
    @Expose
    private MfpAppImage thumbnailImage;
    @Expose
    private boolean tracksSteps;
    @Expose
    private String urlSchemeSuffix;
    @Expose
    private boolean userHasConnected;

    public static class API_RESPONSE_MAPPER extends ApiResponse<MfpPlatformApp> {
    }

    public int describeContents() {
        return 0;
    }

    public MfpAppImage getPromoImage() {
        return this.promoImage;
    }

    public void setPromoImage(MfpAppImage mfpAppImage) {
        this.promoImage = mfpAppImage;
    }

    public MfpPlatformApp() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getAppShortDescription() {
        return this.appShortDescription;
    }

    public void setAppShortDescription(String str) {
        this.appShortDescription = str;
    }

    public String getAppLongDescription() {
        return this.appLongDescription;
    }

    public void setAppLongDescription(String str) {
        this.appLongDescription = str;
    }

    public String getPlatformType() {
        return this.platformType;
    }

    public void setPlatformType(String str) {
        this.platformType = str;
    }

    public String getPlatformSubtype() {
        return this.platformSubtype;
    }

    public void setPlatformSubtype(String str) {
        this.platformSubtype = str;
    }

    @Deprecated
    public String getLandingPageUri() {
        return this.landingPageUri;
    }

    @Deprecated
    public void setLandingPageUri(String str) {
        this.landingPageUri = str;
    }

    public String getConnectUri() {
        return this.connectUri;
    }

    public void setConnectUri(String str) {
        this.connectUri = str;
    }

    public String getStoreLink() {
        return this.storeLink;
    }

    public void setStoreLink(String str) {
        this.storeLink = str;
    }

    @Deprecated
    public String getUrlSchemeSuffix() {
        return this.urlSchemeSuffix;
    }

    @Deprecated
    public void setUrlSchemeSuffix(String str) {
        this.urlSchemeSuffix = str;
    }

    public ArrayList<MfpAppImage> getScreenshotImages() {
        return this.screenshotImages;
    }

    public void setScreenshotImages(ArrayList<MfpAppImage> arrayList) {
        this.screenshotImages = arrayList;
    }

    public MfpAppImage getIconImage() {
        return this.iconImage;
    }

    public void setIconImage(MfpAppImage mfpAppImage) {
        this.iconImage = mfpAppImage;
    }

    public MfpAppImage getThumbnailImage() {
        return this.thumbnailImage;
    }

    public void setThumbnailImage(MfpAppImage mfpAppImage) {
        this.thumbnailImage = mfpAppImage;
    }

    public boolean hasUserConnected() {
        return this.userHasConnected;
    }

    public void setUserHasConnected(boolean z) {
        this.userHasConnected = z;
    }

    public ArrayList<String> getAppCategories() {
        return this.appCategories;
    }

    public void setAppCategories(ArrayList<String> arrayList) {
        this.appCategories = arrayList;
    }

    public boolean isTracksSteps() {
        return this.tracksSteps;
    }

    public void setTracksSteps(boolean z) {
        this.tracksSteps = z;
    }

    public String getClientName() {
        return this.clientName;
    }

    public void setClientName(String str) {
        this.clientName = str;
    }

    public String getClientId() {
        return this.clientId;
    }

    public void setClientId(String str) {
        this.clientId = str;
    }

    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String str) {
        this.appId = str;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public String getDeepConnectUri() {
        return this.deepConnectUri;
    }

    public void setDeepConnectUri(String str) {
        this.deepConnectUri = str;
    }

    public boolean isPurchasable() {
        return this.purchasable;
    }

    public void setPurchasable(boolean z) {
        this.purchasable = z;
    }

    public String getPurchaseUrl() {
        return this.purchaseUrl;
    }

    public void setPurchaseUrl(String str) {
        this.purchaseUrl = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.id);
        parcel.writeString(this.name);
        parcel.writeString(this.appShortDescription);
        parcel.writeString(this.appLongDescription);
        parcel.writeString(this.platformType);
        parcel.writeString(this.platformSubtype);
        parcel.writeString(this.landingPageUri);
        parcel.writeString(this.connectUri);
        parcel.writeString(this.storeLink);
        parcel.writeString(this.urlSchemeSuffix);
        ParcelableUtil.writeList(parcel, this.screenshotImages);
        parcel.writeParcelable(this.iconImage, 0);
        parcel.writeParcelable(this.thumbnailImage, 0);
        parcel.writeParcelable(this.promoImage, 0);
        parcel.writeByte(this.userHasConnected ? (byte) 1 : 0);
        ParcelableUtil.writeStringList(parcel, this.appCategories);
        parcel.writeByte(this.tracksSteps ? (byte) 1 : 0);
        parcel.writeString(this.clientName);
        parcel.writeString(this.clientId);
        parcel.writeString(this.appId);
        parcel.writeString(this.deepConnectUri);
        parcel.writeByte(this.purchasable ? (byte) 1 : 0);
        parcel.writeString(this.purchaseUrl);
    }

    private MfpPlatformApp(Parcel parcel) {
        this.id = parcel.readInt();
        this.name = parcel.readString();
        this.appShortDescription = parcel.readString();
        this.appLongDescription = parcel.readString();
        this.platformType = parcel.readString();
        this.platformSubtype = parcel.readString();
        this.landingPageUri = parcel.readString();
        this.connectUri = parcel.readString();
        this.storeLink = parcel.readString();
        this.urlSchemeSuffix = parcel.readString();
        this.screenshotImages = ParcelableUtil.readList(parcel, MfpAppImage.class);
        this.iconImage = (MfpAppImage) parcel.readParcelable(MfpAppImage.class.getClassLoader());
        this.thumbnailImage = (MfpAppImage) parcel.readParcelable(MfpAppImage.class.getClassLoader());
        this.promoImage = (MfpAppImage) parcel.readParcelable(MfpAppImage.class.getClassLoader());
        boolean z = true;
        this.userHasConnected = parcel.readByte() != 0;
        this.appCategories = ParcelableUtil.readStringList(parcel);
        this.tracksSteps = parcel.readByte() != 0;
        this.clientName = parcel.readString();
        this.clientId = parcel.readString();
        this.appId = parcel.readString();
        this.deepConnectUri = parcel.readString();
        if (parcel.readByte() == 0) {
            z = false;
        }
        this.purchasable = z;
        this.purchaseUrl = parcel.readString();
    }
}
