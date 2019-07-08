package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzbvc {
    public static final int[] zzfsg = new int[0];
    private static final int zzfwu = 11;
    private static final int zzfwv = 12;
    private static final int zzfww = 16;
    private static final int zzfwx = 26;
    public static final long[] zzfwy = new long[0];
    private static final float[] zzfwz = new float[0];
    private static final double[] zzfxa = new double[0];
    private static final boolean[] zzfxb = new boolean[0];
    public static final String[] zzfxc = new String[0];
    public static final byte[][] zzfxd = new byte[0][];
    public static final byte[] zzfxe = new byte[0];

    public static final int zzb(zzbuq zzbuq, int i) throws IOException {
        int position = zzbuq.getPosition();
        zzbuq.zzep(i);
        int i2 = 1;
        while (zzbuq.zzaku() == i) {
            zzbuq.zzep(i);
            i2++;
        }
        zzbuq.zzan(position, i);
        return i2;
    }
}
