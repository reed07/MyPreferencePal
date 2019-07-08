package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzyl {
    public static final int[] zzcaq = new int[0];
    private static final int zzcfg = 11;
    private static final int zzcfh = 12;
    private static final int zzcfi = 16;
    private static final int zzcfj = 26;
    public static final long[] zzcfk = new long[0];
    private static final float[] zzcfl = new float[0];
    private static final double[] zzcfm = new double[0];
    private static final boolean[] zzcfn = new boolean[0];
    public static final String[] zzcfo = new String[0];
    private static final byte[][] zzcfp = new byte[0][];
    public static final byte[] zzcfq = new byte[0];

    public static final int zzb(zzxz zzxz, int i) throws IOException {
        int position = zzxz.getPosition();
        zzxz.zzaq(i);
        int i2 = 1;
        while (zzxz.zzuj() == i) {
            zzxz.zzaq(i);
            i2++;
        }
        zzxz.zzt(position, i);
        return i2;
    }
}
