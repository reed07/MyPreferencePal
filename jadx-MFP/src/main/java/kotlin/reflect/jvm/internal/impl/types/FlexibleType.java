package kotlin.reflect.jvm.internal.impl.types;

import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer;
import kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import org.jetbrains.annotations.NotNull;

/* compiled from: KotlinType.kt */
public abstract class FlexibleType extends UnwrappedType implements SubtypingRepresentatives {
    @NotNull
    private final SimpleType lowerBound;
    @NotNull
    private final SimpleType upperBound;

    @NotNull
    public abstract SimpleType getDelegate();

    @NotNull
    public abstract String render(@NotNull DescriptorRenderer descriptorRenderer, @NotNull DescriptorRendererOptions descriptorRendererOptions);

    public boolean sameTypeConstructor(@NotNull KotlinType kotlinType) {
        Intrinsics.checkParameterIsNotNull(kotlinType, "type");
        return false;
    }

    @NotNull
    public final SimpleType getLowerBound() {
        return this.lowerBound;
    }

    @NotNull
    public final SimpleType getUpperBound() {
        return this.upperBound;
    }

    public FlexibleType(@NotNull SimpleType simpleType, @NotNull SimpleType simpleType2) {
        Intrinsics.checkParameterIsNotNull(simpleType, "lowerBound");
        Intrinsics.checkParameterIsNotNull(simpleType2, "upperBound");
        super(null);
        this.lowerBound = simpleType;
        this.upperBound = simpleType2;
    }

    @NotNull
    public KotlinType getSubTypeRepresentative() {
        return this.lowerBound;
    }

    @NotNull
    public KotlinType getSuperTypeRepresentative() {
        return this.upperBound;
    }

    @NotNull
    public Annotations getAnnotations() {
        return getDelegate().getAnnotations();
    }

    @NotNull
    public TypeConstructor getConstructor() {
        return getDelegate().getConstructor();
    }

    @NotNull
    public List<TypeProjection> getArguments() {
        return getDelegate().getArguments();
    }

    public boolean isMarkedNullable() {
        return getDelegate().isMarkedNullable();
    }

    @NotNull
    public MemberScope getMemberScope() {
        return getDelegate().getMemberScope();
    }

    @NotNull
    public String toString() {
        return DescriptorRenderer.DEBUG_TEXT.renderType(this);
    }
}
