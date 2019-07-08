package kotlin.reflect.jvm.internal.impl.resolve;

import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import org.jetbrains.annotations.Nullable;

/* compiled from: DescriptorEquivalenceForOverrides.kt */
public final class DescriptorEquivalenceForOverrides {
    public static final DescriptorEquivalenceForOverrides INSTANCE = new DescriptorEquivalenceForOverrides();

    private DescriptorEquivalenceForOverrides() {
    }

    public final boolean areEquivalent(@Nullable DeclarationDescriptor declarationDescriptor, @Nullable DeclarationDescriptor declarationDescriptor2) {
        if ((declarationDescriptor instanceof ClassDescriptor) && (declarationDescriptor2 instanceof ClassDescriptor)) {
            return areClassesEquivalent((ClassDescriptor) declarationDescriptor, (ClassDescriptor) declarationDescriptor2);
        }
        if ((declarationDescriptor instanceof TypeParameterDescriptor) && (declarationDescriptor2 instanceof TypeParameterDescriptor)) {
            return areTypeParametersEquivalent$default(this, (TypeParameterDescriptor) declarationDescriptor, (TypeParameterDescriptor) declarationDescriptor2, null, 4, null);
        } else if ((declarationDescriptor instanceof CallableDescriptor) && (declarationDescriptor2 instanceof CallableDescriptor)) {
            return areCallableDescriptorsEquivalent$default(this, (CallableDescriptor) declarationDescriptor, (CallableDescriptor) declarationDescriptor2, false, 4, null);
        } else if (!(declarationDescriptor instanceof PackageFragmentDescriptor) || !(declarationDescriptor2 instanceof PackageFragmentDescriptor)) {
            return Intrinsics.areEqual((Object) declarationDescriptor, (Object) declarationDescriptor2);
        } else {
            return Intrinsics.areEqual((Object) ((PackageFragmentDescriptor) declarationDescriptor).getFqName(), (Object) ((PackageFragmentDescriptor) declarationDescriptor2).getFqName());
        }
    }

    private final boolean areClassesEquivalent(ClassDescriptor classDescriptor, ClassDescriptor classDescriptor2) {
        return Intrinsics.areEqual((Object) classDescriptor.getTypeConstructor(), (Object) classDescriptor2.getTypeConstructor());
    }

    static /* synthetic */ boolean areTypeParametersEquivalent$default(DescriptorEquivalenceForOverrides descriptorEquivalenceForOverrides, TypeParameterDescriptor typeParameterDescriptor, TypeParameterDescriptor typeParameterDescriptor2, Function2 function2, int i, Object obj) {
        if ((i & 4) != 0) {
            function2 = DescriptorEquivalenceForOverrides$areTypeParametersEquivalent$1.INSTANCE;
        }
        return descriptorEquivalenceForOverrides.areTypeParametersEquivalent(typeParameterDescriptor, typeParameterDescriptor2, function2);
    }

    /* access modifiers changed from: private */
    public final boolean areTypeParametersEquivalent(TypeParameterDescriptor typeParameterDescriptor, TypeParameterDescriptor typeParameterDescriptor2, Function2<? super DeclarationDescriptor, ? super DeclarationDescriptor, Boolean> function2) {
        boolean z = true;
        if (Intrinsics.areEqual((Object) typeParameterDescriptor, (Object) typeParameterDescriptor2)) {
            return true;
        }
        if (Intrinsics.areEqual((Object) typeParameterDescriptor.getContainingDeclaration(), (Object) typeParameterDescriptor2.getContainingDeclaration()) || !ownersEquivalent(typeParameterDescriptor, typeParameterDescriptor2, function2)) {
            return false;
        }
        if (typeParameterDescriptor.getIndex() != typeParameterDescriptor2.getIndex()) {
            z = false;
        }
        return z;
    }

    public static /* synthetic */ boolean areCallableDescriptorsEquivalent$default(DescriptorEquivalenceForOverrides descriptorEquivalenceForOverrides, CallableDescriptor callableDescriptor, CallableDescriptor callableDescriptor2, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        return descriptorEquivalenceForOverrides.areCallableDescriptorsEquivalent(callableDescriptor, callableDescriptor2, z);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0084, code lost:
        if (r7.getResult() == kotlin.reflect.jvm.internal.impl.resolve.OverridingUtil.OverrideCompatibilityInfo.Result.OVERRIDABLE) goto L_0x0088;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean areCallableDescriptorsEquivalent(@org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor r7, @org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor r8, boolean r9) {
        /*
            r6 = this;
            java.lang.String r0 = "a"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r7, r0)
            java.lang.String r0 = "b"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r8, r0)
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r7, r8)
            r1 = 1
            if (r0 == 0) goto L_0x0012
            return r1
        L_0x0012:
            kotlin.reflect.jvm.internal.impl.name.Name r0 = r7.getName()
            kotlin.reflect.jvm.internal.impl.name.Name r2 = r8.getName()
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r2)
            r0 = r0 ^ r1
            r2 = 0
            if (r0 == 0) goto L_0x0023
            return r2
        L_0x0023:
            kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor r0 = r7.getContainingDeclaration()
            kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor r3 = r8.getContainingDeclaration()
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r3)
            if (r0 == 0) goto L_0x0032
            return r2
        L_0x0032:
            r0 = r7
            kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor r0 = (kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor) r0
            boolean r3 = kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils.isLocal(r0)
            if (r3 != 0) goto L_0x0089
            r3 = r8
            kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor r3 = (kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor) r3
            boolean r4 = kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils.isLocal(r3)
            if (r4 == 0) goto L_0x0045
            goto L_0x0089
        L_0x0045:
            kotlin.reflect.jvm.internal.impl.resolve.DescriptorEquivalenceForOverrides$areCallableDescriptorsEquivalent$1 r4 = kotlin.reflect.jvm.internal.impl.resolve.DescriptorEquivalenceForOverrides$areCallableDescriptorsEquivalent$1.INSTANCE
            kotlin.jvm.functions.Function2 r4 = (kotlin.jvm.functions.Function2) r4
            boolean r0 = r6.ownersEquivalent(r0, r3, r4)
            if (r0 != 0) goto L_0x0050
            return r2
        L_0x0050:
            kotlin.reflect.jvm.internal.impl.resolve.DescriptorEquivalenceForOverrides$areCallableDescriptorsEquivalent$overridingUtil$1 r0 = new kotlin.reflect.jvm.internal.impl.resolve.DescriptorEquivalenceForOverrides$areCallableDescriptorsEquivalent$overridingUtil$1
            r0.<init>(r7, r8)
            kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeChecker$TypeConstructorEquality r0 = (kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeChecker.TypeConstructorEquality) r0
            kotlin.reflect.jvm.internal.impl.resolve.OverridingUtil r0 = kotlin.reflect.jvm.internal.impl.resolve.OverridingUtil.createWithEqualityAxioms(r0)
            java.lang.String r3 = "OverridingUtil.createWit…= a && y == b})\n        }"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r3)
            r3 = r9 ^ 1
            r4 = 0
            kotlin.reflect.jvm.internal.impl.resolve.OverridingUtil$OverrideCompatibilityInfo r3 = r0.isOverridableBy(r7, r8, r4, r3)
            java.lang.String r5 = "overridingUtil.isOverrid… null, !ignoreReturnType)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r3, r5)
            kotlin.reflect.jvm.internal.impl.resolve.OverridingUtil$OverrideCompatibilityInfo$Result r3 = r3.getResult()
            kotlin.reflect.jvm.internal.impl.resolve.OverridingUtil$OverrideCompatibilityInfo$Result r5 = kotlin.reflect.jvm.internal.impl.resolve.OverridingUtil.OverrideCompatibilityInfo.Result.OVERRIDABLE
            if (r3 != r5) goto L_0x0087
            r9 = r9 ^ r1
            kotlin.reflect.jvm.internal.impl.resolve.OverridingUtil$OverrideCompatibilityInfo r7 = r0.isOverridableBy(r8, r7, r4, r9)
            java.lang.String r8 = "overridingUtil.isOverrid… null, !ignoreReturnType)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r7, r8)
            kotlin.reflect.jvm.internal.impl.resolve.OverridingUtil$OverrideCompatibilityInfo$Result r7 = r7.getResult()
            kotlin.reflect.jvm.internal.impl.resolve.OverridingUtil$OverrideCompatibilityInfo$Result r8 = kotlin.reflect.jvm.internal.impl.resolve.OverridingUtil.OverrideCompatibilityInfo.Result.OVERRIDABLE
            if (r7 != r8) goto L_0x0087
            goto L_0x0088
        L_0x0087:
            r1 = 0
        L_0x0088:
            return r1
        L_0x0089:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.resolve.DescriptorEquivalenceForOverrides.areCallableDescriptorsEquivalent(kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor, kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor, boolean):boolean");
    }

    private final boolean ownersEquivalent(DeclarationDescriptor declarationDescriptor, DeclarationDescriptor declarationDescriptor2, Function2<? super DeclarationDescriptor, ? super DeclarationDescriptor, Boolean> function2) {
        DeclarationDescriptor containingDeclaration = declarationDescriptor.getContainingDeclaration();
        DeclarationDescriptor containingDeclaration2 = declarationDescriptor2.getContainingDeclaration();
        if ((containingDeclaration instanceof CallableMemberDescriptor) || (containingDeclaration2 instanceof CallableMemberDescriptor)) {
            return ((Boolean) function2.invoke(containingDeclaration, containingDeclaration2)).booleanValue();
        }
        return areEquivalent(containingDeclaration, containingDeclaration2);
    }
}
