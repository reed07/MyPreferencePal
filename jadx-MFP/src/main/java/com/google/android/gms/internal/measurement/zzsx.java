package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzsx;
import com.google.android.gms.internal.measurement.zzsy;
import java.io.IOException;

public abstract class zzsx<MessageType extends zzsx<MessageType, BuilderType>, BuilderType extends zzsy<MessageType, BuilderType>> implements zzvv {
    private static boolean zzbtl = false;
    protected int zzbtk = 0;

    public final zzte zztw() {
        try {
            zztm zzao = zzte.zzao(zzvx());
            zzb(zzao.zzui());
            return zzao.zzuh();
        } catch (IOException e) {
            String str = "ByteString";
            String name = getClass().getName();
            StringBuilder sb = new StringBuilder(String.valueOf(name).length() + 62 + String.valueOf(str).length());
            sb.append("Serializing ");
            sb.append(name);
            sb.append(" to a ");
            sb.append(str);
            sb.append(" threw an IOException (should never happen).");
            throw new RuntimeException(sb.toString(), e);
        }
    }

    /* access modifiers changed from: 0000 */
    public int zztx() {
        throw new UnsupportedOperationException();
    }

    /* access modifiers changed from: 0000 */
    public void zzai(int i) {
        throw new UnsupportedOperationException();
    }
}
