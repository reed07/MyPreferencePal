package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.util.Base64;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@zzark
final class zzahs {
    final String zzboa;
    final zzwb zzbqo;
    final int zzdhj;

    static zzahs zzcc(String str) throws IOException {
        String[] split = str.split("\u0000");
        if (split.length == 3) {
            Parcel obtain = Parcel.obtain();
            try {
                String str2 = new String(Base64.decode(split[0], 0), "UTF-8");
                int parseInt = Integer.parseInt(split[1]);
                byte[] decode = Base64.decode(split[2], 0);
                obtain.unmarshall(decode, 0, decode.length);
                obtain.setDataPosition(0);
                zzahs zzahs = new zzahs((zzwb) zzwb.CREATOR.createFromParcel(obtain), str2, parseInt);
                obtain.recycle();
                return zzahs;
            } catch (ParseException | IllegalArgumentException | IllegalStateException e) {
                zzbv.zzlj().zza(e, "QueueSeed.decode");
                throw new IOException("Malformed QueueSeed encoding.", e);
            } catch (Throwable th) {
                obtain.recycle();
                throw th;
            }
        } else {
            throw new IOException("Incorrect field count for QueueSeed.");
        }
    }

    zzahs(zzaho zzaho) {
        this(zzaho.zztj(), zzaho.getAdUnitId(), zzaho.getNetworkType());
    }

    @VisibleForTesting
    private zzahs(zzwb zzwb, String str, int i) {
        this.zzbqo = zzwb;
        this.zzboa = str;
        this.zzdhj = i;
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: 0000 */
    public final String zzty() {
        Parcel obtain = Parcel.obtain();
        try {
            String encodeToString = Base64.encodeToString(this.zzboa.getBytes("UTF-8"), 0);
            String num = Integer.toString(this.zzdhj);
            this.zzbqo.writeToParcel(obtain, 0);
            String encodeToString2 = Base64.encodeToString(obtain.marshall(), 0);
            StringBuilder sb = new StringBuilder(String.valueOf(encodeToString).length() + 2 + String.valueOf(num).length() + String.valueOf(encodeToString2).length());
            sb.append(encodeToString);
            sb.append("\u0000");
            sb.append(num);
            sb.append("\u0000");
            sb.append(encodeToString2);
            String sb2 = sb.toString();
            obtain.recycle();
            return sb2;
        } catch (UnsupportedEncodingException unused) {
            zzaxz.e("QueueSeed encode failed because UTF-8 is not available.");
            obtain.recycle();
            return "";
        } catch (Throwable th) {
            obtain.recycle();
            throw th;
        }
    }
}
