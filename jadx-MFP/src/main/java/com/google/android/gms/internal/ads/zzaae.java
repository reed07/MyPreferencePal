package com.google.android.gms.internal.ads;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import org.json.JSONObject;

final class zzaae extends zzaac<Integer> {
    zzaae(int i, String str, Integer num) {
        super(i, str, num, null);
    }

    public final /* synthetic */ void zza(Editor editor, Object obj) {
        editor.putInt(getKey(), ((Integer) obj).intValue());
    }

    public final /* synthetic */ Object zzb(JSONObject jSONObject) {
        return Integer.valueOf(jSONObject.optInt(getKey(), ((Integer) zzqv()).intValue()));
    }

    public final /* synthetic */ Object zza(SharedPreferences sharedPreferences) {
        return Integer.valueOf(sharedPreferences.getInt(getKey(), ((Integer) zzqv()).intValue()));
    }
}
