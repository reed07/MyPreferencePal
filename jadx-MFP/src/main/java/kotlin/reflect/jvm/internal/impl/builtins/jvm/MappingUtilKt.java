package kotlin.reflect.jvm.internal.impl.builtins.jvm;

import com.myfitnesspal.shared.constants.SharedConstants.Params;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Pair;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructorSubstitution;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructorSubstitution.Companion;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import org.jetbrains.annotations.NotNull;

/* compiled from: mappingUtil.kt */
public final class MappingUtilKt {
    @NotNull
    public static final TypeConstructorSubstitution createMappedTypeParametersSubstitution(@NotNull ClassDescriptor classDescriptor, @NotNull ClassDescriptor classDescriptor2) {
        Intrinsics.checkParameterIsNotNull(classDescriptor, Params.FROM);
        Intrinsics.checkParameterIsNotNull(classDescriptor2, "to");
        boolean z = classDescriptor.getDeclaredTypeParameters().size() == classDescriptor2.getDeclaredTypeParameters().size();
        if (!_Assertions.ENABLED || z) {
            Companion companion = TypeConstructorSubstitution.Companion;
            List declaredTypeParameters = classDescriptor.getDeclaredTypeParameters();
            Intrinsics.checkExpressionValueIsNotNull(declaredTypeParameters, "from.declaredTypeParameters");
            Iterable<TypeParameterDescriptor> iterable = declaredTypeParameters;
            Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
            for (TypeParameterDescriptor typeConstructor : iterable) {
                arrayList.add(typeConstructor.getTypeConstructor());
            }
            Iterable iterable2 = (List) arrayList;
            List declaredTypeParameters2 = classDescriptor2.getDeclaredTypeParameters();
            Intrinsics.checkExpressionValueIsNotNull(declaredTypeParameters2, "to.declaredTypeParameters");
            Iterable<TypeParameterDescriptor> iterable3 = declaredTypeParameters2;
            Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable3, 10));
            for (TypeParameterDescriptor typeParameterDescriptor : iterable3) {
                Intrinsics.checkExpressionValueIsNotNull(typeParameterDescriptor, "it");
                SimpleType defaultType = typeParameterDescriptor.getDefaultType();
                Intrinsics.checkExpressionValueIsNotNull(defaultType, "it.defaultType");
                arrayList2.add(TypeUtilsKt.asTypeProjection(defaultType));
            }
            return Companion.createByConstructorsMap$default(companion, MapsKt.toMap((Iterable<? extends Pair<? extends K, ? extends V>>) CollectionsKt.zip(iterable2, (Iterable<? extends R>) (List) arrayList2)), false, 2, null);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(classDescriptor);
        sb.append(" and ");
        sb.append(classDescriptor2);
        sb.append(" should have same number of type parameters, ");
        sb.append("but ");
        sb.append(classDescriptor.getDeclaredTypeParameters().size());
        sb.append(" / ");
        sb.append(classDescriptor2.getDeclaredTypeParameters().size());
        sb.append(" found");
        throw new AssertionError(sb.toString());
    }
}
