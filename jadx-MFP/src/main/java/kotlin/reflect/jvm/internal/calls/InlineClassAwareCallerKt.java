package kotlin.reflect.jvm.internal.calls;

import kotlin.Metadata;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u001a4\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bH\u0000¨\u0006\t"}, d2 = {"createInlineClassAwareCallerIfNeeded", "Lkotlin/reflect/jvm/internal/calls/Caller;", "M", "Ljava/lang/reflect/Member;", "Lkotlin/reflect/jvm/internal/calls/CallerImpl;", "descriptor", "Lkotlin/reflect/jvm/internal/impl/descriptors/CallableMemberDescriptor;", "isDefault", "", "kotlin-reflect-api"}, k = 2, mv = {1, 1, 13})
/* compiled from: InlineClassAwareCaller.kt */
public final class InlineClassAwareCallerKt {
    @NotNull
    public static /* synthetic */ Caller createInlineClassAwareCallerIfNeeded$default(CallerImpl callerImpl, CallableMemberDescriptor callableMemberDescriptor, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return createInlineClassAwareCallerIfNeeded(callerImpl, callableMemberDescriptor, z);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x006f, code lost:
        if (kotlin.reflect.jvm.internal.impl.resolve.InlineClassesUtilsKt.isInlineClassType(r0) == true) goto L_0x0071;
     */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0074  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x007c  */
    @org.jetbrains.annotations.NotNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <M extends java.lang.reflect.Member> kotlin.reflect.jvm.internal.calls.Caller<M> createInlineClassAwareCallerIfNeeded(@org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.calls.CallerImpl<? extends M> r5, @org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor r6, boolean r7) {
        /*
            java.lang.String r0 = "receiver$0"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r5, r0)
            java.lang.String r0 = "descriptor"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r6, r0)
            java.util.List r0 = r6.getValueParameters()
            java.lang.String r1 = "descriptor.valueParameters"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            boolean r1 = r0 instanceof java.util.Collection
            r2 = 0
            r3 = 1
            if (r1 == 0) goto L_0x0026
            r1 = r0
            java.util.Collection r1 = (java.util.Collection) r1
            boolean r1 = r1.isEmpty()
            if (r1 == 0) goto L_0x0026
            r0 = 0
            goto L_0x004d
        L_0x0026:
            java.util.Iterator r0 = r0.iterator()
        L_0x002a:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x004c
            java.lang.Object r1 = r0.next()
            kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor r1 = (kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor) r1
            java.lang.String r4 = "it"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r4)
            kotlin.reflect.jvm.internal.impl.types.KotlinType r1 = r1.getType()
            java.lang.String r4 = "it.type"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r4)
            boolean r1 = kotlin.reflect.jvm.internal.impl.resolve.InlineClassesUtilsKt.isInlineClassType(r1)
            if (r1 == 0) goto L_0x002a
            r0 = 1
            goto L_0x004d
        L_0x004c:
            r0 = 0
        L_0x004d:
            if (r0 != 0) goto L_0x0071
            kotlin.reflect.jvm.internal.impl.types.KotlinType r0 = r6.getReturnType()
            if (r0 == 0) goto L_0x005b
            boolean r0 = kotlin.reflect.jvm.internal.impl.resolve.InlineClassesUtilsKt.isInlineClassType(r0)
            if (r0 == r3) goto L_0x0071
        L_0x005b:
            boolean r0 = r5 instanceof kotlin.reflect.jvm.internal.calls.BoundCaller
            if (r0 != 0) goto L_0x0072
            kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor r0 = r6.getExtensionReceiverParameter()
            if (r0 == 0) goto L_0x0072
            kotlin.reflect.jvm.internal.impl.types.KotlinType r0 = r0.getType()
            if (r0 == 0) goto L_0x0072
            boolean r0 = kotlin.reflect.jvm.internal.impl.resolve.InlineClassesUtilsKt.isInlineClassType(r0)
            if (r0 != r3) goto L_0x0072
        L_0x0071:
            r2 = 1
        L_0x0072:
            if (r2 == 0) goto L_0x007c
            kotlin.reflect.jvm.internal.calls.InlineClassAwareCaller r0 = new kotlin.reflect.jvm.internal.calls.InlineClassAwareCaller
            r0.<init>(r6, r5, r7)
            kotlin.reflect.jvm.internal.calls.Caller r0 = (kotlin.reflect.jvm.internal.calls.Caller) r0
            goto L_0x007f
        L_0x007c:
            r0 = r5
            kotlin.reflect.jvm.internal.calls.Caller r0 = (kotlin.reflect.jvm.internal.calls.Caller) r0
        L_0x007f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.calls.InlineClassAwareCallerKt.createInlineClassAwareCallerIfNeeded(kotlin.reflect.jvm.internal.calls.CallerImpl, kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor, boolean):kotlin.reflect.jvm.internal.calls.Caller");
    }
}
