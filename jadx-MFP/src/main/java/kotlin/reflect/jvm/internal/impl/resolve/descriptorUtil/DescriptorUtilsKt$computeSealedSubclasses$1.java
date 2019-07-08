package kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil;

import java.util.LinkedHashSet;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.DescriptorKindFilter;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope.DefaultImpls;
import org.jetbrains.annotations.NotNull;

/* compiled from: DescriptorUtils.kt */
final class DescriptorUtilsKt$computeSealedSubclasses$1 extends Lambda implements Function2<MemberScope, Boolean, Unit> {
    final /* synthetic */ LinkedHashSet $result;
    final /* synthetic */ ClassDescriptor $sealedClass;

    DescriptorUtilsKt$computeSealedSubclasses$1(ClassDescriptor classDescriptor, LinkedHashSet linkedHashSet) {
        this.$sealedClass = classDescriptor;
        this.$result = linkedHashSet;
        super(2);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((MemberScope) obj, ((Boolean) obj2).booleanValue());
        return Unit.INSTANCE;
    }

    public final void invoke(@NotNull MemberScope memberScope, boolean z) {
        Intrinsics.checkParameterIsNotNull(memberScope, "scope");
        for (DeclarationDescriptor declarationDescriptor : DefaultImpls.getContributedDescriptors$default(memberScope, DescriptorKindFilter.CLASSIFIERS, null, 2, null)) {
            if (declarationDescriptor instanceof ClassDescriptor) {
                ClassDescriptor classDescriptor = (ClassDescriptor) declarationDescriptor;
                if (DescriptorUtils.isDirectSubclass(classDescriptor, this.$sealedClass)) {
                    this.$result.add(declarationDescriptor);
                }
                if (z) {
                    DescriptorUtilsKt$computeSealedSubclasses$1 descriptorUtilsKt$computeSealedSubclasses$1 = this;
                    MemberScope unsubstitutedInnerClassesScope = classDescriptor.getUnsubstitutedInnerClassesScope();
                    Intrinsics.checkExpressionValueIsNotNull(unsubstitutedInnerClassesScope, "descriptor.unsubstitutedInnerClassesScope");
                    descriptorUtilsKt$computeSealedSubclasses$1.invoke(unsubstitutedInnerClassesScope, z);
                }
            }
        }
    }
}
