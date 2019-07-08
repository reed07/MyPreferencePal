package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.util.Stack;

final class zzik implements zzin {
    private final byte[] zzahi = new byte[8];
    private final Stack<zzim> zzahj = new Stack<>();
    private final zziu zzahk = new zziu();
    private zzio zzahl;
    private int zzahm;
    private int zzahn;
    private long zzaho;

    zzik() {
    }

    public final void zza(zzio zzio) {
        this.zzahl = zzio;
    }

    public final void reset() {
        this.zzahm = 0;
        this.zzahj.clear();
        this.zzahk.reset();
    }

    public final boolean zzb(zzia zzia) throws IOException, InterruptedException {
        String str;
        double d;
        zzpo.checkState(this.zzahl != null);
        while (true) {
            if (this.zzahj.isEmpty() || zzia.getPosition() < ((zzim) this.zzahj.peek()).zzahp) {
                if (this.zzahm == 0) {
                    long zza = this.zzahk.zza(zzia, true, false, 4);
                    if (zza == -2) {
                        zzia.zzdx();
                        while (true) {
                            zzia.zza(this.zzahi, 0, 4);
                            int zzae = zziu.zzae(this.zzahi[0]);
                            if (zzae != -1 && zzae <= 4) {
                                int zza2 = (int) zziu.zza(this.zzahi, zzae, false);
                                if (this.zzahl.zzac(zza2)) {
                                    zzia.zzw(zzae);
                                    zza = (long) zza2;
                                }
                            }
                            zzia.zzw(1);
                        }
                    }
                    if (zza == -1) {
                        return false;
                    }
                    this.zzahn = (int) zza;
                    this.zzahm = 1;
                }
                if (this.zzahm == 1) {
                    this.zzaho = this.zzahk.zza(zzia, false, true, 8);
                    this.zzahm = 2;
                }
                int zzab = this.zzahl.zzab(this.zzahn);
                switch (zzab) {
                    case 0:
                        zzia.zzw((int) this.zzaho);
                        this.zzahm = 0;
                    case 1:
                        long position = zzia.getPosition();
                        this.zzahj.add(new zzim(this.zzahn, this.zzaho + position));
                        this.zzahl.zzd(this.zzahn, position, this.zzaho);
                        this.zzahm = 0;
                        return true;
                    case 2:
                        long j = this.zzaho;
                        if (j <= 8) {
                            this.zzahl.zzc(this.zzahn, zza(zzia, (int) j));
                            this.zzahm = 0;
                            return true;
                        }
                        StringBuilder sb = new StringBuilder(42);
                        sb.append("Invalid integer size: ");
                        sb.append(j);
                        throw new zzfx(sb.toString());
                    case 3:
                        long j2 = this.zzaho;
                        if (j2 <= 2147483647L) {
                            zzio zzio = this.zzahl;
                            int i = this.zzahn;
                            int i2 = (int) j2;
                            if (i2 == 0) {
                                str = "";
                            } else {
                                byte[] bArr = new byte[i2];
                                zzia.readFully(bArr, 0, i2);
                                str = new String(bArr);
                            }
                            zzio.zza(i, str);
                            this.zzahm = 0;
                            return true;
                        }
                        StringBuilder sb2 = new StringBuilder(41);
                        sb2.append("String element size: ");
                        sb2.append(j2);
                        throw new zzfx(sb2.toString());
                    case 4:
                        this.zzahl.zza(this.zzahn, (int) this.zzaho, zzia);
                        this.zzahm = 0;
                        return true;
                    case 5:
                        long j3 = this.zzaho;
                        if (j3 == 4 || j3 == 8) {
                            zzio zzio2 = this.zzahl;
                            int i3 = this.zzahn;
                            int i4 = (int) this.zzaho;
                            long zza3 = zza(zzia, i4);
                            if (i4 == 4) {
                                d = (double) Float.intBitsToFloat((int) zza3);
                            } else {
                                d = Double.longBitsToDouble(zza3);
                            }
                            zzio2.zza(i3, d);
                            this.zzahm = 0;
                            return true;
                        }
                        StringBuilder sb3 = new StringBuilder(40);
                        sb3.append("Invalid float size: ");
                        sb3.append(j3);
                        throw new zzfx(sb3.toString());
                    default:
                        StringBuilder sb4 = new StringBuilder(32);
                        sb4.append("Invalid element type ");
                        sb4.append(zzab);
                        throw new zzfx(sb4.toString());
                }
            } else {
                this.zzahl.zzad(((zzim) this.zzahj.pop()).zzahn);
                return true;
            }
        }
    }

    private final long zza(zzia zzia, int i) throws IOException, InterruptedException {
        zzia.readFully(this.zzahi, 0, i);
        long j = 0;
        for (int i2 = 0; i2 < i; i2++) {
            j = (j << 8) | ((long) (this.zzahi[i2] & 255));
        }
        return j;
    }
}
