package com.facebook.share.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class ShareMessengerOpenGraphMusicTemplateContent extends ShareContent<ShareMessengerOpenGraphMusicTemplateContent, Builder> {
    public static final Creator<ShareMessengerOpenGraphMusicTemplateContent> CREATOR = new Creator<ShareMessengerOpenGraphMusicTemplateContent>() {
        public ShareMessengerOpenGraphMusicTemplateContent createFromParcel(Parcel parcel) {
            return new ShareMessengerOpenGraphMusicTemplateContent(parcel);
        }

        public ShareMessengerOpenGraphMusicTemplateContent[] newArray(int i) {
            return new ShareMessengerOpenGraphMusicTemplateContent[i];
        }
    };
    private final ShareMessengerActionButton button;
    private final Uri url;

    public static class Builder extends com.facebook.share.model.ShareContent.Builder<ShareMessengerOpenGraphMusicTemplateContent, Builder> {
        /* access modifiers changed from: private */
        public ShareMessengerActionButton button;
        /* access modifiers changed from: private */
        public Uri url;

        public Builder setUrl(Uri uri) {
            this.url = uri;
            return this;
        }

        public Builder setButton(ShareMessengerActionButton shareMessengerActionButton) {
            this.button = shareMessengerActionButton;
            return this;
        }

        public Builder readFrom(ShareMessengerOpenGraphMusicTemplateContent shareMessengerOpenGraphMusicTemplateContent) {
            if (shareMessengerOpenGraphMusicTemplateContent == null) {
                return this;
            }
            return ((Builder) super.readFrom(shareMessengerOpenGraphMusicTemplateContent)).setUrl(shareMessengerOpenGraphMusicTemplateContent.getUrl()).setButton(shareMessengerOpenGraphMusicTemplateContent.getButton());
        }

        public ShareMessengerOpenGraphMusicTemplateContent build() {
            return new ShareMessengerOpenGraphMusicTemplateContent(this);
        }
    }

    public int describeContents() {
        return 0;
    }

    private ShareMessengerOpenGraphMusicTemplateContent(Builder builder) {
        super((com.facebook.share.model.ShareContent.Builder) builder);
        this.url = builder.url;
        this.button = builder.button;
    }

    ShareMessengerOpenGraphMusicTemplateContent(Parcel parcel) {
        super(parcel);
        this.url = (Uri) parcel.readParcelable(Uri.class.getClassLoader());
        this.button = (ShareMessengerActionButton) parcel.readParcelable(ShareMessengerActionButton.class.getClassLoader());
    }

    public Uri getUrl() {
        return this.url;
    }

    public ShareMessengerActionButton getButton() {
        return this.button;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.url, i);
        parcel.writeParcelable(this.button, i);
    }
}
