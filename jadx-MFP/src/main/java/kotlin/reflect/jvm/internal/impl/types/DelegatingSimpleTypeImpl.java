package kotlin.reflect.jvm.internal.impl.types;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import org.jetbrains.annotations.NotNull;

/* compiled from: KotlinTypeFactory.kt */
public abstract class DelegatingSimpleTypeImpl extends DelegatingSimpleType {
    @NotNull
    private final SimpleType delegate;

    public DelegatingSimpleTypeImpl(@NotNull SimpleType simpleType) {
        Intrinsics.checkParameterIsNotNull(simpleType, "delegate");
        this.delegate = simpleType;
    }

    /* access modifiers changed from: protected */
    @NotNull
    public SimpleType getDelegate() {
        return this.delegate;
    }

    @NotNull
    public DelegatingSimpleTypeImpl replaceAnnotations(@NotNull Annotations annotations) {
        Intrinsics.checkParameterIsNotNull(annotations, "newAnnotations");
        return annotations != getAnnotations() ? new AnnotatedSimpleType(this, annotations) : this;
    }

    @NotNull
    public SimpleType makeNullableAsSpecified(boolean z) {
        if (z == isMarkedNullable()) {
            return this;
        }
        return getDelegate().makeNullableAsSpecified(z).replaceAnnotations(getAnnotations());
    }
}
