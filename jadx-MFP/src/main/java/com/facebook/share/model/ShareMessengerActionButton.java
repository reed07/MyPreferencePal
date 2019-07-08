package com.facebook.share.model;

import android.os.Parcel;
import android.support.annotation.Nullable;

public abstract class ShareMessengerActionButton implements ShareModel {
    private final String title;

    public static abstract class Builder<M extends ShareMessengerActionButton, B extends Builder> implements ShareModelBuilder<M, B> {
        /* access modifiers changed from: private */
        public String title;

        public B setTitle(@Nullable String str) {
            this.title = str;
            return this;
        }

        public B readFrom(M m) {
            return m == null ? this : setTitle(m.getTitle());
        }
    }

    public int describeContents() {
        return 0;
    }

    protected ShareMessengerActionButton(Builder builder) {
        this.title = builder.title;
    }

    ShareMessengerActionButton(Parcel parcel) {
        this.title = parcel.readString();
    }

    public String getTitle() {
        return this.title;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.title);
    }
}
