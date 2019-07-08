package kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil;

import kotlin.reflect.jvm.internal.impl.utils.DFS.Neighbors;

/* compiled from: DescriptorUtils.kt */
final class DescriptorUtilsKt$firstOverridden$1<N> implements Neighbors<N> {
    final /* synthetic */ boolean $useOriginal;

    DescriptorUtilsKt$firstOverridden$1(boolean z) {
        this.$useOriginal = z;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0012, code lost:
        if (r2 != null) goto L_0x0019;
     */
    @org.jetbrains.annotations.NotNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Iterable<kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor> getNeighbors(kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor r2) {
        /*
            r1 = this;
            boolean r0 = r1.$useOriginal
            if (r0 == 0) goto L_0x000c
            if (r2 == 0) goto L_0x000b
            kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor r2 = r2.getOriginal()
            goto L_0x000c
        L_0x000b:
            r2 = 0
        L_0x000c:
            if (r2 == 0) goto L_0x0015
            java.util.Collection r2 = r2.getOverriddenDescriptors()
            if (r2 == 0) goto L_0x0015
            goto L_0x0019
        L_0x0015:
            java.util.List r2 = kotlin.collections.CollectionsKt.emptyList()
        L_0x0019:
            java.lang.Iterable r2 = (java.lang.Iterable) r2
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt$firstOverridden$1.getNeighbors(kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor):java.lang.Iterable");
    }
}
