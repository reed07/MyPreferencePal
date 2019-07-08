package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzuo;
import com.google.android.gms.internal.measurement.zzuo.zza;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class zzuo<MessageType extends zzuo<MessageType, BuilderType>, BuilderType extends zza<MessageType, BuilderType>> extends zzsx<MessageType, BuilderType> {
    private static Map<Object, zzuo<?, ?>> zzbyh = new ConcurrentHashMap();
    protected zzxe zzbyf = zzxe.zzyl();
    private int zzbyg = -1;

    public static abstract class zza<MessageType extends zzuo<MessageType, BuilderType>, BuilderType extends zza<MessageType, BuilderType>> extends zzsy<MessageType, BuilderType> {
        private final MessageType zzbyi;
        protected MessageType zzbyj;
        private boolean zzbyk = false;

        protected zza(MessageType messagetype) {
            this.zzbyi = messagetype;
            this.zzbyj = (zzuo) messagetype.zza(zze.zzbyp, (Object) null, (Object) null);
        }

        /* access modifiers changed from: protected */
        public final void zzwk() {
            if (this.zzbyk) {
                MessageType messagetype = (zzuo) this.zzbyj.zza(zze.zzbyp, (Object) null, (Object) null);
                zza(messagetype, this.zzbyj);
                this.zzbyj = messagetype;
                this.zzbyk = false;
            }
        }

        public final boolean isInitialized() {
            return zzuo.zza(this.zzbyj, false);
        }

        /* renamed from: zzwl */
        public MessageType zzwn() {
            if (this.zzbyk) {
                return this.zzbyj;
            }
            MessageType messagetype = this.zzbyj;
            zzwh.zzxt().zzak(messagetype).zzy(messagetype);
            this.zzbyk = true;
            return this.zzbyj;
        }

        /* renamed from: zzwm */
        public final MessageType zzwo() {
            MessageType messagetype = (zzuo) zzwn();
            boolean booleanValue = Boolean.TRUE.booleanValue();
            byte byteValue = ((Byte) messagetype.zza(zze.zzbym, (Object) null, (Object) null)).byteValue();
            boolean z = true;
            if (byteValue != 1) {
                if (byteValue == 0) {
                    z = false;
                } else {
                    z = zzwh.zzxt().zzak(messagetype).zzaj(messagetype);
                    if (booleanValue) {
                        messagetype.zza(zze.zzbyn, (Object) z ? messagetype : null, (Object) null);
                    }
                }
            }
            if (z) {
                return messagetype;
            }
            throw new zzxc(messagetype);
        }

        public final BuilderType zza(MessageType messagetype) {
            zzwk();
            zza(this.zzbyj, messagetype);
            return this;
        }

        private static void zza(MessageType messagetype, MessageType messagetype2) {
            zzwh.zzxt().zzak(messagetype).zzd(messagetype, messagetype2);
        }

        public final /* synthetic */ zzsy zzty() {
            return (zza) clone();
        }

        public final /* synthetic */ zzvv zzwj() {
            return this.zzbyi;
        }

        public /* synthetic */ Object clone() throws CloneNotSupportedException {
            zza zza = (zza) ((zzuo) this.zzbyi).zza(zze.zzbyq, (Object) null, (Object) null);
            zza.zza((MessageType) (zzuo) zzwn());
            return zza;
        }
    }

    public static class zzb<T extends zzuo<T, ?>> extends zzsz<T> {
        private final T zzbyi;

        public zzb(T t) {
            this.zzbyi = t;
        }

        public final /* synthetic */ Object zza(zztq zztq, zzub zzub) throws zzuv {
            return zzuo.zza(this.zzbyi, zztq, zzub);
        }
    }

    public static abstract class zzc<MessageType extends zzc<MessageType, BuilderType>, BuilderType> extends zzuo<MessageType, BuilderType> implements zzvx {
        protected zzuf<Object> zzbyl = zzuf.zzvw();
    }

    public static class zzd<ContainingType extends zzvv, Type> extends zztz<ContainingType, Type> {
    }

    /* 'enum' access flag removed */
    public static final class zze {
        public static final int zzbym = 1;
        public static final int zzbyn = 2;
        public static final int zzbyo = 3;
        public static final int zzbyp = 4;
        public static final int zzbyq = 5;
        public static final int zzbyr = 6;
        public static final int zzbys = 7;
        private static final /* synthetic */ int[] zzbyt = {zzbym, zzbyn, zzbyo, zzbyp, zzbyq, zzbyr, zzbys};
        public static final int zzbyu = 1;
        public static final int zzbyv = 2;
        private static final /* synthetic */ int[] zzbyw = {zzbyu, zzbyv};
        public static final int zzbyx = 1;
        public static final int zzbyy = 2;
        private static final /* synthetic */ int[] zzbyz = {zzbyx, zzbyy};

        public static int[] zzwp() {
            return (int[]) zzbyt.clone();
        }
    }

    /* access modifiers changed from: protected */
    public abstract Object zza(int i, Object obj, Object obj2);

    public String toString() {
        return zzvy.zza(this, super.toString());
    }

    public int hashCode() {
        if (this.zzbtk != 0) {
            return this.zzbtk;
        }
        this.zzbtk = zzwh.zzxt().zzak(this).hashCode(this);
        return this.zzbtk;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!((zzuo) zza(zze.zzbyr, (Object) null, (Object) null)).getClass().isInstance(obj)) {
            return false;
        }
        return zzwh.zzxt().zzak(this).equals(this, (zzuo) obj);
    }

    public final boolean isInitialized() {
        boolean booleanValue = Boolean.TRUE.booleanValue();
        byte byteValue = ((Byte) zza(zze.zzbym, (Object) null, (Object) null)).byteValue();
        if (byteValue == 1) {
            return true;
        }
        if (byteValue == 0) {
            return false;
        }
        boolean zzaj = zzwh.zzxt().zzak(this).zzaj(this);
        if (booleanValue) {
            zza(zze.zzbyn, (Object) zzaj ? this : null, (Object) null);
        }
        return zzaj;
    }

    public final BuilderType zzwf() {
        BuilderType buildertype = (zza) zza(zze.zzbyq, (Object) null, (Object) null);
        buildertype.zza(this);
        return buildertype;
    }

    /* access modifiers changed from: 0000 */
    public final int zztx() {
        return this.zzbyg;
    }

    /* access modifiers changed from: 0000 */
    public final void zzai(int i) {
        this.zzbyg = i;
    }

    public final void zzb(zztv zztv) throws IOException {
        zzwh.zzxt().zzi(getClass()).zza(this, zztx.zza(zztv));
    }

    public final int zzvx() {
        if (this.zzbyg == -1) {
            this.zzbyg = zzwh.zzxt().zzak(this).zzai(this);
        }
        return this.zzbyg;
    }

    static <T extends zzuo<?, ?>> T zzg(Class<T> cls) {
        T t = (zzuo) zzbyh.get(cls);
        if (t == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                t = (zzuo) zzbyh.get(cls);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Class initialization cannot fail.", e);
            }
        }
        if (t == null) {
            t = (zzuo) ((zzuo) zzxj.zzk(cls)).zza(zze.zzbyr, (Object) null, (Object) null);
            if (t != null) {
                zzbyh.put(cls, t);
            } else {
                throw new IllegalStateException();
            }
        }
        return t;
    }

    protected static <T extends zzuo<?, ?>> void zza(Class<T> cls, T t) {
        zzbyh.put(cls, t);
    }

    protected static Object zza(zzvv zzvv, String str, Object[] objArr) {
        return new zzwj(zzvv, str, objArr);
    }

    static Object zza(Method method, Object obj, Object... objArr) {
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", e);
        } catch (InvocationTargetException e2) {
            Throwable cause = e2.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            } else if (cause instanceof Error) {
                throw ((Error) cause);
            } else {
                throw new RuntimeException("Unexpected exception thrown by generated accessor method.", cause);
            }
        }
    }

    protected static final <T extends zzuo<T, ?>> boolean zza(T t, boolean z) {
        byte byteValue = ((Byte) t.zza(zze.zzbym, (Object) null, (Object) null)).byteValue();
        if (byteValue == 1) {
            return true;
        }
        if (byteValue == 0) {
            return false;
        }
        return zzwh.zzxt().zzak(t).zzaj(t);
    }

    protected static <E> zzuu<E> zzwg() {
        return zzwi.zzxu();
    }

    static <T extends zzuo<T, ?>> T zza(T t, zztq zztq, zzub zzub) throws zzuv {
        T t2 = (zzuo) t.zza(zze.zzbyp, (Object) null, (Object) null);
        try {
            zzwh.zzxt().zzak(t2).zza(t2, zztt.zza(zztq), zzub);
            zzwh.zzxt().zzak(t2).zzy(t2);
            return t2;
        } catch (IOException e) {
            if (e.getCause() instanceof zzuv) {
                throw ((zzuv) e.getCause());
            }
            throw new zzuv(e.getMessage()).zzg(t2);
        } catch (RuntimeException e2) {
            if (e2.getCause() instanceof zzuv) {
                throw ((zzuv) e2.getCause());
            }
            throw e2;
        }
    }

    public final /* synthetic */ zzvw zzwh() {
        zza zza2 = (zza) zza(zze.zzbyq, (Object) null, (Object) null);
        zza2.zza(this);
        return zza2;
    }

    public final /* synthetic */ zzvw zzwi() {
        return (zza) zza(zze.zzbyq, (Object) null, (Object) null);
    }

    public final /* synthetic */ zzvv zzwj() {
        return (zzuo) zza(zze.zzbyr, (Object) null, (Object) null);
    }
}
