package com.google.android.exoplayer2.metadata.id3;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.exoplayer2.util.Util;

public final class UrlLinkFrame extends Id3Frame {
    public static final Creator<UrlLinkFrame> CREATOR = new Creator<UrlLinkFrame>() {
        public UrlLinkFrame createFromParcel(Parcel parcel) {
            return new UrlLinkFrame(parcel);
        }

        public UrlLinkFrame[] newArray(int i) {
            return new UrlLinkFrame[i];
        }
    };
    @Nullable
    public final String description;
    public final String url;

    public UrlLinkFrame(String str, @Nullable String str2, String str3) {
        super(str);
        this.description = str2;
        this.url = str3;
    }

    UrlLinkFrame(Parcel parcel) {
        super((String) Util.castNonNull(parcel.readString()));
        this.description = parcel.readString();
        this.url = (String) Util.castNonNull(parcel.readString());
    }

    public boolean equals(@Nullable Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        UrlLinkFrame urlLinkFrame = (UrlLinkFrame) obj;
        if (!this.id.equals(urlLinkFrame.id) || !Util.areEqual(this.description, urlLinkFrame.description) || !Util.areEqual(this.url, urlLinkFrame.url)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int hashCode = (527 + this.id.hashCode()) * 31;
        String str = this.description;
        int i = 0;
        int hashCode2 = (hashCode + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.url;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode2 + i;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.id);
        sb.append(": url=");
        sb.append(this.url);
        return sb.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.id);
        parcel.writeString(this.description);
        parcel.writeString(this.url);
    }
}
