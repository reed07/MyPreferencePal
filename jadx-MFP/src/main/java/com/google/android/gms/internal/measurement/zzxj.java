package com.google.android.gms.internal.measurement;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.AccessController;
import java.util.logging.Level;
import java.util.logging.Logger;
import libcore.io.Memory;
import sun.misc.Unsafe;

final class zzxj {
    private static final Logger logger = Logger.getLogger(zzxj.class.getName());
    private static final Class<?> zzbto = zztb.zzuc();
    private static final boolean zzbuo = zzyr();
    private static final Unsafe zzcar = zzyq();
    private static final boolean zzcco = zzn(Long.TYPE);
    private static final boolean zzccp = zzn(Integer.TYPE);
    private static final zzd zzccq;
    private static final boolean zzccr = zzys();
    /* access modifiers changed from: private */
    public static final long zzccs = ((long) zzl(byte[].class));
    private static final long zzcct = ((long) zzl(boolean[].class));
    private static final long zzccu = ((long) zzm(boolean[].class));
    private static final long zzccv = ((long) zzl(int[].class));
    private static final long zzccw = ((long) zzm(int[].class));
    private static final long zzccx = ((long) zzl(long[].class));
    private static final long zzccy = ((long) zzm(long[].class));
    private static final long zzccz = ((long) zzl(float[].class));
    private static final long zzcda = ((long) zzm(float[].class));
    private static final long zzcdb = ((long) zzl(double[].class));
    private static final long zzcdc = ((long) zzm(double[].class));
    private static final long zzcdd = ((long) zzl(Object[].class));
    private static final long zzcde = ((long) zzm(Object[].class));
    private static final long zzcdf;
    /* access modifiers changed from: private */
    public static final boolean zzcdg = (ByteOrder.nativeOrder() != ByteOrder.BIG_ENDIAN);

    static final class zza extends zzd {
        zza(Unsafe unsafe) {
            super(unsafe);
        }

        public final void zza(long j, byte b) {
            Memory.pokeByte((int) (j & -1), b);
        }

        public final byte zzy(Object obj, long j) {
            if (zzxj.zzcdg) {
                return zzxj.zzq(obj, j);
            }
            return zzxj.zzr(obj, j);
        }

        public final void zze(Object obj, long j, byte b) {
            if (zzxj.zzcdg) {
                zzxj.zza(obj, j, b);
            } else {
                zzxj.zzb(obj, j, b);
            }
        }

        public final boolean zzm(Object obj, long j) {
            if (zzxj.zzcdg) {
                return zzxj.zzs(obj, j);
            }
            return zzxj.zzt(obj, j);
        }

        public final void zza(Object obj, long j, boolean z) {
            if (zzxj.zzcdg) {
                zzxj.zzb(obj, j, z);
            } else {
                zzxj.zzc(obj, j, z);
            }
        }

        public final float zzn(Object obj, long j) {
            return Float.intBitsToFloat(zzk(obj, j));
        }

        public final void zza(Object obj, long j, float f) {
            zzb(obj, j, Float.floatToIntBits(f));
        }

        public final double zzo(Object obj, long j) {
            return Double.longBitsToDouble(zzl(obj, j));
        }

        public final void zza(Object obj, long j, double d) {
            zza(obj, j, Double.doubleToLongBits(d));
        }

        public final void zza(byte[] bArr, long j, long j2, long j3) {
            Memory.pokeByteArray((int) (j2 & -1), bArr, (int) j, (int) j3);
        }
    }

    static final class zzb extends zzd {
        zzb(Unsafe unsafe) {
            super(unsafe);
        }

        public final void zza(long j, byte b) {
            Memory.pokeByte(j, b);
        }

        public final byte zzy(Object obj, long j) {
            if (zzxj.zzcdg) {
                return zzxj.zzq(obj, j);
            }
            return zzxj.zzr(obj, j);
        }

        public final void zze(Object obj, long j, byte b) {
            if (zzxj.zzcdg) {
                zzxj.zza(obj, j, b);
            } else {
                zzxj.zzb(obj, j, b);
            }
        }

        public final boolean zzm(Object obj, long j) {
            if (zzxj.zzcdg) {
                return zzxj.zzs(obj, j);
            }
            return zzxj.zzt(obj, j);
        }

        public final void zza(Object obj, long j, boolean z) {
            if (zzxj.zzcdg) {
                zzxj.zzb(obj, j, z);
            } else {
                zzxj.zzc(obj, j, z);
            }
        }

        public final float zzn(Object obj, long j) {
            return Float.intBitsToFloat(zzk(obj, j));
        }

        public final void zza(Object obj, long j, float f) {
            zzb(obj, j, Float.floatToIntBits(f));
        }

        public final double zzo(Object obj, long j) {
            return Double.longBitsToDouble(zzl(obj, j));
        }

        public final void zza(Object obj, long j, double d) {
            zza(obj, j, Double.doubleToLongBits(d));
        }

        public final void zza(byte[] bArr, long j, long j2, long j3) {
            Memory.pokeByteArray(j2, bArr, (int) j, (int) j3);
        }
    }

    static final class zzc extends zzd {
        zzc(Unsafe unsafe) {
            super(unsafe);
        }

        public final void zza(long j, byte b) {
            this.zzcdh.putByte(j, b);
        }

        public final byte zzy(Object obj, long j) {
            return this.zzcdh.getByte(obj, j);
        }

        public final void zze(Object obj, long j, byte b) {
            this.zzcdh.putByte(obj, j, b);
        }

        public final boolean zzm(Object obj, long j) {
            return this.zzcdh.getBoolean(obj, j);
        }

        public final void zza(Object obj, long j, boolean z) {
            this.zzcdh.putBoolean(obj, j, z);
        }

        public final float zzn(Object obj, long j) {
            return this.zzcdh.getFloat(obj, j);
        }

        public final void zza(Object obj, long j, float f) {
            this.zzcdh.putFloat(obj, j, f);
        }

        public final double zzo(Object obj, long j) {
            return this.zzcdh.getDouble(obj, j);
        }

        public final void zza(Object obj, long j, double d) {
            this.zzcdh.putDouble(obj, j, d);
        }

        public final void zza(byte[] bArr, long j, long j2, long j3) {
            this.zzcdh.copyMemory(bArr, zzxj.zzccs + j, null, j2, j3);
        }
    }

    static abstract class zzd {
        Unsafe zzcdh;

        zzd(Unsafe unsafe) {
            this.zzcdh = unsafe;
        }

        public abstract void zza(long j, byte b);

        public abstract void zza(Object obj, long j, double d);

        public abstract void zza(Object obj, long j, float f);

        public abstract void zza(Object obj, long j, boolean z);

        public abstract void zza(byte[] bArr, long j, long j2, long j3);

        public abstract void zze(Object obj, long j, byte b);

        public abstract boolean zzm(Object obj, long j);

        public abstract float zzn(Object obj, long j);

        public abstract double zzo(Object obj, long j);

        public abstract byte zzy(Object obj, long j);

        public final int zzk(Object obj, long j) {
            return this.zzcdh.getInt(obj, j);
        }

        public final void zzb(Object obj, long j, int i) {
            this.zzcdh.putInt(obj, j, i);
        }

        public final long zzl(Object obj, long j) {
            return this.zzcdh.getLong(obj, j);
        }

        public final void zza(Object obj, long j, long j2) {
            this.zzcdh.putLong(obj, j, j2);
        }
    }

    private zzxj() {
    }

    static boolean zzyo() {
        return zzbuo;
    }

    static boolean zzyp() {
        return zzccr;
    }

    static <T> T zzk(Class<T> cls) {
        try {
            return zzcar.allocateInstance(cls);
        } catch (InstantiationException e) {
            throw new IllegalStateException(e);
        }
    }

    private static int zzl(Class<?> cls) {
        if (zzbuo) {
            return zzccq.zzcdh.arrayBaseOffset(cls);
        }
        return -1;
    }

    private static int zzm(Class<?> cls) {
        if (zzbuo) {
            return zzccq.zzcdh.arrayIndexScale(cls);
        }
        return -1;
    }

    static int zzk(Object obj, long j) {
        return zzccq.zzk(obj, j);
    }

    static void zzb(Object obj, long j, int i) {
        zzccq.zzb(obj, j, i);
    }

    static long zzl(Object obj, long j) {
        return zzccq.zzl(obj, j);
    }

    static void zza(Object obj, long j, long j2) {
        zzccq.zza(obj, j, j2);
    }

    static boolean zzm(Object obj, long j) {
        return zzccq.zzm(obj, j);
    }

    static void zza(Object obj, long j, boolean z) {
        zzccq.zza(obj, j, z);
    }

    static float zzn(Object obj, long j) {
        return zzccq.zzn(obj, j);
    }

    static void zza(Object obj, long j, float f) {
        zzccq.zza(obj, j, f);
    }

    static double zzo(Object obj, long j) {
        return zzccq.zzo(obj, j);
    }

    static void zza(Object obj, long j, double d) {
        zzccq.zza(obj, j, d);
    }

    static Object zzp(Object obj, long j) {
        return zzccq.zzcdh.getObject(obj, j);
    }

    static void zza(Object obj, long j, Object obj2) {
        zzccq.zzcdh.putObject(obj, j, obj2);
    }

    static byte zza(byte[] bArr, long j) {
        return zzccq.zzy(bArr, zzccs + j);
    }

    static void zza(byte[] bArr, long j, byte b) {
        zzccq.zze(bArr, zzccs + j, b);
    }

    static void zza(byte[] bArr, long j, long j2, long j3) {
        zzccq.zza(bArr, j, j2, j3);
    }

    static void zza(long j, byte b) {
        zzccq.zza(j, b);
    }

    static long zzb(ByteBuffer byteBuffer) {
        return zzccq.zzl(byteBuffer, zzcdf);
    }

    static Unsafe zzyq() {
        try {
            return (Unsafe) AccessController.doPrivileged(new zzxk());
        } catch (Throwable unused) {
            return null;
        }
    }

    private static boolean zzyr() {
        Unsafe unsafe = zzcar;
        if (unsafe == null) {
            return false;
        }
        try {
            Class cls = unsafe.getClass();
            cls.getMethod("objectFieldOffset", new Class[]{Field.class});
            cls.getMethod("arrayBaseOffset", new Class[]{Class.class});
            cls.getMethod("arrayIndexScale", new Class[]{Class.class});
            cls.getMethod("getInt", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putInt", new Class[]{Object.class, Long.TYPE, Integer.TYPE});
            cls.getMethod("getLong", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putLong", new Class[]{Object.class, Long.TYPE, Long.TYPE});
            cls.getMethod("getObject", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putObject", new Class[]{Object.class, Long.TYPE, Object.class});
            if (zztb.zzub()) {
                return true;
            }
            cls.getMethod("getByte", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putByte", new Class[]{Object.class, Long.TYPE, Byte.TYPE});
            cls.getMethod("getBoolean", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putBoolean", new Class[]{Object.class, Long.TYPE, Boolean.TYPE});
            cls.getMethod("getFloat", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putFloat", new Class[]{Object.class, Long.TYPE, Float.TYPE});
            cls.getMethod("getDouble", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putDouble", new Class[]{Object.class, Long.TYPE, Double.TYPE});
            return true;
        } catch (Throwable th) {
            String valueOf = String.valueOf(th);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 71);
            sb.append("platform method missing - proto runtime falling back to safer methods: ");
            sb.append(valueOf);
            logger.logp(Level.WARNING, "com.google.protobuf.UnsafeUtil", "supportsUnsafeArrayOperations", sb.toString());
            return false;
        }
    }

    private static boolean zzys() {
        Unsafe unsafe = zzcar;
        if (unsafe == null) {
            return false;
        }
        try {
            Class cls = unsafe.getClass();
            cls.getMethod("objectFieldOffset", new Class[]{Field.class});
            cls.getMethod("getLong", new Class[]{Object.class, Long.TYPE});
            if (zzyt() == null) {
                return false;
            }
            if (zztb.zzub()) {
                return true;
            }
            cls.getMethod("getByte", new Class[]{Long.TYPE});
            cls.getMethod("putByte", new Class[]{Long.TYPE, Byte.TYPE});
            cls.getMethod("getInt", new Class[]{Long.TYPE});
            cls.getMethod("putInt", new Class[]{Long.TYPE, Integer.TYPE});
            cls.getMethod("getLong", new Class[]{Long.TYPE});
            cls.getMethod("putLong", new Class[]{Long.TYPE, Long.TYPE});
            cls.getMethod("copyMemory", new Class[]{Long.TYPE, Long.TYPE, Long.TYPE});
            cls.getMethod("copyMemory", new Class[]{Object.class, Long.TYPE, Object.class, Long.TYPE, Long.TYPE});
            return true;
        } catch (Throwable th) {
            String valueOf = String.valueOf(th);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 71);
            sb.append("platform method missing - proto runtime falling back to safer methods: ");
            sb.append(valueOf);
            logger.logp(Level.WARNING, "com.google.protobuf.UnsafeUtil", "supportsUnsafeByteBufferOperations", sb.toString());
            return false;
        }
    }

    private static boolean zzn(Class<?> cls) {
        if (!zztb.zzub()) {
            return false;
        }
        try {
            Class<?> cls2 = zzbto;
            cls2.getMethod("peekLong", new Class[]{cls, Boolean.TYPE});
            cls2.getMethod("pokeLong", new Class[]{cls, Long.TYPE, Boolean.TYPE});
            cls2.getMethod("pokeInt", new Class[]{cls, Integer.TYPE, Boolean.TYPE});
            cls2.getMethod("peekInt", new Class[]{cls, Boolean.TYPE});
            cls2.getMethod("pokeByte", new Class[]{cls, Byte.TYPE});
            cls2.getMethod("peekByte", new Class[]{cls});
            cls2.getMethod("pokeByteArray", new Class[]{cls, byte[].class, Integer.TYPE, Integer.TYPE});
            cls2.getMethod("peekByteArray", new Class[]{cls, byte[].class, Integer.TYPE, Integer.TYPE});
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    private static Field zzyt() {
        if (zztb.zzub()) {
            Field zzb2 = zzb(Buffer.class, "effectiveDirectAddress");
            if (zzb2 != null) {
                return zzb2;
            }
        }
        Field zzb3 = zzb(Buffer.class, "address");
        if (zzb3 == null || zzb3.getType() != Long.TYPE) {
            return null;
        }
        return zzb3;
    }

    private static Field zzb(Class<?> cls, String str) {
        try {
            Field declaredField = cls.getDeclaredField(str);
            declaredField.setAccessible(true);
            return declaredField;
        } catch (Throwable unused) {
            return null;
        }
    }

    /* access modifiers changed from: private */
    public static byte zzq(Object obj, long j) {
        return (byte) (zzk(obj, -4 & j) >>> ((int) (((~j) & 3) << 3)));
    }

    /* access modifiers changed from: private */
    public static byte zzr(Object obj, long j) {
        return (byte) (zzk(obj, -4 & j) >>> ((int) ((j & 3) << 3)));
    }

    /* access modifiers changed from: private */
    public static void zza(Object obj, long j, byte b) {
        long j2 = -4 & j;
        int i = ((~((int) j)) & 3) << 3;
        int i2 = (255 & b) << i;
        zzb(obj, j2, i2 | (zzk(obj, j2) & (~(255 << i))));
    }

    /* access modifiers changed from: private */
    public static void zzb(Object obj, long j, byte b) {
        long j2 = -4 & j;
        int i = (((int) j) & 3) << 3;
        zzb(obj, j2, ((255 & b) << i) | (zzk(obj, j2) & (~(255 << i))));
    }

    /* access modifiers changed from: private */
    public static boolean zzs(Object obj, long j) {
        return zzq(obj, j) != 0;
    }

    /* access modifiers changed from: private */
    public static boolean zzt(Object obj, long j) {
        return zzr(obj, j) != 0;
    }

    /* access modifiers changed from: private */
    public static void zzb(Object obj, long j, boolean z) {
        zza(obj, j, z ? (byte) 1 : 0);
    }

    /* access modifiers changed from: private */
    public static void zzc(Object obj, long j, boolean z) {
        zzb(obj, j, z ? (byte) 1 : 0);
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x00f4  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x00f6  */
    static {
        /*
            java.lang.Class<com.google.android.gms.internal.measurement.zzxj> r0 = com.google.android.gms.internal.measurement.zzxj.class
            java.lang.String r0 = r0.getName()
            java.util.logging.Logger r0 = java.util.logging.Logger.getLogger(r0)
            logger = r0
            sun.misc.Unsafe r0 = zzyq()
            zzcar = r0
            java.lang.Class r0 = com.google.android.gms.internal.measurement.zztb.zzuc()
            zzbto = r0
            java.lang.Class r0 = java.lang.Long.TYPE
            boolean r0 = zzn(r0)
            zzcco = r0
            java.lang.Class r0 = java.lang.Integer.TYPE
            boolean r0 = zzn(r0)
            zzccp = r0
            sun.misc.Unsafe r0 = zzcar
            r1 = 0
            if (r0 != 0) goto L_0x002e
            goto L_0x0053
        L_0x002e:
            boolean r0 = com.google.android.gms.internal.measurement.zztb.zzub()
            if (r0 == 0) goto L_0x004c
            boolean r0 = zzcco
            if (r0 == 0) goto L_0x0040
            com.google.android.gms.internal.measurement.zzxj$zzb r1 = new com.google.android.gms.internal.measurement.zzxj$zzb
            sun.misc.Unsafe r0 = zzcar
            r1.<init>(r0)
            goto L_0x0053
        L_0x0040:
            boolean r0 = zzccp
            if (r0 == 0) goto L_0x0053
            com.google.android.gms.internal.measurement.zzxj$zza r1 = new com.google.android.gms.internal.measurement.zzxj$zza
            sun.misc.Unsafe r0 = zzcar
            r1.<init>(r0)
            goto L_0x0053
        L_0x004c:
            com.google.android.gms.internal.measurement.zzxj$zzc r1 = new com.google.android.gms.internal.measurement.zzxj$zzc
            sun.misc.Unsafe r0 = zzcar
            r1.<init>(r0)
        L_0x0053:
            zzccq = r1
            boolean r0 = zzys()
            zzccr = r0
            boolean r0 = zzyr()
            zzbuo = r0
            java.lang.Class<byte[]> r0 = byte[].class
            int r0 = zzl(r0)
            long r0 = (long) r0
            zzccs = r0
            java.lang.Class<boolean[]> r0 = boolean[].class
            int r0 = zzl(r0)
            long r0 = (long) r0
            zzcct = r0
            java.lang.Class<boolean[]> r0 = boolean[].class
            int r0 = zzm(r0)
            long r0 = (long) r0
            zzccu = r0
            java.lang.Class<int[]> r0 = int[].class
            int r0 = zzl(r0)
            long r0 = (long) r0
            zzccv = r0
            java.lang.Class<int[]> r0 = int[].class
            int r0 = zzm(r0)
            long r0 = (long) r0
            zzccw = r0
            java.lang.Class<long[]> r0 = long[].class
            int r0 = zzl(r0)
            long r0 = (long) r0
            zzccx = r0
            java.lang.Class<long[]> r0 = long[].class
            int r0 = zzm(r0)
            long r0 = (long) r0
            zzccy = r0
            java.lang.Class<float[]> r0 = float[].class
            int r0 = zzl(r0)
            long r0 = (long) r0
            zzccz = r0
            java.lang.Class<float[]> r0 = float[].class
            int r0 = zzm(r0)
            long r0 = (long) r0
            zzcda = r0
            java.lang.Class<double[]> r0 = double[].class
            int r0 = zzl(r0)
            long r0 = (long) r0
            zzcdb = r0
            java.lang.Class<double[]> r0 = double[].class
            int r0 = zzm(r0)
            long r0 = (long) r0
            zzcdc = r0
            java.lang.Class<java.lang.Object[]> r0 = java.lang.Object[].class
            int r0 = zzl(r0)
            long r0 = (long) r0
            zzcdd = r0
            java.lang.Class<java.lang.Object[]> r0 = java.lang.Object[].class
            int r0 = zzm(r0)
            long r0 = (long) r0
            zzcde = r0
            java.lang.reflect.Field r0 = zzyt()
            if (r0 == 0) goto L_0x00e8
            com.google.android.gms.internal.measurement.zzxj$zzd r1 = zzccq
            if (r1 != 0) goto L_0x00e1
            goto L_0x00e8
        L_0x00e1:
            sun.misc.Unsafe r1 = r1.zzcdh
            long r0 = r1.objectFieldOffset(r0)
            goto L_0x00ea
        L_0x00e8:
            r0 = -1
        L_0x00ea:
            zzcdf = r0
            java.nio.ByteOrder r0 = java.nio.ByteOrder.nativeOrder()
            java.nio.ByteOrder r1 = java.nio.ByteOrder.BIG_ENDIAN
            if (r0 != r1) goto L_0x00f6
            r0 = 1
            goto L_0x00f7
        L_0x00f6:
            r0 = 0
        L_0x00f7:
            zzcdg = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzxj.<clinit>():void");
    }
}
