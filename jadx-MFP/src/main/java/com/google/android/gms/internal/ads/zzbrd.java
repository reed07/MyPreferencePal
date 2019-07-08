package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbrd;
import com.google.android.gms.internal.ads.zzbrd.zza;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class zzbrd<MessageType extends zzbrd<MessageType, BuilderType>, BuilderType extends zza<MessageType, BuilderType>> extends zzbpl<MessageType, BuilderType> {
    private static Map<Object, zzbrd<?, ?>> zzfpw = new ConcurrentHashMap();
    protected zzbtv zzfpu = zzbtv.zzaoz();
    private int zzfpv = -1;

    public static abstract class zza<MessageType extends zzbrd<MessageType, BuilderType>, BuilderType extends zza<MessageType, BuilderType>> extends zzbpm<MessageType, BuilderType> {
        private final MessageType zzfpx;
        protected MessageType zzfpy;
        private boolean zzfpz = false;

        protected zza(MessageType messagetype) {
            this.zzfpx = messagetype;
            this.zzfpy = (zzbrd) messagetype.zza(zze.zzfqe, (Object) null, (Object) null);
        }

        /* access modifiers changed from: protected */
        public final void zzamw() {
            if (this.zzfpz) {
                MessageType messagetype = (zzbrd) this.zzfpy.zza(zze.zzfqe, (Object) null, (Object) null);
                zza(messagetype, this.zzfpy);
                this.zzfpy = messagetype;
                this.zzfpz = false;
            }
        }

        public final boolean isInitialized() {
            return zzbrd.zza(this.zzfpy, false);
        }

        /* renamed from: zzamx */
        public MessageType zzamz() {
            if (this.zzfpz) {
                return this.zzfpy;
            }
            MessageType messagetype = this.zzfpy;
            zzbsy.zzaog().zzaf(messagetype).zzs(messagetype);
            this.zzfpz = true;
            return this.zzfpy;
        }

        /* renamed from: zzamy */
        public final MessageType zzana() {
            MessageType messagetype = (zzbrd) zzamz();
            boolean booleanValue = Boolean.TRUE.booleanValue();
            byte byteValue = ((Byte) messagetype.zza(zze.zzfqb, (Object) null, (Object) null)).byteValue();
            boolean z = true;
            if (byteValue != 1) {
                if (byteValue == 0) {
                    z = false;
                } else {
                    z = zzbsy.zzaog().zzaf(messagetype).zzae(messagetype);
                    if (booleanValue) {
                        messagetype.zza(zze.zzfqc, (Object) z ? messagetype : null, (Object) null);
                    }
                }
            }
            if (z) {
                return messagetype;
            }
            throw new zzbtt(messagetype);
        }

        public final BuilderType zza(MessageType messagetype) {
            zzamw();
            zza(this.zzfpy, messagetype);
            return this;
        }

        private static void zza(MessageType messagetype, MessageType messagetype2) {
            zzbsy.zzaog().zzaf(messagetype).zzd(messagetype, messagetype2);
        }

        public final /* synthetic */ zzbpm zzakh() {
            return (zza) clone();
        }

        public final /* synthetic */ zzbsl zzamv() {
            return this.zzfpx;
        }

        public /* synthetic */ Object clone() throws CloneNotSupportedException {
            zza zza = (zza) ((zzbrd) this.zzfpx).zza(zze.zzfqf, (Object) null, (Object) null);
            zza.zza((MessageType) (zzbrd) zzamz());
            return zza;
        }
    }

    public static class zzb<T extends zzbrd<T, ?>> extends zzbpn<T> {
        private final T zzfpx;

        public zzb(T t) {
            this.zzfpx = t;
        }

        public final /* synthetic */ Object zza(zzbqf zzbqf, zzbqq zzbqq) throws zzbrl {
            return zzbrd.zza(this.zzfpx, zzbqf, zzbqq);
        }
    }

    public static abstract class zzc<MessageType extends zzc<MessageType, BuilderType>, BuilderType> extends zzbrd<MessageType, BuilderType> implements zzbsn {
        protected zzbqu<Object> zzfqa = zzbqu.zzami();
    }

    public static class zzd<ContainingType extends zzbsl, Type> extends zzbqo<ContainingType, Type> {
    }

    /* 'enum' access flag removed */
    public static final class zze {
        public static final int zzfqb = 1;
        public static final int zzfqc = 2;
        public static final int zzfqd = 3;
        public static final int zzfqe = 4;
        public static final int zzfqf = 5;
        public static final int zzfqg = 6;
        public static final int zzfqh = 7;
        private static final /* synthetic */ int[] zzfqi = {zzfqb, zzfqc, zzfqd, zzfqe, zzfqf, zzfqg, zzfqh};
        public static final int zzfqj = 1;
        public static final int zzfqk = 2;
        private static final /* synthetic */ int[] zzfql = {zzfqj, zzfqk};
        public static final int zzfqm = 1;
        public static final int zzfqn = 2;
        private static final /* synthetic */ int[] zzfqo = {zzfqm, zzfqn};

        public static int[] values$50KLMJ33DTMIUPRFDTJMOP9FE1P6UT3FC9QMCBQ7CLN6ASJ1EHIM8JB5EDPM2PR59HKN8P949LIN8Q3FCHA6UIBEEPNMMP9R0() {
            return (int[]) zzfqi.clone();
        }
    }

    /* access modifiers changed from: protected */
    public abstract Object zza(int i, Object obj, Object obj2);

    public String toString() {
        return zzbso.zza(this, super.toString());
    }

    public int hashCode() {
        if (this.zzfkx != 0) {
            return this.zzfkx;
        }
        this.zzfkx = zzbsy.zzaog().zzaf(this).hashCode(this);
        return this.zzfkx;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!((zzbrd) zza(zze.zzfqg, (Object) null, (Object) null)).getClass().isInstance(obj)) {
            return false;
        }
        return zzbsy.zzaog().zzaf(this).equals(this, (zzbrd) obj);
    }

    public final boolean isInitialized() {
        boolean booleanValue = Boolean.TRUE.booleanValue();
        byte byteValue = ((Byte) zza(zze.zzfqb, (Object) null, (Object) null)).byteValue();
        if (byteValue == 1) {
            return true;
        }
        if (byteValue == 0) {
            return false;
        }
        boolean zzae = zzbsy.zzaog().zzaf(this).zzae(this);
        if (booleanValue) {
            zza(zze.zzfqc, (Object) zzae ? this : null, (Object) null);
        }
        return zzae;
    }

    /* access modifiers changed from: 0000 */
    public final int zzakg() {
        return this.zzfpv;
    }

    /* access modifiers changed from: 0000 */
    public final void zzei(int i) {
        this.zzfpv = i;
    }

    public final void zzb(zzbqk zzbqk) throws IOException {
        zzbsy.zzaog().zzf(getClass()).zza(this, zzbqm.zza(zzbqk));
    }

    public final int zzamj() {
        if (this.zzfpv == -1) {
            this.zzfpv = zzbsy.zzaog().zzaf(this).zzac(this);
        }
        return this.zzfpv;
    }

    static <T extends zzbrd<?, ?>> T zzd(Class<T> cls) {
        T t = (zzbrd) zzfpw.get(cls);
        if (t == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                t = (zzbrd) zzfpw.get(cls);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Class initialization cannot fail.", e);
            }
        }
        if (t != null) {
            return t;
        }
        String str = "Unable to get default instance for: ";
        String valueOf = String.valueOf(cls.getName());
        throw new IllegalStateException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
    }

    protected static <T extends zzbrd<?, ?>> void zza(Class<T> cls, T t) {
        zzfpw.put(cls, t);
    }

    protected static Object zza(zzbsl zzbsl, String str, Object[] objArr) {
        return new zzbta(zzbsl, str, objArr);
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

    protected static final <T extends zzbrd<T, ?>> boolean zza(T t, boolean z) {
        byte byteValue = ((Byte) t.zza(zze.zzfqb, (Object) null, (Object) null)).byteValue();
        if (byteValue == 1) {
            return true;
        }
        if (byteValue == 0) {
            return false;
        }
        return zzbsy.zzaog().zzaf(t).zzae(t);
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [com.google.android.gms.internal.ads.zzbre, com.google.android.gms.internal.ads.zzbrj] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v0, types: [com.google.android.gms.internal.ads.zzbre, com.google.android.gms.internal.ads.zzbrj]
  assigns: [com.google.android.gms.internal.ads.zzbre]
  uses: [com.google.android.gms.internal.ads.zzbrj]
  mth insns count: 2
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
    	at jadx.core.ProcessClass.process(ProcessClass.java:35)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected static com.google.android.gms.internal.ads.zzbrj zzamr() {
        /*
            com.google.android.gms.internal.ads.zzbre r0 = com.google.android.gms.internal.ads.zzbre.zzanb()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbrd.zzamr():com.google.android.gms.internal.ads.zzbrj");
    }

    protected static <E> zzbrk<E> zzams() {
        return zzbsz.zzaoh();
    }

    static <T extends zzbrd<T, ?>> T zza(T t, zzbqf zzbqf, zzbqq zzbqq) throws zzbrl {
        T t2 = (zzbrd) t.zza(zze.zzfqe, (Object) null, (Object) null);
        try {
            zzbsy.zzaog().zzaf(t2).zza(t2, zzbqi.zza(zzbqf), zzbqq);
            zzbsy.zzaog().zzaf(t2).zzs(t2);
            return t2;
        } catch (IOException e) {
            if (e.getCause() instanceof zzbrl) {
                throw ((zzbrl) e.getCause());
            }
            throw new zzbrl(e.getMessage()).zzj(t2);
        } catch (RuntimeException e2) {
            if (e2.getCause() instanceof zzbrl) {
                throw ((zzbrl) e2.getCause());
            }
            throw e2;
        }
    }

    private static <T extends zzbrd<T, ?>> T zza(T t, byte[] bArr) throws zzbrl {
        T t2 = (zzbrd) t.zza(zze.zzfqe, (Object) null, (Object) null);
        try {
            zzbsy.zzaog().zzaf(t2).zza(t2, bArr, 0, bArr.length, new zzbpr());
            zzbsy.zzaog().zzaf(t2).zzs(t2);
            if (t2.zzfkx == 0) {
                return t2;
            }
            throw new RuntimeException();
        } catch (IOException e) {
            if (e.getCause() instanceof zzbrl) {
                throw ((zzbrl) e.getCause());
            }
            throw new zzbrl(e.getMessage()).zzj(t2);
        } catch (IndexOutOfBoundsException unused) {
            throw zzbrl.zzanc().zzj(t2);
        }
    }

    protected static <T extends zzbrd<T, ?>> T zza(T t, zzbpu zzbpu) throws zzbrl {
        boolean z;
        T zza2 = zza(t, zzbpu, zzbqq.zzamd());
        boolean z2 = false;
        if (zza2 != null) {
            boolean booleanValue = Boolean.TRUE.booleanValue();
            byte byteValue = ((Byte) zza2.zza(zze.zzfqb, (Object) null, (Object) null)).byteValue();
            if (byteValue == 1) {
                z = true;
            } else if (byteValue == 0) {
                z = false;
            } else {
                z = zzbsy.zzaog().zzaf(zza2).zzae(zza2);
                if (booleanValue) {
                    zza2.zza(zze.zzfqc, (Object) z ? zza2 : null, (Object) null);
                }
            }
            if (!z) {
                throw new zzbtt(zza2).zzaox().zzj(zza2);
            }
        }
        if (zza2 != null) {
            boolean booleanValue2 = Boolean.TRUE.booleanValue();
            byte byteValue2 = ((Byte) zza2.zza(zze.zzfqb, (Object) null, (Object) null)).byteValue();
            if (byteValue2 == 1) {
                z2 = true;
            } else if (byteValue2 != 0) {
                z2 = zzbsy.zzaog().zzaf(zza2).zzae(zza2);
                if (booleanValue2) {
                    zza2.zza(zze.zzfqc, (Object) z2 ? zza2 : null, (Object) null);
                }
            }
            if (!z2) {
                throw new zzbtt(zza2).zzaox().zzj(zza2);
            }
        }
        return zza2;
    }

    private static <T extends zzbrd<T, ?>> T zza(T t, zzbpu zzbpu, zzbqq zzbqq) throws zzbrl {
        T zza2;
        try {
            zzbqf zzakp = zzbpu.zzakp();
            zza2 = zza(t, zzakp, zzbqq);
            zzakp.zzeo(0);
            return zza2;
        } catch (zzbrl e) {
            throw e.zzj(zza2);
        } catch (zzbrl e2) {
            throw e2;
        }
    }

    protected static <T extends zzbrd<T, ?>> T zzb(T t, byte[] bArr) throws zzbrl {
        T zza2 = zza(t, bArr);
        if (zza2 != null) {
            boolean booleanValue = Boolean.TRUE.booleanValue();
            byte byteValue = ((Byte) zza2.zza(zze.zzfqb, (Object) null, (Object) null)).byteValue();
            boolean z = true;
            if (byteValue != 1) {
                if (byteValue == 0) {
                    z = false;
                } else {
                    z = zzbsy.zzaog().zzaf(zza2).zzae(zza2);
                    if (booleanValue) {
                        zza2.zza(zze.zzfqc, (Object) z ? zza2 : null, (Object) null);
                    }
                }
            }
            if (!z) {
                throw new zzbtt(zza2).zzaox().zzj(zza2);
            }
        }
        return zza2;
    }

    public final /* synthetic */ zzbsm zzamt() {
        zza zza2 = (zza) zza(zze.zzfqf, (Object) null, (Object) null);
        zza2.zza(this);
        return zza2;
    }

    public final /* synthetic */ zzbsm zzamu() {
        return (zza) zza(zze.zzfqf, (Object) null, (Object) null);
    }

    public final /* synthetic */ zzbsl zzamv() {
        return (zzbrd) zza(zze.zzfqg, (Object) null, (Object) null);
    }
}
