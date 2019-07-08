package com.google.android.gms.internal.icing;

import com.google.android.gms.internal.icing.zzdj;
import com.google.android.gms.internal.icing.zzdj.zza;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class zzdj<MessageType extends zzdj<MessageType, BuilderType>, BuilderType extends zza<MessageType, BuilderType>> extends zzbx<MessageType, BuilderType> {
    private static Map<Object, zzdj<?, ?>> zzjv = new ConcurrentHashMap();
    protected zzfy zzjt = zzfy.zzdp();
    private int zzju = -1;

    public static abstract class zza<MessageType extends zzdj<MessageType, BuilderType>, BuilderType extends zza<MessageType, BuilderType>> extends zzby<MessageType, BuilderType> {
        private final MessageType zzjw;
        protected MessageType zzjx;
        private boolean zzjy = false;

        protected zza(MessageType messagetype) {
            this.zzjw = messagetype;
            this.zzjx = (zzdj) messagetype.zza(zzd.zzkd, (Object) null, (Object) null);
        }

        /* access modifiers changed from: protected */
        public final void zzbw() {
            if (this.zzjy) {
                MessageType messagetype = (zzdj) this.zzjx.zza(zzd.zzkd, (Object) null, (Object) null);
                zza(messagetype, this.zzjx);
                this.zzjx = messagetype;
                this.zzjy = false;
            }
        }

        public final boolean isInitialized() {
            return zzdj.zza(this.zzjx, false);
        }

        /* renamed from: zzbx */
        public MessageType zzbz() {
            if (this.zzjy) {
                return this.zzjx;
            }
            MessageType messagetype = this.zzjx;
            zzfc.zzcy().zzn(messagetype).zze(messagetype);
            this.zzjy = true;
            return this.zzjx;
        }

        /* renamed from: zzby */
        public final MessageType zzca() {
            MessageType messagetype = (zzdj) zzbz();
            boolean booleanValue = Boolean.TRUE.booleanValue();
            byte byteValue = ((Byte) messagetype.zza(zzd.zzka, (Object) null, (Object) null)).byteValue();
            boolean z = true;
            if (byteValue != 1) {
                if (byteValue == 0) {
                    z = false;
                } else {
                    z = zzfc.zzcy().zzn(messagetype).zzm(messagetype);
                    if (booleanValue) {
                        messagetype.zza(zzd.zzkb, (Object) z ? messagetype : null, (Object) null);
                    }
                }
            }
            if (z) {
                return messagetype;
            }
            throw new zzfw(messagetype);
        }

        public final BuilderType zza(MessageType messagetype) {
            zzbw();
            zza(this.zzjx, messagetype);
            return this;
        }

        private static void zza(MessageType messagetype, MessageType messagetype2) {
            zzfc.zzcy().zzn(messagetype).zzc(messagetype, messagetype2);
        }

        public final /* synthetic */ zzby zzah() {
            return (zza) clone();
        }

        public final /* synthetic */ zzeq zzbv() {
            return this.zzjw;
        }

        public /* synthetic */ Object clone() throws CloneNotSupportedException {
            zza zza = (zza) ((zzdj) this.zzjw).zza(zzd.zzke, (Object) null, (Object) null);
            zza.zza((MessageType) (zzdj) zzbz());
            return zza;
        }
    }

    public static class zzb<T extends zzdj<T, ?>> extends zzbz<T> {
        private final T zzjw;

        public zzb(T t) {
            this.zzjw = t;
        }
    }

    public static abstract class zzc<MessageType extends zzc<MessageType, BuilderType>, BuilderType> extends zzdj<MessageType, BuilderType> implements zzes {
        protected zzdc<Object> zzjz = zzdc.zzbh();
    }

    /* 'enum' access flag removed */
    public static final class zzd {
        public static final int zzka = 1;
        public static final int zzkb = 2;
        public static final int zzkc = 3;
        public static final int zzkd = 4;
        public static final int zzke = 5;
        public static final int zzkf = 6;
        public static final int zzkg = 7;
        private static final /* synthetic */ int[] zzkh = {zzka, zzkb, zzkc, zzkd, zzke, zzkf, zzkg};
        public static final int zzki = 1;
        public static final int zzkj = 2;
        private static final /* synthetic */ int[] zzkk = {zzki, zzkj};
        public static final int zzkl = 1;
        public static final int zzkm = 2;
        private static final /* synthetic */ int[] zzkn = {zzkl, zzkm};

        public static int[] zzcb() {
            return (int[]) zzkh.clone();
        }
    }

    /* access modifiers changed from: protected */
    public abstract Object zza(int i, Object obj, Object obj2);

    public String toString() {
        return zzet.zza(this, super.toString());
    }

    public int hashCode() {
        if (this.zzfp != 0) {
            return this.zzfp;
        }
        this.zzfp = zzfc.zzcy().zzn(this).hashCode(this);
        return this.zzfp;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!((zzdj) zza(zzd.zzkf, (Object) null, (Object) null)).getClass().isInstance(obj)) {
            return false;
        }
        return zzfc.zzcy().zzn(this).equals(this, (zzdj) obj);
    }

    public final boolean isInitialized() {
        boolean booleanValue = Boolean.TRUE.booleanValue();
        byte byteValue = ((Byte) zza(zzd.zzka, (Object) null, (Object) null)).byteValue();
        if (byteValue == 1) {
            return true;
        }
        if (byteValue == 0) {
            return false;
        }
        boolean zzm = zzfc.zzcy().zzn(this).zzm(this);
        if (booleanValue) {
            zza(zzd.zzkb, (Object) zzm ? this : null, (Object) null);
        }
        return zzm;
    }

    /* access modifiers changed from: 0000 */
    public final int zzag() {
        return this.zzju;
    }

    /* access modifiers changed from: 0000 */
    public final void zzg(int i) {
        this.zzju = i;
    }

    public final void zzb(zzct zzct) throws IOException {
        zzfc.zzcy().zze(getClass()).zza(this, zzcv.zza(zzct));
    }

    public final int zzbi() {
        if (this.zzju == -1) {
            this.zzju = zzfc.zzcy().zzn(this).zzl(this);
        }
        return this.zzju;
    }

    static <T extends zzdj<?, ?>> T zzc(Class<T> cls) {
        T t = (zzdj) zzjv.get(cls);
        if (t == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                t = (zzdj) zzjv.get(cls);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Class initialization cannot fail.", e);
            }
        }
        if (t == null) {
            t = (zzdj) ((zzdj) zzgd.zzg(cls)).zza(zzd.zzkf, (Object) null, (Object) null);
            if (t != null) {
                zzjv.put(cls, t);
            } else {
                throw new IllegalStateException();
            }
        }
        return t;
    }

    protected static <T extends zzdj<?, ?>> void zza(Class<T> cls, T t) {
        zzjv.put(cls, t);
    }

    protected static Object zza(zzeq zzeq, String str, Object[] objArr) {
        return new zzfe(zzeq, str, objArr);
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

    protected static final <T extends zzdj<T, ?>> boolean zza(T t, boolean z) {
        byte byteValue = ((Byte) t.zza(zzd.zzka, (Object) null, (Object) null)).byteValue();
        if (byteValue == 1) {
            return true;
        }
        if (byteValue == 0) {
            return false;
        }
        return zzfc.zzcy().zzn(t).zzm(t);
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [com.google.android.gms.internal.icing.zzdp, com.google.android.gms.internal.icing.zzef] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v0, types: [com.google.android.gms.internal.icing.zzdp, com.google.android.gms.internal.icing.zzef]
  assigns: [com.google.android.gms.internal.icing.zzef]
  uses: [com.google.android.gms.internal.icing.zzdp]
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
    protected static com.google.android.gms.internal.icing.zzdp zzbq() {
        /*
            com.google.android.gms.internal.icing.zzef r0 = com.google.android.gms.internal.icing.zzef.zzck()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.icing.zzdj.zzbq():com.google.android.gms.internal.icing.zzdp");
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [com.google.android.gms.internal.icing.zzdn, com.google.android.gms.internal.icing.zzcw] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v0, types: [com.google.android.gms.internal.icing.zzdn, com.google.android.gms.internal.icing.zzcw]
  assigns: [com.google.android.gms.internal.icing.zzcw]
  uses: [com.google.android.gms.internal.icing.zzdn]
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
    protected static com.google.android.gms.internal.icing.zzdn zzbr() {
        /*
            com.google.android.gms.internal.icing.zzcw r0 = com.google.android.gms.internal.icing.zzcw.zzaz()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.icing.zzdj.zzbr():com.google.android.gms.internal.icing.zzdn");
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [com.google.android.gms.internal.icing.zzcc, com.google.android.gms.internal.icing.zzdm] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v0, types: [com.google.android.gms.internal.icing.zzcc, com.google.android.gms.internal.icing.zzdm]
  assigns: [com.google.android.gms.internal.icing.zzcc]
  uses: [com.google.android.gms.internal.icing.zzdm]
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
    protected static com.google.android.gms.internal.icing.zzdm zzbs() {
        /*
            com.google.android.gms.internal.icing.zzcc r0 = com.google.android.gms.internal.icing.zzcc.zzan()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.icing.zzdj.zzbs():com.google.android.gms.internal.icing.zzdm");
    }

    protected static <E> zzdq<E> zzbt() {
        return zzfd.zzcz();
    }

    public final /* synthetic */ zzer zzbu() {
        zza zza2 = (zza) zza(zzd.zzke, (Object) null, (Object) null);
        zza2.zza(this);
        return zza2;
    }

    public final /* synthetic */ zzeq zzbv() {
        return (zzdj) zza(zzd.zzkf, (Object) null, (Object) null);
    }
}
