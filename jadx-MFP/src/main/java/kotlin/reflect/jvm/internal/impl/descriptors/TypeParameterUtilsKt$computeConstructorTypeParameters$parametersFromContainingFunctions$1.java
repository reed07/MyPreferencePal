package kotlin.reflect.jvm.internal.impl.descriptors;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

/* compiled from: typeParameterUtils.kt */
final class TypeParameterUtilsKt$computeConstructorTypeParameters$parametersFromContainingFunctions$1 extends Lambda implements Function1<DeclarationDescriptor, Boolean> {
    public static final TypeParameterUtilsKt$computeConstructorTypeParameters$parametersFromContainingFunctions$1 INSTANCE = new TypeParameterUtilsKt$computeConstructorTypeParameters$parametersFromContainingFunctions$1();

    TypeParameterUtilsKt$computeConstructorTypeParameters$parametersFromContainingFunctions$1() {
        super(1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        return Boolean.valueOf(invoke((DeclarationDescriptor) obj));
    }

    public final boolean invoke(@NotNull DeclarationDescriptor declarationDescriptor) {
        Intrinsics.checkParameterIsNotNull(declarationDescriptor, "it");
        return declarationDescriptor instanceof CallableDescriptor;
    }
}
