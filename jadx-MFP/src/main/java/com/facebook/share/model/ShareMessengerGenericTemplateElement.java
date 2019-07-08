package com.facebook.share.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class ShareMessengerGenericTemplateElement implements ShareModel {
    public static final Creator<ShareMessengerGenericTemplateElement> CREATOR = new Creator<ShareMessengerGenericTemplateElement>() {
        public ShareMessengerGenericTemplateElement createFromParcel(Parcel parcel) {
            return new ShareMessengerGenericTemplateElement(parcel);
        }

        public ShareMessengerGenericTemplateElement[] newArray(int i) {
            return new ShareMessengerGenericTemplateElement[i];
        }
    };
    /* access modifiers changed from: private */
    public final ShareMessengerActionButton button;
    /* access modifiers changed from: private */
    public final ShareMessengerActionButton defaultAction;
    /* access modifiers changed from: private */
    public final Uri imageUrl;
    /* access modifiers changed from: private */
    public final String subtitle;
    /* access modifiers changed from: private */
    public final String title;

    public static class Builder implements ShareModelBuilder<ShareMessengerGenericTemplateElement, Builder> {
        /* access modifiers changed from: private */
        public ShareMessengerActionButton button;
        /* access modifiers changed from: private */
        public ShareMessengerActionButton defaultAction;
        /* access modifiers changed from: private */
        public Uri imageUrl;
        /* access modifiers changed from: private */
        public String subtitle;
        /* access modifiers changed from: private */
        public String title;

        public Builder setTitle(String str) {
            this.title = str;
            return this;
        }

        public Builder setSubtitle(String str) {
            this.subtitle = str;
            return this;
        }

        public Builder setImageUrl(Uri uri) {
            this.imageUrl = uri;
            return this;
        }

        public Builder setDefaultAction(ShareMessengerActionButton shareMessengerActionButton) {
            this.defaultAction = shareMessengerActionButton;
            return this;
        }

        public Builder setButton(ShareMessengerActionButton shareMessengerActionButton) {
            this.button = shareMessengerActionButton;
            return this;
        }

        public ShareMessengerGenericTemplateElement build() {
            return new ShareMessengerGenericTemplateElement(this);
        }

        public Builder readFrom(ShareMessengerGenericTemplateElement shareMessengerGenericTemplateElement) {
            if (shareMessengerGenericTemplateElement == null) {
                return this;
            }
            return setTitle(shareMessengerGenericTemplateElement.title).setSubtitle(shareMessengerGenericTemplateElement.subtitle).setImageUrl(shareMessengerGenericTemplateElement.imageUrl).setDefaultAction(shareMessengerGenericTemplateElement.defaultAction).setButton(shareMessengerGenericTemplateElement.button);
        }

        /* access modifiers changed from: 0000 */
        public Builder readFrom(Parcel parcel) {
            return readFrom((ShareMessengerGenericTemplateElement) parcel.readParcelable(ShareMessengerGenericTemplateElement.class.getClassLoader()));
        }
    }

    public int describeContents() {
        return 0;
    }

    private ShareMessengerGenericTemplateElement(Builder builder) {
        this.title = builder.title;
        this.subtitle = builder.subtitle;
        this.imageUrl = builder.imageUrl;
        this.defaultAction = builder.defaultAction;
        this.button = builder.button;
    }

    ShareMessengerGenericTemplateElement(Parcel parcel) {
        this.title = parcel.readString();
        this.subtitle = parcel.readString();
        this.imageUrl = (Uri) parcel.readParcelable(Uri.class.getClassLoader());
        this.defaultAction = (ShareMessengerActionButton) parcel.readParcelable(ShareMessengerActionButton.class.getClassLoader());
        this.button = (ShareMessengerActionButton) parcel.readParcelable(ShareMessengerActionButton.class.getClassLoader());
    }

    public String getTitle() {
        return this.title;
    }

    public String getSubtitle() {
        return this.subtitle;
    }

    public Uri getImageUrl() {
        return this.imageUrl;
    }

    public ShareMessengerActionButton getDefaultAction() {
        return this.defaultAction;
    }

    public ShareMessengerActionButton getButton() {
        return this.button;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.title);
        parcel.writeString(this.subtitle);
        parcel.writeParcelable(this.imageUrl, i);
        parcel.writeParcelable(this.defaultAction, i);
        parcel.writeParcelable(this.button, i);
    }
}
