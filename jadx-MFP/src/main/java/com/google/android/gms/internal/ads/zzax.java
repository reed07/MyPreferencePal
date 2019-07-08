package com.google.android.gms.internal.ads;

import com.brightcove.player.event.AbstractEvent;
import java.io.EOFException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class zzax implements zzaz {
    private static Logger zzcm = Logger.getLogger(zzax.class.getName());
    private ThreadLocal<ByteBuffer> zzcn = new zzay(this);

    public abstract zzbc zza(String str, byte[] bArr, String str2);

    public final zzbc zza(zzbwa zzbwa, zzbd zzbd) throws IOException {
        int read;
        long j;
        long j2;
        zzbwa zzbwa2 = zzbwa;
        zzbd zzbd2 = zzbd;
        long position = zzbwa.position();
        ((ByteBuffer) this.zzcn.get()).rewind().limit(8);
        do {
            read = zzbwa2.read((ByteBuffer) this.zzcn.get());
            if (read == 8) {
                ((ByteBuffer) this.zzcn.get()).rewind();
                long zza = zzbb.zza((ByteBuffer) this.zzcn.get());
                byte[] bArr = null;
                if (zza >= 8 || zza <= 1) {
                    String zzf = zzbb.zzf((ByteBuffer) this.zzcn.get());
                    if (zza == 1) {
                        ((ByteBuffer) this.zzcn.get()).limit(16);
                        zzbwa2.read((ByteBuffer) this.zzcn.get());
                        ((ByteBuffer) this.zzcn.get()).position(8);
                        j = zzbb.zzc((ByteBuffer) this.zzcn.get()) - 16;
                    } else {
                        j = zza == 0 ? zzbwa.size() - zzbwa.position() : zza - 8;
                    }
                    if (AbstractEvent.UUID.equals(zzf)) {
                        ((ByteBuffer) this.zzcn.get()).limit(((ByteBuffer) this.zzcn.get()).limit() + 16);
                        zzbwa2.read((ByteBuffer) this.zzcn.get());
                        bArr = new byte[16];
                        for (int position2 = ((ByteBuffer) this.zzcn.get()).position() - 16; position2 < ((ByteBuffer) this.zzcn.get()).position(); position2++) {
                            bArr[position2 - (((ByteBuffer) this.zzcn.get()).position() - 16)] = ((ByteBuffer) this.zzcn.get()).get(position2);
                        }
                        j2 = j - 16;
                    } else {
                        j2 = j;
                    }
                    zzbc zza2 = zza(zzf, bArr, zzbd2 instanceof zzbc ? ((zzbc) zzbd2).getType() : "");
                    zza2.zza(zzbd2);
                    ((ByteBuffer) this.zzcn.get()).rewind();
                    zza2.zza(zzbwa, (ByteBuffer) this.zzcn.get(), j2, this);
                    return zza2;
                }
                StringBuilder sb = new StringBuilder(80);
                sb.append("Plausibility check failed: size < 8 (size = ");
                sb.append(zza);
                sb.append("). Stop parsing!");
                zzcm.logp(Level.SEVERE, "com.coremedia.iso.AbstractBoxParser", "parseBox", sb.toString());
                return null;
            }
        } while (read >= 0);
        zzbwa2.zzaw(position);
        throw new EOFException();
    }
}
