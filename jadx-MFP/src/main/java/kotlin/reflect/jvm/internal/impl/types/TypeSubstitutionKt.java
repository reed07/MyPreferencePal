package kotlin.reflect.jvm.internal.impl.types;

import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import org.jetbrains.annotations.NotNull;

/* compiled from: TypeSubstitution.kt */
public final class TypeSubstitutionKt {
    @NotNull
    @JvmOverloads
    public static /* synthetic */ KotlinType replace$default(KotlinType kotlinType, List list, Annotations annotations, int i, Object obj) {
        if ((i & 1) != 0) {
            list = kotlinType.getArguments();
        }
        if ((i & 2) != 0) {
            annotations = kotlinType.getAnnotations();
        }
        return replace(kotlinType, list, annotations);
    }

    @NotNull
    @JvmOverloads
    public static final KotlinType replace(@NotNull KotlinType kotlinType, @NotNull List<? extends TypeProjection> list, @NotNull Annotations annotations) {
        KotlinType kotlinType2;
        Intrinsics.checkParameterIsNotNull(kotlinType, "receiver$0");
        Intrinsics.checkParameterIsNotNull(list, "newArguments");
        Intrinsics.checkParameterIsNotNull(annotations, "newAnnotations");
        if ((list.isEmpty() || list == kotlinType.getArguments()) && annotations == kotlinType.getAnnotations()) {
            return kotlinType;
        }
        UnwrappedType unwrap = kotlinType.unwrap();
        if (unwrap instanceof FlexibleType) {
            FlexibleType flexibleType = (FlexibleType) unwrap;
            kotlinType2 = KotlinTypeFactory.flexibleType(replace(flexibleType.getLowerBound(), list, annotations), replace(flexibleType.getUpperBound(), list, annotations));
        } else if (unwrap instanceof SimpleType) {
            kotlinType2 = replace((SimpleType) unwrap, list, annotations);
        } else {
            throw new NoWhenBranchMatchedException();
        }
        return kotlinType2;
    }

    @NotNull
    @JvmOverloads
    public static /* synthetic */ SimpleType replace$default(SimpleType simpleType, List list, Annotations annotations, int i, Object obj) {
        if ((i & 1) != 0) {
            list = simpleType.getArguments();
        }
        if ((i & 2) != 0) {
            annotations = simpleType.getAnnotations();
        }
        return replace(simpleType, list, annotations);
    }

    @NotNull
    @JvmOverloads
    public static final SimpleType replace(@NotNull SimpleType simpleType, @NotNull List<? extends TypeProjection> list, @NotNull Annotations annotations) {
        Intrinsics.checkParameterIsNotNull(simpleType, "receiver$0");
        Intrinsics.checkParameterIsNotNull(list, "newArguments");
        Intrinsics.checkParameterIsNotNull(annotations, "newAnnotations");
        if (list.isEmpty() && annotations == simpleType.getAnnotations()) {
            return simpleType;
        }
        if (list.isEmpty()) {
            return simpleType.replaceAnnotations(annotations);
        }
        return KotlinTypeFactory.simpleType(annotations, simpleType.getConstructor(), list, simpleType.isMarkedNullable());
    }

    @NotNull
    public static final SimpleType asSimpleType(@NotNull KotlinType kotlinType) {
        Intrinsics.checkParameterIsNotNull(kotlinType, "receiver$0");
        UnwrappedType unwrap = kotlinType.unwrap();
        if (!(unwrap instanceof SimpleType)) {
            unwrap = null;
        }
        SimpleType simpleType = (SimpleType) unwrap;
        if (simpleType != null) {
            return simpleType;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("This is should be simple type: ");
        sb.append(kotlinType);
        throw new IllegalStateException(sb.toString().toString());
    }
}
