package kotlin.reflect.full;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SpreadBuilder;
import kotlin.reflect.KCallable;
import kotlin.reflect.KFunction;
import kotlin.reflect.KParameter;
import kotlin.reflect.KParameter.Kind;
import kotlin.reflect.jvm.internal.KCallableImpl;
import kotlin.reflect.jvm.internal.KotlinReflectionInternalError;
import kotlin.reflect.jvm.internal.UtilKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\u001a9\u0010\u000f\u001a\u0002H\u0010\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\u00022\u0016\u0010\u0011\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00130\u0012\"\u0004\u0018\u00010\u0013H@ø\u0001\u0000¢\u0006\u0002\u0010\u0014\u001a7\u0010\u0015\u001a\u0002H\u0010\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\u00022\u0014\u0010\u0011\u001a\u0010\u0012\u0004\u0012\u00020\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00130\u0016H@ø\u0001\u0000¢\u0006\u0002\u0010\u0017\u001a\u001a\u0010\u0018\u001a\u0004\u0018\u00010\u0001*\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010\u0019\u001a\u00020\u001aH\u0007\"$\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u0006\u0012\u0002\b\u00030\u00028FX\u0004¢\u0006\f\u0012\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"$\u0010\u0007\u001a\u0004\u0018\u00010\u0001*\u0006\u0012\u0002\b\u00030\u00028FX\u0004¢\u0006\f\u0012\u0004\b\b\u0010\u0004\u001a\u0004\b\t\u0010\u0006\"(\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00010\u000b*\u0006\u0012\u0002\b\u00030\u00028FX\u0004¢\u0006\f\u0012\u0004\b\f\u0010\u0004\u001a\u0004\b\r\u0010\u000e\u0002\u0004\n\u0002\b\u0019¨\u0006\u001b"}, d2 = {"extensionReceiverParameter", "Lkotlin/reflect/KParameter;", "Lkotlin/reflect/KCallable;", "extensionReceiverParameter$annotations", "(Lkotlin/reflect/KCallable;)V", "getExtensionReceiverParameter", "(Lkotlin/reflect/KCallable;)Lkotlin/reflect/KParameter;", "instanceParameter", "instanceParameter$annotations", "getInstanceParameter", "valueParameters", "", "valueParameters$annotations", "getValueParameters", "(Lkotlin/reflect/KCallable;)Ljava/util/List;", "callSuspend", "R", "args", "", "", "(Lkotlin/reflect/KCallable;[Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "callSuspendBy", "", "(Lkotlin/reflect/KCallable;Ljava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "findParameterByName", "name", "", "kotlin-reflect-api"}, k = 2, mv = {1, 1, 13})
@JvmName(name = "KCallables")
/* compiled from: KCallables.kt */
public final class KCallables {
    @SinceKotlin(version = "1.1")
    public static /* synthetic */ void extensionReceiverParameter$annotations(KCallable kCallable) {
    }

    @SinceKotlin(version = "1.1")
    public static /* synthetic */ void instanceParameter$annotations(KCallable kCallable) {
    }

    @SinceKotlin(version = "1.1")
    public static /* synthetic */ void valueParameters$annotations(KCallable kCallable) {
    }

    /* JADX WARNING: type inference failed for: r3v0 */
    /* JADX WARNING: type inference failed for: r4v1, types: [java.lang.Object] */
    /* JADX WARNING: type inference failed for: r5v0 */
    /* JADX WARNING: type inference failed for: r3v2 */
    /* JADX WARNING: type inference failed for: r3v3 */
    /* JADX WARNING: type inference failed for: r3v4 */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r3v2
  assigns: []
  uses: []
  mth insns count: 27
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
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 3 */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final kotlin.reflect.KParameter getInstanceParameter(@org.jetbrains.annotations.NotNull kotlin.reflect.KCallable<?> r8) {
        /*
            java.lang.String r0 = "receiver$0"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r8, r0)
            java.util.List r8 = r8.getParameters()
            java.lang.Iterable r8 = (java.lang.Iterable) r8
            java.util.Iterator r8 = r8.iterator()
            r0 = 0
            r1 = 0
            r3 = r1
            r2 = 0
        L_0x0013:
            boolean r4 = r8.hasNext()
            if (r4 == 0) goto L_0x0034
            java.lang.Object r4 = r8.next()
            r5 = r4
            kotlin.reflect.KParameter r5 = (kotlin.reflect.KParameter) r5
            kotlin.reflect.KParameter$Kind r5 = r5.getKind()
            kotlin.reflect.KParameter$Kind r6 = kotlin.reflect.KParameter.Kind.INSTANCE
            r7 = 1
            if (r5 != r6) goto L_0x002b
            r5 = 1
            goto L_0x002c
        L_0x002b:
            r5 = 0
        L_0x002c:
            if (r5 == 0) goto L_0x0013
            if (r2 == 0) goto L_0x0031
            goto L_0x0038
        L_0x0031:
            r3 = r4
            r2 = 1
            goto L_0x0013
        L_0x0034:
            if (r2 != 0) goto L_0x0037
            goto L_0x0038
        L_0x0037:
            r1 = r3
        L_0x0038:
            kotlin.reflect.KParameter r1 = (kotlin.reflect.KParameter) r1
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.full.KCallables.getInstanceParameter(kotlin.reflect.KCallable):kotlin.reflect.KParameter");
    }

    /* JADX WARNING: type inference failed for: r3v0 */
    /* JADX WARNING: type inference failed for: r4v1, types: [java.lang.Object] */
    /* JADX WARNING: type inference failed for: r5v0 */
    /* JADX WARNING: type inference failed for: r3v2 */
    /* JADX WARNING: type inference failed for: r3v3 */
    /* JADX WARNING: type inference failed for: r3v4 */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r3v2
  assigns: []
  uses: []
  mth insns count: 27
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
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 3 */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final kotlin.reflect.KParameter getExtensionReceiverParameter(@org.jetbrains.annotations.NotNull kotlin.reflect.KCallable<?> r8) {
        /*
            java.lang.String r0 = "receiver$0"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r8, r0)
            java.util.List r8 = r8.getParameters()
            java.lang.Iterable r8 = (java.lang.Iterable) r8
            java.util.Iterator r8 = r8.iterator()
            r0 = 0
            r1 = 0
            r3 = r1
            r2 = 0
        L_0x0013:
            boolean r4 = r8.hasNext()
            if (r4 == 0) goto L_0x0034
            java.lang.Object r4 = r8.next()
            r5 = r4
            kotlin.reflect.KParameter r5 = (kotlin.reflect.KParameter) r5
            kotlin.reflect.KParameter$Kind r5 = r5.getKind()
            kotlin.reflect.KParameter$Kind r6 = kotlin.reflect.KParameter.Kind.EXTENSION_RECEIVER
            r7 = 1
            if (r5 != r6) goto L_0x002b
            r5 = 1
            goto L_0x002c
        L_0x002b:
            r5 = 0
        L_0x002c:
            if (r5 == 0) goto L_0x0013
            if (r2 == 0) goto L_0x0031
            goto L_0x0038
        L_0x0031:
            r3 = r4
            r2 = 1
            goto L_0x0013
        L_0x0034:
            if (r2 != 0) goto L_0x0037
            goto L_0x0038
        L_0x0037:
            r1 = r3
        L_0x0038:
            kotlin.reflect.KParameter r1 = (kotlin.reflect.KParameter) r1
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.full.KCallables.getExtensionReceiverParameter(kotlin.reflect.KCallable):kotlin.reflect.KParameter");
    }

    @NotNull
    public static final List<KParameter> getValueParameters(@NotNull KCallable<?> kCallable) {
        Intrinsics.checkParameterIsNotNull(kCallable, "receiver$0");
        Iterable parameters = kCallable.getParameters();
        Collection arrayList = new ArrayList();
        for (Object next : parameters) {
            if (((KParameter) next).getKind() == Kind.VALUE) {
                arrayList.add(next);
            }
        }
        return (List) arrayList;
    }

    /* JADX WARNING: type inference failed for: r2v0 */
    /* JADX WARNING: type inference failed for: r3v1, types: [java.lang.Object] */
    /* JADX WARNING: type inference failed for: r4v0 */
    /* JADX WARNING: type inference failed for: r2v2 */
    /* JADX WARNING: type inference failed for: r2v3 */
    /* JADX WARNING: type inference failed for: r2v4 */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r2v2
  assigns: []
  uses: []
  mth insns count: 26
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
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 3 */
    @kotlin.SinceKotlin(version = "1.1")
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final kotlin.reflect.KParameter findParameterByName(@org.jetbrains.annotations.NotNull kotlin.reflect.KCallable<?> r5, @org.jetbrains.annotations.NotNull java.lang.String r6) {
        /*
            java.lang.String r0 = "receiver$0"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r5, r0)
            java.lang.String r0 = "name"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r6, r0)
            java.util.List r5 = r5.getParameters()
            java.lang.Iterable r5 = (java.lang.Iterable) r5
            java.util.Iterator r5 = r5.iterator()
            r0 = 0
            r1 = 0
            r2 = r0
        L_0x0017:
            boolean r3 = r5.hasNext()
            if (r3 == 0) goto L_0x0034
            java.lang.Object r3 = r5.next()
            r4 = r3
            kotlin.reflect.KParameter r4 = (kotlin.reflect.KParameter) r4
            java.lang.String r4 = r4.getName()
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual(r4, r6)
            if (r4 == 0) goto L_0x0017
            if (r1 == 0) goto L_0x0031
            goto L_0x0038
        L_0x0031:
            r1 = 1
            r2 = r3
            goto L_0x0017
        L_0x0034:
            if (r1 != 0) goto L_0x0037
            goto L_0x0038
        L_0x0037:
            r0 = r2
        L_0x0038:
            kotlin.reflect.KParameter r0 = (kotlin.reflect.KParameter) r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.full.KCallables.findParameterByName(kotlin.reflect.KCallable, java.lang.String):kotlin.reflect.KParameter");
    }

    @SinceKotlin(version = "1.3")
    @Nullable
    public static final <R> Object callSuspend(@NotNull KCallable<? extends R> kCallable, @NotNull Object[] objArr, @NotNull Continuation<? super R> continuation) {
        if (!kCallable.isSuspend()) {
            return kCallable.call(Arrays.copyOf(objArr, objArr.length));
        }
        if (kCallable instanceof KFunction) {
            SpreadBuilder spreadBuilder = new SpreadBuilder(2);
            spreadBuilder.addSpread(objArr);
            spreadBuilder.add(continuation);
            Object call = kCallable.call(spreadBuilder.toArray(new Object[spreadBuilder.size()]));
            if (call == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                DebugProbesKt.probeCoroutineSuspended(continuation);
            }
            return call;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Cannot callSuspend on a property ");
        sb.append(kCallable);
        sb.append(": suspend properties are not supported yet");
        throw new IllegalArgumentException(sb.toString());
    }

    @SinceKotlin(version = "1.3")
    @Nullable
    public static final <R> Object callSuspendBy(@NotNull KCallable<? extends R> kCallable, @NotNull Map<KParameter, ? extends Object> map, @NotNull Continuation<? super R> continuation) {
        if (!kCallable.isSuspend()) {
            return kCallable.callBy(map);
        }
        if (kCallable instanceof KFunction) {
            KCallableImpl asKCallableImpl = UtilKt.asKCallableImpl(kCallable);
            if (asKCallableImpl != null) {
                Object callDefaultMethod$kotlin_reflect_api = asKCallableImpl.callDefaultMethod$kotlin_reflect_api(map, continuation);
                if (callDefaultMethod$kotlin_reflect_api == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                    DebugProbesKt.probeCoroutineSuspended(continuation);
                }
                return callDefaultMethod$kotlin_reflect_api;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("This callable does not support a default call: ");
            sb.append(kCallable);
            throw new KotlinReflectionInternalError(sb.toString());
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Cannot callSuspendBy on a property ");
        sb2.append(kCallable);
        sb2.append(": suspend properties are not supported yet");
        throw new IllegalArgumentException(sb2.toString());
    }
}
