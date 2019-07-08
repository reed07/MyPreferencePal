package com.facebook.share.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ShareStoryContent extends ShareContent<ShareStoryContent, Builder> {
    public static final Creator<ShareStoryContent> CREATOR = new Creator<ShareStoryContent>() {
        public ShareStoryContent createFromParcel(Parcel parcel) {
            return new ShareStoryContent(parcel);
        }

        public ShareStoryContent[] newArray(int i) {
            return new ShareStoryContent[i];
        }
    };
    private final String mAttributionLink;
    private final ShareMedia mBackgroundAsset;
    @Nullable
    private final List<String> mBackgroundColorList;
    private final SharePhoto mStickerAsset;

    public static final class Builder extends com.facebook.share.model.ShareContent.Builder<ShareStoryContent, Builder> {
        static final String TAG = "ShareStoryContent$Builder";
        /* access modifiers changed from: private */
        public String mAttributionLink;
        /* access modifiers changed from: private */
        public ShareMedia mBackgroundAsset;
        /* access modifiers changed from: private */
        public List<String> mBackgroundColorList;
        /* access modifiers changed from: private */
        public SharePhoto mStickerAsset;

        public Builder setBackgroundAsset(ShareMedia shareMedia) {
            this.mBackgroundAsset = shareMedia;
            return this;
        }

        public Builder setStickerAsset(SharePhoto sharePhoto) {
            this.mStickerAsset = sharePhoto;
            return this;
        }

        public Builder setBackgroundColorList(List<String> list) {
            this.mBackgroundColorList = list;
            return this;
        }

        public Builder setAttributionLink(String str) {
            this.mAttributionLink = str;
            return this;
        }

        public ShareStoryContent build() {
            return new ShareStoryContent(this);
        }

        public Builder readFrom(ShareStoryContent shareStoryContent) {
            if (shareStoryContent == null) {
                return this;
            }
            return ((Builder) super.readFrom(shareStoryContent)).setBackgroundAsset(shareStoryContent.getBackgroundAsset()).setStickerAsset(shareStoryContent.getStickerAsset()).setBackgroundColorList(shareStoryContent.getBackgroundColorList()).setAttributionLink(shareStoryContent.getAttributionLink());
        }
    }

    public int describeContents() {
        return 0;
    }

    private ShareStoryContent(Builder builder) {
        super((com.facebook.share.model.ShareContent.Builder) builder);
        this.mBackgroundAsset = builder.mBackgroundAsset;
        this.mStickerAsset = builder.mStickerAsset;
        this.mBackgroundColorList = builder.mBackgroundColorList;
        this.mAttributionLink = builder.mAttributionLink;
    }

    ShareStoryContent(Parcel parcel) {
        super(parcel);
        this.mBackgroundAsset = (ShareMedia) parcel.readParcelable(ShareMedia.class.getClassLoader());
        this.mStickerAsset = (SharePhoto) parcel.readParcelable(SharePhoto.class.getClassLoader());
        this.mBackgroundColorList = readUnmodifiableStringList(parcel);
        this.mAttributionLink = parcel.readString();
    }

    public ShareMedia getBackgroundAsset() {
        return this.mBackgroundAsset;
    }

    public SharePhoto getStickerAsset() {
        return this.mStickerAsset;
    }

    @Nullable
    public List<String> getBackgroundColorList() {
        List<String> list = this.mBackgroundColorList;
        if (list == null) {
            return null;
        }
        return Collections.unmodifiableList(list);
    }

    public String getAttributionLink() {
        return this.mAttributionLink;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(this.mBackgroundAsset, 0);
        parcel.writeParcelable(this.mStickerAsset, 0);
        parcel.writeStringList(this.mBackgroundColorList);
        parcel.writeString(this.mAttributionLink);
    }

    @Nullable
    private List<String> readUnmodifiableStringList(Parcel parcel) {
        ArrayList arrayList = new ArrayList();
        parcel.readStringList(arrayList);
        if (arrayList.isEmpty()) {
            return null;
        }
        return Collections.unmodifiableList(arrayList);
    }
}
