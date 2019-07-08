package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import org.json.JSONArray;
import org.json.JSONException;

@zzark
@Class(creator = "RewardItemParcelCreator")
@Reserved({1})
public final class zzawd extends AbstractSafeParcelable {
    public static final Creator<zzawd> CREATOR = new zzawe();
    @Field(id = 2)
    public final String type;
    @Field(id = 3)
    public final int zzefo;

    public zzawd(RewardItem rewardItem) {
        this(rewardItem.getType(), rewardItem.getAmount());
    }

    @Constructor
    public zzawd(@Param(id = 2) String str, @Param(id = 3) int i) {
        this.type = str;
        this.zzefo = i;
    }

    @Nullable
    public static zzawd zza(JSONArray jSONArray) throws JSONException {
        if (jSONArray == null || jSONArray.length() == 0) {
            return null;
        }
        return new zzawd(jSONArray.getJSONObject(0).optString("rb_type"), jSONArray.getJSONObject(0).optInt("rb_amount"));
    }

    @Nullable
    public static zzawd zzdh(@Nullable String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return zza(new JSONArray(str));
        } catch (JSONException unused) {
            return null;
        }
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.type, false);
        SafeParcelWriter.writeInt(parcel, 3, this.zzefo);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final boolean equals(Object obj) {
        if (obj == null || !(obj instanceof zzawd)) {
            return false;
        }
        zzawd zzawd = (zzawd) obj;
        if (!Objects.equal(this.type, zzawd.type) || !Objects.equal(Integer.valueOf(this.zzefo), Integer.valueOf(zzawd.zzefo))) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hashCode(this.type, Integer.valueOf(this.zzefo));
    }
}
