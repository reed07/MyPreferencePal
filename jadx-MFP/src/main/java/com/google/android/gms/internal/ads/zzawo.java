package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

@zzark
@Class(creator = "SafeBrowsingConfigParcelCreator")
@Reserved({1})
public final class zzawo extends AbstractSafeParcelable {
    public static final Creator<zzawo> CREATOR = new zzawp();
    @Field(id = 2)
    public final String zzegh;
    @Field(id = 3)
    public final String zzegi;
    @Field(id = 4)
    public final boolean zzegj;
    @Field(id = 5)
    public final boolean zzegk;
    @Field(id = 6)
    public final List<String> zzegl;
    @Field(id = 7)
    public final boolean zzegm;
    @Field(id = 8)
    public final boolean zzegn;
    @Field(id = 9)
    public final List<String> zzego;

    @Constructor
    public zzawo(@Param(id = 2) String str, @Param(id = 3) String str2, @Param(id = 4) boolean z, @Param(id = 5) boolean z2, @Param(id = 6) List<String> list, @Param(id = 7) boolean z3, @Param(id = 8) boolean z4, @Param(id = 9) List<String> list2) {
        this.zzegh = str;
        this.zzegi = str2;
        this.zzegj = z;
        this.zzegk = z2;
        this.zzegl = list;
        this.zzegm = z3;
        this.zzegn = z4;
        if (list2 == null) {
            list2 = new ArrayList<>();
        }
        this.zzego = list2;
    }

    @Nullable
    public static zzawo zzo(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        zzawo zzawo = new zzawo(jSONObject.optString("click_string", ""), jSONObject.optString("report_url", ""), jSONObject.optBoolean("rendered_ad_enabled", false), jSONObject.optBoolean("non_malicious_reporting_enabled", false), zzbac.zza(jSONObject.optJSONArray("allowed_headers"), null), jSONObject.optBoolean("protection_enabled", false), jSONObject.optBoolean("malicious_reporting_enabled", false), zzbac.zza(jSONObject.optJSONArray("webview_permissions"), null));
        return zzawo;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzegh, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzegi, false);
        SafeParcelWriter.writeBoolean(parcel, 4, this.zzegj);
        SafeParcelWriter.writeBoolean(parcel, 5, this.zzegk);
        SafeParcelWriter.writeStringList(parcel, 6, this.zzegl, false);
        SafeParcelWriter.writeBoolean(parcel, 7, this.zzegm);
        SafeParcelWriter.writeBoolean(parcel, 8, this.zzegn);
        SafeParcelWriter.writeStringList(parcel, 9, this.zzego, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
