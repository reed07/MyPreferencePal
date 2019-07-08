package com.google.android.gms.internal.ads;

import android.content.SharedPreferences.Editor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.json.JSONObject;

@zzark
public final class zzaai {
    private final Collection<zzaac<?>> zzcnk = new ArrayList();
    private final Collection<zzaac<String>> zzcnl = new ArrayList();
    private final Collection<zzaac<String>> zzcnm = new ArrayList();

    public final void zza(zzaac zzaac) {
        this.zzcnk.add(zzaac);
    }

    public final void zzb(zzaac<String> zzaac) {
        this.zzcnl.add(zzaac);
    }

    public final void zzc(zzaac<String> zzaac) {
        this.zzcnm.add(zzaac);
    }

    public final void zza(Editor editor, int i, JSONObject jSONObject) {
        for (zzaac zzaac : this.zzcnk) {
            if (zzaac.getSource() == 1) {
                zzaac.zza(editor, zzaac.zzb(jSONObject));
            }
        }
        if (jSONObject != null) {
            editor.putString("flag_configuration", jSONObject.toString());
        } else {
            zzbbd.e("Flag Json is null.");
        }
    }

    public final List<String> zzqw() {
        ArrayList arrayList = new ArrayList();
        for (zzaac zzd : this.zzcnl) {
            String str = (String) zzwu.zzpz().zzd(zzd);
            if (str != null) {
                arrayList.add(str);
            }
        }
        return arrayList;
    }

    public final List<String> zzqx() {
        List<String> zzqw = zzqw();
        for (zzaac zzd : this.zzcnm) {
            String str = (String) zzwu.zzpz().zzd(zzd);
            if (str != null) {
                zzqw.add(str);
            }
        }
        return zzqw;
    }
}
