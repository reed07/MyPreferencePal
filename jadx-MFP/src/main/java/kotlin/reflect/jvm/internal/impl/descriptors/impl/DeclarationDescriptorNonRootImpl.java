package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorNonRoot;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorWithSource;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.name.Name;
import org.jetbrains.annotations.NotNull;

public abstract class DeclarationDescriptorNonRootImpl extends DeclarationDescriptorImpl implements DeclarationDescriptorNonRoot {
    @NotNull
    private final DeclarationDescriptor containingDeclaration;
    @NotNull
    private final SourceElement source;

    protected DeclarationDescriptorNonRootImpl(@NotNull DeclarationDescriptor declarationDescriptor, @NotNull Annotations annotations, @NotNull Name name, @NotNull SourceElement sourceElement) {
        super(annotations, name);
        this.containingDeclaration = declarationDescriptor;
        this.source = sourceElement;
    }

    @NotNull
    public DeclarationDescriptorWithSource getOriginal() {
        return (DeclarationDescriptorWithSource) super.getOriginal();
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
