package kotlin.reflect.jvm.internal.impl.descriptors;

import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: TypeAliasDescriptor.kt */
public interface TypeAliasDescriptor extends ClassifierDescriptorWithTypeParameters {
    @Nullable
    ClassDescriptor getClassDescriptor();

    @NotNull
    SimpleType getExpandedType();

    @NotNull
    SimpleType getUnderlyingType();
}
