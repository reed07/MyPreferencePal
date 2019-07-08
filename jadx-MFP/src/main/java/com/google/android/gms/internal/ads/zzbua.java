package com.google.android.gms.internal.ads;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.AccessController;
import java.util.logging.Level;
import java.util.logging.Logger;
import libcore.io.Memory;
import sun.misc.Unsafe;

final class zzbua {
    private static final Logger logger = Logger.getLogger(zzbua.class.getName());
    private static final Class<?> zzflb = zzbpp.zzakm();
    private static final boolean zzfme = zzapf();
    private static final Unsafe zzfsh = zzape();
    private static final boolean zzfue = zzj(Long.TYPE);
    private static final boolean zzfuf = zzj(Integer.TYPE);
    private static final zzd zzfug;
    private static final boolean zzfuh = zzapg();
    /* access modifiers changed from: private */
    public static final long zzfui = ((long) zzh(byte[].class));
    private static final long zzfuj = ((long) zzh(boolean[].class));
    private static final long zzfuk = ((long) zzi(boolean[].class));
    private static final long zzful = ((long) zzh(int[].class));
    private static final long zzfum = ((long) zzi(int[].class));
    private static final long zzfun = ((long) zzh(long[].class));
    private static final long zzfuo = ((long) zzi(long[].class));
    private static final long zzfup = ((long) zzh(float[].class));
    private static final long zzfuq = ((long) zzi(float[].class));
    private static final long zzfur = ((long) zzh(double[].class));
    private static final long zzfus = ((long) zzi(double[].class));
    private static final long zzfut = ((long) zzh(Object[].class));
    private static final long zzfuu = ((long) zzi(Object[].class));
    private static final long zzfuv;
    /* access modifiers changed from: private */
    public static final boolean zzfuw = (ByteOrder.nativeOrder() != ByteOrder.BIG_ENDIAN);

    static final class zza extends zzd {
        zza(Unsafe unsafe) {
            super(unsafe);
        }

        public final void zza(long j, byte b) {
            Memory.pokeByte((int) (j & -1), b);
        }

        public final byte zzy(Object obj, long j) {
            if (zzbua.zzfuw) {
                return zzbua.zzq(obj, j);
            }
            return zzbua.zzr(obj, j);
        }

        public final void zze(Object obj, long j, byte b) {
            if (zzbua.zzfuw) {
                zzbua.zza(obj, j, b);
            } else {
                zzbua.zzb(obj, j, b);
            }
        }

        public final boolean zzm(Object obj, long j) {
            if (zzbua.zzfuw) {
                return zzbua.zzs(obj, j);
            }
            return zzbua.zzt(obj, j);
        }

        public final void zza(Object obj, long j, boolean z) {
            if (zzbua.zzfuw) {
                zzbua.zzb(obj, j, z);
            } else {
                zzbua.zzc(obj, j, z);
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
            if (zzbua.zzfuw) {
                return zzbua.zzq(obj, j);
            }
            return zzbua.zzr(obj, j);
        }

        public final void zze(Object obj, long j, byte b) {
            if (zzbua.zzfuw) {
                zzbua.zza(obj, j, b);
            } else {
                zzbua.zzb(obj, j, b);
            }
        }

        public final boolean zzm(Object obj, long j) {
            if (zzbua.zzfuw) {
                return zzbua.zzs(obj, j);
            }
            return zzbua.zzt(obj, j);
        }

        public final void zza(Object obj, long j, boolean z) {
            if (zzbua.zzfuw) {
                zzbua.zzb(obj, j, z);
            } else {
                zzbua.zzc(obj, j, z);
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
            this.zzfux.putByte(j, b);
        }

        public final byte zzy(Object obj, long j) {
            return this.zzfux.getByte(obj, j);
        }

        public final void zze(Object obj, long j, byte b) {
            this.zzfux.putByte(obj, j, b);
        }

        public final boolean zzm(Object obj, long j) {
            return this.zzfux.getBoolean(obj, j);
        }

        public final void zza(Object obj, long j, boolean z) {
            this.zzfux.putBoolean(obj, j, z);
        }

        public final float zzn(Object obj, long j) {
            return this.zzfux.getFloat(obj, j);
        }

        public final void zza(Object obj, long j, float f) {
            this.zzfux.putFloat(obj, j, f);
        }

        public final double zzo(Object obj, long j) {
            return this.zzfux.getDouble(obj, j);
        }

        public final void zza(Object obj, long j, double d) {
            this.zzfux.putDouble(obj, j, d);
        }

        public final void zza(byte[] bArr, long j, long j2, long j3) {
            this.zzfux.copyMemory(bArr, zzbua.zzfui + j, null, j2, j3);
        }
    }

    static abstract class zzd {
        Unsafe zzfux;

        zzd(Unsafe unsafe) {
            this.zzfux = unsafe;
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
            return this.zzfux.getInt(obj, j);
        }

        public final void zzb(Object obj, long j, int i) {
            this.zzfux.putInt(obj, j, i);
        }

        public final long zzl(Object obj, long j) {
            return this.zzfux.getLong(obj, j);
        }

        public final void zza(Object obj, long j, long j2) {
            this.zzfux.putLong(obj, j, j2);
        }
    }

    private zzbua() {
    }

    static boolean zzapc() {
        return zzfme;
    }

    static boolean zzapd() {
        return zzfuh;
    }

    private static int zzh(Class<?> cls) {
        if (zzfme) {
            return zzfug.zzfux.arrayBaseOffset(cls);
        }
        return -1;
    }

    private static int zzi(Class<?> cls) {
        if (zzfme) {
            return zzfug.zzfux.arrayIndexScale(cls);
        }
        return -1;
    }

    static int zzk(Object obj, long j) {
        return zzfug.zzk(obj, j);
    }

    static void zzb(Object obj, long j, int i) {
        zzfug.zzb(obj, j, i);
    }

    static long zzl(Object obj, long j) {
        return zzfug.zzl(obj, j);
    }

    static void zza(Object obj, long j, long j2) {
        zzfug.zza(obj, j, j2);
    }

    static boolean zzm(Object obj, long j) {
        return zzfug.zzm(obj, j);
    }

    static void zza(Object obj, long j, boolean z) {
        zzfug.zza(obj, j, z);
    }

    static float zzn(Object obj, long j) {
        return zzfug.zzn(obj, j);
    }

    static void zza(Object obj, long j, float f) {
        zzfug.zza(obj, j, f);
    }

    static double zzo(Object obj, long j) {
        return zzfug.zzo(obj, j);
    }

    static void zza(Object obj, long j, double d) {
        zzfug.zza(obj, j, d);
    }

    static Object zzp(Object obj, long j) {
        return zzfug.zzfux.getObject(obj, j);
    }

    static void zza(Object obj, long j, Object obj2) {
        zzfug.zzfux.putObject(obj, j, obj2);
    }

    static byte zza(byte[] bArr, long j) {
        return zzfug.zzy(bArr, zzfui + j);
    }

    static void zza(byte[] bArr, long j, byte b) {
        zzfug.zze(bArr, zzfui + j, b);
    }

    static void zza(byte[] bArr, long j, long j2, long j3) {
        zzfug.zza(bArr, j, j2, j3);
    }

    static void zza(long j, byte b) {
        zzfug.zza(j, b);
    }

    static long zzo(ByteBuffer byteBuffer) {
        return zzfug.zzl(byteBuffer, zzfuv);
    }

    static Unsafe zzape() {
        try {
            return (Unsafe) AccessController.doPrivileged(new zzbub());
        } catch (Throwable unused) {
            return null;
        }
    }

    private static boolean zzapf() {
        Unsafe unsafe = zzfsh;
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
            if (zzbpp.zzakl()) {
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

    private static boolean zzapg() {
        Unsafe unsafe = zzfsh;
        if (unsafe == null) {
            return false;
        }
        try {
            Class cls = unsafe.getClass();
            cls.getMethod("objectFieldOffset", new Class[]{Field.class});
            cls.getMethod("getLong", new Class[]{Object.class, Long.TYPE});
            if (zzaph() == null) {
                return false;
            }
            if (zzbpp.zzakl()) {
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

    private static boolean zzj(Class<?> cls) {
        if (!zzbpp.zzakl()) {
            return false;
        }
        try {
            Class<?> cls2 = zzflb;
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

    private static Field zzaph() {
        if (zzbpp.zzakl()) {
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
            java.lang.Class<com.google.android.gms.internal.ads.zzbua> r0 = com.google.android.gms.internal.ads.zzbua.class
            java.lang.String r0 = r0.getName()
            java.util.logging.Logger r0 = java.util.logging.Logger.getLogger(r0)
            logger = r0
            sun.misc.Unsafe r0 = zzape()
            zzfsh = r0
            java.lang.Class r0 = com.google.android.gms.internal.ads.zzbpp.zzakm()
            zzflb = r0
            java.lang.Class r0 = java.lang.Long.TYPE
            boolean r0 = zzj(r0)
            zzfue = r0
            java.lang.Class r0 = java.lang.Integer.TYPE
            boolean r0 = zzj(r0)
            zzfuf = r0
            sun.misc.Unsafe r0 = zzfsh
            r1 = 0
            if (r0 != 0) goto L_0x002e
            goto L_0x0053
        L_0x002e:
            boolean r0 = com.google.android.gms.internal.ads.zzbpp.zzakl()
            if (r0 == 0) goto L_0x004c
            boolean r0 = zzfue
            if (r0 == 0) goto L_0x0040
            com.google.android.gms.internal.ads.zzbua$zzb r1 = new com.google.android.gms.internal.ads.zzbua$zzb
            sun.misc.Unsafe r0 = zzfsh
            r1.<init>(r0)
            goto L_0x0053
        L_0x0040:
            boolean r0 = zzfuf
            if (r0 == 0) goto L_0x0053
            com.google.android.gms.internal.ads.zzbua$zza r1 = new com.google.android.gms.internal.ads.zzbua$zza
            sun.misc.Unsafe r0 = zzfsh
            r1.<init>(r0)
            goto L_0x0053
        L_0x004c:
            com.google.android.gms.internal.ads.zzbua$zzc r1 = new com.google.android.gms.internal.ads.zzbua$zzc
            sun.misc.Unsafe r0 = zzfsh
            r1.<init>(r0)
        L_0x0053:
            zzfug = r1
            boolean r0 = zzapg()
            zzfuh = r0
            boolean r0 = zzapf()
            zzfme = r0
            java.lang.Class<byte[]> r0 = byte[].class
            int r0 = zzh(r0)
            long r0 = (long) r0
            zzfui = r0
            java.lang.Class<boolean[]> r0 = boolean[].class
            int r0 = zzh(r0)
            long r0 = (long) r0
            zzfuj = r0
            java.lang.Class<boolean[]> r0 = boolean[].class
            int r0 = zzi(r0)
            long r0 = (long) r0
            zzfuk = r0
            java.lang.Class<int[]> r0 = int[].class
            int r0 = zzh(r0)
            long r0 = (long) r0
            zzful = r0
            java.lang.Class<int[]> r0 = int[].class
            int r0 = zzi(r0)
            long r0 = (long) r0
            zzfum = r0
            java.lang.Class<long[]> r0 = long[].class
            int r0 = zzh(r0)
            long r0 = (long) r0
            zzfun = r0
            java.lang.Class<long[]> r0 = long[].class
            int r0 = zzi(r0)
            long r0 = (long) r0
            zzfuo = r0
            java.lang.Class<float[]> r0 = float[].class
            int r0 = zzh(r0)
            long r0 = (long) r0
            zzfup = r0
            java.lang.Class<float[]> r0 = float[].class
            int r0 = zzi(r0)
            long r0 = (long) r0
            zzfuq = r0
            java.lang.Class<double[]> r0 = double[].class
            int r0 = zzh(r0)
            long r0 = (long) r0
            zzfur = r0
            java.lang.Class<double[]> r0 = double[].class
            int r0 = zzi(r0)
            long r0 = (long) r0
            zzfus = r0
            java.lang.Class<java.lang.Object[]> r0 = java.lang.Object[].class
            int r0 = zzh(r0)
            long r0 = (long) r0
            zzfut = r0
            java.lang.Class<java.lang.Object[]> r0 = java.lang.Object[].class
            int r0 = zzi(r0)
            long r0 = (long) r0
            zzfuu = r0
            java.lang.reflect.Field r0 = zzaph()
            if (r0 == 0) goto L_0x00e8
            com.google.android.gms.internal.ads.zzbua$zzd r1 = zzfug
            if (r1 != 0) goto L_0x00e1
            goto L_0x00e8
        L_0x00e1:
            sun.misc.Unsafe r1 = r1.zzfux
            long r0 = r1.objectFieldOffset(r0)
            goto L_0x00ea
        L_0x00e8:
            r0 = -1
        L_0x00ea:
            zzfuv = r0
            java.nio.ByteOrder r0 = java.nio.ByteOrder.nativeOrder()
            java.nio.ByteOrder r1 = java.nio.ByteOrder.BIG_ENDIAN
            if (r0 != r1) goto L_0x00f6
            r0 = 1
            goto L_0x00f7
        L_0x00f6:
            r0 = 0
        L_0x00f7:
            zzfuw = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbua.<clinit>():void");
    }
}
