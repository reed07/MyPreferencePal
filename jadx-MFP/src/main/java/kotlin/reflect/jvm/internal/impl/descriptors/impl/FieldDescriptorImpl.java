package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.FieldDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotatedImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import org.jetbrains.annotations.NotNull;

/* compiled from: FieldDescriptorImpl.kt */
public final class FieldDescriptorImpl extends AnnotatedImpl implements FieldDescriptor {
    @NotNull
    private final PropertyDescriptor correspondingProperty;

    public FieldDescriptorImpl(@NotNull Annotations annotations, @NotNull PropertyDescriptor propertyDescriptor) {
        Intrinsics.checkParameterIsNotNull(annotations, "annotations");
        Intrinsics.checkParameterIsNotNull(propertyDescriptor, "correspondingProperty");
        super(annotations);
        this.correspondingProperty = propertyDescriptor;
    }
}
