package kotlin.reflect.jvm.internal.impl.types;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: SpecialTypes.kt */
public final class SpecialTypesKt {
    @Nullable
    public static final AbbreviatedType getAbbreviatedType(@NotNull KotlinType kotlinType) {
        Intrinsics.checkParameterIsNotNull(kotlinType, "receiver$0");
        UnwrappedType unwrap = kotlinType.unwrap();
        if (!(unwrap instanceof AbbreviatedType)) {
            unwrap = null;
        }
        return (AbbreviatedType) unwrap;
    }

    @Nullable
    public static final SimpleType getAbbreviation(@NotNull KotlinType kotlinType) {
        Intrinsics.checkParameterIsNotNull(kotlinType, "receiver$0");
        AbbreviatedType abbreviatedType = getAbbreviatedType(kotlinType);
        if (abbreviatedType != null) {
            return abbreviatedType.getAbbreviation();
        }
        return null;
    }

    @NotNull
    public static final SimpleType withAbbreviation(@NotNull SimpleType simpleType, @NotNull SimpleType simpleType2) {
        Intrinsics.checkParameterIsNotNull(simpleType, "receiver$0");
        Intrinsics.checkParameterIsNotNull(simpleType2, "abbreviatedType");
        if (KotlinTypeKt.isError(simpleType)) {
            return simpleType;
        }
        return new AbbreviatedType(simpleType, simpleType2);
    }

    public static final boolean isDefinitelyNotNullType(@NotNull KotlinType kotlinType) {
        Intrinsics.checkParameterIsNotNull(kotlinType, "receiver$0");
        return kotlinType.unwrap() instanceof DefinitelyNotNullType;
    }

    @NotNull
    public static final SimpleType makeSimpleTypeDefinitelyNotNullOrNotNull(@NotNull SimpleType simpleType) {
        Intrinsics.checkParameterIsNotNull(simpleType, "receiver$0");
        DefinitelyNotNullType makeDefinitelyNotNull$descriptors = DefinitelyNotNullType.Companion.makeDefinitelyNotNull$descriptors(simpleType);
        return makeDefinitelyNotNull$descriptors != null ? makeDefinitelyNotNull$descriptors : simpleType.makeNullableAsSpecified(false);
    }

    @NotNull
    public static final UnwrappedType makeDefinitelyNotNullOrNotNull(@NotNull UnwrappedType unwrappedType) {
        Intrinsics.checkParameterIsNotNull(unwrappedType, "receiver$0");
        DefinitelyNotNullType makeDefinitelyNotNull$descriptors = DefinitelyNotNullType.Companion.makeDefinitelyNotNull$descriptors(unwrappedType);
        return makeDefinitelyNotNull$descriptors != null ? makeDefinitelyNotNull$descriptors : unwrappedType.makeNullableAsSpecified(false);
    }
}
