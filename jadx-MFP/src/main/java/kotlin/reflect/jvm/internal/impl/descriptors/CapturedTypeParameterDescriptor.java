package kotlin.reflect.jvm.internal.impl.descriptors;

import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import org.jetbrains.annotations.NotNull;

/* compiled from: typeParameterUtils.kt */
final class CapturedTypeParameterDescriptor implements TypeParameterDescriptor {
    private final DeclarationDescriptor declarationDescriptor;
    private final int declaredTypeParametersCount;
    private final TypeParameterDescriptor originalDescriptor;

    public <R, D> R accept(DeclarationDescriptorVisitor<R, D> declarationDescriptorVisitor, D d) {
        return this.originalDescriptor.accept(declarationDescriptorVisitor, d);
    }

    @NotNull
    public Annotations getAnnotations() {
        return this.originalDescriptor.getAnnotations();
    }

    @NotNull
    public SimpleType getDefaultType() {
        return this.originalDescriptor.getDefaultType();
    }

    @NotNull
    public Name getName() {
        return this.originalDescriptor.getName();
    }

    @NotNull
    public SourceElement getSource() {
        return this.originalDescriptor.getSource();
    }

    @NotNull
    public TypeConstructor getTypeConstructor() {
        return this.originalDescriptor.getTypeConstructor();
    }

    @NotNull
    public List<KotlinType> getUpperBounds() {
        return this.originalDescriptor.getUpperBounds();
    }

    @NotNull
    public Variance getVariance() {
        return this.originalDescriptor.getVariance();
    }

    public boolean isCapturedFromOuterDeclaration() {
        return true;
    }

    public boolean isReified() {
        return this.originalDescriptor.isReified();
    }

    public CapturedTypeParameterDescriptor(@NotNull TypeParameterDescriptor typeParameterDescriptor, @NotNull DeclarationDescriptor declarationDescriptor2, int i) {
        Intrinsics.checkParameterIsNotNull(typeParameterDescriptor, "originalDescriptor");
        Intrinsics.checkParameterIsNotNull(declarationDescriptor2, "declarationDescriptor");
        this.originalDescriptor = typeParameterDescriptor;
        this.declarationDescriptor = declarationDescriptor2;
        this.declaredTypeParametersCount = i;
    }

    @NotNull
    public TypeParameterDescriptor getOriginal() {
        TypeParameterDescriptor original = this.originalDescriptor.getOriginal();
        Intrinsics.checkExpressionValueIsNotNull(original, "originalDescriptor.original");
        return original;
    }

    @NotNull
    public DeclarationDescriptor getContainingDeclaration() {
        return this.declarationDescriptor;
    }

    public int getIndex() {
        return this.declaredTypeParametersCount + this.originalDescriptor.getIndex();
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.originalDescriptor.toString());
        sb.append("[inner-copy]");
        return sb.toString();
    }
}
