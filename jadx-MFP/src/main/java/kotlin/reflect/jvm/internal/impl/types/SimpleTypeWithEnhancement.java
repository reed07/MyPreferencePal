package kotlin.reflect.jvm.internal.impl.types;

import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import org.jetbrains.annotations.NotNull;

/* compiled from: TypeWithEnhancement.kt */
public final class SimpleTypeWithEnhancement extends DelegatingSimpleType implements TypeWithEnhancement {
    @NotNull
    private final SimpleType delegate;
    @NotNull
    private final KotlinType enhancement;

    /* access modifiers changed from: protected */
    @NotNull
    public SimpleType getDelegate() {
        return this.delegate;
    }

    @NotNull
    public KotlinType getEnhancement() {
        return this.enhancement;
    }

    public SimpleTypeWithEnhancement(@NotNull SimpleType simpleType, @NotNull KotlinType kotlinType) {
        Intrinsics.checkParameterIsNotNull(simpleType, "delegate");
        Intrinsics.checkParameterIsNotNull(kotlinType, "enhancement");
        this.delegate = simpleType;
        this.enhancement = kotlinType;
    }

    @NotNull
    public UnwrappedType getOrigin() {
        return getDelegate();
    }

    @NotNull
    public SimpleType replaceAnnotations(@NotNull Annotations annotations) {
        Intrinsics.checkParameterIsNotNull(annotations, "newAnnotations");
        UnwrappedType wrapEnhancement = TypeWithEnhancementKt.wrapEnhancement(getOrigin().replaceAnnotations(annotations), getEnhancement());
        if (wrapEnhancement != null) {
            return (SimpleType) wrapEnhancement;
        }
        throw new TypeCastException("null cannot be cast to non-null type org.jetbrains.kotlin.types.SimpleType");
    }

    @NotNull
    public SimpleType makeNullableAsSpecified(boolean z) {
        UnwrappedType wrapEnhancement = TypeWithEnhancementKt.wrapEnhancement(getOrigin().makeNullableAsSpecified(z), getEnhancement().unwrap().makeNullableAsSpecified(z));
        if (wrapEnhancement != null) {
            return (SimpleType) wrapEnhancement;
        }
        throw new TypeCastException("null cannot be cast to non-null type org.jetbrains.kotlin.types.SimpleType");
    }
}
