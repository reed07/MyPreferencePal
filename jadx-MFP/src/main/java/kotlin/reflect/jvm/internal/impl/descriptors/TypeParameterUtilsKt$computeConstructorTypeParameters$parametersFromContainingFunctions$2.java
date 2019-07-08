package kotlin.reflect.jvm.internal.impl.descriptors;

import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.sequences.Sequence;
import org.jetbrains.annotations.NotNull;

/* compiled from: typeParameterUtils.kt */
final class TypeParameterUtilsKt$computeConstructorTypeParameters$parametersFromContainingFunctions$2 extends Lambda implements Function1<DeclarationDescriptor, Sequence<? extends TypeParameterDescriptor>> {
    public static final TypeParameterUtilsKt$computeConstructorTypeParameters$parametersFromContainingFunctions$2 INSTANCE = new TypeParameterUtilsKt$computeConstructorTypeParameters$parametersFromContainingFunctions$2();

    TypeParameterUtilsKt$computeConstructorTypeParameters$parametersFromContainingFunctions$2() {
        super(1);
    }

    @NotNull
    public final Sequence<TypeParameterDescriptor> invoke(@NotNull DeclarationDescriptor declarationDescriptor) {
        Intrinsics.checkParameterIsNotNull(declarationDescriptor, "it");
        List typeParameters = ((CallableDescriptor) declarationDescriptor).getTypeParameters();
        Intrinsics.checkExpressionValueIsNotNull(typeParameters, "(it as CallableDescriptor).typeParameters");
        return CollectionsKt.asSequence(typeParameters);
    }
}
