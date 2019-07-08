package com.google.android.gms.internal.icing;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.ByteOrder;
import java.security.AccessController;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.Unsafe;

final class zzgd {
    private static final Logger logger = Logger.getLogger(zzgd.class.getName());
    private static final Class<?> zzft = zzcb.zzam();
    private static final boolean zzgn = zzdu();
    private static final Unsafe zzme = zzdt();
    private static final boolean zzob = zzj(Long.TYPE);
    private static final boolean zzoc = zzj(Integer.TYPE);
    private static final zzd zzod;
    private static final boolean zzoe = zzdv();
    private static final long zzof = ((long) zzh(byte[].class));
    private static final long zzog = ((long) zzh(boolean[].class));
    private static final long zzoh = ((long) zzi(boolean[].class));
    private static final long zzoi = ((long) zzh(int[].class));
    private static final long zzoj = ((long) zzi(int[].class));
    private static final long zzok = ((long) zzh(long[].class));
    private static final long zzol = ((long) zzi(long[].class));
    private static final long zzom = ((long) zzh(float[].class));
    private static final long zzon = ((long) zzi(float[].class));
    private static final long zzoo = ((long) zzh(double[].class));
    private static final long zzop = ((long) zzi(double[].class));
    private static final long zzoq = ((long) zzh(Object[].class));
    private static final long zzor = ((long) zzi(Object[].class));
    private static final long zzos;
    /* access modifiers changed from: private */
    public static final boolean zzot = (ByteOrder.nativeOrder() != ByteOrder.BIG_ENDIAN);

    static final class zza extends zzd {
        zza(Unsafe unsafe) {
            super(unsafe);
        }

        public final byte zzx(Object obj, long j) {
            if (zzgd.zzot) {
                return zzgd.zzp(obj, j);
            }
            return zzgd.zzq(obj, j);
        }

        public final void zze(Object obj, long j, byte b) {
            if (zzgd.zzot) {
                zzgd.zza(obj, j, b);
            } else {
                zzgd.zzb(obj, j, b);
            }
        }

        public final boolean zzl(Object obj, long j) {
            if (zzgd.zzot) {
                return zzgd.zzr(obj, j);
            }
            return zzgd.zzs(obj, j);
        }

        public final void zza(Object obj, long j, boolean z) {
            if (zzgd.zzot) {
                zzgd.zzb(obj, j, z);
            } else {
                zzgd.zzc(obj, j, z);
            }
        }

        public final float zzm(Object obj, long j) {
            return Float.intBitsToFloat(zzj(obj, j));
        }

        public final void zza(Object obj, long j, float f) {
            zza(obj, j, Float.floatToIntBits(f));
        }

        public final double zzn(Object obj, long j) {
            return Double.longBitsToDouble(zzk(obj, j));
        }

        public final void zza(Object obj, long j, double d) {
            zza(obj, j, Double.doubleToLongBits(d));
        }
    }

    static final class zzb extends zzd {
        zzb(Unsafe unsafe) {
            super(unsafe);
        }

        public final byte zzx(Object obj, long j) {
            if (zzgd.zzot) {
                return zzgd.zzp(obj, j);
            }
            return zzgd.zzq(obj, j);
        }

        public final void zze(Object obj, long j, byte b) {
            if (zzgd.zzot) {
                zzgd.zza(obj, j, b);
            } else {
                zzgd.zzb(obj, j, b);
            }
        }

        public final boolean zzl(Object obj, long j) {
            if (zzgd.zzot) {
                return zzgd.zzr(obj, j);
            }
            return zzgd.zzs(obj, j);
        }

        public final void zza(Object obj, long j, boolean z) {
            if (zzgd.zzot) {
                zzgd.zzb(obj, j, z);
            } else {
                zzgd.zzc(obj, j, z);
            }
        }

        public final float zzm(Object obj, long j) {
            return Float.intBitsToFloat(zzj(obj, j));
        }

        public final void zza(Object obj, long j, float f) {
            zza(obj, j, Float.floatToIntBits(f));
        }

        public final double zzn(Object obj, long j) {
            return Double.longBitsToDouble(zzk(obj, j));
        }

        public final void zza(Object obj, long j, double d) {
            zza(obj, j, Double.doubleToLongBits(d));
        }
    }

    static final class zzc extends zzd {
        zzc(Unsafe unsafe) {
            super(unsafe);
        }

        public final byte zzx(Object obj, long j) {
            return this.zzou.getByte(obj, j);
        }

        public final void zze(Object obj, long j, byte b) {
            this.zzou.putByte(obj, j, b);
        }

        public final boolean zzl(Object obj, long j) {
            return this.zzou.getBoolean(obj, j);
        }

        public final void zza(Object obj, long j, boolean z) {
            this.zzou.putBoolean(obj, j, z);
        }

        public final float zzm(Object obj, long j) {
            return this.zzou.getFloat(obj, j);
        }

        public final void zza(Object obj, long j, float f) {
            this.zzou.putFloat(obj, j, f);
        }

        public final double zzn(Object obj, long j) {
            return this.zzou.getDouble(obj, j);
        }

        public final void zza(Object obj, long j, double d) {
            this.zzou.putDouble(obj, j, d);
        }
    }

    static abstract class zzd {
        Unsafe zzou;

        zzd(Unsafe unsafe) {
            this.zzou = unsafe;
        }

        public abstract void zza(Object obj, long j, double d);

        public abstract void zza(Object obj, long j, float f);

        public abstract void zza(Object obj, long j, boolean z);

        public abstract void zze(Object obj, long j, byte b);

        public abstract boolean zzl(Object obj, long j);

        public abstract float zzm(Object obj, long j);

        public abstract double zzn(Object obj, long j);

        public abstract byte zzx(Object obj, long j);

        public final int zzj(Object obj, long j) {
            return this.zzou.getInt(obj, j);
        }

        public final void zza(Object obj, long j, int i) {
            this.zzou.putInt(obj, j, i);
        }

        public final long zzk(Object obj, long j) {
            return this.zzou.getLong(obj, j);
        }

        public final void zza(Object obj, long j, long j2) {
            this.zzou.putLong(obj, j, j2);
        }
    }

    private zzgd() {
    }

    static boolean zzdr() {
        return zzgn;
    }

    static boolean zzds() {
        return zzoe;
    }

    static <T> T zzg(Class<T> cls) {
        try {
            return zzme.allocateInstance(cls);
        } catch (InstantiationException e) {
            throw new IllegalStateException(e);
        }
    }

    private static int zzh(Class<?> cls) {
        if (zzgn) {
            return zzod.zzou.arrayBaseOffset(cls);
        }
        return -1;
    }

    private static int zzi(Class<?> cls) {
        if (zzgn) {
            return zzod.zzou.arrayIndexScale(cls);
        }
        return -1;
    }

    static int zzj(Object obj, long j) {
        return zzod.zzj(obj, j);
    }

    static void zza(Object obj, long j, int i) {
        zzod.zza(obj, j, i);
    }

    static long zzk(Object obj, long j) {
        return zzod.zzk(obj, j);
    }

    static void zza(Object obj, long j, long j2) {
        zzod.zza(obj, j, j2);
    }

    static boolean zzl(Object obj, long j) {
        return zzod.zzl(obj, j);
    }

    static void zza(Object obj, long j, boolean z) {
        zzod.zza(obj, j, z);
    }

    static float zzm(Object obj, long j) {
        return zzod.zzm(obj, j);
    }

    static void zza(Object obj, long j, float f) {
        zzod.zza(obj, j, f);
    }

    static double zzn(Object obj, long j) {
        return zzod.zzn(obj, j);
    }

    static void zza(Object obj, long j, double d) {
        zzod.zza(obj, j, d);
    }

    static Object zzo(Object obj, long j) {
        return zzod.zzou.getObject(obj, j);
    }

    static void zza(Object obj, long j, Object obj2) {
        zzod.zzou.putObject(obj, j, obj2);
    }

    static byte zza(byte[] bArr, long j) {
        return zzod.zzx(bArr, zzof + j);
    }

    static void zza(byte[] bArr, long j, byte b) {
        zzod.zze(bArr, zzof + j, b);
    }

    static Unsafe zzdt() {
        try {
            return (Unsafe) AccessController.doPrivileged(new zzge());
        } catch (Throwable unused) {
            return null;
        }
    }

    private static boolean zzdu() {
        Unsafe unsafe = zzme;
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
            if (zzcb.zzal()) {
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

    private static boolean zzdv() {
        Unsafe unsafe = zzme;
        if (unsafe == null) {
            return false;
        }
        try {
            Class cls = unsafe.getClass();
            cls.getMethod("objectFieldOffset", new Class[]{Field.class});
            cls.getMethod("getLong", new Class[]{Object.class, Long.TYPE});
            if (zzdw() == null) {
                return false;
            }
            if (zzcb.zzal()) {
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
        if (!zzcb.zzal()) {
            return false;
        }
        try {
            Class<?> cls2 = zzft;
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

    private static Field zzdw() {
        if (zzcb.zzal()) {
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
    public static byte zzp(Object obj, long j) {
        return (byte) (zzj(obj, -4 & j) >>> ((int) (((~j) & 3) << 3)));
    }

    /* access modifiers changed from: private */
    public static byte zzq(Object obj, long j) {
        return (byte) (zzj(obj, -4 & j) >>> ((int) ((j & 3) << 3)));
    }

    /* access modifiers changed from: private */
    public static void zza(Object obj, long j, byte b) {
        long j2 = -4 & j;
        int i = ((~((int) j)) & 3) << 3;
        int i2 = (255 & b) << i;
        zza(obj, j2, i2 | (zzj(obj, j2) & (~(255 << i))));
    }

    /* access modifiers changed from: private */
    public static void zzb(Object obj, long j, byte b) {
        long j2 = -4 & j;
        int i = (((int) j) & 3) << 3;
        zza(obj, j2, ((255 & b) << i) | (zzj(obj, j2) & (~(255 << i))));
    }

    /* access modifiers changed from: private */
    public static boolean zzr(Object obj, long j) {
        return zzp(obj, j) != 0;
    }

    /* access modifiers changed from: private */
    public static boolean zzs(Object obj, long j) {
        return zzq(obj, j) != 0;
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
            java.lang.Class<com.google.android.gms.internal.icing.zzgd> r0 = com.google.android.gms.internal.icing.zzgd.class
            java.lang.String r0 = r0.getName()
            java.util.logging.Logger r0 = java.util.logging.Logger.getLogger(r0)
            logger = r0
            sun.misc.Unsafe r0 = zzdt()
            zzme = r0
            java.lang.Class r0 = com.google.android.gms.internal.icing.zzcb.zzam()
            zzft = r0
            java.lang.Class r0 = java.lang.Long.TYPE
            boolean r0 = zzj(r0)
            zzob = r0
            java.lang.Class r0 = java.lang.Integer.TYPE
            boolean r0 = zzj(r0)
            zzoc = r0
            sun.misc.Unsafe r0 = zzme
            r1 = 0
            if (r0 != 0) goto L_0x002e
            goto L_0x0053
        L_0x002e:
            boolean r0 = com.google.android.gms.internal.icing.zzcb.zzal()
            if (r0 == 0) goto L_0x004c
            boolean r0 = zzob
            if (r0 == 0) goto L_0x0040
            com.google.android.gms.internal.icing.zzgd$zzb r1 = new com.google.android.gms.internal.icing.zzgd$zzb
            sun.misc.Unsafe r0 = zzme
            r1.<init>(r0)
            goto L_0x0053
        L_0x0040:
            boolean r0 = zzoc
            if (r0 == 0) goto L_0x0053
            com.google.android.gms.internal.icing.zzgd$zza r1 = new com.google.android.gms.internal.icing.zzgd$zza
            sun.misc.Unsafe r0 = zzme
            r1.<init>(r0)
            goto L_0x0053
        L_0x004c:
            com.google.android.gms.internal.icing.zzgd$zzc r1 = new com.google.android.gms.internal.icing.zzgd$zzc
            sun.misc.Unsafe r0 = zzme
            r1.<init>(r0)
        L_0x0053:
            zzod = r1
            boolean r0 = zzdv()
            zzoe = r0
            boolean r0 = zzdu()
            zzgn = r0
            java.lang.Class<byte[]> r0 = byte[].class
            int r0 = zzh(r0)
            long r0 = (long) r0
            zzof = r0
            java.lang.Class<boolean[]> r0 = boolean[].class
            int r0 = zzh(r0)
            long r0 = (long) r0
            zzog = r0
            java.lang.Class<boolean[]> r0 = boolean[].class
            int r0 = zzi(r0)
            long r0 = (long) r0
            zzoh = r0
            java.lang.Class<int[]> r0 = int[].class
            int r0 = zzh(r0)
            long r0 = (long) r0
            zzoi = r0
            java.lang.Class<int[]> r0 = int[].class
            int r0 = zzi(r0)
            long r0 = (long) r0
            zzoj = r0
            java.lang.Class<long[]> r0 = long[].class
            int r0 = zzh(r0)
            long r0 = (long) r0
            zzok = r0
            java.lang.Class<long[]> r0 = long[].class
            int r0 = zzi(r0)
            long r0 = (long) r0
            zzol = r0
            java.lang.Class<float[]> r0 = float[].class
            int r0 = zzh(r0)
            long r0 = (long) r0
            zzom = r0
            java.lang.Class<float[]> r0 = float[].class
            int r0 = zzi(r0)
            long r0 = (long) r0
            zzon = r0
            java.lang.Class<double[]> r0 = double[].class
            int r0 = zzh(r0)
            long r0 = (long) r0
            zzoo = r0
            java.lang.Class<double[]> r0 = double[].class
            int r0 = zzi(r0)
            long r0 = (long) r0
            zzop = r0
            java.lang.Class<java.lang.Object[]> r0 = java.lang.Object[].class
            int r0 = zzh(r0)
            long r0 = (long) r0
            zzoq = r0
            java.lang.Class<java.lang.Object[]> r0 = java.lang.Object[].class
            int r0 = zzi(r0)
            long r0 = (long) r0
            zzor = r0
            java.lang.reflect.Field r0 = zzdw()
            if (r0 == 0) goto L_0x00e8
            com.google.android.gms.internal.icing.zzgd$zzd r1 = zzod
            if (r1 != 0) goto L_0x00e1
            goto L_0x00e8
        L_0x00e1:
            sun.misc.Unsafe r1 = r1.zzou
            long r0 = r1.objectFieldOffset(r0)
            goto L_0x00ea
        L_0x00e8:
            r0 = -1
        L_0x00ea:
            zzos = r0
            java.nio.ByteOrder r0 = java.nio.ByteOrder.nativeOrder()
            java.nio.ByteOrder r1 = java.nio.ByteOrder.BIG_ENDIAN
            if (r0 != r1) goto L_0x00f6
            r0 = 1
            goto L_0x00f7
        L_0x00f6:
            r0 = 0
        L_0x00f7:
            zzot = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.icing.zzgd.<clinit>():void");
    }
}
