package com.facebook.share.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.facebook.internal.NativeProtocol;

public final class ShareOpenGraphObject extends ShareOpenGraphValueContainer<ShareOpenGraphObject, Builder> {
    public static final Creator<ShareOpenGraphObject> CREATOR = new Creator<ShareOpenGraphObject>() {
        public ShareOpenGraphObject createFromParcel(Parcel parcel) {
            return new ShareOpenGraphObject(parcel);
        }

        public ShareOpenGraphObject[] newArray(int i) {
            return new ShareOpenGraphObject[i];
        }
    };

    public static final class Builder extends com.facebook.share.model.ShareOpenGraphValueContainer.Builder<ShareOpenGraphObject, Builder> {
        public Builder() {
            putBoolean(NativeProtocol.OPEN_GRAPH_CREATE_OBJECT_KEY, true);
        }

        public ShareOpenGraphObject build() {
            return new ShareOpenGraphObject(this);
        }

        /* access modifiers changed from: 0000 */
        public Builder readFrom(Parcel parcel) {
            return (Builder) readFrom((ShareOpenGraphObject) parcel.readParcelable(ShareOpenGraphObject.class.getClassLoader()));
        }
    }

    private ShareOpenGraphObject(Builder builder) {
        super((com.facebook.share.model.ShareOpenGraphValueContainer.Builder<P, E>) builder);
    }

    ShareOpenGraphObject(Parcel parcel) {
        super(parcel);
    }
}
