package com.google.android.gms.internal.ads;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import java.util.List;

@zzark
@Class(creator = "CacheOfferingCreator")
@Reserved({1})
public final class zzty extends AbstractSafeParcelable {
    public static final Creator<zzty> CREATOR = new zztz();
    @Nullable
    @Field(id = 2)
    public final String url;
    @Field(id = 3)
    private final long zzbzv;
    @Field(id = 4)
    private final String zzbzw;
    @Field(id = 5)
    private final String zzbzx;
    @Field(id = 6)
    private final String zzbzy;
    @Field(id = 7)
    private final Bundle zzbzz;
    @Field(id = 8)
    public final boolean zzcaa;
    @Field(id = 9)
    public long zzcab;

    @Nullable
    public static zzty zzbb(String str) {
        return zzd(Uri.parse(str));
    }

    @Nullable
    public static zzty zzd(Uri uri) {
        long j;
        try {
            if (!"gcache".equals(uri.getScheme())) {
                return null;
            }
            List pathSegments = uri.getPathSegments();
            if (pathSegments.size() != 2) {
                int size = pathSegments.size();
                StringBuilder sb = new StringBuilder(62);
                sb.append("Expected 2 path parts for namespace and id, found :");
                sb.append(size);
                zzaxz.zzeo(sb.toString());
                return null;
            }
            String str = (String) pathSegments.get(0);
            String str2 = (String) pathSegments.get(1);
            String host = uri.getHost();
            String queryParameter = uri.getQueryParameter("url");
            boolean equals = AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(uri.getQueryParameter("read_only"));
            String queryParameter2 = uri.getQueryParameter("expiration");
            if (queryParameter2 == null) {
                j = 0;
            } else {
                j = Long.parseLong(queryParameter2);
            }
            long j2 = j;
            Bundle bundle = new Bundle();
            for (String str3 : zzbv.zzlh().zzh(uri)) {
                if (str3.startsWith("tag.")) {
                    bundle.putString(str3.substring(4), uri.getQueryParameter(str3));
                }
            }
            zzty zzty = new zzty(queryParameter, j2, host, str, str2, bundle, equals, 0);
            return zzty;
        } catch (NullPointerException | NumberFormatException e) {
            zzaxz.zzc("Unable to parse Uri into cache offering.", e);
            return null;
        }
    }

    @Constructor
    zzty(@Nullable @Param(id = 2) String str, @Param(id = 3) long j, @Param(id = 4) String str2, @Param(id = 5) String str3, @Param(id = 6) String str4, @Param(id = 7) Bundle bundle, @Param(id = 8) boolean z, @Param(id = 9) long j2) {
        this.url = str;
        this.zzbzv = j;
        if (str2 == null) {
            str2 = "";
        }
        this.zzbzw = str2;
        if (str3 == null) {
            str3 = "";
        }
        this.zzbzx = str3;
        if (str4 == null) {
            str4 = "";
        }
        this.zzbzy = str4;
        if (bundle == null) {
            bundle = new Bundle();
        }
        this.zzbzz = bundle;
        this.zzcaa = z;
        this.zzcab = j2;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.url, false);
        SafeParcelWriter.writeLong(parcel, 3, this.zzbzv);
        SafeParcelWriter.writeString(parcel, 4, this.zzbzw, false);
        SafeParcelWriter.writeString(parcel, 5, this.zzbzx, false);
        SafeParcelWriter.writeString(parcel, 6, this.zzbzy, false);
        SafeParcelWriter.writeBundle(parcel, 7, this.zzbzz, false);
        SafeParcelWriter.writeBoolean(parcel, 8, this.zzcaa);
        SafeParcelWriter.writeLong(parcel, 9, this.zzcab);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
