package com.google.android.gms.internal.icing;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.facebook.share.internal.MessengerShareContentUtility;
import com.google.android.gms.appindexing.AppIndexApi.AppIndexingLink;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.icing.zzak.zza;
import com.google.android.gms.internal.icing.zzak.zza.C0034zza;
import com.google.android.gms.internal.icing.zzak.zza.zzb;
import com.google.android.gms.internal.icing.zzak.zza.zzb.C0035zza;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.zip.CRC32;

@ShowFirstParty
@Class(creator = "UsageInfoCreator")
@Reserved({1000})
public final class zzx extends AbstractSafeParcelable {
    public static final Creator<zzx> CREATOR = new zzz();
    @Field(id = 1)
    private final zzj zzaj;
    @Field(id = 2)
    private final long zzak;
    @Field(id = 3)
    private int zzal;
    @Field(id = 4)
    private final String zzam;
    @Field(id = 5)
    private final zzg zzan;
    @Field(defaultValue = "false", id = 6)
    private final boolean zzao;
    @Field(defaultValue = "-1", id = 7)
    private int zzap;
    @Field(id = 8)
    private int zzaq;
    @Field(id = 9)
    private final String zzar;

    @VisibleForTesting
    public zzx(String str, Intent intent, String str2, Uri uri, String str3, List<AppIndexingLink> list, int i) {
        Intent intent2 = intent;
        String str4 = str2;
        Uri uri2 = uri;
        this(zza(str, intent), System.currentTimeMillis(), 0, null, zza(intent, str2, uri, null, list).zzb(), false, -1, 1, null);
    }

    @Constructor
    zzx(@Param(id = 1) zzj zzj, @Param(id = 2) long j, @Param(id = 3) int i, @Param(id = 4) String str, @Param(id = 5) zzg zzg, @Param(id = 6) boolean z, @Param(id = 7) int i2, @Param(id = 8) int i3, @Param(id = 9) String str2) {
        this.zzaj = zzj;
        this.zzak = j;
        this.zzal = i;
        this.zzam = str;
        this.zzan = zzg;
        this.zzao = z;
        this.zzap = i2;
        this.zzaq = i3;
        this.zzar = str2;
    }

    public static zzj zza(String str, Intent intent) {
        return new zzj(str, "", zza(intent));
    }

    private static zzl zza(String str, String str2) {
        return new zzl(str2, new zzt(str).zzb(true).zzc(), str);
    }

    private static String zza(Intent intent) {
        String uri = intent.toUri(1);
        CRC32 crc32 = new CRC32();
        try {
            crc32.update(uri.getBytes("UTF-8"));
            return Long.toHexString(crc32.getValue());
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e);
        }
    }

    @VisibleForTesting
    public static zzh zza(Intent intent, String str, Uri uri, String str2, List<AppIndexingLink> list) {
        zzh zzh = new zzh();
        zzh.zza(new zzl(str, new zzt("title").zzc(true).zzd("name").zzc(), "text1"));
        if (uri != null) {
            zzh.zza(new zzl(uri.toString(), new zzt(MessengerShareContentUtility.BUTTON_URL_TYPE).zzb(true).zzd("url").zzc()));
        }
        if (list != null) {
            C0034zza zzf = zza.zzf();
            zzb[] zzbArr = new zzb[list.size()];
            for (int i = 0; i < zzbArr.length; i++) {
                C0035zza zzh2 = zzb.zzh();
                AppIndexingLink appIndexingLink = (AppIndexingLink) list.get(i);
                zzh2.zzg(appIndexingLink.appIndexingUrl.toString()).zze(appIndexingLink.viewId);
                if (appIndexingLink.webUrl != null) {
                    zzh2.zzh(appIndexingLink.webUrl.toString());
                }
                zzbArr[i] = (zzb) ((zzdj) zzh2.zzca());
            }
            zzf.zzb(Arrays.asList(zzbArr));
            zzh.zza(new zzl(((zza) ((zzdj) zzf.zzca())).toByteArray(), new zzt("outlinks").zzb(true).zzd(".private:outLinks").zzc("blob").zzc()));
        }
        String action = intent.getAction();
        if (action != null) {
            zzh.zza(zza("intent_action", action));
        }
        String dataString = intent.getDataString();
        if (dataString != null) {
            zzh.zza(zza("intent_data", dataString));
        }
        ComponentName component = intent.getComponent();
        if (component != null) {
            zzh.zza(zza("intent_activity", component.getClassName()));
        }
        Bundle extras = intent.getExtras();
        if (extras != null) {
            String string = extras.getString("intent_extra_data_key");
            if (string != null) {
                zzh.zza(zza("intent_extra_data", string));
            }
        }
        return zzh.zza(str2).zza(true);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zzaj, i, false);
        SafeParcelWriter.writeLong(parcel, 2, this.zzak);
        SafeParcelWriter.writeInt(parcel, 3, this.zzal);
        SafeParcelWriter.writeString(parcel, 4, this.zzam, false);
        SafeParcelWriter.writeParcelable(parcel, 5, this.zzan, i, false);
        SafeParcelWriter.writeBoolean(parcel, 6, this.zzao);
        SafeParcelWriter.writeInt(parcel, 7, this.zzap);
        SafeParcelWriter.writeInt(parcel, 8, this.zzaq);
        SafeParcelWriter.writeString(parcel, 9, this.zzar, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final String toString() {
        return String.format(Locale.US, "UsageInfo[documentId=%s, timestamp=%d, usageType=%d, status=%d]", new Object[]{this.zzaj, Long.valueOf(this.zzak), Integer.valueOf(this.zzal), Integer.valueOf(this.zzaq)});
    }
}
