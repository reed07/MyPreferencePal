package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import java.util.Map;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor.DefaultImpls;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: typeEnhancement.kt */
final class EnhancedTypeAnnotationDescriptor implements AnnotationDescriptor {
    public static final EnhancedTypeAnnotationDescriptor INSTANCE = new EnhancedTypeAnnotationDescriptor();

    @NotNull
    public String toString() {
        return "[EnhancedType]";
    }

    private EnhancedTypeAnnotationDescriptor() {
    }

    @Nullable
    public FqName getFqName() {
        return DefaultImpls.getFqName(this);
    }

    private final Void throwError() {
        throw new IllegalStateException("No methods should be called on this descriptor. Only its presence matters".toString());
    }

    @NotNull
    public KotlinType getType() {
        throwError();
        throw null;
    }

    @NotNull
    public Map<Name, ConstantValue<?>> getAllValueArguments() {
        throwError();
        throw null;
    }

    @NotNull
    public SourceElement getSource() {
        throwError();
        throw null;
    }
}
