package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import org.jetbrains.annotations.NotNull;

public abstract class ClassDescriptorBase extends AbstractClassDescriptor {
    private final DeclarationDescriptor containingDeclaration;
    private final boolean isExternal;
    private final SourceElement source;

    protected ClassDescriptorBase(@NotNull StorageManager storageManager, @NotNull DeclarationDescriptor declarationDescriptor, @NotNull Name name, @NotNull SourceElement sourceElement, boolean z) {
        super(storageManager, name);
        this.containingDeclaration = declarationDescriptor;
        this.source = sourceElement;
        this.isExternal = z;
    }

    public boolean isExternal() {
        return this.isExternal;
    }

    @NotNull
    public DeclarationDescriptor getContainingDeclaration() {
        return this.containingDeclaration;
    }

    @NotNull
    public SourceElement getSource() {
        return this.source;
    }
}
