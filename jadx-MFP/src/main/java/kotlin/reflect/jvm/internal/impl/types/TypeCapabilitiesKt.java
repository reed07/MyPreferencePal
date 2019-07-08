package kotlin.reflect.jvm.internal.impl.types;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: TypeCapabilities.kt */
public final class TypeCapabilitiesKt {
    public static final boolean isCustomTypeVariable(@NotNull KotlinType kotlinType) {
        Intrinsics.checkParameterIsNotNull(kotlinType, "receiver$0");
        UnwrappedType unwrap = kotlinType.unwrap();
        if (!(unwrap instanceof CustomTypeVariable)) {
            unwrap = null;
        }
        CustomTypeVariable customTypeVariable = (CustomTypeVariable) unwrap;
        if (customTypeVariable != null) {
            return customTypeVariable.isTypeVariable();
        }
        return false;
    }

    @Nullable
    public static final CustomTypeVariable getCustomTypeVariable(@NotNull KotlinType kotlinType) {
        Intrinsics.checkParameterIsNotNull(kotlinType, "receiver$0");
        UnwrappedType unwrap = kotlinType.unwrap();
        if (!(unwrap instanceof CustomTypeVariable)) {
            unwrap = null;
        }
        CustomTypeVariable customTypeVariable = (CustomTypeVariable) unwrap;
        if (customTypeVariable == null || !customTypeVariable.isTypeVariable()) {
            return null;
        }
        return customTypeVariable;
    }

    @NotNull
    public static final KotlinType getSubtypeRepresentative(@NotNull KotlinType kotlinType) {
        Intrinsics.checkParameterIsNotNull(kotlinType, "receiver$0");
        UnwrappedType unwrap = kotlinType.unwrap();
        if (!(unwrap instanceof SubtypingRepresentatives)) {
            unwrap = null;
        }
        SubtypingRepresentatives subtypingRepresentatives = (SubtypingRepresentatives) unwrap;
        if (subtypingRepresentatives == null) {
            return kotlinType;
        }
        KotlinType subTypeRepresentative = subtypingRepresentatives.getSubTypeRepresentative();
        return subTypeRepresentative != null ? subTypeRepresentative : kotlinType;
    }

    @NotNull
    public static final KotlinType getSupertypeRepresentative(@NotNull KotlinType kotlinType) {
        Intrinsics.checkParameterIsNotNull(kotlinType, "receiver$0");
        UnwrappedType unwrap = kotlinType.unwrap();
        if (!(unwrap instanceof SubtypingRepresentatives)) {
            unwrap = null;
        }
        SubtypingRepresentatives subtypingRepresentatives = (SubtypingRepresentatives) unwrap;
        if (subtypingRepresentatives == null) {
            return kotlinType;
        }
        KotlinType superTypeRepresentative = subtypingRepresentatives.getSuperTypeRepresentative();
        return superTypeRepresentative != null ? superTypeRepresentative : kotlinType;
    }

    public static final boolean sameTypeConstructors(@NotNull KotlinType kotlinType, @NotNull KotlinType kotlinType2) {
        Intrinsics.checkParameterIsNotNull(kotlinType, "first");
        Intrinsics.checkParameterIsNotNull(kotlinType2, "second");
        UnwrappedType unwrap = kotlinType.unwrap();
        if (!(unwrap instanceof SubtypingRepresentatives)) {
            unwrap = null;
        }
        SubtypingRepresentatives subtypingRepresentatives = (SubtypingRepresentatives) unwrap;
        if (!(subtypingRepresentatives != null ? subtypingRepresentatives.sameTypeConstructor(kotlinType2) : false)) {
            UnwrappedType unwrap2 = kotlinType2.unwrap();
            if (!(unwrap2 instanceof SubtypingRepresentatives)) {
                unwrap2 = null;
            }
            SubtypingRepresentatives subtypingRepresentatives2 = (SubtypingRepresentatives) unwrap2;
            if (!(subtypingRepresentatives2 != null ? subtypingRepresentatives2.sameTypeConstructor(kotlinType) : false)) {
                return false;
            }
        }
        return true;
    }
}
