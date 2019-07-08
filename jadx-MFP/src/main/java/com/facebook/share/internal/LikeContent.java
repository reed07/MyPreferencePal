package com.facebook.share.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.facebook.share.model.ShareModel;
import com.facebook.share.model.ShareModelBuilder;

@Deprecated
public class LikeContent implements ShareModel {
    @Deprecated
    public static final Creator<LikeContent> CREATOR = new Creator<LikeContent>() {
        public LikeContent createFromParcel(Parcel parcel) {
            return new LikeContent(parcel);
        }

        public LikeContent[] newArray(int i) {
            return new LikeContent[i];
        }
    };
    private final String objectId;
    private final String objectType;

    @Deprecated
    public static class Builder implements ShareModelBuilder<LikeContent, Builder> {
        /* access modifiers changed from: private */
        public String objectId;
        /* access modifiers changed from: private */
        public String objectType;

        @Deprecated
        public Builder setObjectId(String str) {
            this.objectId = str;
            return this;
        }

        @Deprecated
        public Builder setObjectType(String str) {
            this.objectType = str;
            return this;
        }

        @Deprecated
        public LikeContent build() {
            return new LikeContent(this);
        }

        @Deprecated
        public Builder readFrom(LikeContent likeContent) {
            if (likeContent == null) {
                return this;
            }
            return setObjectId(likeContent.getObjectId()).setObjectType(likeContent.getObjectType());
        }
    }

    @Deprecated
    public int describeContents() {
        return 0;
    }

    private LikeContent(Builder builder) {
        this.objectId = builder.objectId;
        this.objectType = builder.objectType;
    }

    @Deprecated
    LikeContent(Parcel parcel) {
        this.objectId = parcel.readString();
        this.objectType = parcel.readString();
    }

    @Deprecated
    public String getObjectId() {
        return this.objectId;
    }

    @Deprecated
    public String getObjectType() {
        return this.objectType;
    }

    @Deprecated
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.objectId);
        parcel.writeString(this.objectType);
    }
}
