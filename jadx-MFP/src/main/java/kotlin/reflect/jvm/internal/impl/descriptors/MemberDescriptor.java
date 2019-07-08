package kotlin.reflect.jvm.internal.impl.descriptors;

import org.jetbrains.annotations.NotNull;

public interface MemberDescriptor extends DeclarationDescriptorNonRoot, DeclarationDescriptorWithVisibility {
    @NotNull
    Modality getModality();

    @NotNull
    Visibility getVisibility();

    boolean isActual();

    boolean isExpect();

    boolean isExternal();
}
