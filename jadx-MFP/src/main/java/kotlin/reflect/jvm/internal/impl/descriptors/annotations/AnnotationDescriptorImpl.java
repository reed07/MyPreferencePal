package kotlin.reflect.jvm.internal.impl.descriptors.annotations;

import java.util.Map;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor.DefaultImpls;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class AnnotationDescriptorImpl implements AnnotationDescriptor {
    private final KotlinType annotationType;
    private final SourceElement source;
    private final Map<Name, ConstantValue<?>> valueArguments;

    public AnnotationDescriptorImpl(@NotNull KotlinType kotlinType, @NotNull Map<Name, ConstantValue<?>> map, @NotNull SourceElement sourceElement) {
        this.annotationType = kotlinType;
        this.valueArguments = map;
        this.source = sourceElement;
    }

    @NotNull
    public KotlinType getType() {
        return this.annotationType;
    }

    @Nullable
    public FqName getFqName() {
        return DefaultImpls.getFqName(this);
    }

    @NotNull
    public Map<Name, ConstantValue<?>> getAllValueArguments() {
        return this.valueArguments;
    }

    @NotNull
    public SourceElement getSource() {
        return this.source;
    }

    public String toString() {
        return DescriptorRenderer.FQ_NAMES_IN_TYPES.renderAnnotation(this, null);
    }
}
