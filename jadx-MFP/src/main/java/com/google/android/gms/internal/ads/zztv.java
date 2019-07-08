package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.ParcelFileDescriptor.AutoCloseInputStream;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import java.io.InputStream;
import javax.annotation.concurrent.GuardedBy;

@zzark
@Class(creator = "CacheEntryParcelCreator")
@Reserved({1})
public final class zztv extends AbstractSafeParcelable {
    public static final Creator<zztv> CREATOR = new zztw();
    @Nullable
    @GuardedBy("this")
    @Field(getter = "getContentFileDescriptor", id = 2)
    private ParcelFileDescriptor zzbzu;

    public zztv() {
        this(null);
    }

    @Constructor
    public zztv(@Nullable @Param(id = 2) ParcelFileDescriptor parcelFileDescriptor) {
        this.zzbzu = parcelFileDescriptor;
    }

    public final synchronized boolean zzoe() {
        return this.zzbzu != null;
    }

    @Nullable
    public final synchronized InputStream zzof() {
        if (this.zzbzu == null) {
            return null;
        }
        AutoCloseInputStream autoCloseInputStream = new AutoCloseInputStream(this.zzbzu);
        this.zzbzu = null;
        return autoCloseInputStream;
    }

    private final synchronized ParcelFileDescriptor zzog() {
        return this.zzbzu;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, zzog(), i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
