package com.facebook.share.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;

public final class ShareOpenGraphContent extends ShareContent<ShareOpenGraphContent, Builder> {
    public static final Creator<ShareOpenGraphContent> CREATOR = new Creator<ShareOpenGraphContent>() {
        public ShareOpenGraphContent createFromParcel(Parcel parcel) {
            return new ShareOpenGraphContent(parcel);
        }

        public ShareOpenGraphContent[] newArray(int i) {
            return new ShareOpenGraphContent[i];
        }
    };
    private final ShareOpenGraphAction action;
    private final String previewPropertyName;

    public static final class Builder extends com.facebook.share.model.ShareContent.Builder<ShareOpenGraphContent, Builder> {
        /* access modifiers changed from: private */
        public ShareOpenGraphAction action;
        /* access modifiers changed from: private */
        public String previewPropertyName;

        public Builder setAction(@Nullable ShareOpenGraphAction shareOpenGraphAction) {
            ShareOpenGraphAction shareOpenGraphAction2;
            if (shareOpenGraphAction == null) {
                shareOpenGraphAction2 = null;
            } else {
                shareOpenGraphAction2 = new com.facebook.share.model.ShareOpenGraphAction.Builder().readFrom(shareOpenGraphAction).build();
            }
            this.action = shareOpenGraphAction2;
            return this;
        }

        public Builder setPreviewPropertyName(@Nullable String str) {
            this.previewPropertyName = str;
            return this;
        }

        public ShareOpenGraphContent build() {
            return new ShareOpenGraphContent(this);
        }

        public Builder readFrom(ShareOpenGraphContent shareOpenGraphContent) {
            if (shareOpenGraphContent == null) {
                return this;
            }
            return ((Builder) super.readFrom(shareOpenGraphContent)).setAction(shareOpenGraphContent.getAction()).setPreviewPropertyName(shareOpenGraphContent.getPreviewPropertyName());
        }
    }

    public int describeContents() {
        return 0;
    }

    private ShareOpenGraphContent(Builder builder) {
        super((com.facebook.share.model.ShareContent.Builder) builder);
        this.action = builder.action;
        this.previewPropertyName = builder.previewPropertyName;
    }

    ShareOpenGraphContent(Parcel parcel) {
        super(parcel);
        this.action = new com.facebook.share.model.ShareOpenGraphAction.Builder().readFrom(parcel).build();
        this.previewPropertyName = parcel.readString();
    }

    @Nullable
    public ShareOpenGraphAction getAction() {
        return this.action;
    }

    @Nullable
    public String getPreviewPropertyName() {
        return this.previewPropertyName;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(this.action, 0);
        parcel.writeString(this.previewPropertyName);
    }
}
