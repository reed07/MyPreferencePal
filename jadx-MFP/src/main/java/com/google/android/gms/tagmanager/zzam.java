package com.google.android.gms.tagmanager;

import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zza;
import com.google.android.gms.internal.measurement.zzb;
import com.google.android.gms.internal.measurement.zzp;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

@VisibleForTesting
final class zzam extends zzbq {
    private static final String ID = zza.FUNCTION_CALL.toString();
    private static final String zzazk = zzb.ADDITIONAL_PARAMS.toString();
    private static final String zzbaz = zzb.FUNCTION_CALL_NAME.toString();
    private final zzan zzbba;

    public zzam(zzan zzan) {
        super(ID, zzbaz);
        this.zzbba = zzan;
    }

    public final boolean zznk() {
        return false;
    }

    public final zzp zzc(Map<String, zzp> map) {
        String zzc = zzgj.zzc((zzp) map.get(zzbaz));
        HashMap hashMap = new HashMap();
        zzp zzp = (zzp) map.get(zzazk);
        if (zzp != null) {
            Object zzh = zzgj.zzh(zzp);
            if (!(zzh instanceof Map)) {
                zzdi.zzab("FunctionCallMacro: expected ADDITIONAL_PARAMS to be a map.");
                return zzgj.zzqq();
            }
            for (Entry entry : ((Map) zzh).entrySet()) {
                hashMap.put(entry.getKey().toString(), entry.getValue());
            }
        }
        try {
            return zzgj.zzj(this.zzbba.zza(zzc, hashMap));
        } catch (Exception e) {
            String message = e.getMessage();
            StringBuilder sb = new StringBuilder(String.valueOf(zzc).length() + 34 + String.valueOf(message).length());
            sb.append("Custom macro/tag ");
            sb.append(zzc);
            sb.append(" threw exception ");
            sb.append(message);
            zzdi.zzab(sb.toString());
            return zzgj.zzqq();
        }
    }
}
