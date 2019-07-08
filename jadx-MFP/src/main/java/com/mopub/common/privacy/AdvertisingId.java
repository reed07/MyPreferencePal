package com.mopub.common.privacy;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.mopub.common.Preconditions;
import java.io.Serializable;
import java.util.Calendar;
import java.util.UUID;

public class AdvertisingId implements Serializable {
    @NonNull
    final String mAdvertisingId;
    final boolean mDoNotTrack;
    @NonNull
    final Calendar mLastRotation = Calendar.getInstance();
    @NonNull
    final String mMopubId;

    AdvertisingId(@NonNull String str, @NonNull String str2, boolean z, long j) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(str);
        this.mAdvertisingId = str;
        this.mMopubId = str2;
        this.mDoNotTrack = z;
        this.mLastRotation.setTimeInMillis(j);
    }

    public String getIdentifier(boolean z) {
        return (this.mDoNotTrack || !z) ? this.mMopubId : this.mAdvertisingId;
    }

    @NonNull
    public String getIdWithPrefix(boolean z) {
        if (this.mDoNotTrack || !z || this.mAdvertisingId.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            sb.append("mopub:");
            sb.append(this.mMopubId);
            return sb.toString();
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("ifa:");
        sb2.append(this.mAdvertisingId);
        return sb2.toString();
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    public String getIfaWithPrefix() {
        if (TextUtils.isEmpty(this.mAdvertisingId)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("ifa:");
        sb.append(this.mAdvertisingId);
        return sb.toString();
    }

    public boolean isDoNotTrack() {
        return this.mDoNotTrack;
    }

    @NonNull
    static AdvertisingId generateExpiredAdvertisingId() {
        String str = "";
        AdvertisingId advertisingId = new AdvertisingId(str, generateIdString(), false, (Calendar.getInstance().getTimeInMillis() - 86400000) - 1);
        return advertisingId;
    }

    @NonNull
    static AdvertisingId generateFreshAdvertisingId() {
        String str = "";
        AdvertisingId advertisingId = new AdvertisingId(str, generateIdString(), false, Calendar.getInstance().getTimeInMillis());
        return advertisingId;
    }

    @NonNull
    static String generateIdString() {
        return UUID.randomUUID().toString();
    }

    /* access modifiers changed from: 0000 */
    public boolean isRotationRequired() {
        return Calendar.getInstance().getTimeInMillis() - this.mLastRotation.getTimeInMillis() >= 86400000;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("AdvertisingId{mLastRotation=");
        sb.append(this.mLastRotation);
        sb.append(", mAdvertisingId='");
        sb.append(this.mAdvertisingId);
        sb.append('\'');
        sb.append(", mMopubId='");
        sb.append(this.mMopubId);
        sb.append('\'');
        sb.append(", mDoNotTrack=");
        sb.append(this.mDoNotTrack);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AdvertisingId)) {
            return false;
        }
        AdvertisingId advertisingId = (AdvertisingId) obj;
        if (this.mDoNotTrack == advertisingId.mDoNotTrack && this.mAdvertisingId.equals(advertisingId.mAdvertisingId)) {
            return this.mMopubId.equals(advertisingId.mMopubId);
        }
        return false;
    }

    public int hashCode() {
        return (((this.mAdvertisingId.hashCode() * 31) + this.mMopubId.hashCode()) * 31) + (this.mDoNotTrack ? 1 : 0);
    }
}
