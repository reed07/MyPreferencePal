package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;

public final class zzbow implements zzbjx {
    private Mac zzfkb;
    private final int zzfkc;
    private final String zzfkd;
    private final Key zzfke;

    public zzbow(String str, Key key, int i) throws GeneralSecurityException {
        String str2;
        if (i >= 10) {
            char c = 65535;
            int hashCode = str.hashCode();
            if (hashCode != -1823053428) {
                if (hashCode != 392315118) {
                    if (hashCode == 392317873 && str.equals("HMACSHA512")) {
                        c = 2;
                    }
                } else if (str.equals("HMACSHA256")) {
                    c = 1;
                }
            } else if (str.equals("HMACSHA1")) {
                c = 0;
            }
            switch (c) {
                case 0:
                    if (i > 20) {
                        throw new InvalidAlgorithmParameterException("tag size too big");
                    }
                    break;
                case 1:
                    if (i > 32) {
                        throw new InvalidAlgorithmParameterException("tag size too big");
                    }
                    break;
                case 2:
                    if (i > 64) {
                        throw new InvalidAlgorithmParameterException("tag size too big");
                    }
                    break;
                default:
                    String str3 = "unknown Hmac algorithm: ";
                    String valueOf = String.valueOf(str);
                    if (valueOf.length() != 0) {
                        str2 = str3.concat(valueOf);
                    } else {
                        str2 = new String(str3);
                    }
                    throw new NoSuchAlgorithmException(str2);
            }
            this.zzfkd = str;
            this.zzfkc = i;
            this.zzfke = key;
            this.zzfkb = (Mac) zzbol.zzfjs.zzfu(str);
            this.zzfkb.init(key);
            return;
        }
        throw new InvalidAlgorithmParameterException("tag size too small, need at least 10 bytes");
    }

    public final byte[] zzj(byte[] bArr) throws GeneralSecurityException {
        Mac mac;
        try {
            mac = (Mac) this.zzfkb.clone();
        } catch (CloneNotSupportedException unused) {
            mac = (Mac) zzbol.zzfjs.zzfu(this.zzfkd);
            mac.init(this.zzfke);
        }
        mac.update(bArr);
        byte[] bArr2 = new byte[this.zzfkc];
        System.arraycopy(mac.doFinal(), 0, bArr2, 0, this.zzfkc);
        return bArr2;
    }
}
