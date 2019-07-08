package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbpl;
import com.google.android.gms.internal.ads.zzbpm;
import java.io.IOException;

public abstract class zzbpl<MessageType extends zzbpl<MessageType, BuilderType>, BuilderType extends zzbpm<MessageType, BuilderType>> implements zzbsl {
    private static boolean zzfky = false;
    protected int zzfkx = 0;

    public final zzbpu zzakf() {
        try {
            zzbqb zzen = zzbpu.zzen(zzamj());
            zzb(zzen.zzakt());
            return zzen.zzaks();
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

    public final byte[] toByteArray() {
        try {
            byte[] bArr = new byte[zzamj()];
            zzbqk zzt = zzbqk.zzt(bArr);
            zzb(zzt);
            zzt.zzalv();
            return bArr;
        } catch (IOException e) {
            String str = "byte array";
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
    public int zzakg() {
        throw new UnsupportedOperationException();
    }

    /* access modifiers changed from: 0000 */
    public void zzei(int i) {
        throw new UnsupportedOperationException();
    }
}
