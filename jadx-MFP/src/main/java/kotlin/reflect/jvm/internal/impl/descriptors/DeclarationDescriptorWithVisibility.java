package kotlin.reflect.jvm.internal.impl.descriptors;

import org.jetbrains.annotations.NotNull;

public interface DeclarationDescriptorWithVisibility extends DeclarationDescriptor {
    @NotNull
    Visibility getVisibility();
}
