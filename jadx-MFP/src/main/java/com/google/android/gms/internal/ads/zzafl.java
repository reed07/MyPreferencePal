package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import java.util.Map;
import java.util.Map.Entry;

@zzark
@Class(creator = "HttpRequestParcelCreator")
public final class zzafl extends AbstractSafeParcelable {
    public static final Creator<zzafl> CREATOR = new zzafm();
    @Field(id = 1)
    private final String url;
    @Field(id = 2)
    private final String[] zzdgi;
    @Field(id = 3)
    private final String[] zzdgj;

    @Constructor
    zzafl(@Param(id = 1) String str, @Param(id = 2) String[] strArr, @Param(id = 3) String[] strArr2) {
        this.url = str;
        this.zzdgi = strArr;
        this.zzdgj = strArr2;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.url, false);
        SafeParcelWriter.writeStringArray(parcel, 2, this.zzdgi, false);
        SafeParcelWriter.writeStringArray(parcel, 3, this.zzdgj, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public static zzafl zzh(zzr zzr) throws zza {
        Map headers = zzr.getHeaders();
        int size = headers.size();
        String[] strArr = new String[size];
        String[] strArr2 = new String[size];
        int i = 0;
        for (Entry entry : headers.entrySet()) {
            strArr[i] = (String) entry.getKey();
            strArr2[i] = (String) entry.getValue();
            i++;
        }
        return new zzafl(zzr.getUrl(), strArr, strArr2);
    }
}
