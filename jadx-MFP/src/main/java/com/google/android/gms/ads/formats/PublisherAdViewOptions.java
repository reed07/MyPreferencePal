package com.google.android.gms.ads.formats;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.doubleclick.AppEventListener;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.internal.ads.zzark;
import com.google.android.gms.internal.ads.zzwh;
import com.google.android.gms.internal.ads.zzxt;
import com.google.android.gms.internal.ads.zzxu;

@zzark
@Class(creator = "PublisherAdViewOptionsCreator")
public final class PublisherAdViewOptions extends AbstractSafeParcelable {
    public static final Creator<PublisherAdViewOptions> CREATOR = new zzc();
    @Field(getter = "getManualImpressionsEnabled", id = 1)
    private final boolean zzbli;
    @Nullable
    @Field(getter = "getAppEventListenerBinder", id = 2, type = "android.os.IBinder")
    private final zzxt zzblj;
    @Nullable
    private AppEventListener zzblk;

    public static final class Builder {
        /* access modifiers changed from: private */
        public boolean zzbli = false;
        /* access modifiers changed from: private */
        public AppEventListener zzblk;

        public final Builder setManualImpressionsEnabled(boolean z) {
            this.zzbli = z;
            return this;
        }

        public final Builder setAppEventListener(AppEventListener appEventListener) {
            this.zzblk = appEventListener;
            return this;
        }

        public final PublisherAdViewOptions build() {
            return new PublisherAdViewOptions(this);
        }
    }

    private PublisherAdViewOptions(Builder builder) {
        this.zzbli = builder.zzbli;
        this.zzblk = builder.zzblk;
        AppEventListener appEventListener = this.zzblk;
        this.zzblj = appEventListener != null ? new zzwh(appEventListener) : null;
    }

    @Constructor
    PublisherAdViewOptions(@Param(id = 1) boolean z, @Nullable @Param(id = 2) IBinder iBinder) {
        this.zzbli = z;
        this.zzblj = iBinder != null ? zzxu.zzd(iBinder) : null;
    }

    @Nullable
    public final AppEventListener getAppEventListener() {
        return this.zzblk;
    }

    public final boolean getManualImpressionsEnabled() {
        return this.zzbli;
    }

    @Nullable
    public final zzxt zzib() {
        return this.zzblj;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBoolean(parcel, 1, getManualImpressionsEnabled());
        zzxt zzxt = this.zzblj;
        SafeParcelWriter.writeIBinder(parcel, 2, zzxt == null ? null : zzxt.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
