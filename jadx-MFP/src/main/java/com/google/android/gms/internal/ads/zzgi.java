package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public interface zzgi {
    public static final ByteBuffer zzabh = ByteBuffer.allocateDirect(0).order(ByteOrder.nativeOrder());

    void flush();

    boolean isActive();

    void reset();

    boolean zzb(int i, int i2, int i3) throws zzgj;

    boolean zzcj();

    int zzco();

    int zzcp();

    void zzcq();

    ByteBuffer zzcr();

    void zzi(ByteBuffer byteBuffer);
}
