package kotlin.reflect.jvm.internal.impl.types.checker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructorSubstitution;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import kotlin.reflect.jvm.internal.impl.utils.FunctionsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: NewCapturedType.kt */
public final class NewCapturedTypeKt {
    @Nullable
    public static /* synthetic */ SimpleType captureFromArguments$default(SimpleType simpleType, CaptureStatus captureStatus, Function2 function2, int i, Object obj) {
        if ((i & 4) != 0) {
            function2 = FunctionsKt.getDO_NOTHING_2();
        }
        return captureFromArguments(simpleType, captureStatus, function2);
    }

    @Nullable
    public static final SimpleType captureFromArguments(@NotNull SimpleType simpleType, @NotNull CaptureStatus captureStatus, @NotNull Function2<? super Integer, ? super NewCapturedType, Unit> function2) {
        boolean z;
        Intrinsics.checkParameterIsNotNull(simpleType, "type");
        Intrinsics.checkParameterIsNotNull(captureStatus, "status");
        Intrinsics.checkParameterIsNotNull(function2, "acceptNewCapturedType");
        if (simpleType.getArguments().size() != simpleType.getConstructor().getParameters().size()) {
            return null;
        }
        List arguments = simpleType.getArguments();
        Iterable<TypeProjection> iterable = arguments;
        boolean z2 = true;
        if (!(iterable instanceof Collection) || !((Collection) iterable).isEmpty()) {
            Iterator it = iterable.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (((TypeProjection) it.next()).getProjectionKind() == Variance.INVARIANT) {
                        z = true;
                        continue;
                    } else {
                        z = false;
                        continue;
                    }
                    if (!z) {
                        z2 = false;
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        if (z2) {
            return null;
        }
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (TypeProjection typeProjection : iterable) {
            if (typeProjection.getProjectionKind() != Variance.INVARIANT) {
                typeProjection = TypeUtilsKt.asTypeProjection(new NewCapturedType(captureStatus, (typeProjection.isStarProjection() || typeProjection.getProjectionKind() != Variance.IN_VARIANCE) ? null : typeProjection.getType().unwrap(), typeProjection));
            }
            arrayList.add(typeProjection);
        }
        List list = (List) arrayList;
        TypeSubstitutor buildSubstitutor = TypeConstructorSubstitution.Companion.create(simpleType.getConstructor(), list).buildSubstitutor();
        int size = arguments.size();
        for (int i = 0; i < size; i++) {
            TypeProjection typeProjection2 = (TypeProjection) arguments.get(i);
            TypeProjection typeProjection3 = (TypeProjection) list.get(i);
            if (typeProjection2.getProjectionKind() != Variance.INVARIANT) {
                Object obj = simpleType.getConstructor().getParameters().get(i);
                Intrinsics.checkExpressionValueIsNotNull(obj, "type.constructor.parameters[index]");
                List upperBounds = ((TypeParameterDescriptor) obj).getUpperBounds();
                Intrinsics.checkExpressionValueIsNotNull(upperBounds, "type.constructor.parameters[index].upperBounds");
                Iterable<KotlinType> iterable2 = upperBounds;
                Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable2, 10));
                for (KotlinType safeSubstitute : iterable2) {
                    arrayList2.add(NewKotlinTypeChecker.INSTANCE.transformToNewType(buildSubstitutor.safeSubstitute(safeSubstitute, Variance.INVARIANT).unwrap()));
                }
                List list2 = (List) arrayList2;
                if (!typeProjection2.isStarProjection() && typeProjection2.getProjectionKind() == Variance.OUT_VARIANCE) {
                    list2 = CollectionsKt.plus((Collection<? extends T>) list2, NewKotlinTypeChecker.INSTANCE.transformToNewType(typeProjection2.getType().unwrap()));
                }
                KotlinType type = typeProjection3.getType();
                if (type != null) {
                    NewCapturedType newCapturedType = (NewCapturedType) type;
                    newCapturedType.getConstructor().initializeSupertypes(list2);
                    function2.invoke(Integer.valueOf(i), newCapturedType);
                } else {
                    throw new TypeCastException("null cannot be cast to non-null type org.jetbrains.kotlin.types.checker.NewCapturedType");
                }
            }
        }
        return KotlinTypeFactory.simpleType(simpleType.getAnnotations(), simpleType.getConstructor(), list, simpleType.isMarkedNullable());
    }
}
