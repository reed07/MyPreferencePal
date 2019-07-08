package kotlin.reflect.jvm.internal.impl.types;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptorWithTypeParameters;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import org.jetbrains.annotations.NotNull;

/* compiled from: StarProjectionImpl.kt */
public final class StarProjectionImplKt {
    @NotNull
    public static final KotlinType starProjectionType(@NotNull TypeParameterDescriptor typeParameterDescriptor) {
        Intrinsics.checkParameterIsNotNull(typeParameterDescriptor, "receiver$0");
        DeclarationDescriptor containingDeclaration = typeParameterDescriptor.getContainingDeclaration();
        if (containingDeclaration != null) {
            TypeConstructor typeConstructor = ((ClassifierDescriptorWithTypeParameters) containingDeclaration).getTypeConstructor();
            Intrinsics.checkExpressionValueIsNotNull(typeConstructor, "classDescriptor.typeConstructor");
            List parameters = typeConstructor.getParameters();
            Intrinsics.checkExpressionValueIsNotNull(parameters, "classDescriptor.typeConstructor.parameters");
            Iterable<TypeParameterDescriptor> iterable = parameters;
            Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
            for (TypeParameterDescriptor typeParameterDescriptor2 : iterable) {
                Intrinsics.checkExpressionValueIsNotNull(typeParameterDescriptor2, "it");
                arrayList.add(typeParameterDescriptor2.getTypeConstructor());
            }
            TypeSubstitutor create = TypeSubstitutor.create((TypeSubstitution) new StarProjectionImplKt$starProjectionType$1((List) arrayList));
            List upperBounds = typeParameterDescriptor.getUpperBounds();
            Intrinsics.checkExpressionValueIsNotNull(upperBounds, "this.upperBounds");
            KotlinType substitute = create.substitute((KotlinType) CollectionsKt.first(upperBounds), Variance.OUT_VARIANCE);
            if (substitute != null) {
                return substitute;
            }
            SimpleType defaultBound = DescriptorUtilsKt.getBuiltIns(typeParameterDescriptor).getDefaultBound();
            Intrinsics.checkExpressionValueIsNotNull(defaultBound, "builtIns.defaultBound");
            return defaultBound;
        }
        throw new TypeCastException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassifierDescriptorWithTypeParameters");
    }
}
