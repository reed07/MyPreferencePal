package com.google.android.gms.internal.ads;

import android.content.res.Resources;
import android.os.Bundle;
import com.google.ads.mediation.inmobi.InMobiNetworkValues;
import com.google.android.gms.ads.impl.R;
import com.google.android.gms.ads.internal.zzbv;
import com.myfitnesspal.shared.db.table.ImagesTable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.json.JSONException;
import org.json.JSONObject;

@zzark
public final class zzaqq implements zzaqe<zzabs> {
    private final boolean zzdmt;
    private final boolean zzdut;
    private final boolean zzduu;

    public zzaqq(boolean z, boolean z2, boolean z3) {
        this.zzdut = z;
        this.zzduu = z2;
        this.zzdmt = z3;
    }

    public final /* synthetic */ zzacf zza(zzapw zzapw, JSONObject jSONObject) throws JSONException, InterruptedException, ExecutionException {
        String str;
        zzapw zzapw2 = zzapw;
        JSONObject jSONObject2 = jSONObject;
        List<zzbcb> zza = zzapw.zza(jSONObject, ImagesTable.TABLE_NAME, false, this.zzdut, this.zzduu);
        zzbcb zza2 = zzapw2.zza(jSONObject2, "app_icon", true, this.zzdut);
        zzbcb zzc = zzapw2.zzc(jSONObject2, "video");
        zzbcb zzg = zzapw.zzg(jSONObject);
        ArrayList arrayList = new ArrayList();
        for (zzbcb zzbcb : zza) {
            arrayList.add((zzabr) zzbcb.get());
        }
        zzbgg zzc2 = zzapw.zzc(zzc);
        String string = jSONObject2.getString("headline");
        if (this.zzdmt) {
            Resources resources = zzbv.zzlj().getResources();
            str = resources != null ? resources.getString(R.string.s7) : "Test Ad";
            if (string != null) {
                StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 3 + String.valueOf(string).length());
                sb.append(str);
                sb.append(" : ");
                sb.append(string);
                str = sb.toString();
            }
        } else {
            str = string;
        }
        zzabs zzabs = new zzabs(str, arrayList, jSONObject2.getString("body"), (zzadb) zza2.get(), jSONObject2.getString("call_to_action"), jSONObject2.optDouble(InMobiNetworkValues.RATING, -1.0d), jSONObject2.optString("store"), jSONObject2.optString("price"), (zzabm) zzg.get(), new Bundle(), zzc2 != null ? zzc2.zzabu() : null, zzc2 != null ? zzc2.getView() : null, null, null);
        return zzabs;
    }
}
