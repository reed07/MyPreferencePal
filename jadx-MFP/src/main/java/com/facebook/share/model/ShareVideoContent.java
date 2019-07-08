package com.facebook.share.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;

public final class ShareVideoContent extends ShareContent<ShareVideoContent, Builder> implements ShareModel {
    public static final Creator<ShareVideoContent> CREATOR = new Creator<ShareVideoContent>() {
        public ShareVideoContent createFromParcel(Parcel parcel) {
            return new ShareVideoContent(parcel);
        }

        public ShareVideoContent[] newArray(int i) {
            return new ShareVideoContent[i];
        }
    };
    private final String contentDescription;
    private final String contentTitle;
    private final SharePhoto previewPhoto;
    private final ShareVideo video;

    public static final class Builder extends com.facebook.share.model.ShareContent.Builder<ShareVideoContent, Builder> {
        /* access modifiers changed from: private */
        public String contentDescription;
        /* access modifiers changed from: private */
        public String contentTitle;
        /* access modifiers changed from: private */
        public SharePhoto previewPhoto;
        /* access modifiers changed from: private */
        public ShareVideo video;

        public Builder setContentDescription(@Nullable String str) {
            this.contentDescription = str;
            return this;
        }

        public Builder setContentTitle(@Nullable String str) {
            this.contentTitle = str;
            return this;
        }

        public Builder setPreviewPhoto(@Nullable SharePhoto sharePhoto) {
            SharePhoto sharePhoto2;
            if (sharePhoto == null) {
                sharePhoto2 = null;
            } else {
                sharePhoto2 = new com.facebook.share.model.SharePhoto.Builder().readFrom(sharePhoto).build();
            }
            this.previewPhoto = sharePhoto2;
            return this;
        }

        public Builder setVideo(@Nullable ShareVideo shareVideo) {
            if (shareVideo == null) {
                return this;
            }
            this.video = new com.facebook.share.model.ShareVideo.Builder().readFrom(shareVideo).build();
            return this;
        }

        public ShareVideoContent build() {
            return new ShareVideoContent(this);
        }

        public Builder readFrom(ShareVideoContent shareVideoContent) {
            if (shareVideoContent == null) {
                return this;
            }
            return ((Builder) super.readFrom(shareVideoContent)).setContentDescription(shareVideoContent.getContentDescription()).setContentTitle(shareVideoContent.getContentTitle()).setPreviewPhoto(shareVideoContent.getPreviewPhoto()).setVideo(shareVideoContent.getVideo());
        }
    }

    public int describeContents() {
        return 0;
    }

    private ShareVideoContent(Builder builder) {
        super((com.facebook.share.model.ShareContent.Builder) builder);
        this.contentDescription = builder.contentDescription;
        this.contentTitle = builder.contentTitle;
        this.previewPhoto = builder.previewPhoto;
        this.video = builder.video;
    }

    ShareVideoContent(Parcel parcel) {
        super(parcel);
        this.contentDescription = parcel.readString();
        this.contentTitle = parcel.readString();
        com.facebook.share.model.SharePhoto.Builder readFrom = new com.facebook.share.model.SharePhoto.Builder().readFrom(parcel);
        if (readFrom.getImageUrl() == null && readFrom.getBitmap() == null) {
            this.previewPhoto = null;
        } else {
            this.previewPhoto = readFrom.build();
        }
        this.video = new com.facebook.share.model.ShareVideo.Builder().readFrom(parcel).build();
    }

    @Nullable
    public String getContentDescription() {
        return this.contentDescription;
    }

    @Nullable
    public String getContentTitle() {
        return this.contentTitle;
    }

    @Nullable
    public SharePhoto getPreviewPhoto() {
        return this.previewPhoto;
    }

    @Nullable
    public ShareVideo getVideo() {
        return this.video;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.contentDescription);
        parcel.writeString(this.contentTitle);
        parcel.writeParcelable(this.previewPhoto, 0);
        parcel.writeParcelable(this.video, 0);
    }
}
