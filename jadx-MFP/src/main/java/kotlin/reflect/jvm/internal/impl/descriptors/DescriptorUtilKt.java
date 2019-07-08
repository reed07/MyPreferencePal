package kotlin.reflect.jvm.internal.impl.descriptors;

/* compiled from: descriptorUtil.kt */
public final class DescriptorUtilKt {
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0067  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor resolveClassByFqName(@org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor r4, @org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.name.FqName r5, @org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation r6) {
        /*
            java.lang.String r0 = "receiver$0"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r4, r0)
            java.lang.String r0 = "fqName"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r5, r0)
            java.lang.String r0 = "lookupLocation"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r6, r0)
            boolean r0 = r5.isRoot()
            r1 = 0
            if (r0 == 0) goto L_0x0017
            return r1
        L_0x0017:
            kotlin.reflect.jvm.internal.impl.name.FqName r0 = r5.parent()
            java.lang.String r2 = "fqName.parent()"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r2)
            kotlin.reflect.jvm.internal.impl.descriptors.PackageViewDescriptor r0 = r4.getPackage(r0)
            kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope r0 = r0.getMemberScope()
            kotlin.reflect.jvm.internal.impl.name.Name r2 = r5.shortName()
            java.lang.String r3 = "fqName.shortName()"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r3)
            kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor r0 = r0.getContributedClassifier(r2, r6)
            boolean r2 = r0 instanceof kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
            if (r2 != 0) goto L_0x003a
            r0 = r1
        L_0x003a:
            kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor r0 = (kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor) r0
            if (r0 == 0) goto L_0x003f
            return r0
        L_0x003f:
            kotlin.reflect.jvm.internal.impl.name.FqName r0 = r5.parent()
            java.lang.String r2 = "fqName.parent()"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r2)
            kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor r4 = resolveClassByFqName(r4, r0, r6)
            if (r4 == 0) goto L_0x0062
            kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope r4 = r4.getUnsubstitutedInnerClassesScope()
            if (r4 == 0) goto L_0x0062
            kotlin.reflect.jvm.internal.impl.name.Name r5 = r5.shortName()
            java.lang.String r0 = "fqName.shortName()"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, r0)
            kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor r4 = r4.getContributedClassifier(r5, r6)
            goto L_0x0063
        L_0x0062:
            r4 = r1
        L_0x0063:
            boolean r5 = r4 instanceof kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
            if (r5 != 0) goto L_0x0068
            r4 = r1
        L_0x0068:
            kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor r4 = (kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor) r4
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.descriptors.DescriptorUtilKt.resolveClassByFqName(kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor, kotlin.reflect.jvm.internal.impl.name.FqName, kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation):kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor");
    }
}
